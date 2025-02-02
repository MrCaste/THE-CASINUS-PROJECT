package com.casino.DAO;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * Interfaz que define las operaciones de acceso a datos (DAO) para la gestión de usuarios en la base de datos.
 * 
 * <p>Esta interfaz proporciona métodos esenciales para interactuar con la base de datos de usuarios,
 * incluyendo la creación, actualización, eliminación y validación de usuarios.</p>
 * 
 * <h2>Operaciones principales:</h2>
 * <ul>
 *   <li>Establecer conexión con la base de datos.</li>
 *   <li>Actualizar información de usuario.</li>
 *   <li>Insertar nuevos usuarios.</li>
 *   <li>Eliminar usuarios.</li>
 *   <li>Validar si un usuario ya existe.</li>
 *   <li>Validar credenciales de inicio de sesión.</li>
 * </ul>
 * 
 * @author [Pedro Castellano]
 * @version 1.0
 * @since [2025]
 */
public interface Usuarios_DAO {

    /**
     * Establece una conexión con la base de datos.
     * 
     * <p>Este método establece una conexión con la base de datos para realizar operaciones sobre los usuarios.
     * La conexión se obtiene a través de un objeto <code>Connection</code> y puede lanzar una excepción si
     * ocurre un error en el proceso de conexión.</p>
     * 
     * @return Un objeto <code>Connection</code> que representa la conexión a la base de datos.
     * @throws SQLException Si ocurre un error al intentar establecer la conexión con la base de datos.
     */
    public Connection establecerConexion() throws SQLException;

    /**
     * Actualiza los datos de un usuario en la base de datos.
     * 
     * <p>Este método permite actualizar el nombre de usuario y la contraseña de un usuario ya existente en la base de datos.</p>
     * 
     * @param usuario El nombre de usuario actual que se desea actualizar.
     * @param newUsuario El nuevo nombre de usuario para reemplazar el actual.
     * @param password La nueva contraseña para el usuario.
     */
    public void update(String usuario, String newUsuario, String password);

    /**
     * Inserta un nuevo usuario en la base de datos.
     * 
     * <p>Este método permite insertar un nuevo usuario en la base de datos proporcionando un nombre de usuario y una contraseña.</p>
     * 
     * @param usuario El nombre de usuario del nuevo usuario a insertar.
     * @param password La contraseña del nuevo usuario a insertar.
     */
    public void insert(String usuario, String password);

    /**
     * Elimina un usuario de la base de datos.
     * 
     * <p>Este método permite borrar un usuario de la base de datos mediante el nombre de usuario proporcionado.</p>
     * 
     * @param usuario El nombre de usuario del usuario que se desea eliminar.
     */
    public void borrar(String usuario);

    /**
     * Valida si un nombre de usuario puede ser creado.
     * 
     * <p>Este método valida si un nombre de usuario está disponible para ser creado. Se utiliza para asegurarse
     * de que no exista un usuario con el mismo nombre antes de crear una cuenta.</p>
     * 
     * @param usuario El nombre de usuario que se desea verificar.
     * @return <code>true</code> si el nombre de usuario está disponible, <code>false</code> si ya existe en la base de datos.
     */
    public boolean validarCreacion(String usuario);

    /**
     * Valida el login de un usuario.
     * 
     * <p>Este método valida si un usuario puede iniciar sesión, comprobando su nombre de usuario y contraseña
     * en la base de datos.</p>
     * 
     * @param usuario El nombre de usuario que intenta iniciar sesión.
     * @param password La contraseña asociada al nombre de usuario.
     * @return <code>true</code> si las credenciales son correctas, <code>false</code> si no lo son.
     */
    public boolean validarLogin(String usuario, String password);
}
