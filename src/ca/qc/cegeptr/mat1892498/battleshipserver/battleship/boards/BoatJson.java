package ca.qc.cegeptr.mat1892498.battleshipserver.battleship.boards;

import java.util.Arrays;

public class BoatJson {

    private String[] pos;
    private String direction;

    public String getDirection() {
        return direction;
    }

    public String[] getPos() {
        return pos;
    }

    public String toString(){
        return "Positions : " + Arrays.toString(this.pos) + " Direction : " + this.direction;
    }

}