
package com.carango.bom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.carango.bom.repository.user.entity.UserEntity;



@Component
public class UserDataLoaderConfig implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private CrudRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (userRepository.count() == 0) {
           
        	userRepository.save(new UserEntity(1L, "AlanPaiva", "$2a$10$jkXxJkzyX0.ou.ySo4wNq.OMp6aqMjp8x9Tq.gOKYVouFi.SB2fv."));
            userRepository.save(new UserEntity(2L, "SamuelS", "$2a$10$IDzQc7M.RbK4stTWcY1fputiYA5Ks4lH9ocunsTx7DnXZMU7darnu"));
            userRepository.save(new UserEntity(3L, "SidneiS", "$2a$10$DCt5JzNrwQl47ILyCcnG7uCHN4Hrcn4wA3SPT7fFmHVkEM.Ye/5XK"));
            userRepository.save(new UserEntity(4L, "ThiagoH", "$2a$10$sweCAnjAXlz/CmbHSujrOO8.Kw9SBowEr2xhd.knxu7aBiP8j5xNq"));
            userRepository.save(new UserEntity(5L, "TiagoG", "$2a$10$gdxXqRnkUSyosZvXllw.Cu8Vl7ybj4NgQ4WKErRwqK7mUnFAvFejm"));
            userRepository.save(new UserEntity(6L, "WiliamN", "$2a$10$O.LkaT2c2lGmTy9H2zpdEuwecqaXWoVmoAmnDJkIbH/L5rSeBhcl2"));

        }
    }
}
