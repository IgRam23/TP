package tp1.logic.lemmingRoles;

import java.util.HashMap;
import java.util.Map;
import tp1.logic.*;

public class LemmingRoleFactory {
    private static Map<String, LemmingRole> roles = new HashMap<>();
   
    static {
        roles.put("Parachuter", new ParachuterRole());
        roles.put("P", new ParachuterRole());
        roles.put("Walker", new WalkerRole());    
        roles.put("W", new WalkerRole());
    }

    public static LemmingRole parse(String input) {
        if (roles.containsKey(input)) {
            return roles.get(input); // Devuelve el rol existente
        }
        throw new IllegalArgumentException("[ERROR] Error: Unknown Role");
    }
}
 