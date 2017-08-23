package DAO.Impl;

import com.mysema.query.jpa.impl.JPAQuery;
import models.QUser;
import DAO.UserDAO;
import models.User;

import javax.persistence.EntityManager;

public class UserDAOImpl implements UserDAO {

    private EntityManager em;

    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }


    @Override
    public User get(String login, String pass) {
        return (new JPAQuery(em))
                .from(QUser.user)
                .where(QUser.user.login.eq(login).and(QUser.user.password.eq(pass)))
                .singleResult(QUser.user);
    }

    @Override
    public User get(String authToken) {
        return (new JPAQuery(em))
                .from(QUser.user)
                .where(QUser.user.authToken.eq(authToken))
                .singleResult(QUser.user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }
}
