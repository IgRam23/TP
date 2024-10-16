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
    	removeDead();
	}
    
    //Convierte los distintos elementos del juego a simbolos string
    public String positionToString(Position p) {    	
    	
    	if(isSolid(p)) { //hay una pared
    		return Messages.WALL;
    	} else { //evaluamos los elementos que pueden coincidir en una posicion
    		
    		StringBuilder respuesta = new StringBuilder();
    		int cont = 0;
    		
    		if(isExit(p)) { //hay una puerta
    			respuesta.append(Messages.EXIT_DOOR); 
    			cont++; //aumentamos el contador
    		}
    		
    		int indice = isLemming(p, 0); 
    		
    		if(indice != -1) { //hay por lo menos un lemming en esa posicion
    			
        		Lemming l = Lem(p, indice);
        		respuesta.append(l.getIcon());
        		cont++; //aumentamos el contador de lemmings
        		
        		indice = isLemming(p, indice + 1); //miramos a ver si hay un segundo lemming
        		
        		if(indice != -1 && cont < 2) { //si hay un lemming tambien en esa posicion se tiene que comprobar que no hay ya mas de dos elementos (lemming + puerta)
        										//y si no los hay entonces puede haber dos lemmings en la misma posicion
        			Lemming l2 = Lem(p, indice);
            		respuesta.append(l2.getIcon());
            		
        		}
    		}
    		
    		if(cont == 0) //no hay nada en esa posicion
    			respuesta.append(" ");
    		
    		return respuesta.toString();
    	}    	
    }
    
    //Quitamos los lemmings muertos
    private void removeDead() {
        ArrayList<Lemming> lemmingsToRemove = new ArrayList<>(); //usamos una lista temporal (variable local)
        
        for (Lemming lemming : lemmings) {
            if (!lemming.isAlive()) {
                lemmingsToRemove.add(lemming);   //incluimos a los lemmings en la lista temporal
            }
        }
        
	    for (Lemming lemming : lemmingsToRemove) { //quitamos a los lemmings muertos de la lista original
	        lemmings.remove(lemming);
	        numLemmings--;   
	    }
    }
    
    //Deuvelve el lemming que se encuentra en una posicion especifica
    public Lemming getLemmingAt(Position pos) {          
        for (Lemming lemming : lemmings) {
            if (lemming.isInPosition(pos)) { 
                return lemming;
            }
        }
        return null; //si no encuentra ningun lemming
    }
    
    //Devuelve un booleano sobre si hay una pared en la pos p
    public boolean isSolid(Position p) {
    	for(int i = 0; i < walls.size(); i++){
    		if(walls.get(i).isInPosition(p))
    			return true;
    	}
    	return false;
    }
    
    //Devuelve un booleano sobre si hay una ExitDoor en la pos p
    public boolean isExit(Position p) {
    	for(int i = 0; i < doors.size(); i++){
    		if(doors.get(i).isInPosition(p))
    			return true;
    	}
    	
    	return false;
    }
    
    //Devuelve el indice de la posicion en la lista que ocupa un lemming en la lista de lemmings (si lo hay), sino devuelve -1
    public int isLemming(Position p, int ind) {
    	for(int i = ind; i < lemmings.size(); i++) {
    		if(lemmings.get(i).isInPosition(p)) {
    			return i;
    		}
    	}
    	return -1;
    }
    
    //Devuelve el lemming que se encuentra en la pos i de la lista de lemmings
    public Lemming Lem(Position pos, int i) {
    		return lemmings.get(i);
    }
   
	
}