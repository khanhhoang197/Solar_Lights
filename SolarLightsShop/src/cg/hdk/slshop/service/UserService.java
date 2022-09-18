package cg.hdk.slshop.service;

import cg.hdk.slshop.model.Role;
import cg.hdk.slshop.model.User;
import cg.hdk.slshop.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    public static final String PATH = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\users.csv";
    public static final String PATH_ADMIN = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\admin.csv";
    private static UserService instance;

    public UserService() {

    }

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();
        return instance;
    }

    @Override
    public  List<User> findAll() {
        List<User> users = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH);
        for (String record : records) {
            users.add(User.parseUser(record));
        }
        return users;
    }

    @Override
    public User adminLogin(String username, String password) {
        List<User> users = findAll();

        for (User user : users) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password) && user.getRole() == Role.ADMIN)

                return user;
        }
        return null;
    }

    @Override
    public User memberLogin(String username, String password) {
        List<User> users = findAll();
        for (User user : users) {


            if (user.getUsername().equals(username) && user.getPassword().equals(password)
                    && user.getRole().equals(Role.USER)) {
                return user;
            }
        }
        return null;
    }


    @Override
    public void add(User newUser) {
        List<User> users = findAll();
        newUser.setTimeCreate(Instant.now());
        users.add(newUser);
        if (Role.parseRole("USER") == Role.USER)
            CSVUtils.write(PATH, users);
        if (Role.parseRole("ADMIN") == Role.ADMIN)
            CSVUtils.write(PATH_ADMIN, users);
    }

    @Override
    public boolean existsUserId(Long id) {
        return findById(id) != null;
    }

    @Override
    public boolean existsByEmail(String email) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    @Override
    public boolean existsByUsername(String userName) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getUsername().equals(userName))
                return true;
        }
        return false;
    }

    @Override
    public User findById(Long id) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getIdUser() == id)
                return user;
        }
        return null;
    }

}


