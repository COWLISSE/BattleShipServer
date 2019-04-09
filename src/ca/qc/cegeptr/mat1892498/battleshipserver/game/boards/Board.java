package ca.qc.cegeptr.mat1892498.battleshipserver.game.boards;

import ca.qc.cegeptr.mat1892498.battleshipserver.game.Boats;

import java.util.HashMap;

public class Board {

    private HashMap<String, Boats> boats = new HashMap<>();

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
