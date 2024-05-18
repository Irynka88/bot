package com.project22.BeautyRoom_22Bot.service;

import com.project22.BeautyRoom_22Bot.model.*;
import com.project22.BeautyRoom_22Bot.repository.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProcedureService {
    private final ProcedureRepository procedureRepository;
    public Optional<Procedure> findByName(String name){
        return Optional.of(procedureRepository.findByName(name));
    }


}
