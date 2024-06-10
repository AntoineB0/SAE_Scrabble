package scrabble.model;

public class Board {
	
	private Square[][] grid;

    
    public Board() {
        grid = new Square[15][15]; 
        initializeBoard();
    }
    
    public Square getSquare(int column,int row) {
        return grid[row][column];
    }
    
    public int getRows() {
        return grid.length;
    }

    
    public int getColumns() {
        return grid[0].length;
    }
    
    public void setSquare(Square square) {
        grid[square.getX()][square.getY()] = square;
    }
    
    private void initializeBoard() {
    	for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                this.setSquare(new Square(null, Multiplier.VOID, i, j));
            }
        }
    }
    
    
    public void printBoard() {
    	System.out.println("  |A|B|C|D|E|F|G|H|I|J|K|L|M|N|O");
    	for (int i = 0; i < this.getRows(); i++) {
    		
    		if (i<9) {
    			System.out.print(" "+ (i+1) +"|");
    		}
    		else{
    			System.out.print((i+1)+"|");
    			}
    		
            for (int j = 0; j < this.getColumns(); j++) {
            	if (grid[i][j].getTile()==null) {
            		if ((i==7) && (j==7)) { 
            			System.out.print("*|");
            		}
            		else {
            			System.out.print(" |");
            		}
            	}
            	
            	else{
            		System.out.print(grid[i][j].getTile() + "|");
            	}
            }
            System.out.println();
        }
    }


}
