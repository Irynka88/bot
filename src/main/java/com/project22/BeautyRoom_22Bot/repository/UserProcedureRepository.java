package com.project22.BeautyRoom_22Bot.repository;

import com.project22.BeautyRoom_22Bot.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserProcedureRepository extends JpaRepository<UserProcedure, Long> {
    Optional<UserProcedure> findByUserAndProcedure(User user, Procedure procedure);
    Optional<List<UserProcedure>> findAllByUser(User user);
}
