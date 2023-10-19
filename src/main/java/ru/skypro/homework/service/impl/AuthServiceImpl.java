package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final UserMapper userMapper;

//    public AuthServiceImpl(UserDetailsManager manager,
//                           PasswordEncoder passwordEncoder) {
//        this.manager = manager;
//        this.encoder = passwordEncoder;
//    }

    @Override
    public boolean login(String userName, String password) {
//        if (!manager.userExists(userName)) {
          if (!userService.userExists(userName)){
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
//        if (manager.userExists(register.getUsername())) {
        if (userService.userExists(register.getUsername())) {
        return false;
        }
//        manager.createUser(
//                User.builder()
//                        .passwordEncoder(this.encoder::encode)
//                        .password(register.getPassword())
//                        .username(register.getUsername())
//                        .roles(register.getRole().name())
//                        .build());
//        register.setRole(role);
        register.setPassword(encoder.encode(register.getPassword()));
        userService.createUser(userMapper.registerReqDtoToEntity(register));
        return true;
    }
    @Override
    public boolean setPassword(String oldPassword, NewPassword newPassword, String name) {
        if (encoder.matches(newPassword.getCurrentPassword(), manager.loadUserByUsername(name).getPassword())) {
            userService.changePassword(oldPassword, encoder.encode(newPassword.getNewPassword()), name);
            return true;
        }
        return false;
    }

}
