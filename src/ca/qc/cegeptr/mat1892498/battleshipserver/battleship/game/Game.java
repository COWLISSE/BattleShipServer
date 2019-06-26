package ca.qc.cegeptr.mat1892498.battleshipserver.battleship.game;

import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.boards.Board;
import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.boards.BoardManager;
import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.boards.Coord;
import ca.qc.cegeptr.mat1892498.battleshipserver.battleship.users.User;
import ca.qc.cegeptr.mat1892498.battleshipserver.requests.Response;

import java.util.*;

public class Game {

    private User[] players = new User[2];
    private int turn;
    private Map<Integer, AttackTypes> attacks = new HashMap<>();
    private Map<User, Integer> hitCount = new HashMap<>();

    public Game(User player1, User player2){
        hitCount.put(player1, 0);
        hitCount.put(player2, 0);
        this.players[0] = player1;
        this.players[1] = player2;
        this.turn = 0;
        getPlayers()[0].getCtx().channel().writeAndFlush(new Response("{'game': 'starting'}"));
        getPlayers()[1].getCtx().channel().writeAndFlush(new Response("{'game': 'starting'}"));
        this.sendTurn();
    }

    public int getHitCount(User player){
        return hitCount.get(player);
    }

    public void increaseHit(User player){
        hitCount.put(player, hitCount.get(player) + 1);
    }

    public void end(User winner, User looser){
        winner.getCtx().channel().writeAndFlush(new Response("{'game_state': 'won'}"));
        looser.getCtx().channel().writeAndFlush(new Response("{'game_state': 'lost'}"));
        winner.setGame(null);
        looser.setGame(null);
        GamesManager.games.remove(this);
    }

    public boolean hit(User user, int x, int y){
        Board board = BoardManager.boards.get(user.getChannelId());
        List<Coord> coordList = new ArrayList<>();
        board.boats.forEach((pos, boat) -> {
            for(String string : pos) coordList.add(new Coord(Integer.parseInt(string.split(",")[0]), Integer.parseInt(string.split(",")[1])));
        });
        return coordList.stream().anyMatch(coord -> coord.getX() == x && coord.getY() == y);
    }

    public User[] getPlayers() {
        return players;
    }

    public void setPlayers(User[] players) {
        this.players = players;
    }

    public Map<Integer, AttackTypes> getAttacks() {
        return attacks;
    }

    public void setAttacks(Map<Integer, AttackTypes> attacks) {
        this.attacks = attacks;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void changeTurn(){
        this.turn = turn == 0 ? 1 : 0;
    }

    public void sendTurn(){
        players[0].getCtx().channel().writeAndFlush(turn == 0 ? new Response("{'turn': 'yours'}") : new Response("{'turn': 'his'}"));
        players[1].getCtx().channel().writeAndFlush(turn == 1 ? new Response("{'turn': 'yours'}") : new Response("{'turn': 'his'}"));
    }

}
