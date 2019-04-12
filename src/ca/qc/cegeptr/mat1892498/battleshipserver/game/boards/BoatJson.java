package ca.qc.cegeptr.mat1892498.battleshipserver.game.boards;

import java.util.Arrays;

public class BoatJson {

    private String[] pos;
    private String direction;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String[] getPos() {
        return pos;
    }

    public String toString(){
        return "Positions : " + Arrays.toString(this.pos) + " Direction : " + this.direction;
    }

}