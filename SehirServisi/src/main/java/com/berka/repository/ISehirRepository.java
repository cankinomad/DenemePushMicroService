package com.berka.repository;

import com.berka.repository.entity.Sehir;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISehirRepository extends JpaRepository<Sehir,Long> {

    boolean existsBySehirtamisim(String sehirTamIsim);

}
