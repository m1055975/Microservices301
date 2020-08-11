package com.mindtree.authorization.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.authorization.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

}
