package com.project22.BeautyRoom_22Bot.service;

import com.project22.BeautyRoom_22Bot.model.*;
import com.project22.BeautyRoom_22Bot.repository.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserProcedureService {
    private final UserProcedureRepository userProcedureRepository;
    private final UserService userService;
    private final ProcedureService procedureService;

    public UserProcedure saveData(UserProcedure userProcedure){
        return userProcedureRepository.save(userProcedure);
    }

    public UserProcedure createUserProcedure(Long chatId, String name){
        User user = userService.findById(chatId).orElseThrow();
        Procedure procedure = procedureService.findByName(name).orElseThrow();
        if (checkIfExists(user, procedure)){
            throw new IllegalArgumentException("The data already exists");
        }

        UserProcedure userProcedure = new UserProcedure();
        userProcedure.setProcedure(procedure);
        userProcedure.setUser(user);
        return userProcedure;
    }

    public boolean checkIfExists(User user, Procedure procedure){
        return userProcedureRepository.findByUserAndProcedure(user, procedure).isPresent();
    }

    public List<UserProcedure> findAll(User user){
        return userProcedureRepository.findAllByUser(user).orElseThrow();
    }
}
