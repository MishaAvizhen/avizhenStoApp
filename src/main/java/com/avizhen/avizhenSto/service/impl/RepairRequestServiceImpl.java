package com.avizhen.avizhenSto.service.impl;

import com.avizhen.avizhenSto.constants.RepairRequestConstants;
import com.avizhen.avizhenSto.constants.RoleConstants;
import com.avizhen.avizhenSto.dao.RepairRequestDao;
import com.avizhen.avizhenSto.dao.UserDao;
import com.avizhen.avizhenSto.dto.RepairRequestDto;
import com.avizhen.avizhenSto.entity.RepairRequest;
import com.avizhen.avizhenSto.entity.User;
import com.avizhen.avizhenSto.exception.InvalidRepairRequestException;
import com.avizhen.avizhenSto.service.RepairRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RepairRequestServiceImpl implements RepairRequestService {
    @Autowired
    private RepairRequestDao repairRequestDao;
    @Autowired
    private UserDao userDao;

        @Transactional
        @Override
        public void registerRepairRequest(RepairRequestDto repairRequestDto, User repairRequestUser) throws InvalidRepairRequestException {
            Date dateOfRepairRecord = repairRequestDto.getDateOfRepair();
            validateRepairRequestData(dateOfRepairRecord);
            RepairRequest repairRequest = new RepairRequest();
            repairRequest.setDateOfRepair(repairRequestDto.getDateOfRepair());
            repairRequest.setCarRemark(repairRequestDto.getCarRemark());
            repairRequest.setRepairDescription(repairRequestDto.getRepairDescription());
            repairRequest.setStatus(RepairRequestConstants.STATUS_IN_PROGRESS);
            repairRequest.setUser(repairRequestUser);

            repairRequestDao.saveRepairRequest(repairRequest);

        }

    @Transactional
    @Override
    public List<RepairRequest> getListOfActiveRepairRequests() {
        List<RepairRequest> resultList = new ArrayList<>();
        List<RepairRequest> allRepairRequests = repairRequestDao.findAllRepairRequests();
        for (RepairRequest repairRequest : allRepairRequests) {
            if (repairRequest.getStatus().equals(RepairRequestConstants.STATUS_IN_PROGRESS)) {
                resultList.add(repairRequest);
            }
        }
        return resultList;

    }
    @Transactional
    @Override
    public List<RepairRequest> getListAllRepairRequestsOfUsers(Integer userId) {
        User userRepairRequest = userDao.findById(userId);
        List<RepairRequest> resultList = new ArrayList<>();
        List<RepairRequest> repairRequestListOfUsers = userRepairRequest.getRepairRequestList();
        for (RepairRequest listOfUser : repairRequestListOfUsers) {
            resultList.add(listOfUser);
        }
        return resultList;
    }

    private void validateRepairRequestData(Date dateOfRepairRecord) throws InvalidRepairRequestException {
        if (dateOfRepairRecord == null) {
            throw new InvalidRepairRequestException("Date is empty, Enter date ... ");
        }
        if (dateOfRepairRecord.before(new Date())) {
            throw new InvalidRepairRequestException("Inncorrect date, enter future date ... ");
        }
    }

}
