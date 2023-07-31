package com.berka.service;

import com.berka.dto.request.GirisRequestDto;
import com.berka.dto.request.KayitRequestDto;
import com.berka.dto.response.KayitResponseDto;
import com.berka.exception.ErrorType;
import com.berka.exception.KullaniciManagerException;
import com.berka.mapper.IKullaniciMapper;
import com.berka.rabbitmq.model.KullaniciSkorGuncelleModel;
import com.berka.repository.IKullaniciRepository;
import com.berka.repository.entity.Kullanici;
import com.berka.utility.JwtTokenManager;
import com.berka.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KullaniciServisi extends ServiceManager<Kullanici,Long> {

    private final IKullaniciRepository repository;
    private final JwtTokenManager jwtTokenManager;

    public KullaniciServisi(IKullaniciRepository repository, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public KayitResponseDto saveKullanici(KayitRequestDto dto) {
        boolean existsedByKullaniciadi = repository.existsByKullaniciadi(dto.getKullaniciadi());
        if (existsedByKullaniciadi) {
            throw new KullaniciManagerException(ErrorType.USERNAME_EXIST);
        }

        try {
            Kullanici kullanici = IKullaniciMapper.INSTANCE.toKullanici(dto);
            save(kullanici);
            return IKullaniciMapper.INSTANCE.toKayitResponseDto(kullanici);
        } catch (Exception e) {
            throw new KullaniciManagerException(ErrorType.USER_NOT_CREATED);
        }

    }

    public String giris(GirisRequestDto dto) {
        Optional<Kullanici> optionalKullanici = repository.findByKullaniciadiAndSifre(dto.getKullaniciadi(), dto.getSifre());
        if (optionalKullanici.isEmpty()) {
            throw new KullaniciManagerException(ErrorType.USER_NOT_FOUND);
        }


        Optional<String> optionalToken = jwtTokenManager.createToken(optionalKullanici.get().getId());

        if (optionalToken.isEmpty()) {
            throw new KullaniciManagerException(ErrorType.TOKEN_NOT_CREATED);
        }
        return optionalToken.get();
    }

    public Boolean skorGuncelle(KullaniciSkorGuncelleModel model) {

        Optional<Kullanici> optionalKullanici = findById(model.getKullaniciid());
        if (optionalKullanici.isEmpty()) {
            throw new KullaniciManagerException(ErrorType.USER_NOT_FOUND);
        }

        try {
            optionalKullanici.get().setSkor(model.getSkor());
            update(optionalKullanici.get());
            return true;
        } catch (Exception e) {
            throw new KullaniciManagerException(ErrorType.BAD_REQUEST);
        }
    }

    public Boolean tahminHakkiControl(Long userid) {
        Optional<Kullanici> optionalKullanici = findById(userid);
        if (optionalKullanici.isEmpty()) {
            throw new KullaniciManagerException(ErrorType.USER_NOT_FOUND);
        }

        if (optionalKullanici.get().getTahminhakki() > 0) {
            optionalKullanici.get().setTahminhakki(optionalKullanici.get().getTahminhakki()-1);
            update(optionalKullanici.get());
            return true;
        } else {
            return false;
        }

    }

    public String hakYenile(String token) {
        Optional<Long> optionaltokenId = jwtTokenManager.getIdFromToken(token);

        if (optionaltokenId.isEmpty()) {
            throw new KullaniciManagerException(ErrorType.INVALID_TOKEN);
        }

        Optional<Kullanici> optionalKullanici = repository.findById(optionaltokenId.get());
        if (optionalKullanici.isEmpty()) {
            throw new KullaniciManagerException(ErrorType.USER_NOT_FOUND);
        }

        if (optionalKullanici.get().getTahminhakki() >= 5) {
            return "Tahmin hakkiniz 5'in uzerinde, bu islem sadece tahmin hakki 5'in altinda ulan kullaniclar icin calismaktadir. " +
                    "Daha fazla hak isterseniz lutfen iletisime geciniz";
        } else {
            optionalKullanici.get().setTahminhakki(5);
            update(optionalKullanici.get());
            return "Tahmin hakkiniz basariyla guncellenmistir. Iyi oyunlar dileriz!";
        }


    }

    public KayitResponseDto findByToken(String token) {
        Optional<Long> optionalId = jwtTokenManager.getIdFromToken(token);
        if (optionalId.isEmpty()) {
            throw new KullaniciManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<Kullanici> optionalKullanici = findById(optionalId.get());
        if (optionalKullanici.isEmpty()) {
            throw new KullaniciManagerException(ErrorType.USER_NOT_FOUND);
        }

        return IKullaniciMapper.INSTANCE.toKayitResponseDto(optionalKullanici.get());

    }
}
