package ca.qc.cegeptr.mat1892498.battleshipserver.game.boards;

import ca.qc.cegeptr.mat1892498.battleshipserver.Server;
import ca.qc.cegeptr.mat1892498.battleshipserver.game.Boats;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.*;

public class Board {

    private HashMap<String, Boats> boats = new HashMap<>();

    public Board(Map.Entry<String, JsonElement> boats){
//        List<BoatJson> bateaux = new ArrayList<>();
        BoatJson[] boatList = new BoatJson[5];
        JsonArray list = boats.getValue().getAsJsonArray();
        for(int i = 0; i < boatList.length; i++)
            boatList[i] = Server.gson.fromJson(list.get(i), BoatJson.class);
        System.out.println(BoardManager.verifyPositions(boatList));
//        list.forEach(pos -> bateaux.add(Server.gson.fromJson(pos, BoatJson.class)));
//        System.err.println(BoardManager.verifyPositions(bateaux));
    }

    public boolean addBoat(Boats boat, String pos){
        if(pos.replaceAll("\\d+", "X").matches("X,X"))
            boats.put(pos, boat);
        else return false;
        return true;
    }

    public HashMap<String, Boats> getBoats() {
        return boats;
    }

    public void setBoats(HashMap<String, Boats> boats) {
        this.boats = boats;
    }
}
