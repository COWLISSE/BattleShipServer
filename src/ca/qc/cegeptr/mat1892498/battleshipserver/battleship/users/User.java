package ca.qc.cegeptr.mat1892498.battleshipserver.battleship.users;

import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.game.Game;
import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.game.Hit;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String channelId;
    private String name;
    private ChannelHandlerContext ctx;
    private Game game = null;
    private List<Hit> hits = new ArrayList<>();

    public User(String channelId, ChannelHandlerContext ctx){
        this.setChannelId(channelId);
        this.setName("Unknown");
        this.setCtx(ctx);
    }

    public User(String channelId, ChannelHandlerContext ctx, String name){
        this.setChannelId(channelId);
        this.setName(name);
        this.setCtx(ctx);
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public boolean hasHit(int x, int y){
        return hits.stream().anyMatch(hit-> hit.getX() == x && hit.getY() == y);
    }

    public void hit(int x, int y){
        hits.add(new Hit(x, y));
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

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
