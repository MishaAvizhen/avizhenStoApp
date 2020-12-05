package com.avizhen.avizhenSto.service;

import com.avizhen.avizhenSto.dto.RepairRecordDto;
import com.avizhen.avizhenSto.dto.RepairRequestDto;
import com.avizhen.avizhenSto.entity.DetailCatalog;
import com.avizhen.avizhenSto.entity.RepairRecord;
import com.avizhen.avizhenSto.entity.RepairRequest;
import com.avizhen.avizhenSto.entity.User;
import com.avizhen.avizhenSto.exception.InvalidRepairRecordException;

import java.util.List;

/**
 * Created by Александр on 15.11.2020.
 */
public interface RepairRecordService {
    void registerRepairRecord(RepairRecordDto repairRecordDto, int  repairRequestId) throws InvalidRepairRecordException;

    List<RepairRecord> getListOfAllRepairRecord();
    RepairRecord getRepairRecordById(Integer repairRecordId);

}
