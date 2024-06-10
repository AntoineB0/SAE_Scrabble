package scrabble.model;


import java.util.ArrayList;
import java.util.Collections;
 

public class Rack {
	private ArrayList<TileInstance> tilesOnRack;
	public static int RackSize = 7;

    public Rack() {
        this.tilesOnRack = new ArrayList<>();
    }
    
    public void addTile(Bag bag ) {
        while (tilesOnRack.size() < RackSize) {
        	tilesOnRack.add(bag.drawTile());
        }
    } 
    
    public void addTileOnRank(int rank,Bag bag ) {
        if ( (tilesOnRack.size() < RackSize) && (tilesOnRack.size() >= rank) && (rank >= 0) ) {
        	tilesOnRack.add(rank,bag.drawTile());
        } 
        else {
            System.out.println("Rang incorrect.");
        }
    }
    
    public void swapTileOnRank(int rank, Bag bag) {
    	if (rank <= tilesOnRack.size()) {
            bag.addTile(tilesOnRack.remove(rank));
    	    this.addTileOnRank(rank,bag);
    	}
    	else {
    		System.out.println("Rang incorrect");
    	}
    }
    
    public void addSpecificTile(Tiles tile, Bag bag) {
	    if (tilesOnRack.size() < RackSize) {
	        tilesOnRack.add(new TileInstance(tile));
	    } else {
	        System.out.println("Chevalet plein.");
	    }
	}
    
    public void removeTile(TileInstance tile) {
        if (!tilesOnRack.remove(tile)) {
            System.out.println("Ce jeton n'est pas sur votre chevalet");
        }
    }
    
    public void removeTile(TileInstance tile, Bag bag) {
        if (tilesOnRack.remove(tile)) {
        	bag.addTile(tile);
        }
        else {
        	System.out.println("Ce jeton n'est pas sur votre chevalet");
        }
    }
    
    public TileInstance findJoker() {
        for (TileInstance tile : tilesOnRack) { 
            if (tile.isJoker()) { 
                return tile;
            }
        }
        return null; 
    } 
    
    public void printRack() {
        System.out.println("Jetons sur le chevalet :");
        for (TileInstance tile : tilesOnRack) {
            System.out.print(tile.toString() + " ");
        }
        System.out.println();   
    }

	public ArrayList<TileInstance> getTilesOnRack() {
		return tilesOnRack;
	}
	
	public ArrayList<Character> getTilesOnRackToChar() {
		ArrayList<Character> tilesOnRackString = new ArrayList<>();
		for (TileInstance tileInstance : tilesOnRack) {
			tilesOnRackString.add(tileInstance.toChar());
		}
		return tilesOnRackString;
	}

	public void shuffle() {
		Collections.shuffle(tilesOnRack);
		
	}
}


