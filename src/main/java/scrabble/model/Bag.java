package scrabble.model;

import java.util.ArrayList;
import java.util.Collections;

public class Bag {
	private ArrayList<TileInstance> tileList;

	public Bag() {
		this.tileList = new ArrayList<>();
		initializeTiles();
	}
	 
	private void initializeTiles() {
		Tiles[] tilesArray = Tiles.values(); // .values() return the whole enum in an array
        for (Tiles tile : tilesArray) {
        	for (int i = 0; i < tile.getNumberInBag(); i++) {
                addTile(new TileInstance(tile));
            }
        }
    }
	
	public ArrayList<TileInstance> getTileList() {
		return tileList;
	}
	
	public int getTileListLenght() {
		return tileList.size();
	}

	public void addTile(TileInstance tile) {
        tileList.add(tile);
    }
    
    public TileInstance drawTile() {
        if (tileList.isEmpty()) {
            System.out.println("Sac vide");
            return null;
        }
        Collections.shuffle(tileList); 
        return tileList.remove(0);
    }
}