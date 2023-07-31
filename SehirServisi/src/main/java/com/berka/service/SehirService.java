package com.berka.service;

import com.berka.dto.response.BlurluCografiKonumVeSehirResponseDto;
import com.berka.dto.request.EkleReqeustDto;
import com.berka.dto.request.TahminControlRequestDto;
import com.berka.exception.ErrorType;
import com.berka.exception.SehirManagerException;
import com.berka.repository.ISehirRepository;
import com.berka.repository.entity.Sehir;
import com.berka.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SehirService extends ServiceManager<Sehir,Long> {

    private final ISehirRepository repository;

    public SehirService(ISehirRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Sehir ekle(EkleReqeustDto dto) {

        boolean existsedBySehirtamisim = repository.existsBySehirtamisim(dto.getSehirtamisim().toUpperCase());
        if (existsedBySehirtamisim) {
            throw new SehirManagerException(ErrorType.SEHIR_ALREADY_EXIST);
        }

        Sehir sehir = Sehir.builder().sehirBolgesi(dto.getSehirBolgesi())
                .sehirplaka(dto.getSehirplaka())
                .sehirtamisim(dto.getSehirtamisim().toUpperCase())
                .sehirblurluisim(dto.getSehirtamisim().toUpperCase().substring(0, 2))
                .build();
        return save(sehir);
    }

    public BlurluCografiKonumVeSehirResponseDto sehirCografiKonumGetir(Long sehirid) {

        Optional<Sehir> optionalSehir = repository.findById(sehirid);
        if (optionalSehir.isEmpty()) {
            throw new SehirManagerException(ErrorType.SEHIR_NOT_FOUND);
        }
        return BlurluCografiKonumVeSehirResponseDto.builder()
                .blurlucografikonum(optionalSehir.get().getSehirBolgesi().substring(0,2))
                .blurlusehirismi(optionalSehir.get().getSehirblurluisim())
                .build();
    }

    public Boolean tahminControl(TahminControlRequestDto dto) {
        Optional<Sehir> optionalSehir = findById(dto.getSehirid());
        if (optionalSehir.isEmpty()) {
            throw new SehirManagerException(ErrorType.SEHIR_NOT_FOUND);
        }

        if (optionalSehir.get().getSehirtamisim().equalsIgnoreCase(dto.getTahmin())) {
            return true;
        }
        return false;
    }
}
