package com.fedclient.manager;

import com.fedclient.fed.websocket.WebSocketService;

public class TaskServiceManager {

    private static WebSocketService webSocketService;


    public static void setWebSocketService(WebSocketService mwebSocketService){
        webSocketService=mwebSocketService;
    }

    public static WebSocketService getWebSocketService(){
        return webSocketService;
    }


}
