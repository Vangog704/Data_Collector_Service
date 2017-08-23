package DAO.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.mysema.query.jpa.impl.JPAQuery;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import play.libs.Json;
import java.util.List;
import models.QField;
import java.util.Map;
import DAO.FieldDAO;
import models.Field;


public class FieldDAOImpl implements FieldDAO {

    private EntityManager em;

    public FieldDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Field> getListActive() {
        List<Field> result;
        result = (new JPAQuery(em)
                .from(QField.field)
                .where(QField.field.isActive.eq(true)))
                .list(QField.field);
        return result;
    }

    @Override
    public List<Field> getList() {
        List<Field> result;
        result = (new JPAQuery(em)
                .from(QField.field))
                .list(QField.field);
        return result;
    }

    @Override
    public Map<String, Field> getMap() {
        Map<String,Field> result = new HashMap<>();
            for(Field field: getListActive()) {
                result.put(field.getLabel(),field);
            }
        return result;
    }

    @Override
    public void add(Field field) {
        em.merge(field);
    }

    @Override
    public Field getById(String id) {
        Field result;
        result = (new JPAQuery(em)
                .from(QField.field)
                .where(QField.field.label.eq(id))).singleResult(QField.field);
        return result;
    }

    @Override
    public void delete(String id) {
        em.remove(getById(id));
    }

    @Override
    public JsonNode getListActiveJson() {
        return Json.toJson(getListActive());
    }

    @Override
    public JsonNode getListJson() {
        return Json.toJson(getList());
    }

    @Override
    public JsonNode getNameListJson() {
        List<String> fieldlist = new ArrayList<>();

        for(Field field : getListActive())
            fieldlist.add(field.getLabel());

        JsonNode result = Json.toJson(fieldlist);
        return result;
    }
}
