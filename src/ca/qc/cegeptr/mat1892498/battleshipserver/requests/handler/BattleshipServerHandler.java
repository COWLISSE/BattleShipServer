package ca.qc.cegeptr.mat1892498.battleshipserver.requests.handler;

import ca.qc.cegeptr.mat1892498.battleshipserver.requests.Response;
import ca.qc.cegeptr.mat1892498.battleshipserver.game.users.User;
import ca.qc.cegeptr.mat1892498.battleshipserver.game.users.UserManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class BattleshipServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserManager.USERS.put(ctx.channel().id().asLongText(), new User(ctx.channel().id().asLongText(), ctx));
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        UserManager.USERS.remove(ctx.channel().id().asLongText());
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Response requestData = (Response) msg;
//        Response responseData = new Response();
//        responseData.json = Server.gson.toJson(new ShipLocation());
//        ctx.writeAndFlush(responseData);
        System.out.println(ctx.channel().id() + " -- " + requestData.toString());
//        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Cause: " + cause.getMessage());
    }

}