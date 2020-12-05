package com.avizhen.avizhenSto.entity;

import com.avizhen.avizhenSto.constants.RoleConstants;
import com.sun.istack.internal.NotNull;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "user", schema = "avizhen_sto")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotNull
    @Column(name = "user_role", nullable = false)
    private String userRole;

    @NotNull
    @Column(name = "login", nullable = false)
    private String login;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<RepairRequest> repairRequestList;

    public List<RepairRequest> getRepairRequestList() {
        return repairRequestList;
    }

    public void setRepairRequestList(List<RepairRequest> repairRequestList) {
        this.repairRequestList = repairRequestList;
    }

    public User(){

    }


    public User(String name, String surname, String phoneNumber, String userRole, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.userRole = userRole;
        this.login = login;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public boolean isAdmin(){
        return RoleConstants.ADMIN_ROLE.equals(this.userRole);
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userRole='" + userRole + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
