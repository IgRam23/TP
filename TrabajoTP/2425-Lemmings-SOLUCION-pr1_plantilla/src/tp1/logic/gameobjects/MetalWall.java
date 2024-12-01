package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class MetalWall extends Wall {

	public MetalWall (GameWorld game, Position pos){
		super(game, pos);
	}
	
	@Override
	public  GameObject parse(String line, GameWorld game) throws ObjectParseException, OffBoardException{
		
		String[] words = line.trim().split("\\s+");
		
	    // Verificar si el comando corresponde a una pared ("wall")
	    if (!words[1].equalsIgnoreCase("MetalWall")||!words[1].equalsIgnoreCase("MW")) {
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
        
	    return new MetalWall(game, position);
	}
	
	
	
	@Override
	public boolean isSolid() {
		return true;
	}

    @Override
    public String getIcon() {
        return Messages.METALWALL; // Representación de la pared
    }

    @Override 
    public void update() {
    	
    }
    
    public boolean receiveInteraction(GameItem other) {
        return false; 
    }
	
    @Override
    public boolean interactWith(ExitDoor door) {
        return false;
    }
    @Override
    public boolean interactWith(Wall wall) {
        return false;
    }
    
    public boolean interactWith(Lemming lemming) {
        return false;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
}
