package ca.qc.cegeptr.mat1892498.battleshipserver.game;

import ca.qc.cegeptr.mat1892498.battleshipserver.Server;
import ca.qc.cegeptr.mat1892498.battleshipserver.game.boards.Board;
import ca.qc.cegeptr.mat1892498.battleshipserver.game.boards.BoardManager;
import ca.qc.cegeptr.mat1892498.battleshipserver.game.users.User;
import ca.qc.cegeptr.mat1892498.battleshipserver.game.users.UserManager;
import ca.qc.cegeptr.mat1892498.battleshipserver.requests.Response;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.Set;

public class ActionHandler {

    public ActionHandler(ChannelHandlerContext ctx, Response json){
        if(!json.getJson().isEmpty()){
            System.out.println(json);
            JsonElement element = Server.gson.fromJson(json.getJson(), JsonElement.class);
            JsonObject obj = element.getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
            for (Map.Entry<String, JsonElement> entry: entries) {
                // Detection of keys sent to the server by the clients
                switch (entry.getKey()){
                    case "connection":
                        this.addUser(ctx);
                        break;
                    case "boats":
                        BoardManager.boards.put(ctx.channel().id().toString(), new Board(entry));
                        break;
                    default:
                        System.out.println("Unknow key");
                        break;
                }
            }
        }
    }

    public void addUser(ChannelHandlerContext ctx){
        if(!UserManager.USERS.containsKey(ctx.channel().id().toString()))
            UserManager.USERS.put(ctx.channel().id().toString(), new User(ctx.channel().id().toString(), ctx));
    }

}
