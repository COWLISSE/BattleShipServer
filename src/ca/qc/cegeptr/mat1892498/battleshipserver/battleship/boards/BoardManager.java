package ca.qc.cegeptr.mat1892498.battleshipserver.battleship.boards;

import java.util.HashMap;
import java.util.Map;

public class BoardManager {

    private static int[] boatSizes = {5,4,3,3,2};
    public static Map<String, Board> boards = new HashMap<>();

    public static boolean verifyPositions(BoatJson[] boat){
        int good = 17;
        for(int i = 0; i < boat.length; i++){
            if(boat[i].getPos().length != boatSizes[i]) continue;
            for(int j = 0; j < boat[i].getPos().length; j++){
                String[] pos = boat[i].getPos()[j].split(",");
                int x = Integer.parseInt(pos[0]);
                int y = Integer.parseInt(pos[1]);
                if(x >= 0 && x <= 9 && y >= 0 && y <= 9){
                   switch (boat[i].getDirection()){
                       case "NORTH":
//                           x== y--
                           if(x == Integer.parseInt(boat[i].getPos()[0].split(",")[0]))
                               if((y + j) == Integer.parseInt(boat[i].getPos()[0].split(",")[1]))
                                   good--;
                           break;
                       case "EAST":
                           // x++ y==
                           if(y == Integer.parseInt(boat[i].getPos()[0].split(",")[1]))
                               if((x - j) == Integer.parseInt(boat[i].getPos()[0].split(",")[0]))
                                   good--;
                           break;
                       case "SOUTH":
//                            x== y++
                           if(x == Integer.parseInt(boat[i].getPos()[0].split(",")[0]))
                               if((y - j) == Integer.parseInt(boat[i].getPos()[0].split(",")[1]))
                                   good--;
                           break;
                       case "WEST":
//                            x-- y==
                           if(y == Integer.parseInt(boat[i].getPos()[0].split(",")[1]))
                               if((x + j) == Integer.parseInt(boat[i].getPos()[0].split(",")[0]))
                                   good--;
                           break;
                       default:
                           return false;
                   }
                   // Superposed---
                   for(int k = 0; k < boat.length; k++){
                       for(int l = 0; l < boat[k].getPos().length; l++){
                           if(i == k && j == l) continue;
                           if(boat[i].getPos()[j].equals(boat[k].getPos()[l]))
                               good++;
                       }
                   }
                   //---
                }
            }
        }

        return good == 0;
    }

}
