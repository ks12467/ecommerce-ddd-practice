package com.hyeonlo.ecommerce.domain.auth.repository;

import com.hyeonlo.ecommerce.domain.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
