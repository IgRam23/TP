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
	
    //Actualizar el estado del juego
	public void update() {		
	    if (isAlive) {
	      rol.play(this);     
	    }
	}
	
	//Mata a un lemming
	public void die() {
        if (isAlive) {
            isAlive = false;
            game.lemmingHasDied();  
        }
    }
	
	//Devuelve cuantos ciclos lleva cayendo el lemming
	public int getFallDistance() {
		return fallDistance;
	}
	
	//Devuelve un booleano en funcion de si el lemming esta vivo
	public boolean isAlive() {
		return isAlive;
	}
	
	//Devuelve un booleano indicando si el lemming esta en el aire
	public boolean isInAir() {
		Position pos_abajo = new Position(pos.getCol() + 1, pos.getRow());
	    return !game.isSolid(pos_abajo); 
	}
	
	//Devuelve un booleano indicando si el lemming tiene que cambiar de direccion (se va a chocar con una pared)
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
	
	//Devuelve un booleano indicando si el lemming se encuentra en la posicion de salida 
	public boolean isInExit() {
	    return game.isExit(pos);
	}

	//Establece la direccion en la que se tiene que mover el lemming
	public void walkOrFall() { 
		if(isInExit()) {
			exit();
			return;
		}
		else if (isInAir()) { //si esta en el aire 
			fallDistance++;
	        if (pos.getCol() + 1 >= Game.DIM_Y) { //comprobamos si la posición abajo está fuera del tablero para ver si hay que llamar a die()
	            die();  
	            return; 
	        }	        
	        
	        if(dir != Direction.DOWN) {
	        	dir_anterior = dir;
	        }
	        
	        dir = Direction.DOWN;
		}
		else if(dir == Direction.DOWN) { //si esta empezando a caer
			rol.handleFall(this);
			dir = dir_anterior;
		}
		else if (isInWall()) { //si se va a chocar con una pared
			dir_anterior = dir;
			dir = (dir == Direction.RIGHT) ? Direction.LEFT : Direction.RIGHT;
        }
		
		this.pos = rol.move(dir);
	}
	
	//Actualiza el estado del lemming y del juego si hay un nuevo lemming que sale por la puerta
	public void exit() {
	    if (isAlive) {
	        game.incrementLemmingsExit(); // Incrementa el número de lemmings que han salido
	        isAlive = false; // Marcar el lemming como muerto porque ha salido
	        if (game.numLemmingsExit() >= game.numLemmingsToWin()) {
	            game.setFinished(true);    // Solo termina el juego si han salido suficientes lemmings
	        }
	    }
    }
	
	//Verifica si el lemming esta en la posicion pos 
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