package com.avizhen.avizhenSto.dto;

/**
 * Created by Александр on 12.11.2020.
 */
public class UserRegistrationDto {

    private String name;
    private String surname;
    private String phoneNumber;
    private String login;
    private String password;

    private UserRegistrationDto(Builder builder) {
        this.name = builder.name;
        this.surname = builder.surname;
        this.phoneNumber = builder.phoneNumber;
        this.login = builder.login;
        this.password = builder.password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder {
        private String name;

        private String surname;

        private String phoneNumber;

        private String login;

        private String password;

        public Builder(String login, String password) {
            this.password = password;
            this.login = login;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public UserRegistrationDto build() {
            return new UserRegistrationDto(this);
        }
    }
}
