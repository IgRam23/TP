package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.*;

public class GameObjectContainer {
	private List<GameObject> objects;
	private int numLemmings;
    private int numWalls;
    private int numDoors;	
	
	
	public GameObjectContainer() {
		objects = new ArrayList<GameObject>();
		numLemmings = 0;
        numWalls = 0;
        numDoors = 0;
	}
	
	// Only one add method (polymorphism)
	public void add(GameObject object) {
		objects.add(object);

        if (object instanceof Lemming) {
            numLemmings++;
        } else if (object instanceof Wall) {
            numWalls++;
        } else if (object instanceof ExitDoor) {
            numDoors++;
        }
	}
	
	
	public boolean isSolid(Position p) {
	    for (GameObject obj : objects) {
	        if (obj instanceof Wall && obj.isInPosition(p)) {
	            return true; // Retorna true si hay un muro en la posición
	        }
	    }
	    return false; // No hay muros
	}

	public boolean isExit(Position p) {
	    for (GameObject obj : objects) {
	        if (obj instanceof ExitDoor && obj.isInPosition(p)) {
	            return true; // Retorna true si hay una exitDoor en la posición
	        }
	    }
	    return false; // No hay exitDoor
	}

	
	//Lista de objetos convertida a representacion en cadena
	@Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (GameObject obj : objects) {
            result.append(obj.getIcon()).append("\n");
        }
        return result.toString();
    }
	
	//Recorre la lista de lemmings y los va actualizando
    public void update() {
    	for (GameObject obj : objects) {
            if (obj instanceof Lemming) {
                ((Lemming) obj).update();
            }
        }
    	removeDead();
	}
    
    //Convierte los distintos elementos del juego a simbolos string
    public String positionToString(Position p) {    	
    	StringBuilder respuesta = new StringBuilder();
        int cont = 0;

        for (GameObject obj : objects) {
            if (obj.isInPosition(p)) {
                respuesta.append(obj.getIcon()); 
                cont++;
            }
        }

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

        // Recalcular el número de lemmings restantes
        numLemmings = 0; // Reiniciamos el contador
        for (GameObject obj : objects) {
            if (obj instanceof Lemming) {
                numLemmings++; // Contamos solo los lemmings
            }
        }
    }
    
   
    public int getNumLemmings() {
        return numLemmings;
    }

    public int getNumWalls() {
        return numWalls;
    }

    public int getNumDoors() {
        return numDoors;
    }
	
}