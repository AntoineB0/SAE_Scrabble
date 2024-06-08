package scrabble.model;


public class Square {
	private TileInstance tile;
    private Multiplier multiplier;
    private int x; 
    private int y;
    
	public Square(TileInstance tile, Multiplier multiplier, int x, int y) {
		this.tile = tile;
		this.multiplier= multiplier;
		this.x = x;
		this.y = y;
	}
	
	public Square(int x, int y) {
		this.tile = null;
		this.multiplier = null;
		this.x = x;
		this.y = y;
	}

	public TileInstance getTile() {
		return tile;
	}

	public Multiplier getMultiplier() {
		return multiplier;
	}

	public boolean isEmpty() {
		return tile == null;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setTile(TileInstance tile) {
		this.tile = tile;
	}

	public void setMultiplier(Multiplier multiplier) {
		this.multiplier = multiplier;
	}

	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Square [tile=" + tile + ", multiplier=" + multiplier + ", x=" + x + ", y="+ y + "]";
	}

}
		