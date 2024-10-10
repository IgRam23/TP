package tp1.logic;

/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	private int col;
	private int row;

	// Constructor
    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    
    public boolean equals(Position pos) {
    	return pos.row == row && pos.col == col;
    }
    public Position andaDerch() {
        return new Position(this.col + 1, this.row);
    }

    // Retorna una nueva posición movida a la izquierda
    public Position andaIzq() {
        return new Position(this.col - 1, this.row);
    }
    
    

}

