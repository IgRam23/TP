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
	
	//Si el elmming esta vivo, lo mata
	public void die() {
        if (isAlive) {
            isAlive = false;
            game.lemmingHasDied();  // Notifica al juego que este lemming ha muerto
        }
    }
	
	//Devuelve cuantos ciclos lleva cayendo el lemming
	public int getFallDistance() {
		return fallDistance;
	}
	
	// Verifica si el lemming esta vivo
	public boolean isAlive() {
		return isAlive;
	}
	
	// Verifica si el lemming esta en el aire
	public boolean isInAir() {
		Position pos_abajo = new Position(pos.getCol() + 1, pos.getRow());
	    return !game.isSolid(pos_abajo); 
	}

	// Establece la dir en la que se tiene que mover el lemming
	public void walkOrFall() {
		
		if(isInExit()) {
			exit();
			return;
		}
		else if (isInAir()) {
	        
			fallDistance++;
			//rol.handleFall(this); //muere si cae mas de 3 pisos
						
	        if (pos.getCol() + 1 >= Game.DIM_Y) { // Verifica si la posición abajo está fuera del tablero
	            die(); // Llama al método que mata al lemming 
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
			//return;
		}
		else if (isInWall()) {
			dir_anterior = dir;
			dir = (dir == Direction.RIGHT) ? Direction.LEFT : Direction.RIGHT;
         
        }
		
		this.pos = rol.move(dir);
	}
	
	
	//Verifica si el lemming tiene que cambiar de direccion
	public boolean isInWall() {
	    if (dir == Direction.RIGHT) {
	        Position pos_derech = new Position(pos.getCol(), pos.getRow() + 1);
	        return (pos_derech.getRow() >= Game.DIM_X || game.isSolid(pos_derech));
	    } else if (dir == Direction.LEFT) { 
	        Position pos_izq = new Position(pos.getCol(), pos.getRow() - 1);
	        return (pos_izq.getRow() < 0 || game.isSolid(pos_izq));
	    }
	    return false; // No hay pared si no se está moviendo
	}
	
	public boolean isInExit() {
	    return game.isExit(pos);
	}
	
	public void exit() {
		
	    if (isAlive) {
	        game.incrementLemmingsExit(); // Incrementa el número de lemmings que han salido
	        isAlive = false; // Marcar el lemming como muerto porque ha salido
	        if (game.numLemmingsExit() >= game.numLemmingsToWin()) {
	            game.setFinished(true);    // Solo termina el juego si han salido suficientes lemmings
	        }
	    }
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
		else if (this.dir == Direction.DOWN) {
			if(this.dir_anterior == Direction.LEFT) {
				return Messages.LEMMING_LEFT;
			}
			else if(this.dir_anterior == Direction.RIGHT) {
				return Messages.LEMMING_RIGHT;
			}
		}
		return Messages.LEMMING_BLOCKING;
	}
	
}