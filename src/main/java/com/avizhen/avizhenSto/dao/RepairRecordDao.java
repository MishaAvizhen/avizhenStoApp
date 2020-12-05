package com.avizhen.avizhenSto.dao;

import com.avizhen.avizhenSto.entity.RepairRecord;
import com.avizhen.avizhenSto.entity.User;

import java.util.List;

/**
 * Created by Александр on 09.11.2020.
 */
public interface RepairRecordDao {

    void saveRepairRecord(RepairRecord repairRecord );

    List<RepairRecord> findAllRepairRecords();

    void deleteRepairRecordById(int id);

    RepairRecord findByid(int id);

    void updateRepairRecord(RepairRecord repairRecord);
}
