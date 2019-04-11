package ca.qc.cegeptr.mat1892498.battleshipserver;

import com.google.gson.Gson;

public class Server {

    public static Gson gson = new Gson();

    public static void main(String[] args) throws Exception {
        new BattleshipServer().run();
    }
}
