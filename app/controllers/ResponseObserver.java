package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;

public class ResponseObserver extends Thread{

    //Declaration vars
    private static ResponseObserver obj;

    private Deque<JsonNode> dataDeque;
    private Set<ResponseSenderSocket> Subscribers;

    //Constructors
    private ResponseObserver() {
        dataDeque = new ArrayDeque<>();
        Subscribers = new HashSet<>();
    }

    //Methods
    public static ResponseObserver getInstanse() {
        if(obj == null)
        {
            obj = new ResponseObserver();
            obj.setDaemon(true);
            obj.start();
        }
        return obj;
    }

    @Override
    public void run() {
        while(true)
        {
            if(!dequeIsEmpty())
                BroadCast(getResponse());

            try { Thread.sleep(3000); }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized void BroadCast(JsonNode message) {
        Iterator<ResponseSenderSocket> it = Subscribers.iterator();
        ResponseSenderSocket actor= null;
        while(it.hasNext())
        {
            actor = it.next();
            actor.sendMessage(message.toString());
        }
    }

    //Getters & Setters------------------------
    public synchronized boolean dequeIsEmpty() {
        return dataDeque.isEmpty();
    }

    public synchronized void addSocket(ResponseSenderSocket usersession) {
        Subscribers.add(usersession);
    }

    public synchronized void removeSocket(ResponseSenderSocket usersession) {
        Subscribers.remove(usersession);
    }

    public synchronized void addResponse(JsonNode data) {
        dataDeque.push(data);
    }

    public synchronized JsonNode getResponse() {
        return dataDeque.pop();
    }
    //------------------------------------------
}
