package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;

public class RegisterDTO extends RepresentationModel<RegisterDTO> {
    private String name;
    private int age;
    private String address;
    private String username;
    private String password;
    private String role;

    public RegisterDTO(String name, int age, String address, String username, String password, String role) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
