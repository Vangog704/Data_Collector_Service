package controllers;

import play.db.jpa.Transactional;
import DAO.Impl.UserDataDAOImpl;
import util.HeaderMakerFilter;
import DAO.Impl.FieldDAOImpl;
import play.mvc.Controller;
import javax.inject.Inject;
import play.db.jpa.JPAApi;
import models.UserData;
import play.mvc.Result;
import DAO.UserDataDAO;
import play.mvc.With;
import DAO.FieldDAO;


public class UserServiceController extends Controller {

    @Inject
    private JPAApi api;

    @Transactional()
    @With(HeaderMakerFilter.class)
    public Result getActiveFields() {
        FieldDAO FDAO = new FieldDAOImpl(api.em());
        return ok(FDAO.getListActiveJson());
    }

    @Transactional
    @With(HeaderMakerFilter.class)
    public Result saveUserData() {
        UserDataDAO UDDAO = new UserDataDAOImpl(api.em());
        try {
            UDDAO.add(new UserData(request().body().asJson()));
            ResponseObserver.getInstanse().addResponse(request().body().asJson());
        }
        catch(Exception e) {
            return badRequest();
        }
        return ok();
    }
}
