package ca.qc.cegeptr.mat1892498.battleshipserver.game.boards;

import java.util.HashMap;
import java.util.Map;

public class BoardManager {

    private int[] boatSizes = {5,4,3,3,2};
    public static Map<String, Board> boards = new HashMap<>();

    public static boolean verifyPositions(BoatJson[] boat){
        int good = 17;
        for(int i = 0; i < boat.length; i++){
            for(int j = 0; j < boat[i].getPos().length; j++){
                String[] pos = boat[i].getPos()[j].split(",");
                String x = pos[0];
                String y = pos[1];
                if(Integer.parseInt(x) >= 0 && Integer.parseInt(x) <= 9 && Integer.parseInt(y) >= 0 && Integer.parseInt(y) <= 9){
                   switch (boat[i].getDirection()){
                       case "NORTH":
                            // x== y--
                           if(x.equals(boat[i].getPos()[0].split(",")[0]))
                               if(y.equals(String.valueOf(Integer.parseInt(boat[i].getPos()[0].split(",")[1]) - i)))
                               good--;
                            else System.out.println("RIP NORTH 2");
                           else System.out.println("RIP NORTH 1");
                           break;
                       case "EAST":
                           // x++ y==
                           if(y.equals(boat[i].getPos()[0].split(",")[1]))
                               if(x.equals(String.valueOf(Integer.parseInt(boat[i].getPos()[0].split(",")[0]) - i)))
                                good--;
                               else System.out.println("RIP EAST 2");
                           else System.out.println("RIP EAST 1");
                           break;
                       case "SOUTH":
                           // x== y++
                           if(x.equals(boat[i].getPos()[0].split(",")[0]) && y.equals(String.valueOf(Integer.parseInt(boat[i].getPos()[0].split(",")[1]) + i)))
                               good--;
                           else System.out.println("RIP SOUTH");
                           break;
                       case "WEST":
                           // x-- y==
                           if(y.equals(boat[i].getPos()[0].split(",")[1]) && x.equals(String.valueOf(Integer.parseInt(boat[i].getPos()[0].split(",")[0]) - i)))
                               good--;
                           else System.out.println("RIP WEST");
                           break;
                       default:
                           System.out.println("RIP DEFAULT");
                           return false;
                   }
                }
            }
        }
        return good == 0;
    }

}
