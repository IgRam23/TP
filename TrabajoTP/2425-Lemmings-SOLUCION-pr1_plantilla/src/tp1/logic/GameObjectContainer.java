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

        // Iteramos sobre todos los objetos en el contenedor
        for (GameObject obj : objects) {
            // Si el objeto es un Lemming y no está vivo, lo añadimos a la lista para eliminar
            if (obj instanceof Lemming) {
                Lemming lemming = (Lemming) obj; // Convertimos a Lemming
                if (!lemming.isAlive()) {
                    lemmingsToRemove.add(obj);
                }
            }
        }

        // Ahora eliminamos los lemmings muertos de la lista de objetos
        for (GameObject lemming : lemmingsToRemove) {
            objects.remove(lemming); // Removemos el lemming muerto
        }
        
    }
    
    public boolean isSolidAt(Position pos, GameObject sourceObject) {
        for (GameObject obj : objects) {
            if (obj != sourceObject && obj.isInPosition(pos) && obj.isSolid()) {
                return true; // Hay un objeto sólido en esa posición
            }
        }
        return false; // No hay objeto sólido en esa posición
    }

    
    public boolean isExitAt(Position pos) {
        for (GameObject obj : objects) {
            if (obj.isInPosition(pos) && obj.isExit()) {
                return true; // Hay un objeto que es una salida en esa posición
            }
        }
        return false; // No hay objeto que sea una salida en esa posición
    }
    
    
 // Devuelve el índice del lemming que ocupa la posición en la lista de objetos (si lo hay), sino devuelve -1
    public int isLemming(Position p, int ind) {
        for (int i = ind; i < objects.size(); i++) {
            GameObject obj = objects.get(i);
            // Comprobamos si el objeto es un lemming y si está en la posición
            if (obj instanceof Lemming && obj.isInPosition(p)) {
                return i; // Retornamos el índice si encontramos un lemming en la posición
            }
        }
        return -1; // Si no encontramos, retornamos -1
    }

 // Devuelve el Lemming en la posición indicada, si lo hay; sino, devuelve null
    private Lemming Lem(Position p) {
        for (GameObject obj : objects) {
            if (obj instanceof Lemming && obj.isInPosition(p)) {
                return (Lemming) obj; // Hacemos el casting a Lemming
            }
        }
        return null; // Si no se encuentra, devolvemos null
    }

    
    
}