package DAO;

import models.User;

public interface UserDAO {
    User get(String login, String pass);
    User get(String authToken);
    void update(User user);
}
