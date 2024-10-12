package tp1.logic.gameobjects;

import tp1.logic.*; //con el asterisco me mete las carpetas enteras

import tp1.view.Messages;

import tp1.logic.lemmingRoles.*;

public class Lemming {
	
	private Position pos; 
	private Direction dir;
	private Direction dir_anterior;
	private boolean isAlive;
	private WalkerRole rol;
	private Game game;
	private int fallDistance;
	
    public Lemming(Game game, Position pos){
    	this.game = game;
        this.pos = pos;
        this.dir = Direction.RIGHT;
        this.dir_anterior = Direction.RIGHT;
        this.isAlive = true; // Inicialmente, el lemming está vivo
        this.rol = new WalkerRole(pos);
        this.fallDistance = 0;
	}
	
	public void update() {
		
	    if (isAlive) {
	      rol.play(this);     
	    }
	}
	
	//ns si esta bien 
	public Game getGame() {
        return game;
    }
	
	//Devuelve cuanros ciclos lleva cayendo el lemming
	public int getFallDistance() {
		return fallDistance;
	}
	
	//Muere el lemming :(
	public void dead() {
		isAlive = false;
	}
	
	// Verifica si el lemming esta vivo
	public boolean isAlive() {
		return isAlive;
	}
	
	// Verifica si el lemming esta en el aire
	public boolean isInAir() {
	    // Calcula la posición inmediatamente debajo del lemming
	    Position pos_abajo = new Position(pos.getCol() + 1, pos.getRow());
	  
	    // Verifica si hay una pared en la posición debajo
	    return !game.getContainer().isSolid(pos_abajo); // Devuelve true si no hay una pared
	}

	// Establece la dir en la que se tiene que mover el lemming
	public void walkOrFall() {
		
		if(isInExit()) {
			game.incrementLemmingsExit();
			//dead();//esto esta mal, es para q no sigan saliendo
			return;
		}
		else if (isInAir()) {
	        
			fallDistance++;
			
	        if (pos.getCol() + 1 >= Game.DIM_Y) { // Verifica si la posición abajo está fuera del tablero
	            dead(); // Llama al método que mata al lemming
	            return; 
	        }
	        if(dir != Direction.DOWN) {
	        	dir_anterior = dir;
	        }
	        dir = Direction.DOWN;
		}
		else if(dir == Direction.DOWN) {
			rol.handleFall(this);
			dir = dir_anterior;
		}
		else if (isInWall()) {
			   // Cambia la dirección si hay una pared
			dir_anterior = dir;
			dir = (dir == Direction.RIGHT) ? Direction.LEFT : Direction.RIGHT;
         
        }
		
		this.pos = rol.move(dir);
	}
	
	
	//Verifica si el lemming tiene que cambiar de direccion
	public boolean isInWall() {
	    if (dir == Direction.RIGHT) {
	        Position pos_derech = new Position(pos.getCol(), pos.getRow() + 1);
	        return (pos_derech.getRow() >= Game.DIM_X || game.getContainer().isSolid(pos_derech));
	    } else if (dir == Direction.LEFT) {
	        Position pos_izq = new Position(pos.getCol(), pos.getRow() - 1);
	        return (pos_izq.getRow() < 0 || game.getContainer().isSolid(pos_izq));
	    }
	    return false; // No hay pared si no se está moviendo
	}
	
	public boolean isInExit() {
	        return game.getContainer().isExit(pos);
	}
	
	
	//Verifica si el lemming esta en pos 
	public boolean isInPosition(Position pos) {
		return this.pos.equals(pos);
	}
	
	//Dibuja el lemming
	public String getIcon() {
		if (this.dir == Direction.RIGHT)
			return Messages.LEMMING_RIGHT; 
		else if (this.dir == Direction.LEFT)
			return Messages.LEMMING_LEFT;
		else if (this.dir == Direction.DOWN)
			return Messages.LEMMING_PARACHUTE;
		return Messages.LEMMING_BLOCKING;
	}
	
	//Ns que hace
	public String toString() {
		
		
		return " ";
	}
	
}