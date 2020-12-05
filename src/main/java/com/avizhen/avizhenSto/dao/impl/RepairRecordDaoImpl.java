package com.avizhen.avizhenSto.dao.impl;

import com.avizhen.avizhenSto.dao.AbstractDao;
import com.avizhen.avizhenSto.dao.RepairRecordDao;
import com.avizhen.avizhenSto.entity.RepairRecord;
import com.avizhen.avizhenSto.entity.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("repairRecordDao")
public class RepairRecordDaoImpl extends AbstractDao implements RepairRecordDao {


    @Override
    public void saveRepairRecord(RepairRecord repairRecord) {
        persist(repairRecord);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RepairRecord> findAllRepairRecords() {
        Criteria criteria = getSession().createCriteria(RepairRecord.class);
        return (List<RepairRecord>) criteria.list();
    }

    @Transactional
    @Override
    public void deleteRepairRecordById(int id) {
        Query query = getSession().createSQLQuery("DELETE FROM repair_record WHERE id=:id ");
        query.setString("id", String.valueOf(id));
        query.executeUpdate();

    }

    @Override
    public RepairRecord findByid(int id) {
        Criteria criteria = getSession().createCriteria(RepairRecord.class);
        criteria.add(Restrictions.eq("id", id));
        return (RepairRecord) criteria.uniqueResult();
    }

    @Override
    public void updateRepairRecord(RepairRecord repairRecord) {
        getSession().update(repairRecord);

    }
}
