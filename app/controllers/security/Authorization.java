package controllers.security;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import play.db.jpa.Transactional;
import DAO.Impl.UserDAOImpl;
import javax.inject.Inject;
import play.db.jpa.JPAApi;
import java.util.List;
import play.mvc.*;


public class Authorization extends Action.Simple{

    @Inject
    private JPAApi api;

    @Transactional
    public CompletionStage<Result> call(Http.Context ctx) {
        List<String> tokens = ctx.request().getHeaders().toMap().get("authToken");
        if(tokens != null) {
            String token = tokens.get(0);
            if( !token.equals("")&&(new UserDAOImpl(api.em())).get(token) != null){
                return delegate.call(ctx);
            }
        }
        return CompletableFuture.completedFuture(Results.unauthorized());
    }
}
