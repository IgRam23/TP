package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.exceptions.RoleParseException;
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
    
	public  GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException{
			
		 //1. Se corresponde con un lemming: si no, null
        //2. De la 1ª palabra se puede obtener una posición
        //3. De la 3ª se puede obtener una dirección VÁLIDA
        //4. De la 4ª se puede obtener un número
        //5. De la 5ª palabra se puede obtener un rol
        //En ese caso, devolver NUEVO lemming con esos atributos
		
		String[] words = line.trim().split("\\s+");

		
		
	    // Verificar si el comando corresponde a una pared ("wall")
	    if (!words[1].equalsIgnoreCase("Lemming")||!words[1].equalsIgnoreCase("L")) {
	        return null; // No corresponde a este tipo de objeto
	    }

	    if (words.length < 5) {
	        throw new ObjectParseException("Incorrect parameter count for Lemming.");
	    }
	    
	    
	    //Obtenemos la posicion
	    String coordinates = words[0].substring(1, words[0].length() - 1);
        String[] coords = coordinates.split(",");  // ["3", "4"]
        int row = Integer.parseInt(coords[0]);  // 3
        int col = Integer.parseInt(coords[1]);  // 4
        
        Position position = new Position(row,col);
        
       
        //Obtenemos el rol
        //LemmingRole role = LemmingRoleFactory.parse(words[4]);
     
      
        Lemming lemming = new Lemming (game, position,container,rol);
        
        //Obtenemos la direccion
        // lemming.dir = words[2];
        
        //Obtenemos la caida
        lemming.fallDistance = Integer.parseInt(words[3]);
       
       
 
        /*
        if (!game.isValidPosition(position)) {
        	        throw new OffBoardException("Position is off-board: " + position);
        }*/
        
	    return lemming;
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