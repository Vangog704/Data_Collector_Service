package util;

import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

public class HeaderMakerFilter extends Action.Simple{

    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        ctx.response().setHeader("Access-Control-Allow-Origin","*");
        ctx.response().setHeader("Access-Control-Allow-Headers","authToken");
        return delegate.call(ctx);
    }
}
