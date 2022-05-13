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
    public User getUser(User user) {
        return getUserById(user.getId());
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User editUser(User user) {
            User editedUser = getUserById(user.getId());
            editedUser.setFirstName(user.getFirstName());
            editedUser.setLastName(user.getLastName());
            editedUser.setEmail(user.getEmail());
            editedUser.setPassword(user.getPassword());
            return userRepository.save(editedUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
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
}
