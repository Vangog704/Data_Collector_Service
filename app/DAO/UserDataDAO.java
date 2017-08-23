package DAO;

import com.fasterxml.jackson.databind.JsonNode;
import models.UserData;
import java.util.List;

public interface UserDataDAO {
    List<UserData> getList();
    JsonNode getListJson();
    void add(UserData data);
    long getNumber();
}
