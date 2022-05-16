package com.codeinsight.flightreservation.flightreservation.services;

import com.codeinsight.flightreservation.flightreservation.entities.User;
import java.util.List;

public interface UserService {

    public User saveUser(User user);
    public void deleteUser(User user);
    public User editUser(User user);
    public List<User> getAllUsers();
    public User findUserById(long id);
    public User findUserByEmail(String email);

}
