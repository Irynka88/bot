package com.project22.BeautyRoom_22Bot.repository;

import com.project22.BeautyRoom_22Bot.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {
    Procedure findByName(String name);
}
