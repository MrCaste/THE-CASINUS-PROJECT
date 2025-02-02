package com.casino.Modelo;

import javafx.scene.paint.Color;
/**
 * La clase <code>NumerosRuleta</code> representa un número en una ruleta, incluyendo su valor numérico,
 * el color asociado a ese número, y las coordenadas angulares tanto del número como de la rueda.
 * Esta clase se utiliza para representar cada número en la ruleta con información adicional sobre su 
 * color y ubicación en el tablero de la ruleta.
 * 
 * <p>Los objetos de la clase <code>NumerosRuleta</code> contienen el número en sí, un color que representa 
 * el tipo de apuesta (por ejemplo, rojo, verde, oro), y dos valores angulares: uno para el número en el tablero 
 * y otro para la ubicación en la rueda de la ruleta.</p>
 * 
 * @author [Pedro Castellano]
 * @version 1.0
 * @since [2025]
 */
public class NumerosRuleta {
    
    private int numero;
    private Color color;
    private float anguloNumero;
    private float anguloRueda;

    /**
     * Constructor de la clase <code>NumerosRuleta</code>.
     * Inicializa un número en la ruleta con su número, color y los ángulos correspondientes.
     * 
     * @param numero El número que representa este objeto.
     * @param color El color asociado con este número.
     * @param anguloNumero El ángulo en el que el número está posicionado en la rueda.
     * @param anguloRueda El ángulo en el que la rueda está posicionada con respecto a este número.
     */
    public NumerosRuleta(int numero, Color color, float anguloNumero, float anguloRueda) {
        this.numero = numero;
        this.color = color;
        this.anguloNumero = anguloNumero;
        this.anguloRueda = anguloRueda;
    }

    /**
     * Constructor vacío de la clase <code>NumerosRuleta</code>.
     * Este constructor permite crear un objeto de tipo <code>NumerosRuleta</code> sin inicializar los valores.
     */
    public NumerosRuleta() {

    }

    /**
     * Obtiene el número representado por este objeto.
     * 
     * @return El número de este objeto.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Obtiene el color asociado con el número en la rueda.
     * 
     * @return El color asociado con el número.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Obtiene el ángulo en el que el número está posicionado en la rueda.
     * 
     * @return El ángulo en el que el número está ubicado en la rueda.
     */
    public float getAnguloNumero() {
        return anguloNumero;
    }

    /**
     * Establece el número representado por este objeto.
     * 
     * @param numero El número a establecer en este objeto.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Establece el color asociado con el número en la rueda.
     * 
     * @param color El color a establecer para este número.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Establece el ángulo en el que el número está posicionado en la rueda.
     * 
     * @param anguloNumero El ángulo a establecer para este número.
     */
    public void setAnguloNumero(float anguloNumero) {
        this.anguloNumero = anguloNumero;
    }

    /**
     * Obtiene el ángulo de la rueda donde el número se encuentra.
     * 
     * @return El ángulo de la rueda correspondiente a este número.
     */
    public float getAnguloRueda() {
        return anguloRueda;
    }

    /**
     * Establece el ángulo de la rueda donde el número se encuentra.
     * 
     * @param anguloRueda El ángulo a establecer para la rueda.
     */
    public void setAnguloRueda(float anguloRueda) {
        this.anguloRueda = anguloRueda;
    }
}
