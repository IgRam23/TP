package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.*;
import tp1.view.Messages;

public class GameObjectContainer {
	private List<GameObject> objects;
	
	
	public GameObjectContainer() {
		objects = new ArrayList<GameObject>();
	}
	
	// Only one add method (polymorphism)
	public void add(GameObject object) {
		objects.add(object);
	}
	
	
	//Recorre la lista de lemmings y los va actualizando
    public void update() {
    	for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            object.update(); 
        }
    	removeDead();
	}
    
    //Convierte los distintos elementos del juego a simbolos string
	public String positionToString(Position p) {     
	    StringBuilder respuesta = new StringBuilder();
	    int cont = 0; // Contador para los elementos encontrados
	
	    // Primero, verificamos si hay una pared en la posición
	    for (GameObject obj : objects) {
	        if (obj.isInPosition(p)) {
	            if (obj.isSolid()) { // Si hay una pared
	                return Messages.WALL; // Retornamos el icono de la pared
	            }
	            // Si es una puerta de salida
	            if (obj.isExit()) {
	                respuesta.append(Messages.EXIT_DOOR); 
	                cont++; // Aumentamos el contador por la puerta
	            }
	        }
	    }
	
	    // Ahora buscamos lemmings en la posición
	    int indice = isLemming(p, 0); 
	
	    if (indice != -1) { // Si hay al menos un lemming
	        Lemming l = Lem(p);
	        respuesta.append(l.getIcon());
	        cont++; // Aumentamos el contador de lemmings
	
	        // Miramos si hay un segundo lemming en la misma posición
	        indice = isLemming(p, indice + 1); 
	        if (indice != -1 && cont < 2) { // Solo agregamos si no hemos alcanzado 2 elementos
	            Lemming l2 = Lem(p);
	            respuesta.append(l2.getIcon());
	        }
	    }
	
	    // Si no hemos encontrado nada, añadimos un espacio
	    if (cont == 0) {
	        respuesta.append(" ");
	    }
	
	    return respuesta.toString();
	}
	
	//Quitamos los lemmings muertos
    private void removeDead() {
    	List<GameObject> lemmingsToRemove = new ArrayList<>();

        for (GameObject obj : objects) {

            if (obj.isLemming()) { //comprobacion de que es un lemming
                Lemming lemming = (Lemming) obj; 
                if (!lemming.isAlive()) {
                    lemmingsToRemove.add(obj);
                }
            }
        }

        for (GameObject lemming : lemmingsToRemove) {
            objects.remove(lemming); 
        }
        
    }
    
    //Comprueba si el objeto es una pared
    public boolean isSolidAt(Position pos, GameObject sourceObject) {
        for (GameObject obj : objects) {
            if (obj.isInPosition(pos) && obj.isSolid()) {
                return true; 
            }
        }
        return false; 
    }

    //Comprueba si el objeto es una puerta de salida
    public boolean isExitAt(Position pos) {
        for (GameObject obj : objects) {
            if (obj.isInPosition(pos) && obj.isExit()) {
                return true; 
            }
        }
        return false; 
    }
    
    
    //Devuelve el índice del lemming que ocupa la posición en la lista de objetos (si lo hay), sino devuelve -1
    public int isLemming(Position p, int ind) {
        for (int i = ind; i < objects.size(); i++) {
            GameObject obj = objects.get(i);

            if (obj.isInPosition(p) && obj.isLemming()) {
                return i; 
            }
        }
        return -1; 
    }
    
    //Devuelve el Lemming en la posición indicada, si lo hay; sino, devuelve null
    private Lemming Lem(Position p) {
        for (GameObject obj : objects) {
            if (obj.isInPosition(p) && obj.isLemming()) { 
                return (Lemming) obj; 
            }
        }
        return null; 
    }    

    
    
}