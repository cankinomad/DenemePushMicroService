package com.berka.service;

import com.berka.dto.request.TahminRequestDto;
import com.berka.exception.ErrorType;
import com.berka.exception.TahminManagerException;
import com.berka.manager.IKullaniciManager;
import com.berka.manager.ISehirManager;
import com.berka.mapper.ITahminMapper;
import com.berka.rabbitmq.producer.SkorGuncelleProducer;
import com.berka.repository.ITahminRepository;
import com.berka.repository.entity.Tahmin;
import com.berka.repository.enums.EDogruluk;
import com.berka.utility.JwtTokenManager;
import com.berka.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TahminService extends ServiceManager<Tahmin, Long> {

    private final ITahminRepository repository;
    private final JwtTokenManager jwtTokenManager;
    private final ISehirManager sehirManager;
    private final IKullaniciManager kullaniciManager;

    private final SkorGuncelleProducer skorGuncelleProducer;

    public TahminService(ITahminRepository repository, JwtTokenManager jwtTokenManager, ISehirManager sehirManager, IKullaniciManager kullaniciManager, SkorGuncelleProducer skorGuncelleProducer) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
        this.sehirManager = sehirManager;
        this.kullaniciManager = kullaniciManager;
        this.skorGuncelleProducer = skorGuncelleProducer;
    }

    public String tahminYap(TahminRequestDto dto) {
        Optional<Long> optionalUserid = jwtTokenManager.getIdFromToken(dto.getToken());
        if (optionalUserid.isEmpty()) {
            throw new TahminManagerException(ErrorType.USER_NOT_FOUND);
        }

        Boolean body1 = kullaniciManager.tahminHakkiControl(optionalUserid.get()).getBody();
        if (body1) {
            Tahmin tahmin = Tahmin.builder()
                    .kullaniciid(optionalUserid.get())
                    .tahmin(dto.getTahmin())
                    .sehirid(dto.getSehirid())
                    .build();

            Boolean body = sehirManager.tahminControl(ITahminMapper.INSTANCE.toTahminControlRequestDto(tahmin)).getBody();



            if (body) {
                tahmin.setDogruluk(EDogruluk.DOGRU);
            } else {
                tahmin.setDogruluk(EDogruluk.YANLIS);
            }

            save(tahmin);

            skorGuncelleProducer.skorGuncelle(ITahminMapper.INSTANCE.toSkorGuncelleModel(tahmin));

            return "Tahmininiz kaydedildi";
        }
        else {
            return "Uzgunum bu kullanicinin tahmin hakki dolmustur. Dilerseniz deneme hakkinizi " +
                    "KullaniciServisi uzerindeki HakkimiYenile metoduna token vererek doldurabilirsiniz";
        }


    }
}
