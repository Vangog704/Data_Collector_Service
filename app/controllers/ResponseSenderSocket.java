package controllers;

import akka.actor.*;

public class ResponseSenderSocket extends UntypedAbstractActor {

    public final ActorRef out;

    public static Props props(ActorRef out) {
        return Props.create(ResponseSenderSocket.class, out);
    }

    public ResponseSenderSocket(ActorRef out) {
        this.out = out;
        ResponseObserver.getInstanse().addSocket(this);
    }

    public void sendMessage(String message) {
        out.tell(message.toString(),self());
    }

    @Override
    public void onReceive(Object message) throws Throwable {
        /*do nothing*/
    }

    public void postStop() throws Exception {
        ResponseObserver.getInstanse().removeSocket(this);
    }
}