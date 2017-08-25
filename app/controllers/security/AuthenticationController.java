package controllers.security;

import com.fasterxml.jackson.databind.JsonNode;
import play.db.jpa.Transactional;
import util.HeaderMakerFilter;
import DAO.Impl.UserDAOImpl;
import javax.inject.Inject;
import play.db.jpa.JPAApi;
import play.libs.Json;
import java.util.List;
import DAO.UserDAO;
import models.User;
import play.mvc.*;


public class AuthenticationController extends Controller {

    @Inject
    private JPAApi api;

    @Transactional()
    @With(HeaderMakerFilter.class)
    public Result logIn() {
        JsonNode body = request().body().asJson();
        String login = body.findValue("login").asText(),
               password = body.findValue("password").asText();

        if(login != null && password != null) {
            UserDAO UDAO = new UserDAOImpl(api.em());
            User user = UDAO.get(login,password);
            if(user!=null) {
                user.newToken();
                UDAO.update(user);
                return ok(Json.newObject().put("authToken",user.getAuthToken()).toString());
            }
        }
        return Results.unauthorized();
    }

    @Transactional()
    @With(HeaderMakerFilter.class)
    public Result logOut() {
        JsonNode body = request().body().asJson();
        String token = body.findValue("login").asText();
        if(token != null) {
            UserDAO UDAO = new UserDAOImpl(api.em());
            User user = UDAO.get(token);
            if(user != null) {
                user.setAuthToken(null);
                UDAO.update(user);
            }
        }
        return ok();
    }

}
