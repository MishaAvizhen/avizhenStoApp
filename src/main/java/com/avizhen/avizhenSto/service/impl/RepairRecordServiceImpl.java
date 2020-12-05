package com.avizhen.avizhenSto.service.impl;

import com.avizhen.avizhenSto.constants.RepairRequestConstants;
import com.avizhen.avizhenSto.dao.RepairRecordDao;
import com.avizhen.avizhenSto.dao.RepairRequestDao;
import com.avizhen.avizhenSto.dto.RepairRecordDto;
import com.avizhen.avizhenSto.dto.RepairRequestDto;
import com.avizhen.avizhenSto.entity.RepairRecord;
import com.avizhen.avizhenSto.entity.RepairRequest;
import com.avizhen.avizhenSto.entity.User;
import com.avizhen.avizhenSto.exception.InvalidRepairRecordException;
import com.avizhen.avizhenSto.exception.InvalidRepairRequestException;
import com.avizhen.avizhenSto.service.RepairRecordService;
import com.avizhen.avizhenSto.service.RepairRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepairRecordServiceImpl implements RepairRecordService {
    @Autowired
    private RepairRecordDao repairRecordDao;
    @Autowired
    private RepairRequestDao repairRequestDao;

    @Transactional
    @Override
    public void registerRepairRecord(RepairRecordDto repairRecordDto, int repairRequestId) throws InvalidRepairRecordException {
        RepairRecord repairRecord = new RepairRecord();
        repairRecord.setRepairDescription(repairRecordDto.getRepairDescription());
        repairRecord.setDetailPrice(repairRecordDto.getDetailPrice());
        repairRecord.setWorkPrice(repairRecordDto.getWorkPrice());
        repairRecord.setOtherNotes(repairRecordDto.getOtherNotes());
        RepairRequest repairRequestDaoById = repairRequestDao.findById(repairRequestId);
        repairRecord.setRepairRequest(repairRequestDaoById);

        repairRecordDao.saveRepairRecord(repairRecord);

        repairRequestDaoById.setStatus(RepairRequestConstants.STATUS_PROCESSED);
        repairRequestDao.saveRepairRequest(repairRequestDaoById);
    }

    @Transactional
    @Override
    public List<RepairRecord> getListOfAllRepairRecord() {
        List<RepairRecord> resultRepairRecordList = new ArrayList<>();
        List<RepairRecord> allRepairRecordList = repairRecordDao.findAllRepairRecords();
        for (RepairRecord repairRecord : allRepairRecordList) {
            resultRepairRecordList.add(repairRecord);
        }
        return resultRepairRecordList;
    }
    @Transactional
    @Override
    public RepairRecord getRepairRecordById(Integer repairRecordId) {
        return repairRecordDao.findByid(repairRecordId);
    }


    private void repairRecordData(String  repairDescription) throws InvalidRepairRecordException {
        if (repairDescription==null) {
            throw new InvalidRepairRecordException("Repair description is empty, enter something...");

        }

    }
}
