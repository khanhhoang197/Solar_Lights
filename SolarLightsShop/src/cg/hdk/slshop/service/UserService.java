package cg.hdk.slshop.service;

import cg.hdk.slshop.model.Role;
import cg.hdk.slshop.model.User;
import cg.hdk.slshop.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService {
    public static final String PATH = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\users.csv";
    private static UserService instance;

    public UserService() {

    }

    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();
        return instance;
    }

    @Override
    public List<User> findAll() {
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
            if (user.getUsername().equals(username) && user.getPassword().equals(password) && user.getRole().equals(Role.ADMIN)) {
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
        CSVUtils.write(PATH, users);
    }

    @Override
    public void update(User newUser) {
        List<User> users = findAll();
        for (User user : users){
            if (user.getIdUser() == newUser.getIdUser()){
                String fullName = newUser.getFullName();
                if (fullName != null && !fullName.isEmpty())
                    user.setFullName(fullName);
                String phone = newUser.getPhoneNumber();
                if (phone != null && !phone.isEmpty())
                    user.setPhoneNumber(newUser.getPhoneNumber());
                String address = newUser.getAddress();
                if (address !=null && !address.isEmpty())
                    user.setAddress(newUser.getAddress());
                user.setTimeUpdate(Instant.now());
                CSVUtils.write(PATH, users);
                break;
            }

        }


    }

    @Override
    public void revome(Long id) {
    List<User> users = findAll();
    for (User user : users){
        if (user.getIdUser().equals(id)){
            users.remove(user);
            break;
        }else {
            System.out.println("Id không tồn tại");
        }
    }
    CSVUtils.write(PATH, users);
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
    public boolean existByPhone(String phone) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getPhoneNumber().equals(phone))
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

    public static void main(String[] args) {
        UserService userService = new UserService();
//        userService.add();
    }
}
