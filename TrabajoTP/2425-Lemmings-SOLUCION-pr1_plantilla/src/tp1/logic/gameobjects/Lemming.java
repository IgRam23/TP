package tp1.logic.gameobjects;

import tp1.logic.*; 

import tp1.view.Messages;

import tp1.logic.lemmingRoles.*;

public class Lemming extends GameObject{
	
	private Direction dir;
	private Direction dir_anterior;
	private boolean isAlive;
	WalkerRole rol;
	private int fallDistance;
	GameObjectContainer container;
	
    public Lemming(GameWorld game, Position pos, GameObjectContainer container){
    	super(game, pos);
    	this.container = container;
    	this.rol = WalkerRole(pos);  
        this.dir = Direction.RIGHT;
        this.dir_anterior = Direction.RIGHT;
        this.isAlive = true; // Inicialmente, el lemming está vivo
        this.fallDistance = 0;
	}
	
    //Creo un estado WalkerRole
    private WalkerRole WalkerRole(Position pos) {
    	return new WalkerRole(pos); 
	}

    //Actualizar el estado del juego
    @Override
	public void update() {		
	    if (isAlive) {
	      rol.play(this);     
	    }
	}    
    
    @Override
    public boolean isLemming() {
    	return true;
    }

    @Override
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
  		return Messages.EMPTY; 
  	}
    
    @Override
    public boolean isSolid() {
    	return false;
    }
    
    @Override
    public boolean isExit() {
    	return false;
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
		Position pos_abajo = new Position(pos.getCol(), pos.getRow() + 1);
	    return !container.isSolidAt(pos_abajo, this); 
	}
	
	//Devuelve un booleano indicando si el lemming tiene que cambiar de direccion (se va a chocar con una pared)
	public boolean isInWall() {
	    if (dir == Direction.RIGHT) {
	        Position pos_derech = new Position(pos.getCol() + 1, pos.getRow());
	        return (pos_derech.getCol() >= Game.DIM_X || container.isSolidAt(pos_derech, this));
	    } else if (dir == Direction.LEFT) { 
	        Position pos_izq = new Position(pos.getCol() - 1, pos.getRow());
	        return (pos_izq.getCol() < 0 || container.isSolidAt(pos_izq, this));
	    }
	    return false; // No hay pared si no se está moviendo
	}
	
	//Devuelve un booleano indicando si el lemming se encuentra en la posicion de salida 
	public boolean isInExit() {
	    return container.isExitAt(pos); 
	}

	//Establece la direccion en la que se tiene que mover el lemming
	public void walkOrFall() { 
		if(isInExit()) {
			exit();
			return;
		}
		else if (isInAir()) { //si esta en el aire 
			fallDistance++;
	        if (pos.getRow() + 1 >= Game.DIM_Y) { //comprobamos si la posición abajo está fuera del tablero para ver si hay que llamar a die()
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
			return;
        }
		
		this.pos = rol.move(dir);   
	}
	

	//Actualiza el estado del lemming y del juego si hay un nuevo lemming que sale por la puerta
	public void exit() {
	    if (isAlive) {
	        game.incrementLemmingsExit(); 
	        isAlive = false; 
	        if (game.numLemmingsExit() >= game.numLemmingsToWin()) {
	            game.setFinished(true);    
	        }
	    }
    }
	
	//Verifica si el lemming esta en la posicion pos 
	public boolean isInPosition(Position pos) {
		return this.pos.equals(pos);
	}
	
}