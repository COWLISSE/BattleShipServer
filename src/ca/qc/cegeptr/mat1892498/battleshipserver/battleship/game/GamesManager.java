package ca.qc.cegeptr.mat1892498.battleshipserver.battleship.game;

import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.users.User;
import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.users.UserManager;
import ca.qc.cegeptr.mat1892498.battleshipserver.requests.Response;

import java.util.*;

public class GamesManager {

    public static Queue<User> waiting = new LinkedList<>();
    public static Map<String, User> parties = new HashMap<>();
    public static List<Game> games = new ArrayList<>();

    public static void linkWaiters(){
        if(waiting.size() >= 2){
            int last_index = (waiting.size() % 2 == 0) ? waiting.size() : waiting.size() - 1;
            for(int i = 0; i < last_index / 2; i++) {
                User first = waiting.poll();
                User seconde = waiting.poll();
                Game game = new Game(first, seconde);
                games.add(game);
                if (first != null && seconde != null){
                    first.setGame(game);
                    seconde.setGame(game);
                    first.getCtx().channel().writeAndFlush(new Response("{'username': '" + seconde.getName() + "'}"));
                    seconde.getCtx().channel().writeAndFlush(new Response("{'username': '" + first.getName() + "'}"));
                }
            }
        }
    }

    public static void removePlayer(String id) {
        waiting.remove(UserManager.USERS.get(id));
        for (Map.Entry<String, User> entry : parties.entrySet()) {
            if (entry.getValue().getChannelId().equals(id))
                parties.remove(entry.getKey());
        }
        if(UserManager.USERS.get(id).getGame() != null) {
            User[] players = UserManager.USERS.get(id).getGame().getPlayers();
            for (User player : players) {
                if(!player.getChannelId().equals(id))
                    player.getCtx().channel().writeAndFlush("{'game', 'win'}");
            }
            games.remove(UserManager.USERS.get(id).getGame());
        }
    }


}
