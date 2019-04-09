package ca.qc.cegeptr.mat1892498.battleshipserver;

import com.google.gson.Gson;

public class Server {

    public static Gson gson = new Gson();

    public static void main(String[] args) throws Exception {
        new BattleshipServer().run();
//        Scanner scanner = new Scanner(System.in);
//        new Thread(() -> {
//            try {
//                new BattleshipServer().run();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
//
//        while(true){
//            if(scanner.hasNextLine()){
//                scanner.next();
//                List<String> keys = new ArrayList<String>(BattleshipServerHandler.USERS.keySet());
//                if(BattleshipServerHandler.USERS.isEmpty()){
//                    System.out.println("Tayeul la liste et vide");
//                    continue;
//                }
//                Gson gson = new Gson();
//                ResponseData responseData = new ResponseData();
//                responseData.json = gson.toJson(new ShipLocation());
//
//                BattleshipServerHandler.USERS.get(keys.get(new Random().nextInt(keys.size()))).writeAndFlush(responseData);
//            }
//        }
    }
}
