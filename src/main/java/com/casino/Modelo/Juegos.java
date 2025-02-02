package com.casino.Modelo;

import javafx.scene.control.Label;
/**
 * La clase abstracta <code>Juegos</code> proporciona una estructura base para diferentes tipos de juegos.
 * Esta clase define un método común para realizar apuestas, que es utilizado por los juegos derivados.
 * Los métodos y atributos específicos de cada tipo de juego deben ser implementados en las subclases.
 * 
 * <p>La clase <code>Juegos</code> no es instanciable directamente, ya que su propósito es ser extendida por otras clases 
 * que representen juegos concretos. Los juegos derivados deben definir sus propias reglas, pero pueden usar el 
 * método <code>apostar</code> para gestionar las apuestas y la resta de fichas del usuario.</p>
 * 
 * @author [Pedro Castellano]
 * @version 1.0
 * @since [2025]
 */
public abstract class Juegos {

    /**
     * Realiza una apuesta restando las fichas del usuario.
     * 
     * <p>Este método es común para todos los juegos. Cuando un usuario realiza una apuesta, 
     * se resta la cantidad apostada de las fichas del usuario.</p>
     *
     * @param apuesta La cantidad de fichas que el usuario desea apostar.
     * @param usuario El usuario que realiza la apuesta. 
     * @throws IllegalArgumentException Si el usuario no tiene suficientes fichas para hacer la apuesta.
     */
    public void apostar(int apuesta, Usuario usuario) {
        usuario.restarFichas(apuesta);
    }

    /**
     * Método abstracto para manejar las ganancias del usuario.
     * <p>Este método debe ser implementado por las subclases para especificar cómo el usuario 
     * gana en el juego y se le recompensa. Puede variar dependiendo del tipo de juego.</p>
     *
     * @param usuario El usuario que ha ganado.
     * @param label El {@link Label} donde se mostrará el mensaje o la cantidad ganada.
     */
    public abstract void Ganar(Usuario usuario, Label label);
}
