package tp1.logic.gameobjects;

import tp1.logic.*; 

import tp1.view.Messages;

import tp1.logic.lemmingRoles.*;

public class Lemming extends GameObject{
	
	private Direction dir;
	private Direction dir_anterior;
	private boolean isAlive;
	private LemmingRole rol;
	private int fallDistance;
	private GameObjectContainer container;
	
    public Lemming(GameWorld game, Position pos, GameObjectContainer container, LemmingRole role){
    	super(game, pos);
    	this.container = container;
        this.dir = Direction.RIGHT;
        this.dir_anterior = Direction.RIGHT;
        this.isAlive = true; // Inicialmente, el lemming está vivo
        this.fallDistance = 0;
        this.rol = role;
	}
    
    
    
    public void disableRole() {
    	this.rol = new WalkerRole();
    }

    //Actualizar el estado del juego
    @Override
	public void update() {		
	    if (isAlive) {
	      this.rol.play(this);     
	    }
	}    

    @Override
    public boolean setRole(LemmingRole newRole) {
    	if(newRole != null && newRole != rol) {
    		rol = newRole;
    		rol.start(this);
    		return true;
    	}
    	return false; 
    } 
    

  //Dibuja el lemming
    @Override
  	public String getIcon() {
    	return rol.getIcon(this);
    	/*
    	if(this.rol.getRoleType() == "WalkerRole") {
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
    		
    	} else if(this.rol.getRoleType() == "Parachuter") {
    		return Messages.LEMMING_PARACHUTE;
    	}  		
    	
  		return Messages.EMPTY; */
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
	
	public boolean isInSoftFloor() {
		Position pos_debajo = new Position(pos.getCol(), pos.getRow() - 1);
		return(container.isSolidAt(pos_debajo, this));
	}
	
	
	//Devuelve un booleano indicando si el lemming se encuentra en la posicion de salida 
	public boolean isInExit() {
	    return container.isExitAt(pos); 
	}
	public void fall(){
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
	//Establece la direccion en la que se tiene que mover el lemming
	public void walkOrFall() { 
		
		if(isInExit()) {
			exit();
			return;
		}
		else if (isInAir()) { //si esta en el aire 
			fall();
			
		}
		else if(dir == Direction.DOWN) { //si esta empezando a caer
			rol.handleFall(this);
			
			dir = dir_anterior;
		}
		
		if (isInWall()) { //si se va a chocar con una pared
			dir_anterior = dir;
			dir = (dir == Direction.RIGHT) ? Direction.LEFT : Direction.RIGHT;
			return;
        }
		
		pos = move(dir);  
	} 
	   
    //Mueve al lemming
	public Position move(Direction dir) {       
		int newCol = pos.getCol() + dir.getX(); 
        int newRow = pos.getRow() + dir.getY(); 
        
        if(newCol > 0 && newCol < Game.DIM_X ) {
        	 pos = new Position(newCol, newRow); 
        }else {
        	if(dir == Direction.RIGHT) {
        		dir = Direction.LEFT;
        	}else if(dir == Direction.LEFT){
        		dir = Direction.RIGHT;
        	}
        }
        
        return pos; 
    }

	//Actualiza el estado del lemming y del juego si hay un nuevo lemming que sale por la puerta
	public void exit() {
	    if (this.isAlive) {
	        game.incrementLemmingsExit(); 
	        this.isAlive = false; 
	        /*if (game.numLemmingsExit() >= game.numLemmingsToWin()) {
	            game.setFinished(true);    
	        }*/
	    }
    }
	
	//Verifica si el lemming esta en la posicion pos 
	public boolean isInPosition(Position pos) {
		return this.pos.equals(pos);
	}
	
	public boolean receiveInteraction(GameItem other) {
	    return other.interactWith(this);
	}
	
	@Override
	public boolean interactWith(Wall wall) {
		return rol.interactWith(wall,this);
		
	}
	
	@Override
	public boolean interactWith(ExitDoor door) {
		return false;
	}
	
	@Override
	public boolean interactWith(Lemming lemming) {
	   
	    return false;
	}
	
	
	
	
	//ns si es correcto
	public void increaseFallDistance() {
		this.fallDistance++;
	}
	
	public void changePreviousDir(Direction dir) {
		
		this.dir_anterior = dir;
	}
	
    public void changeDir(Direction dir) {
		
		this.dir = dir;
	}
	
	public Direction getDirection() {
		
		return this.dir;
	}
	
    public Direction getPreviousDirection() {
		
		return this.dir_anterior;
	}
    
     public void changeFall(int newFall) {
 		
 		this.fallDistance = newFall;
 	}
}