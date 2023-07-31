package com.berka.repository;

import com.berka.KullaniciServiceApplication;
import com.berka.repository.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IKullaniciRepository extends JpaRepository<Kullanici,Long> {

    boolean existsByKullaniciadi(String kullaniciAdi);

    Optional<Kullanici> findByKullaniciadiAndSifre(String kullaniciAdi, String sifre);

}
