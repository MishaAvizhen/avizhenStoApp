package com.avizhen.avizhenSto.dao.impl;

import com.avizhen.avizhenSto.dao.AbstractDao;
import com.avizhen.avizhenSto.dao.DetailCatalogDao;
import com.avizhen.avizhenSto.entity.DetailCatalog;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("detailCatalogDao")
public class DetailCatalogDaoImp extends AbstractDao implements DetailCatalogDao {

    @Override
    public void saveDetailCatalog(DetailCatalog detailCatalog) {
        persist(detailCatalog);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DetailCatalog> findAllDetails() {
        Criteria criteria = getSession().createCriteria(DetailCatalog.class);
        return (List<DetailCatalog>) criteria.list();
    }

    @Transactional
    @Override
    public void deleteDetailCatalogById(int id) {
        Query query = getSession().createSQLQuery("DELETE FROM detail_catalog WHERE id=:id ");
        query.setString("id", String.valueOf(id));
        query.executeUpdate();

    }

    @Override
    public DetailCatalog findById(int id) {
        Criteria criteria = getSession().createCriteria(DetailCatalog.class);
        criteria.add(Restrictions.eq("id", id));
        return (DetailCatalog) criteria.uniqueResult();
    }

    @Override
    public DetailCatalog findByName(String name) {
        Criteria criteria = getSession().createCriteria(DetailCatalog.class);
        criteria.add(Restrictions.eq("name", name));
        return (DetailCatalog) criteria.uniqueResult();
    }

    @Override
    public void updateDetailCatalog(DetailCatalog detailCatalog) {
        getSession().update(detailCatalog);

    }
}
