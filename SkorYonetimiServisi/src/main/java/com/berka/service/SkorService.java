package com.berka.service;

import com.berka.mapper.ISkorMapper;
import com.berka.rabbitmq.model.SkorGuncelleModel;
import com.berka.rabbitmq.producer.KullaniciSkorGuncelleProducer;
import com.berka.repository.ISkorRepository;
import com.berka.repository.entity.Skor;
import com.berka.repository.enums.EDogruluk;
import com.berka.utility.ServiceManager;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkorService extends ServiceManager<Skor,Long> {

    private final ISkorRepository repository;
    private final KullaniciSkorGuncelleProducer kullaniciSkorGuncelleProducer;

    public SkorService(ISkorRepository repository, KullaniciSkorGuncelleProducer kullaniciSkorGuncelleProducer) {
        super(repository);
        this.repository = repository;
        this.kullaniciSkorGuncelleProducer = kullaniciSkorGuncelleProducer;
    }

    public String skorGuncelle(SkorGuncelleModel model) {
        Optional<Skor> optionalSkor = repository.findByKullaniciid(model.getKullaniciid());

        String skor = skorBossaKontrol(model, optionalSkor);
        if (skor != null) return skor;

        return skorBosDegilse(model, optionalSkor);

    }

    //eger Daha once boyle bir kullanici varsa update metodu ile ilerliyor
    private String skorBosDegilse(SkorGuncelleModel model, Optional<Skor> optionalSkor) {
        if (model.getDogruluk().equals(EDogruluk.DOGRU)) {
            optionalSkor.get().setSkor(optionalSkor.get().getSkor() + 10L);
            update(optionalSkor.get());
            kullaniciSkorGuncelleProducer.kullaniciSkorGuncelle(ISkorMapper.INSTANCE.toKullaniciSkorGuncelleModel(optionalSkor.get()));
            return optionalSkor.get().getKullaniciid() + " kullaniciid'li kullanicinin skoru: " + optionalSkor.get().getSkor();
        } else {
            if (optionalSkor.get().getSkor() <= 5L) {
                optionalSkor.get().setSkor(0L);
                update(optionalSkor.get());
                kullaniciSkorGuncelleProducer.kullaniciSkorGuncelle(ISkorMapper.INSTANCE.toKullaniciSkorGuncelleModel(optionalSkor.get()));
                return optionalSkor.get().getKullaniciid() + " kullaniciid'li kullanicinin skoru: " + optionalSkor.get().getSkor();
            }else{
                optionalSkor.get().setSkor(optionalSkor.get().getSkor()-5L);
                update(optionalSkor.get());
                kullaniciSkorGuncelleProducer.kullaniciSkorGuncelle(ISkorMapper.INSTANCE.toKullaniciSkorGuncelleModel(optionalSkor.get()));
                return optionalSkor.get().getKullaniciid() + " kullaniciid'li kullanicinin skoru: " + optionalSkor.get().getSkor();
            }
        }
    }

    //eger daha once boyle bir kullanici yoksa save metodu ile kaydediyor.
    private String skorBossaKontrol(SkorGuncelleModel model, Optional<Skor> optionalSkor) {
        if (optionalSkor.isEmpty()) {

            if (model.getDogruluk().equals(EDogruluk.DOGRU)) {
                Skor skor = Skor.builder().skor(10L).kullaniciid(model.getKullaniciid()).build();
                save(skor);
                kullaniciSkorGuncelleProducer.kullaniciSkorGuncelle(ISkorMapper.INSTANCE.toKullaniciSkorGuncelleModel(skor));
                return skor.getKullaniciid() + " kullaniciid'li kullanicinin skoru: " + skor.getSkor();
            }else {
                Skor skor = Skor.builder().skor(0L).kullaniciid(model.getKullaniciid()).build();
                save(skor);
                kullaniciSkorGuncelleProducer.kullaniciSkorGuncelle(ISkorMapper.INSTANCE.toKullaniciSkorGuncelleModel(skor));
                return skor.getKullaniciid() + " kullaniciid'li kullanicinin skoru: " + skor.getSkor();
            }
        }
        return null;
    }



    public List<Skor> sirala() {
       return repository.findByOrderBySkorDesc();
    }
}
