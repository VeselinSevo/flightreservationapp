package com.codeinsight.flightreservation.flightreservation.services;

import com.codeinsight.flightreservation.flightreservation.entities.User;
import java.util.List;

public interface UserService {

    public User saveUser(User user);
    public void deleteUser(User delUser);
    public User editUser(User editUser);
    public List<User> getAllUsers();
    public User findUserById(long id);
    public User findUserByEmail(String email);
}
