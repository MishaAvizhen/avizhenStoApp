package com.avizhen.avizhenSto.dao;

import com.avizhen.avizhenSto.entity.RepairRequest;
import com.avizhen.avizhenSto.entity.User;

import java.util.List;

public interface RepairRequestDao {
    void saveRepairRequest(RepairRequest repairRequest);

    List<RepairRequest> findAllRepairRequests();

    void deleteRepairRequestById(int id);

    RepairRequest findById(int id);

    void updateRepairRequest(RepairRequest repairRequest);
}
