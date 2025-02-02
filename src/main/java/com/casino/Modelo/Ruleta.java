package com.casino.Modelo;

import com.casino.Controlador.InicioController;
import com.casino.Vista.AnimacionRueda;

import javafx.application.Platform;
import javafx.scene.control.Label;
/**
 * La clase <code>Ruleta</code> es una subclase de <code>Juegos</code> que gestiona el juego de la ruleta,
 * incluyendo las apuestas realizadas por los jugadores y el cálculo de las ganancias en función de los números ganadores.
 * 
 * <p>Esta clase maneja el proceso de la ruleta, desde las apuestas realizadas hasta el cálculo de las ganancias
 * cuando un jugador gana en el juego. Además, se encarga de actualizar las ganancias totales del jugador
 * y la interfaz de usuario correspondiente.</p>
 * 
 * @author [Pedro Castellano]
 * @version 1.0
 * @since [2025]
 */
public class Ruleta extends Juegos{

    private int gananciasTotales = 0;
    ApuestasRuleta apuestas = new ApuestasRuleta();

    /**
     * Método encargado de manejar el proceso de cálculo de ganancias cuando un usuario gana en el juego.
     * 
     * Este método invoca el cálculo de las victorias basado en el número ganado en la ruleta, las apuestas realizadas
     * por el jugador y actualiza la interfaz de usuario con las ganancias correspondientes.
     * 
     * @param usuario El usuario que está jugando y al que se le actualizan las ganancias.
     * @param label El label en la interfaz de usuario que muestra las ganancias del jugador.
    */
    @Override
    public void Ganar(Usuario usuario, Label label) {
        calcularVictoria(InicioController.getUsuarioSesion(), AnimacionRueda.getNumero(), apuestas, label);
    }

    /**
     * Calcula las ganancias del usuario basadas en el número de la ruleta, las apuestas realizadas por el usuario y actualiza 
     * las fichas del usuario.
     * 
     * El cálculo de las ganancias se realiza de acuerdo con las apuestas hechas a los colores específicos (amarillo, verde, azul, 
     * morado, rojo) y las reglas de pago correspondientes a cada número en la ruleta.
     * 
     * Después de realizar el cálculo, las apuestas se resetean a cero.
     * 
     * @param usuario El usuario que está realizando las apuestas y al que se le actualizan las fichas.
     * @param numero El número que se ha obtenido en la ruleta.
     * @param apuestaColor El objeto que contiene las apuestas realizadas por el usuario en cada color.
     * @param label El label que se actualiza para mostrar el monto de las ganancias en la interfaz de usuario.
    */
    public void calcularVictoria(Usuario usuario, NumerosRuleta numero, ApuestasRuleta apuestaColor, Label label) {
        gananciasTotales = 0;
        boolean gano = false;
        
        // Verifica si el número obtenido en la ruleta corresponde a las apuestas realizadas por el jugador y calcula las ganancias.
        if (!apuestaColor.esVacioAmarillo() && numero.getNumero() == 1) {
            gananciasTotales += apuestaColor.getApuestaAmarillo() * 2;
            gano = true;
        }
        if (!apuestaColor.esVacioVerde() && numero.getNumero() == 3) {
            gananciasTotales += apuestaColor.getApuestaVerde() * 4;
            gano = true;
        }
        if (!apuestaColor.esVacioAzul() && numero.getNumero() == 5) {
            gananciasTotales += apuestaColor.getApuestaAzul() * 6;
            gano = true;
        }
        if (!apuestaColor.esVacioMorado() && numero.getNumero() == 10) {
            gananciasTotales += apuestaColor.getApuestaMorado() * 11;
            gano = true;
        }
        if (!apuestaColor.esVacioRojo() && numero.getNumero() == 20) {
            gananciasTotales += apuestaColor.getApuestaRojo() * 21;
            gano = true;
        }
        
        // Si el jugador ganó, actualiza la interfaz de usuario y agrega las ganancias al usuario.
        if (gano) {
            Platform.runLater(() -> {
                label.setText("+" + String.valueOf(gananciasTotales));
                label.setVisible(true);
                usuario.sumarFichas(gananciasTotales);
            });
        }
        
        // Resetea las apuestas a cero.
        apuestaColor.setApuestaAmarillo(0);
        apuestaColor.setApuestaAzul(0);
        apuestaColor.setApuestaMorado(0);
        apuestaColor.setApuestaRojo(0);
        apuestaColor.setApuestaVerde(0);
    }

    /**
     * Devuelve las apuestas realizadas por el jugador.
     * 
     * @return El objeto {@link ApuestasRuleta} que contiene las apuestas realizadas por el usuario.
    */
    public ApuestasRuleta getApuestas() {
        return apuestas;
    }
}
