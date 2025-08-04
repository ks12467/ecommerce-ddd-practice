package com.hyeonlo.ecommerce.domain.user.entity;

import com.hyeonlo.ecommerce.domain.user.enums.UserRole;
import com.hyeonlo.ecommerce.domain.user.enums.UserStatus;
import com.hyeonlo.ecommerce.global.util.TimeStamped;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Users extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String loginId;

    private String password;

    private String userName;

    private String email;

    private UserRole userRole = UserRole.USER;

    private UserStatus userStatus = UserStatus.ACTIVE;

    private Users(String loginId, String password, String userName, String email, UserRole userRole, UserStatus userStatus) {
        this.loginId = loginId;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    public static Users from(String loginId, String password, String userName, String email, UserRole userRole, UserStatus userStatus) {
        return new Users(
                loginId,
                password,
                userName,
                email,
                userRole,
                userStatus
        );
    }

    public void updateLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateUserName(String userName) {
        this.userName = userName;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void chageStatus() {
        this.userStatus = UserStatus.WITHDRAW;
    }
}
