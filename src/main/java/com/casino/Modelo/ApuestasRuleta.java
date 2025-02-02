package com.casino.Modelo;
/**
 * La clase <code>ApuestasRuleta</code> modela las apuestas realizadas en una ruleta. 
 * Cada color en la ruleta tiene un monto de apuesta asociado. Los colores disponibles son: 
 * <code>Amarillo</code>, <code>Verde</code>, <code>Azul</code>, <code>Morado</code> y <code>Rojo</code>.
 * El sistema comienza con todas las apuestas en 0.
 * 
 * <p>Esta clase se utiliza para representar las apuestas individuales hechas en una ruleta, donde cada color 
 * puede recibir una cantidad de dinero como apuesta. Las apuestas se inician en cero y se pueden modificar posteriormente
 * para reflejar las apuestas actuales de cada jugador.</p>
 * 
 * <p>La clase no implementa la lógica de cálculo de ganancias o pérdidas, solo mantiene los montos de las apuestas
 * asociadas a cada color.</p>
 *
 * @author [Pedro Castellano]
 * @version 1.0
 * @since [2025]
 */
public class ApuestasRuleta {

    private int apuestaAmarillo;
    private int apuestaVerde;
    private int apuestaAzul;
    private int apuestaMorado;
    private int apuestaRojo;
    
    /**
     * Constructor de la clase ApuestasRuleta.
     * Inicializa todas las apuestas en 0.
    */
    public ApuestasRuleta() {
        apuestaAmarillo = 0;
        apuestaVerde = 0;
        apuestaAzul = 0;
        apuestaMorado = 0;
        apuestaRojo = 0;
    }

    
    /**
     * Establece la cantidad de fichas apostadas al color amarillo.
     * 
     * @param apuestaAmarillo La cantidad de fichas apostadas al color amarillo.
    */
    public void setApuestaAmarillo(int apuestaAmarillo) {
        this.apuestaAmarillo = apuestaAmarillo;
    }

    /**
     * Establece la cantidad de fichas apostadas al color verde.
     * 
     * @param apuestaVerde La cantidad de fichas apostadas al color verde.
    */
    public void setApuestaVerde(int apuestaVerde) {
        this.apuestaVerde = apuestaVerde;
    }

    /**
     * Establece la cantidad de fichas apostadas al color azul.
     * 
     * @param apuestaAzul La cantidad de fichas apostadas al color azul.
    */
    public void setApuestaAzul(int apuestaAzul) {
        this.apuestaAzul = apuestaAzul;
    }

    /**
     * Establece la cantidad de fichas apostadas al color morado.
     * 
     * @param apuestaMorado La cantidad de fichas apostadas al color morado.
    */
    public void setApuestaMorado(int apuestaMorado) {
        this.apuestaMorado = apuestaMorado;
    }

    /**
     * Establece la cantidad de fichas apostadas al color rojo.
     * 
     * @param apuestaRojo La cantidad de fichas apostadas al color rojo.
    */
    public void setApuestaRojo(int apuestaRojo) {
        this.apuestaRojo = apuestaRojo;
    }

    /**
     * Obtiene la cantidad apostada al color amarillo.
     * 
     * @return La cantidad apostada al color amarillo.
    */
    public int getApuestaAmarillo() {
        return apuestaAmarillo;
    }

    /**
     * Obtiene la cantidad apostada al color verde.
     * 
     * @return La cantidad apostada al color verde.
    */
    public int getApuestaVerde() {
        return apuestaVerde;
    }

    /**
     * Obtiene la cantidad apostada al color azul.
     * 
     * @return La cantidad apostada al color azul.
    */
    public int getApuestaAzul() {
        return apuestaAzul;
    }

    /**
     * Obtiene la cantidad apostada al color morado.
     * 
     * @return La cantidad apostada al color morado.
    */
    public int getApuestaMorado() {
        return apuestaMorado;
    }

    /**
     * Obtiene la cantidad apostada al color rojo.
     * 
     * @return La cantidad apostada al color rojo.
    */
    public int getApuestaRojo() {
        return apuestaRojo;
    }

    /**
     * Establece una cantidad de apuesta para un color específico.
     * 
     * @param apuesta La cantidad que se desea apostar.
     * @param color El color para el cual se establecerá la apuesta.
    */
    public void seteoGeneral(int apuesta, String color) {
        if (color.toLowerCase().matches("amarillo")) {
            this.apuestaAmarillo = apuesta;
        } else if (color.toLowerCase().matches("verde")) {
            this.apuestaVerde = apuesta;
        } else if (color.toLowerCase().matches("azul")) {
            this.apuestaAzul = apuesta;
        } else if (color.toLowerCase().matches("morado")) {
            this.apuestaMorado = apuesta;
        } else if (color.toLowerCase().matches("rojo")) {
            this.apuestaRojo = apuesta;
        }
    }

    /**
     * Verifica si la apuesta al color amarillo está vacía (igual a 0).
     * 
     * @return true si la apuesta es 0, false en caso contrario.
    */
    public boolean esVacioAmarillo() {
        return apuestaAmarillo == 0;
    }

    /**
     * Verifica si la apuesta al color verde está vacía (igual a 0).
     * 
     * @return true si la apuesta es 0, false en caso contrario.
    */
    public boolean esVacioVerde() {
        return apuestaVerde == 0;
    }

    /**
     * Verifica si la apuesta al color azul está vacía (igual a 0).
     * 
     * @return true si la apuesta es 0, false en caso contrario.
    */
    public boolean esVacioAzul() {
        return apuestaAzul == 0;
    }

    /**
     * Verifica si la apuesta al color morado está vacía (igual a 0).
     * 
     * @return true si la apuesta es 0, false en caso contrario.
    */
    public boolean esVacioMorado() {
        return apuestaMorado == 0;
    }

    /**
     * Verifica si la apuesta al color rojo está vacía (igual a 0).
     * 
     * @return true si la apuesta es 0, false en caso contrario.
    */
    public boolean esVacioRojo() {
        return apuestaRojo == 0;
    }
}
