package com.casino.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Implementación de la interfaz {@code Usuarios_DAO} para la gestión de usuarios en la base de datos.
 * 
 * <p>Esta clase proporciona métodos para realizar operaciones de acceso a datos (DAO) sobre la tabla 
 * de usuarios en la base de datos del casino. Incluye funcionalidades para establecer la conexión con 
 * la base de datos, insertar, actualizar, eliminar y buscar usuarios.</p>
 * 
 * <h2>Operaciones principales:</h2>
 * <ul>
 *   <li>Conectar con la base de datos.</li>
 *   <li>Insertar nuevos usuarios.</li>
 *   <li>Actualizar datos de usuario.</li>
 *   <li>Eliminar usuarios.</li>
 *   <li>Buscar usuarios por nombre de usuario.</li>
 *   <li>Validar credenciales de usuario.</li>
 * </ul>
 * 
 * <h2>Ejemplo de uso:</h2>
 * <pre>
 * {@code
 * Implementacion_DAO dao = new Implementacion_DAO();
 * Connection conexion = dao.establecerConexion();
 * dao.insertarUsuario("nuevoUsuario", "password123");
 * }
 * </pre>
 * 
 * @author [Pedro Castellano]
 * @version 1.0
 * @since [2025]
 */
public class Implementacion_DAO implements Usuarios_DAO {
    private final String URL         = "jdbc:mysql://localhost:3306/casino";
    private final String USER        = "Casste";
    private final String PASS        = "Casinoproyect22";
    private final String UPDATE      = "UPDATE Usuarios SET username=? AND pass=? WHERE username=?";
    private final String INSERT      = "INSERT INTO Usuarios(username, pass) VALUES (?, ?)";
    private final String DELETE      = "DELETE FROM Usuarios WHERE username=?";
    private final String SEARCH      = "SELECT user_id FROM Usuarios WHERE username=?";
    private final String SEARCHLOGIN = "SELECT user_id FROM Usuarios WHERE username=? AND pass=?";

    /**
     * Establece la conexión con la base de datos utilizando las credenciales especificadas.
     * 
     * @return La conexión a la base de datos.
     * @throws SQLException Si ocurre un error al establecer la conexión con la base de datos.
    */
    @Override
    public Connection establecerConexion() throws SQLException {
        System.out.println("Conectando con la base de datos...");
        Connection connect = DriverManager.getConnection(URL, USER, PASS);
        return connect;
    }

    /**
     * Actualiza la información de un usuario en la base de datos.
     * 
     * <p>Este método recibe un nombre de usuario existente, un nuevo nombre de usuario y una nueva contraseña,
     * y actualiza esos datos en la base de datos.</p>
     * 
     * @param usuario El nombre de usuario a actualizar.
     * @param newUsuario El nuevo nombre de usuario.
     * @param newPassword La nueva contraseña para el usuario.
    */
    @Override
    public void update(String usuario, String newUsuario, String newPassword) {
        try( Connection connect = establecerConexion() ){
            PreparedStatement state = connect.prepareStatement(UPDATE);
            state.setString(1, newUsuario);
            state.setString(2, newPassword);
            state.setString(3, usuario);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Inserta un nuevo usuario en la base de datos.
     * 
     * <p>Este método recibe un nombre de usuario y una contraseña, y los inserta en la base de datos.</p>
     * 
     * @param usuario El nombre del usuario a insertar.
     * @param password La contraseña asociada al usuario.
    */
    @Override
    public void insert(String usuario, String password) {
        try ( Connection connect = establecerConexion() ){
            PreparedStatement state = connect.prepareStatement(INSERT);
            state.setString(1, usuario);
            state.setString(2, password);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Elimina un usuario de la base de datos.
     * 
     * <p>Este método recibe el nombre de un usuario y elimina el registro correspondiente de la base de datos.</p>
     * 
     * @param usuario El nombre del usuario que se desea eliminar.
    */
    @Override
    public void borrar(String usuario) {
        try ( Connection connect = establecerConexion() ){
            PreparedStatement state = connect.prepareStatement(DELETE);
            state.setString(1, usuario);
            state.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Verifica si un usuario ya existe en la base de datos.
     * 
     * <p>Este método consulta la base de datos para verificar si ya existe un usuario con el nombre especificado.</p>
     * 
     * @param usuario El nombre del usuario a verificar.
     * @return {@code true} si el usuario ya existe en la base de datos, de lo contrario {@code false}.
    */
    @Override
    public boolean validarCreacion(String usuario) {
        try ( Connection connect = establecerConexion() ){
            PreparedStatement state = connect.prepareStatement(SEARCH);
            state.setString(1, usuario);
            ResultSet result = state.executeQuery();
            if ( result.next() ) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Valida las credenciales de inicio de sesión de un usuario.
     * 
     * <p>Este método verifica si el nombre de usuario y la contraseña proporcionados corresponden a un usuario válido
     * en la base de datos.</p>
     * 
     * @param usuario El nombre de usuario para validar.
     * @param password La contraseña asociada al usuario.
     * @return {@code true} si el nombre de usuario y la contraseña coinciden con los datos en la base de datos, 
     *         de lo contrario {@code false}.
    */
    @Override
    public boolean validarLogin(String usuario, String password) {
        try ( Connection connect = establecerConexion() ){
            PreparedStatement state = connect.prepareStatement(SEARCHLOGIN);
            state.setString(1, usuario);
            state.setString(2, password);
            ResultSet result = state.executeQuery();
            if ( result.next() ) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
}
