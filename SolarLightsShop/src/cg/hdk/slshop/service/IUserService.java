package cg.hdk.slshop.service;

import cg.hdk.slshop.model.Role;
import cg.hdk.slshop.model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User adminLogin(String username, String password);
    User memberLogin(String username, String password);
    void addUser(User newUser);
    boolean existsUserId(Long id);
    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
    User findById(Long id);

}
