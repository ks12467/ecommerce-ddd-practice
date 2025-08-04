package com.hyeonlo.ecommerce.domain.user.repository;

import com.hyeonlo.ecommerce.domain.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
