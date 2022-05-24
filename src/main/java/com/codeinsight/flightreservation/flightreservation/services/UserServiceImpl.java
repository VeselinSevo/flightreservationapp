package com.codeinsight.flightreservation.flightreservation.services;

import com.codeinsight.flightreservation.flightreservation.entities.User;
import com.codeinsight.flightreservation.flightreservation.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User delUser) {
        userRepository.delete(delUser);
    }

    @Override
    public User editUser(User editUser) {
            User newUser = findUserById(editUser.getId());
            newUser.setFirstName(editUser.getFirstName());
            newUser.setLastName(editUser.getLastName());
            newUser.setEmail(editUser.getEmail());
            newUser.setPassword(editUser.getPassword());
            return userRepository.save(newUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        if(userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public User findUserByEmail(String email) {
        if(userRepository.findUserByEmail(email) != null) {
            return userRepository.findUserByEmail(email);
        } throw new RuntimeException("User not found");
    }

    @Override
    public List<String> findSubscribedUsersEmails() {
        return userRepository.findSubscribedUsersEmails();
    }
}
