package ca.qc.cegeptr.mat1892498.battleshipserver.game;

import ca.qc.cegeptr.mat1892498.battleshipserver.game.users.User;
import ca.qc.cegeptr.mat1892498.battleshipserver.game.users.UserManager;
import ca.qc.cegeptr.mat1892498.battleshipserver.requests.Response;
import io.netty.channel.ChannelHandlerContext;

public class ActionHandler {

    public ActionHandler(ChannelHandlerContext ctx, Response json){
        this.addUser(ctx);
        System.out.println(json);
    }

    public void addUser(ChannelHandlerContext ctx){
        if(!UserManager.USERS.containsKey(ctx.channel().id().toString()))
            UserManager.USERS.put(ctx.channel().id().toString(), new User(ctx.channel().id().toString(), ctx));
    }

}
