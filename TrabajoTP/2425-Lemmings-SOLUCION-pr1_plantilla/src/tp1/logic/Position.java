package tp1.logic;

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
   
    
    
    //preguntar si tenemos que agregar alguna funcion de lemming/walkerrole aqui (algo dijo en la primera defensa)
}

