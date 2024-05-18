package com.project22.BeautyRoom_22Bot.model;

import com.project22.BeautyRoom_22Bot.repository.UserProcedureRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "procedures")
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Integer price;

    public void test(){
        Procedure procedure = new Procedure();

        User user = new User();
        UserProcedure userProcedure = new UserProcedure();
        userProcedure.setProcedure(procedure);
        userProcedure.setUser(user);
        UserProcedureRepository userProcedureRepository = null;
        userProcedureRepository.save(userProcedure);
    }


}

