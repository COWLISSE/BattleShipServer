package ca.qc.cegeptr.mat1892498.battleshipserver.battleship.boards;

import ca.qc.cegeptr.mat1892498.battleshipserver.Server;
import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.Boats;
import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.users.UserManager;
import ca.qc.cegeptr.mat1892498.battleshipserver.requests.Response;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.*;

public class Board {

    public HashMap<String[], Boats> boats = new HashMap<>();

    public Board(String channelId, Map.Entry<String, JsonElement> boats){
        BoatJson[] boatList = new BoatJson[5];
        JsonArray list = boats.getValue().getAsJsonArray();
        for(int i = 0; i < boatList.length; i++)
            boatList[i] = Server.gson.fromJson(list.get(i), BoatJson.class);
        if(BoardManager.verifyPositions(boatList)){
            for(int i = 0; i < boatList.length; i++)
                switch (i){
                    case 0:
                        this.boats.put(boatList[i].getPos(), Boats.CARRIER);
                        break;
                    case 1:
                        this.boats.put(boatList[i].getPos(), Boats.BATTLESHIP);
                        break;
                    case 2:
                        this.boats.put(boatList[i].getPos(), Boats.CRUISER);
                        break;
                    case 3:
                        this.boats.put(boatList[i].getPos(), Boats.SUBMARINE);
                        break;
                    case 4:
                        this.boats.put(boatList[i].getPos(), Boats.DESTROYER);
                        break;
                }
            UserManager.USERS.get(channelId).getCtx().channel().writeAndFlush(new Response("{'board': 'success'}"));
        }else UserManager.USERS.get(channelId).getCtx().channel().writeAndFlush(new Response("{'board': 'failed', 'error': 'Illegal board plan'}"));
    }
}
