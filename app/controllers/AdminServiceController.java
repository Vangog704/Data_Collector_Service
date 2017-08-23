package controllers;

import controllers.security.Authorization;
import play.libs.streams.ActorFlow;
import play.db.jpa.Transactional;
import akka.stream.Materializer;
import DAO.Impl.UserDataDAOImpl;
import akka.actor.ActorSystem;
import util.HeaderMakerFilter;
import DAO.Impl.FieldDAOImpl;
import play.mvc.Controller;
import play.db.jpa.JPAApi;
import play.mvc.WebSocket;
import DAO.UserDataDAO;
import play.mvc.Result;
import play.libs.Json;
import play.mvc.With;
import DAO.FieldDAO;
import models.Field;

import javax.inject.Inject;

public class AdminServiceController extends Controller {

    @Inject
    private JPAApi api;

    private final ActorSystem actorSystem;
    private final Materializer materializer;

    @Inject
    public AdminServiceController (ActorSystem actorSystem, Materializer materializer) {
        this.actorSystem = actorSystem;
        this.materializer = materializer;
    }

    public WebSocket socket() {
        return WebSocket.Text.accept(
                request -> ActorFlow.actorRef(ResponseSenderSocket::props,actorSystem, materializer)
        );
    }

    @Transactional
    @With({Authorization.class,HeaderMakerFilter.class})
    public Result getFields() {
        FieldDAO FDAO = new FieldDAOImpl(api.em());
        return ok(FDAO.getListJson());
    }

    @Transactional
    @With({Authorization.class,HeaderMakerFilter.class})
    public Result getUserData() {

        UserDataDAO UDDAO = new UserDataDAOImpl(api.em());
        return ok(UDDAO.getListJson());
    }

    @Transactional
    @With({Authorization.class,HeaderMakerFilter.class})
    public Result getFieldNames() {
        FieldDAO FDAO = new FieldDAOImpl(api.em());
        return ok(FDAO.getNameListJson());
    }

    @Transactional
    @With({Authorization.class,HeaderMakerFilter.class})
    public Result getFieldById(String id) {
        FieldDAO FDAO = new FieldDAOImpl(api.em());
        return ok(Json.toJson(FDAO.getById(id)));
    }

    @Transactional
    @With({Authorization.class,HeaderMakerFilter.class})
    public Result saveField() {
        FieldDAO FDAO = new FieldDAOImpl(api.em());
        try {
            FDAO.add(new Field(request().body().asJson()));
        }
        catch(NullPointerException e) {
            System.out.println(e.getStackTrace().toString());
            return badRequest();
        }
        return ok();
    }

    @Transactional
    @With({Authorization.class,HeaderMakerFilter.class})
    public Result getResponseNumber() {
        UserDataDAO UDDAO = new UserDataDAOImpl(api.em());
        return ok(Json.newObject().put("number",UDDAO.getNumber()));
    }

    @Transactional
    @With({Authorization.class,HeaderMakerFilter.class})
    public Result deleteField(String id) {
        FieldDAO FDAO = new FieldDAOImpl(api.em());
        FDAO.delete(id);
        return ok();
    }

    public Result saveOptions(String path) {
        return ok()
                .withHeader("Access-Control-Allow-Origin" , "*")
                .withHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
                .withHeader("Access-Control-Allow-Headers", "Accept, Origin, Content-type, X-Json, X-Prototype-Version, X-Requested-With, authToken")
                .withHeader("Access-Control-Allow-Credentials", "true");
    }
}
