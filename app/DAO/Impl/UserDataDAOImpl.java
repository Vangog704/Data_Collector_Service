package DAO.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.mysema.query.jpa.impl.JPAQuery;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import models.QUserData;
import DAO.UserDataDAO;
import models.UserData;
import play.libs.Json;
import java.util.List;
import java.util.Map;

public class UserDataDAOImpl implements UserDataDAO{

    private EntityManager em;

    public UserDataDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<UserData> getList() {
        return (new JPAQuery(em))
                .from(QUserData.userData).list(QUserData.userData);
    }

    @Override
    public void add(UserData data) {
        em.merge(data);
    }

    @Override
    public long getNumber() {
        return (new JPAQuery(em))
                .from(QUserData.userData).count();
    }

    @Override
    public JsonNode getListJson() {
        List<Map<String,String>> datas = new ArrayList<>();

        for(UserData data : getList())
            datas.add(data.getData());

        return Json.toJson(datas);
    }
}
