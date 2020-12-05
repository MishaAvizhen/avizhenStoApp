package com.avizhen.avizhenSto.service;

import com.avizhen.avizhenSto.dto.RepairRequestDto;
import com.avizhen.avizhenSto.dto.UserRegistrationDto;
import com.avizhen.avizhenSto.entity.RepairRecord;
import com.avizhen.avizhenSto.entity.User;
import com.avizhen.avizhenSto.exception.InvalidRegistrationException;
import com.avizhen.avizhenSto.exception.InvalidRepairRequestException;

import java.util.List;


public interface UserService {

    void printUserInfoById(Integer id);

    void updateUserName(Integer id);

    User loginUserOrReturnNull(String login, String password);

    void registerClient(UserRegistrationDto userRegistrationDto) throws InvalidRegistrationException;

    List<RepairRecord> getUserRepairRecordList(Integer userId);






}
