package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.UserSecurity;
import ru.skypro.homework.entities.UserEntity;
import ru.skypro.homework.mappers.UserMapper;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;
    @Mock
    private UserDetailsManagerImpl manager;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private UserMapper mapper;
    @Spy
    private BCryptPasswordEncoder encoder;
    private final String userName = "aaa@ug.ru";
    private final String password = "123456789";
    private final String renewedPassword = "renewedPassword";
    private final String fName = "Oleg";
    private final String lName = "Olegov";
    private final String phone = "+78008889922";
    private final Role role = Role.USER;


    @Test
    void loginTrue() {
        UserSecurity userSecurity = getUserSecurity();
        when(userService.userExists(userName)).thenReturn(true);
        when(manager.loadUserByUsername(userName)).thenReturn(userSecurity);
        Assertions.assertTrue(authService.login(userName, password));
    }

    @Test
    void loginIfUserNotExists() {
        when(userService.userExists(userName)).thenReturn(false);
        Assertions.assertFalse(authService.login(userName, password));
    }

    @Test
    void loginIfInvalidPassword() {
        UserSecurity userSecurity = getUserSecurity();
        when(userService.userExists(userName)).thenReturn(true);
        when(manager.loadUserByUsername(userName)).thenReturn(userSecurity);
        when(encoder.matches(password, userSecurity.getPassword())).thenReturn(false);
        Assertions.assertFalse(authService.login(userName, password));
    }


    @Test
    void registerIfAlreadyExists() {
        Register req = getRegisterReq();
        when(userService.userExists(userName)).thenReturn(true);
        Assertions.assertFalse(authService.register(req));
    }

    @Test
    void registerSuccessfully() {
        Register req = getRegisterReq();
        UserEntity entity = getEntity();
        when(userService.userExists(userName)).thenReturn(false);
        when(mapper.registerReqDtoToEntity(req)).thenReturn(entity);
        Assertions.assertTrue(authService.register(req));
        verify(userService).createUser(entity);
    }


    @Test
    void setPasswordSuccessfully() {
        NewPassword newPassword = new NewPassword();
        String newEncodePass = "123456";
        newPassword.setCurrentPassword(password);
        newPassword.setNewPassword(renewedPassword);
        UserSecurity user = getUserSecurity();
        when(manager.loadUserByUsername(userName)).thenReturn(user);
        when(encoder.encode(renewedPassword)).thenReturn(newEncodePass);
        Assertions.assertTrue(authService.setPassword(newPassword, userName));
        verify(userService).changePassword(newEncodePass, userName);
    }

    @Test
    void setPasswordNoMatchingCurrentPassword() {
        NewPassword newPassword = new NewPassword();
        newPassword.setCurrentPassword("pass");
        newPassword.setNewPassword(renewedPassword);
        UserSecurity user = getUserSecurity();
        when(manager.loadUserByUsername(userName)).thenReturn(user);
        Assertions.assertFalse(authService.setPassword(newPassword, userName));
    }

    private UserSecurity getUserSecurity() {
        return new UserSecurity(getEntity());
    }

    private Register getRegisterReq() {
        Register req = new Register();
        req.setPhone(phone);
        req.setUsername(userName);
        req.setPassword(password);
        req.setLastName(lName);
        req.setFirstName(fName);
        req.setRole(role);
        return req;
    }

    private UserEntity getEntity() {
        int id = 1;
        return new UserEntity(id, userName, encoder.encode(password), fName, lName, phone, role, null);
    }
}
