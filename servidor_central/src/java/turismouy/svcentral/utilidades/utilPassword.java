package turismouy.svcentral.utilidades;
import org.mindrot.jbcrypt.BCrypt;

public class utilPassword {
    
    // Método para encriptar una contraseña
    public static String encriptar(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Método para verificar si una contraseña coincide con su versión encriptada
    public static boolean checkPassword(String password, String passwordEncriptada) {
        return BCrypt.checkpw(password, passwordEncriptada);
    }
}