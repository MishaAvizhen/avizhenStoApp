package com.avizhen.avizhenSto.dao.impl;

import com.avizhen.avizhenSto.dao.AbstractDao;
import com.avizhen.avizhenSto.dao.UserDao;
import com.avizhen.avizhenSto.entity.User;
import com.avizhen.avizhenSto.util.CustomHibernateDaoSupport;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao implements UserDao {


    @Override
    public void saveUser(User user) {
        persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = getSession().createCriteria(User.class);
        return (List < User >) criteria.list();
    }

    @Override
    public void deleteUserById(int id) {
        Query query = getSession().createSQLQuery("DELETE FROM user WHERE id=:id ");
        query.setString("id", String.valueOf(id));
        query.executeUpdate();
    }

    @Override
    public User findById(int id) {
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));
        return (User) criteria.uniqueResult();
    }

    @Override
    public void updateUser(User user) {
    getSession().update(user);

    }
}
