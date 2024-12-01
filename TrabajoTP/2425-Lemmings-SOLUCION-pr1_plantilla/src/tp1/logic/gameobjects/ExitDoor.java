package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.*;
import tp1.view.Messages;

public class ExitDoor extends GameObject{
	
	//Constructor
	public ExitDoor(GameWorld game, Position pos, GameObjectContainer container){
		super(game, pos);
		
	}

public  GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException{
		
		String[] words = line.trim().split("\\s+");
		
	    // Verificar si el comando corresponde a una pared ("wall")
	    if (!words[1].equalsIgnoreCase("ExitDoor")||!words[1].equalsIgnoreCase("ED")) {
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
        
	   //return new ExitDoor (game, position,container);
        return this;
	}
	
	
	
	
	
	
    @Override
    public String getIcon() {
        return Messages.EXIT_DOOR; 
    }
    
    @Override
    public void update() {
    }
    
    @Override
    public boolean isExit() {
    	return true;
    }
    
    @Override
    public boolean interactWith(Wall wall) {
        return false;
    }

    @Override
    public boolean interactWith(ExitDoor door) {
        // La puerta de salida no interactúa con otras puertas de salida
        return false;
    }

    @Override
    public boolean interactWith(Lemming lemming) {
        return lemming.interactWith(this); // No hubo interacción
    }
    
    @Override
    public boolean receiveInteraction(GameItem other) {
        return other.interactWith(this); 
    }
}
    
    
    
    

