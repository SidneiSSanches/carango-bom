
package com.carango.bom.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carango.bom.repository.user.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {    
	UserEntity findByLogin(String login);   
}
