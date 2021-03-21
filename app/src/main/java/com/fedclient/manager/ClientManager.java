package com.fedclient.manager;

import com.fedclient.domain.Client;

public class ClientManager {
    private static Client client;

    public static Client getCurrentClient(){
        if(client == null){
            client = new Client();
        }
        return client;
    }

    public static void setCurrentClient(Client user){
        client = user;
    }

    public static void clear(){
        client = null;
    }
}
