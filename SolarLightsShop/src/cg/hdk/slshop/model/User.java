package cg.hdk.slshop.model;

import java.time.Instant;

public class User {
    private long idUser;
    private String username;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String email;
    private Role role;
    private Instant timeCreate;
    private Instant timeUpdate;

    public User() {

    }

    public User(long idUser, String username, String password, String fullName,
                String phoneNumber, String address, String email, Role role) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.role = role;
    }

    public User(long idUser, String fullName, String phoneNumber, String address, String email, Role role, Instant timeCreate, Instant timeUpdate) {
        this.idUser = idUser;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
        this.role = role;
        this.timeCreate = timeCreate;
        this.timeUpdate = timeUpdate;
    }

    public static User parseUser(String rawUser) {
        User user = new User();
        String[] array = rawUser.split(",");
        user.setIdUser(Long.parseLong(array[0]));
        user.setUsername(array[1]);
        user.setPassword(array[2]);
        user.setFullName(array[3]);
        user.setPhoneNumber(array[4]);
        user.setAddress(array[5]);
        user.setEmail(array[6]);
        user.role = Role.parseRole(array[7]);
        user.timeCreate = Instant.parse(array[8]);
        String temp = array[9];
        if (temp != null && !temp.equals("null")) user.timeUpdate = Instant.parse(temp);
        return user;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Instant getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Instant timeCreate) {
        this.timeCreate = timeCreate;
    }

    public Instant getTimeUpdate() {
        return timeUpdate;
    }

    public void setTimeUpdate(Instant timeUpdate) {
        this.timeUpdate = timeUpdate;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                idUser,
                username,
                password,
                fullName,
                phoneNumber,
                address,
                email,
                role,
                timeCreate,
                timeUpdate);
    }

}
