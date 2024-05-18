package com.project22.BeautyRoom_22Bot.repository;

import com.project22.BeautyRoom_22Bot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
