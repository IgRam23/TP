package tp1.logic;

import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;
import tp1.logic.gameobjects.*;

import java.util.ArrayList; 

public class GameObjectContainer {

	private ArrayList<Lemming> lemmings;
	private int numLemmings;
	private ArrayList<Wall> walls;
	private int numWalls;
	private ArrayList<ExitDoor> doors;; 
	private int numDoors;
	 
	public GameObjectContainer() {
		this.lemmings = new ArrayList<>();
		this.walls = new ArrayList<>();
		this.doors = new ArrayList<>();
	}
	public void add(Lemming l) {
		lemmings.add(l);
		numLemmings ++;
	}
	public void add(Wall w) { 
		walls.add(w);
		numWalls++;
	}
	public void add(ExitDoor d) { 
		doors.add(d);
		numDoors++;
	}
	
	//Recorre la lista de lemmings y los va actualizando
    public void update() {
    	for(int i = 0; i < numLemmings ;i++) {
    		Lemming lemming = lemmings.get(i);
       		lemming.update(); 
    	}
    	//Eliminamos a los lemmings muertos
    	removeDead();
	}
    
    public String positionToString(Position p) {    	
    	if(isSolid(p)) {
    		return Messages.WALL;
    	} else {
    		StringBuilder respuesta = new StringBuilder();
    		int cont = 0;
    		
    		if(isExit(p)) {
    			respuesta.append(Messages.EXIT_DOOR);
    			cont++;
    		}
    		
    		int indice = isLemming(p, 0);
    		
    		if(indice != -1) {
        		Lemming l = Lem(p, indice);
        		respuesta.append(l.getIcon());
        		cont++;
        		indice = isLemming(p, indice + 1);
        		if(indice != -1 && cont < 2) {
        			Lemming l2 = Lem(p, indice);
            		respuesta.append(l2.getIcon());
        		}
    		}
    		
    		if(cont == 0)
    			respuesta.append(" ");
    		
    		return respuesta.toString();
    	}    	
    }
    
    //Quitamos los lemmings muertos
    private void removeDead() {
        ArrayList<Lemming> lemmingsToRemove = new ArrayList<>();
        
        for (Lemming lemming : lemmings) {
            if (!lemming.isAlive()) {
                lemmingsToRemove.add(lemming);
            }
        }
        
     // Quitamos los lemmings muertos de la lista original
	    for (Lemming lemming : lemmingsToRemove) {
	        lemmings.remove(lemming);
	        numLemmings--;   // Reduce el número total de lemmings

	    }

    }
    
    public Lemming getLemmingAt(Position pos) {          
        for (Lemming lemming : lemmings) {
            if (lemming.isInPosition(pos)) { 
                return lemming;
            }
        }
        return null; //si no encuentra ningun lemming
    }
    
    //Verificamos si hay una wall en la pos p
    public boolean isSolid(Position p) {
    	for(int i = 0; i < walls.size(); i++){
    		if(walls.get(i).isInPosition(p))
    			return true;
    	}
    	return false;
    }
    
    //Verificamos si hay una ExitDoor en la pos p
    public boolean isExit(Position p) {
    	for(int i = 0; i < doors.size(); i++){
    		if(doors.get(i).isInPosition(p))
    			return true;
    	}
    	
    	return false;
    }
    
    //Verificamos si hay un lemming en la pos p
    public int isLemming(Position p, int ind) {
    	for(int i = ind; i < lemmings.size(); i++) {
    		if(lemmings.get(i).isInPosition(p)) {
    			return i;
    		}
    	}
    	return -1;
    }
    //Devuelve el lemming de la pos p
    public Lemming Lem(Position p,int i) {
    		return lemmings.get(i);
   
    }
   
	
}