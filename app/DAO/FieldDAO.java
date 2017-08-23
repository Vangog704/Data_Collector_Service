package DAO;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.Map;
import models.Field;

public interface FieldDAO {
    JsonNode getListActiveJson();
    List<Field> getListActive();
    JsonNode getNameListJson();
    Map<String,Field> getMap();
    Field getById(String id);
    JsonNode getListJson();
    List<Field> getList();

    void delete(String id);

    void add(Field field);
}
