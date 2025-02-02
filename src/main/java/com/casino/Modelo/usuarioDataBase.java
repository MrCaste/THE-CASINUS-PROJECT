package com.casino.Modelo;

import java.util.HashMap;
import java.util.Map;

/**
 * La clase <code>usuarioDataBase</code> simula una base de datos en memoria para almacenar usuarios del sistema.
 * Utiliza un <code>Map</code> donde las claves son los nombres de usuario y los valores son los objetos de tipo <code>Usuario</code>.
 * 
 * <p>Esta clase proporciona un método para agregar nuevos usuarios al sistema con sus credenciales (nombre de usuario y contraseña),
 * almacenándolos en un mapa que los asocia de forma única a su nombre de usuario.</p>
 * 
 * @author [Pedro Castellano]
 * @version 1.0
 * @since [2025]
 */
public class usuarioDataBase {

    // Mapa que almacena los usuarios con su nombre de usuario como clave.
    private static Map<String, Usuario> usuarios = new HashMap<>();
    
    /**
     * Agrega un nuevo usuario al sistema con el nombre de usuario y contraseña proporcionados.
     * 
     * @param usuario El nombre de usuario del nuevo usuario.
     * @param contraseña La contraseña del nuevo usuario.
    */
    public static void agregarUsuario(String usuario, String contraseña){
        usuarios.put(usuario, new Usuario(usuario, contraseña));
    }
    
    /**
     * Valida si el nombre de usuario y la contraseña coinciden con un usuario registrado.
     * 
     * @param usuario El nombre de usuario a validar.
     * @param contraseña La contraseña a validar.
     * @return `true` si el usuario existe y la contraseña coincide, `false` en caso contrario.
    */
    public static boolean validarUsuario(String usuario, String contraseña){
        Usuario user = obtenerUsuario(usuario);
        return user != null && user.getClave().equals(contraseña);
    }
    
    /**
     * Verifica si un usuario con el nombre proporcionado ya existe en el sistema.
     * 
     * @param usuario El nombre de usuario a verificar.
     * @return `true` si el usuario existe, `false` si no existe.
    */
    public static boolean ifExist(String usuario){
        Usuario user = obtenerUsuario(usuario);
        return user != null && user.getNombreUsuario().equals(usuario);
    }
    
    /**
     * Obtiene un usuario del sistema utilizando su nombre de usuario.
     * 
     * @param usuario El nombre de usuario del usuario que se desea obtener.
     * @return El objeto `Usuario` correspondiente al nombre de usuario, o `null` si no se encuentra.
    */
    public static Usuario obtenerUsuario(String usuario){
        return usuarios.get(usuario);
    }
}
