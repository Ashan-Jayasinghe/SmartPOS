package com.pos.system.smartpos.repository;

import com.pos.system.smartpos.dto.requests.UserUpdateRequestDTO;
import com.pos.system.smartpos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Integer> {

    boolean existsByUserEmail(String userEmail);
    User getUserByUserEmailEquals(String userEmail);

    User getUserByUserIdEquals(int userId);

}
