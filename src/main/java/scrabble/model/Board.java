package scrabble.model;

public class Board {
	
	private Square[][] grid;

    
    public Board() {
        grid = new Square[15][15]; 
        initializeBoard();
        initializeMultipliers();
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
    
    private void initializeMultipliers() {
        int[][] doubleLetterCoordinates = {
                {1, 4}, {1, 12}, {3, 7}, {3, 9},
                {4, 1}, {4, 8}, {4, 15}, {7, 3},
                {7, 7}, {7, 9}, {7, 13}, {8, 4},
                {8, 12}, {9, 3}, {9, 7}, {9, 9},
                {9, 13}, {12, 1}, {12, 8}, {12, 15},
                {13, 7}, {13, 9}, {15, 4}, {15, 12}
        };

        int[][] tripleLetterCoordinates = {
                {2, 6}, {2, 10}, {6, 2}, {6, 6},
                {6, 10}, {6, 14}, {10, 2}, {10, 6},
                {10, 10}, {10, 14}, {14, 6}, {14, 10}
        };

        int[][] doubleWordCoordinates = {
                {2, 2}, {3, 3}, {4, 4}, {5, 5},
                {2, 14}, {3, 13}, {4, 12}, {5, 11},{8,8},
                {11, 5}, {12, 4}, {13, 3}, {14, 2},
                {11, 11}, {12, 12}, {13, 13}, {14, 14}
        };

        int[][] tripleWordCoordinates = {
                {1, 1}, {1, 8}, {1, 15}, {8, 1},
                {8, 15}, {15, 1}, {15, 8}, {15, 15}
        };

            
        for (int[] coord : doubleLetterCoordinates) {
            int x = coord[0] - 1;
            int y = coord[1] - 1;
            getSquare(x,y).setMultiplier(Multiplier.DOUBLE_LETTER);
        }

        for (int[] coord : tripleLetterCoordinates) {
            int x = coord[0] - 1;
            int y = coord[1] - 1;
            getSquare(x,y).setMultiplier(Multiplier.TRIPLE_LETTER);
        }

            
        for (int[] coord : doubleWordCoordinates) {
            int x = coord[0] - 1;
            int y = coord[1] - 1;
            getSquare(x,y).setMultiplier(Multiplier.DOUBLE_WORD);
        } 
        
        for (int[] coord : tripleWordCoordinates) {
            int x = coord[0] - 1;
            int y = coord[1] - 1;
            getSquare(x,y).setMultiplier(Multiplier.TRIPLE_WORD);
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
