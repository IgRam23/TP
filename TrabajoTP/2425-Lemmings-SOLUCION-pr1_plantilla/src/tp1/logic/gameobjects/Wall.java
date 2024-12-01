package tp1.logic.gameobjects;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.*;
import tp1.view.Messages;

public class Wall extends GameObject{ 
	//Constructor
	public Wall (GameWorld game, Position pos){
		super(game, pos);
	}

	public  GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException{
		
		String[] words = line.trim().split("\\s+");

		
	    // Verificar si el comando corresponde a una pared ("wall")
	    if (!words[1].equalsIgnoreCase("Wall")||!words[1].equalsIgnoreCase("W")) {
	        return null; // No corresponde a este tipo de objeto
	    }
	    
	    if (words.length < 2) {
	        throw new ObjectParseException("Incorrect parameter count for Wall.");
	    }
	    
	    

	    String coordinates = words[0].substring(1, words[0].length() - 1);
        String[] coords = coordinates.split(",");  // ["3", "4"]
        int row = Integer.parseInt(coords[0]);  // 3
        int col = Integer.parseInt(coords[1]);  // 4
        
        Position position = new Position(row,col);
        
        /*
        if (!game.isValidPosition(position)) {
        	        throw new OffBoardException("Position is off-board: " + position);
        }*/
        
	    return new Wall(game, position);
	}
	

    @Override
    public String getIcon() {
        return Messages.WALL; // Representación de la pared
    }
    
    @Override
    public void update() {
    }
    
    @Override
    public boolean isSolid() {
    	return true;
    }

    @Override
    public boolean interactWith(Wall wall){
        // Los muros no interactúan con otros muros, por lo que devolveremos false.
        return false;
    }

    @Override
    public boolean interactWith(ExitDoor door) {
        // Definir la lógica de interacción con una puerta si es necesario
        return false;
    }

    @Override
    public boolean interactWith(Lemming lemming) {
    	
    	
    	if (lemming.isInPosition(this.pos)) {
           
    		this.isAlive = false;
    		return true;
        }
        return false; // No hubo interacción
    
    }
    
    public boolean receiveInteraction(GameItem other) {
        return other.interactWith(this); 
    }
    
    
    public void killWall(){
    	
    	this.isAlive = false;
    }
    
    public Position getPos() {
    	Position pos = new Position(this.pos.getCol(),this.pos.getRow());
    	return pos;
    }
    
    
    
}