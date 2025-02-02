package com.casino.Modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 * La clase <code>Usuario</code> representa a un jugador en el sistema, con su nombre de usuario, clave y el número de fichas disponibles.
 * Además, esta clase gestiona las apuestas realizadas por el usuario durante el juego.
 * 
 * <p>El objeto <code>Usuario</code> es responsable de mantener información relacionada con el jugador, como su nombre de usuario,
 * su clave de acceso, y la cantidad de fichas que posee. Además, gestiona las apuestas realizadas por el jugador en los diferentes juegos.</p>
 * 
 * @author [Pedro Castellano]
 * @version 1.0
 * @since [2025]
 */
public class Usuario {

    private String nombreUsuario;
    private String clave;
    private IntegerProperty fichas;
    private int apuesta;

    /**
     * Constructor que crea un usuario con nombre de usuario y contraseña.
     * Inicializa las fichas a 0.
     * 
     * @param nombreUsuario El nombre de usuario del jugador.
     * @param clave La contraseña del jugador.
     */
    public Usuario(String nombreUsuario, String clave) {
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        fichas = new SimpleIntegerProperty();
    }

    /**
     * Constructor por defecto. Crea un usuario sin inicializar las propiedades.
     */
    public Usuario() {
        // Constructor vacío para crear un usuario sin inicializar las propiedades
    }

    /**
     * Suma la cantidad de fichas al total de fichas del usuario.
     * 
     * @param apuesta La cantidad de fichas a sumar.
     */
    public void sumarFichas(int apuesta) {
        this.fichas.set(this.fichas.get() + apuesta);
    }

    /**
     * Resta la cantidad de fichas al total de fichas del usuario.
     * Si el resultado es negativo, no se realiza la operación.
     * 
     * @param apuesta La cantidad de fichas a restar.
     */
    public void restarFichas(int apuesta) {
        int resultado = this.fichas.get() - apuesta;
        if (resultado >= 0) {
            this.fichas.set(resultado);
        }
    }
    
    /**
     * Obtiene el nombre de usuario del jugador.
     * 
     * @return El nombre de usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Establece el nombre de usuario del jugador.
     * 
     * @param nombreUsuario El nombre de usuario que se desea establecer.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Obtiene la contraseña del jugador.
     * 
     * @return La contraseña del usuario.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Establece la contraseña del jugador.
     * 
     * @param clave La contraseña que se desea establecer.
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Obtiene la cantidad de fichas que tiene el usuario.
     * 
     * @return La cantidad de fichas del usuario.
     */
    public int getFichas() {
        return this.fichas.get();
    }

    /**
     * Establece la cantidad de fichas del usuario.
     * 
     * @param fichas La cantidad de fichas que se desea establecer.
     */
    public void setFichas(int fichas) {
        this.fichas.set(fichas);
    }

    /**
     * Devuelve la propiedad IntegerProperty de fichas, que permite enlazar el valor de fichas a un control en la interfaz gráfica.
     * 
     * @return La propiedad de fichas (IntegerProperty).
     */
    public IntegerProperty fichasProperty() {
        return fichas;
    }

    /**
     * Obtiene el valor de la apuesta realizada por el usuario.
     * 
     * @return El valor de la apuesta.
     */
    public int getApuesta() {
        return apuesta;
    }

    /**
     * Establece el valor de la apuesta realizada por el usuario.
     * 
     * @param apuesta La cantidad de fichas apostadas.
     */
    public void setApuesta(int apuesta) {
        this.apuesta = apuesta;
    }
}
