package org.acme.tsg.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.tsg.utils.PasswordEncoder;
import org.acme.tsg.domain.Roles;
import org.acme.tsg.domain.User;
import org.acme.tsg.dto.UserDTO;
import org.acme.tsg.repo.UserRepository;


import java.util.List;
import java.util.Optional;

;

@ApplicationScoped
@Transactional
public class UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.name);
        user.setLastname(userDTO.lastname);
        user.setPassword(userDTO.password);
        user.setEmail(userDTO.email);
        user.setAddress(userDTO.address);
        user.setContract(userDTO.contract);
        user.setPhone(userDTO.phone);
        user.setLastUpload(userDTO.lastUpload);
        user.setIsAggregated(true);
        user.setIsAktive(true);
        user.setPassword(passwordEncoder.encode(userDTO.password));
        user.setRoles(String.valueOf(Roles.USER));
        user.setApi(true);

        userRepo.save(user);

        return user;
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public Optional<User> updateUser(Long id, UserDTO userDTO) {
        return userRepo.findById(id).map(user -> {
           user.setName(userDTO.name);
           user.setLastname(userDTO.lastname);
           user.setEmail(userDTO.email);
           user.setPassword(userDTO.password);
           return user;
        });
    }
    public List<User> userList () {
        return userRepo.findAll();
    }




    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow();
    }

    public User findByName(String name) {
        return userRepo.findByName(name).orElseThrow();
    }



    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
