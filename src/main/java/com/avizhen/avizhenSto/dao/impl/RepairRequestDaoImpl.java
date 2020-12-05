package com.avizhen.avizhenSto.dao.impl;

import com.avizhen.avizhenSto.dao.AbstractDao;
import com.avizhen.avizhenSto.dao.RepairRequestDao;
import com.avizhen.avizhenSto.entity.RepairRequest;
import com.avizhen.avizhenSto.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repairRequestDao")
public class RepairRequestDaoImpl extends AbstractDao implements RepairRequestDao {
    @Override
    public void saveRepairRequest(RepairRequest repairRequest) {
        persist(repairRequest);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RepairRequest> findAllRepairRequests() {
        Criteria criteria = getSession().createCriteria(RepairRequest.class);
        return (List < RepairRequest >) criteria.list();
    }

    @Override
    public void deleteRepairRequestById(int id) {
        Query query = getSession().createSQLQuery("DELETE FROM repair_request WHERE id=:id ");
        query.setString("id", String.valueOf(id));
        query.executeUpdate();

    }

    @Override
    public RepairRequest findById(int id) {
        Criteria criteria = getSession().createCriteria(RepairRequest.class);
        criteria.add(Restrictions.eq("id", id));
        return (RepairRequest) criteria.uniqueResult();
    }

    @Override
    public void updateRepairRequest(RepairRequest repairRequest) {
        getSession().update(repairRequest);

    }
}
