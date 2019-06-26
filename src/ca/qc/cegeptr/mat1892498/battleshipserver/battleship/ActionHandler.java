package ca.qc.cegeptr.mat1892498.battleshipserver.battleship;

import ca.qc.cegeptr.mat1892498.battleshipserver.Server;
import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.boards.Board;
import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.boards.BoardManager;
import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.game.Game;
import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.game.GamesManager;
import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.users.User;
import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.users.UserManager;
import ca.qc.cegeptr.mat1892498.battleshipserver.requests.Response;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.netty.channel.ChannelHandlerContext;

import java.awt.*;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ActionHandler {

    public ActionHandler(ChannelHandlerContext ctx, Response json){
        if(!json.getJson().isEmpty()){
            System.out.println(json);
            JsonElement element = Server.gson.fromJson(json.getJson(), JsonElement.class);
            JsonObject obj = element.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
            for (Map.Entry<String, JsonElement> entry: entries) {
                // Detection of keys sent by the clients
                switch (entry.getKey()){
                    case "connection":
                        GamesManager.removePlayer(ctx.channel().id().asLongText());
                        this.addUser(ctx);
                        break;
                    case "boats":
                        GamesManager.removePlayer(ctx.channel().id().asLongText());
                        BoardManager.boards.put(ctx.channel().id().asLongText(), new Board(ctx.channel().id().asLongText(), entry));
                        break;
                    case "game":
                        GamesManager.removePlayer(ctx.channel().id().asLongText());
                        if(UserManager.USERS.containsKey(ctx.channel().id().asLongText())) {
                            if (entry.getValue().getAsString().equals("party")) {
                                String unique = UUID.randomUUID().toString().substring(28);
                                GamesManager.parties.put(unique, UserManager.USERS.get(ctx.channel().id().asLongText()));
                                ctx.channel().writeAndFlush(new Response("{'party': '" + unique + "'}"));
                            }else GamesManager.waiting.add(UserManager.USERS.get(ctx.channel().id().asLongText()));
                            GamesManager.linkWaiters();
                        }else ctx.channel().writeAndFlush(new Response("{'error': 'You're not an authentified user'}"));
                        break;
                    case "hit":
                        String coord = entry.getValue().getAsString();
                        User attacker = UserManager.USERS.get(ctx.channel().id().asLongText());

                        if(attacker != null){
                            if(attacker.getGame() != null){
                                Game game = attacker.getGame();
                                User opponent = game.getPlayers()[0].equals(attacker) ? game.getPlayers()[1] : game.getPlayers()[0];

                                int x = Integer.parseInt(coord.split(",")[0]);
                                int y = Integer.parseInt(coord.split(",")[1]);
                                if(game.getPlayers()[game.getTurn()].equals(attacker)) {
                                    if(!attacker.hasHit(x, y)){
                                        attacker.hit(x, y);
                                        if(game.hit(opponent, x, y)) {
                                            game.increaseHit(attacker);
                                            attacker.getCtx().channel().writeAndFlush(new Response("{'hit_success': '" + x + "," + y + "'}"));
                                            if(game.getHitCount(attacker) >= 17)
                                                game.end(attacker, opponent);
                                        }else{
                                            game.changeTurn();
                                            attacker.getCtx().channel().writeAndFlush(new Response("{'hit_failed': '" + x + "," + y + "'}"));
                                        }
                                        opponent.getCtx().channel().writeAndFlush(new Response("{'got_hit': '" + x + "," + y + "'}"));
                                    } else {
                                        ctx.channel().writeAndFlush(new Response("{'hit': 'already'}"));
                                    }
                                    game.sendTurn();
                                }
                            }
                        }
                        break;
                    case "username":
                        User att = UserManager.USERS.get(ctx.channel().id().asLongText());
                        if(att != null) {
                            att.setName(entry.getValue().getAsString());
                        }
                    break;
                    case "version":
                        if(entry.getValue().getAsInt() == 1)
                            ctx.channel().writeAndFlush(new Response("{'version': 'ok'}"));
                        else ctx.channel().writeAndFlush(new Response("{'version': 'wrong'}"));
                    break;
                    case "chat":
                        User at = UserManager.USERS.get(ctx.channel().id().asLongText());

                        if(at != null) {
                            if (at.getGame() != null) {
                                Game game = at.getGame();
                                User opponent = game.getPlayers()[0].equals(at) ? game.getPlayers()[1] : game.getPlayers()[0];
                                opponent.getCtx().channel().writeAndFlush(new Response("{'chat': '" +  entry.getValue().getAsString() + "'}"));
                            }
                        }
                        break;
                    default:
                        System.out.println("Unknown key");
                        break;
                }
            }
        }
    }

    public void addUser(ChannelHandlerContext ctx){
        if(!UserManager.USERS.containsKey(ctx.channel().id().asLongText()))
            UserManager.USERS.put(ctx.channel().id().asLongText(), new User(ctx.channel().id().asLongText(), ctx));
    }

}
