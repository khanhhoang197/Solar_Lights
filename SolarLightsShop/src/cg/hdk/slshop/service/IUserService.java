package cg.hdk.slshop.service;

import cg.hdk.slshop.model.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User adminLogin(String username, String password);
    void add(User newUser);
    void update(User newUser);
    void revome(Long id);
    boolean existsUserId(Long id);
    boolean existsByEmail(String email);
    boolean existByPhone(String phone);
    boolean existsByUsername(String userName);
    User findById(Long id);

}
