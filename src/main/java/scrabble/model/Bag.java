package scrabble.model;


import java.util.ArrayList;
import java.util.Collections;

public class Bag {
	private ArrayList<Tiles> tileList;

	public Bag() {
		this.tileList = new ArrayList<>();
		initializeTiles();
	}
	 
	private void initializeTiles() {
        addTilesToBag(Tiles.E, 15);
        addTilesToBag(Tiles.A, 9);
        addTilesToBag(Tiles.I, 8);
        addTilesToBag(Tiles.N, 6);
        addTilesToBag(Tiles.O, 6);
        addTilesToBag(Tiles.R, 6);
        addTilesToBag(Tiles.S, 6);
        addTilesToBag(Tiles.T, 6);
        addTilesToBag(Tiles.U, 6);
        addTilesToBag(Tiles.L, 5);
        addTilesToBag(Tiles.D, 3);
        addTilesToBag(Tiles.M, 3);
        addTilesToBag(Tiles.B, 2);
        addTilesToBag(Tiles.C, 2);
        addTilesToBag(Tiles.P, 2);
        addTilesToBag(Tiles.F, 2);
        addTilesToBag(Tiles.H, 2);
        addTilesToBag(Tiles.V, 2);
        addTilesToBag(Tiles.G, 2);
        addTilesToBag(Tiles.J, 1);
        addTilesToBag(Tiles.Q, 1);
        addTilesToBag(Tiles.K, 1);
        addTilesToBag(Tiles.W, 1);
        addTilesToBag(Tiles.X, 1);
        addTilesToBag(Tiles.Y, 1);
        addTilesToBag(Tiles.Z, 1);
        addTilesToBag(Tiles.j, 2); 
    }
	
	public ArrayList<Tiles> getTileList() {
		return tileList;
	}
	
	public int getTileListLenght() {
		return tileList.size();
	}

	public void addTile(Tiles tile) {
        tileList.add(tile);
    }
	
    
    private void addTilesToBag(Tiles tile, int frequency) {
        for (int i = 0; i < frequency; i++) {
            tileList.add(tile);
        }
    }
    
    public Tiles drawTile() {
        if (tileList.isEmpty()) {
            System.out.println("Sac vide");
            return null;
        }
        
        Collections.shuffle(tileList); 
        return tileList.remove(0);
    }
}
