package ca.qc.cegeptr.mat1892498.battleshipserver.game;

public enum Boats {
    CARRIER(5), BATTLESHIP(4), CRUISER(3), SUBMARINE(3), DESTROYER(2);

    private final int width;

    Boats(int width){
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public static Boats getBoat(String name){
        switch (name){
            case "CARRIER":
                return Boats.CARRIER;
            case "BATTLESHIP":
                return Boats.BATTLESHIP;
            case "CRUISER":
                return Boats.CRUISER;
            case "SUBMARINE":
                return Boats.SUBMARINE;
            case "DESTROYER":
                return Boats.DESTROYER;
            default:
                return null;
        }
    }

}
