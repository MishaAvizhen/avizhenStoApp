package com.avizhen.avizhenSto.service;

import com.avizhen.avizhenSto.dto.RepairRequestDto;
import com.avizhen.avizhenSto.entity.RepairRequest;
import com.avizhen.avizhenSto.entity.User;
import com.avizhen.avizhenSto.exception.InvalidRepairRequestException;

import java.util.List;

/**
 * Created by Александр on 13.11.2020.
 */
public interface RepairRequestService {
    void registerRepairRequest(RepairRequestDto repairRequestDto, User repairRequestUser) throws InvalidRepairRequestException;

    List<RepairRequest> getListOfActiveRepairRequests();
    List<RepairRequest> getListAllRepairRequestsOfUsers(Integer userId);
}
