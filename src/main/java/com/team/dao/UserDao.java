package com.team.dao;

import com.team.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("userDao")
public class UserDao {
    @Resource
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 获取用户对象
     */
    public UserEntity getUser(int id){
        Session session = sessionFactory.openSession();
        UserEntity user = session.get(UserEntity.class, id);
        session.close();
        return user;
    }
    /**
     * 保存用户信息
     */
    public void save(UserEntity user){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
    /**
     * 用户合法性判断
     */
    public boolean isValidUser(String name, String password) {
        name = name.trim(); password = password.trim();

        String hql = "from UserEntity where name = :name and password = :password";
        Session session = sessionFactory.openSession();

        List list = session.createQuery(hql).setParameter("name", name)
                .setParameter("password", password).list();
        if(list.size() != 0) {
            session.close();
            return true;
        }
        session.close();
        return false;
    }
}
