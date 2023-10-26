package turismouy.svcentral.interfaces;

import java.time.LocalDate;
import java.util.List;
import turismouy.svcentral.datatypes.dataUsuario;
import turismouy.svcentral.excepciones.ParametrosInvalidosExcepcion;
import turismouy.svcentral.excepciones.UsuarioNoExisteExcepcion;
import turismouy.svcentral.excepciones.UsuarioYaExisteExcepcion;

public interface IUsuarioController {

     /**
     * Caso de uso:
     * @implNote Registro :D
     * @param nickname String (unico)
     * @param nombre String
     * @param apellido String
     * @param email String (unico)
     * @param nacionalidad String
     * @param nacimiento LocalDate
     */
    public abstract void registrarTurista(String nickname, String nombre, String apellido, String email, String nacionalidad, LocalDate nacimiento) throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion;
    public abstract void registrarTurista(String nickname, String nombre, String apellido, String email, String nacionalidad, LocalDate nacimiento, String password) throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion;

    /**
     * Caso de uso:
     * @param nickname String
     * @return dataUsuario (DataType)
     * @throws UsuarioNoExisteExcepcion
     */
    public abstract dataUsuario mostrarInfo(String nickname) throws UsuarioNoExisteExcepcion;

    /**
     * Caso de uso:
     * @implNote Registro :D
     * @param nickname String (unico)
     * @param nombre String
     * @param apellido String
     * @param email String (unico)
     * @param descripcion String
     * @param url String
     * @param nacimiento LocalDate
     */
    public abstract void registrarProveedor(String nickname, String nombre, String apellido, String email, String descripcion, String url, LocalDate nacimiento) throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion;
    public abstract void registrarProveedor(String nickname, String nombre, String apellido, String email, String descripcion, String url, LocalDate nacimiento, String password) throws ParametrosInvalidosExcepcion, UsuarioYaExisteExcepcion;

    /**
     * Caso de uso:
     * @param nickname String (unico)
     * @param nombre String
     * @param apellido String
     * @param nacimiento LocalDate
     * @throws ParametrosInvalidosExcepcion
     */
    public abstract void modificarUsuario(String nickname, String nombre, String apellido, LocalDate nacimiento) throws ParametrosInvalidosExcepcion;
    public abstract void modificarUsuario(String nickname, String nombre, String apellido, LocalDate nacimiento, byte[] imageData) throws ParametrosInvalidosExcepcion;
    
    /**
     * Lista todos los usuarios.
     * @return List de dataUsuario
     */
    public abstract List<dataUsuario> listarUsuarios();

    public abstract List<dataUsuario> listarProveedores();

    public abstract boolean login(String usuario, String password);
}
