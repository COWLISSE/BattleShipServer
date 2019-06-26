package ca.qc.cegeptr.mat1892498.battleshipserver.battleship;

public enum Boats {
    CARRIER(5), BATTLESHIP(4), CRUISER(3), SUBMARINE(3), DESTROYER(2);

    private final int width;

    Boats(int width){
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

}
