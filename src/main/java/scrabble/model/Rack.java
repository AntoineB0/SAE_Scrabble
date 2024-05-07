package scrabble.model;



import java.util.ArrayList;


public class Rack {
	private ArrayList<Tiles> tilesOnRack;
	public static int RackSize = 7;

    public Rack() {
        this.tilesOnRack = new ArrayList<>();
    }
    

    public void addTiles(int numberToAdd, Bag bag) {
    	for (int i = 0; i < numberToAdd; i++) {
    		this.addTile(bag);
    	}
    }
    /**
     * 
     * @param bag
     */
    public void addTile(Bag bag ) {
        if (tilesOnRack.size() < RackSize) {
        	tilesOnRack.add(bag.drawTile());
        } 
        else {
            System.out.println("Chevalet plein.");
        }
    } 
    public void addTile(int rank,Bag bag ) {
        if ( (tilesOnRack.size() < RackSize) && (tilesOnRack.size() >= rank) && (rank >= 0) ) {
        	tilesOnRack.add(rank,bag.drawTile());
        } 
        else {
            System.out.println("Rang incorrect.");
        }
    }
 
    
    
    public void swapTile(int rank, Bag bag) {
    	if (rank <= tilesOnRack.size()) {
            bag.addTile(tilesOnRack.remove(rank));
    	    this.addTile(rank,bag);
    	}
    	else {
    		System.out.println("Rang incorrect");
    	}
    }
    
    
    
    public void removeTile(int rank, Bag bag) {
    	if (rank <= tilesOnRack.size()) {
    		bag.addTile(tilesOnRack.remove(rank));
    	}
    	else {
    		System.out.println("Rang incorrect");
    	}
    }
    
    public void removeTile(Tiles tile, Bag bag) {
        if (tilesOnRack.remove(tile)) {
        	bag.addTile(tile);
        }
        else {
        	System.out.println("Ce jeton n'est pas sur votre chevalet");
        }
    }


    
    
    public void printRack() {
        System.out.println("Jetons sur le chevalet :");
        for (Tiles tile : tilesOnRack) {
            System.out.print(tile.name() + " ");
        }
        System.out.println();
        
    }
    
    
}

