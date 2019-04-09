package ca.qc.cegeptr.mat1892498.battleshipserver.game.users;

import ca.qc.cegeptr.mat1892498.battleshipserver.game.GameType;
import io.netty.channel.ChannelHandlerContext;

public class User {

    private String channelId;
    private String name;
    private ChannelHandlerContext ctx;
    private GameType state;

    public User(String channelId, ChannelHandlerContext ctx){
        this.setChannelId(channelId);
        this.setName("Unknown");
        this.setCtx(ctx);
        this.setState(GameType.WAITING);
    }

    public User(String channelId, ChannelHandlerContext ctx, String name){
        this.setChannelId(channelId);
        this.setName(name);
        this.setCtx(ctx);
        this.setState(GameType.WAITING);
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public GameType getState() {
        return state;
    }

    public String getName() {
        return name;
    }


    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(GameType state) {
        this.state = state;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
