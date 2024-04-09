package scrabble.model;

public class Square {
	private Tiles tile;
    private Multiplier multiplier;
    private boolean isEmpty;
    private int x; 
    private int y;
    
    
	public Square(Tiles tile, Multiplier multiplier, boolean isEmpty, int x, int y) {
		
		this.tile = tile;
		this.multiplier= multiplier;
		this.isEmpty = isEmpty;
		this.x = x;
		this.y = y;
	}
	
	public Square(int x, int y) {
		
		this.tile = null;
		this.multiplier = null;
		this.isEmpty = true;
		this.x = x;
		this.y = y;
	}

	public Tiles getTile() {
		return tile;
	}

	public Multiplier getMultiplier() {
		return multiplier;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setTile(Tiles tile) {
		this.tile = tile;
		this.setEmpty(false);
	}

	public void setMultiplier(Multiplier multiplier) {
		this.multiplier = multiplier;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Square [tile=" + tile + ", multiplier=" + multiplier + ", isEmpty=" + isEmpty + ", x=" + x + ", y="+ y + "]";
	}
	
	
	
	
	
	
	
    
    
}
	