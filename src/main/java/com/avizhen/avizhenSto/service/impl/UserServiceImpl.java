package com.avizhen.avizhenSto.service.impl;

import com.avizhen.avizhenSto.constants.RepairRequestConstants;
import com.avizhen.avizhenSto.dao.RepairRequestDao;
import com.avizhen.avizhenSto.dao.UserDao;
import com.avizhen.avizhenSto.dto.RepairRequestDto;
import com.avizhen.avizhenSto.dto.UserRegistrationDto;
import com.avizhen.avizhenSto.entity.RepairRecord;
import com.avizhen.avizhenSto.entity.RepairRequest;
import com.avizhen.avizhenSto.entity.User;
import com.avizhen.avizhenSto.exception.InvalidRegistrationException;
import com.avizhen.avizhenSto.exception.InvalidRepairRequestException;
import com.avizhen.avizhenSto.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.avizhen.avizhenSto.constants.RoleConstants.CLIENT_ROLE;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Transactional
    @Override
    public void printUserInfoById(Integer id) {
        User user = userDao.findById(1);
        List<RepairRequest> repairRequestList = user.getRepairRequestList();
        for (RepairRequest repairRequest : repairRequestList) {
            System.out.println(repairRequest);

        }

    }

    @Transactional
    @Override
    public void updateUserName(Integer Id) {
        User user = userDao.findById(1);
        user.setName("Misha");

    }

    @Transactional
    @Override
    public User loginUserOrReturnNull(String login, String password) {
        User userByLogin = findUserByLogin(login);
        if (userByLogin != null) {
            if (userByLogin.getPassword().equals(password)) {
                return userByLogin;
            }
        }
        return null;
    }

    private User findUserByLogin(String login) {
        List<User> allUsers = userDao.findAllUsers();
        for (User user : allUsers) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        //test git
        return null;
    }

    @Transactional
    @Override
    public void registerClient(UserRegistrationDto userRegistrationDto) throws InvalidRegistrationException {
        validateInputData(userRegistrationDto.getLogin());
        User user = new User();
        user.setName(userRegistrationDto.getName());
        user.setLogin(userRegistrationDto.getLogin());
        user.setSurname(userRegistrationDto.getSurname());
        user.setPassword(userRegistrationDto.getPassword());
        user.setPhoneNumber(userRegistrationDto.getPhoneNumber());
        user.setUserRole(CLIENT_ROLE);

        userDao.saveUser(user);
    }
    @Transactional
    @Override
    public List<RepairRecord> getUserRepairRecordList(Integer userId) {
        User user = userDao.findById(userId);
        if (user == null) {
            return Collections.emptyList();
        }
            //получить список записей ремонта пользователя
        List<RepairRecord> resultList = new ArrayList<>();

        List<RepairRequest> userRepairRequestList = user.getRepairRequestList();
        for (RepairRequest repairRequest : userRepairRequestList) {
            if (repairRequest.getStatus().equals(RepairRequestConstants.STATUS_PROCESSED)) {
                RepairRecord currentRequestRepairRecord = repairRequest.getRepairRecord();
                if (currentRequestRepairRecord != null) {
                    resultList.add(currentRequestRepairRecord);
                }
            }
        }
        return resultList;
    }

    private void validateInputData(String userLogin) throws InvalidRegistrationException {
        User userByLogin = findUserByLogin(userLogin);
        if (userByLogin != null) {
            throw new InvalidRegistrationException("User with provided login already exists: " + userLogin);
        }
        if (userLogin.length() < 4) {
            throw new InvalidRegistrationException("Provided login is too short: " + userLogin);
        }
    }

}
