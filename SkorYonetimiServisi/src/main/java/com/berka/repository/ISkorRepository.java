package com.berka.repository;

import com.berka.repository.entity.Skor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISkorRepository extends JpaRepository<Skor,Long> {

    Optional<Skor> findByKullaniciid(Long kullanciid);

    //@Query(value = "Select s from Skor  as s order by s.skor desc",nativeQuery = true)
    List<Skor> findByOrderBySkorDesc();
}
