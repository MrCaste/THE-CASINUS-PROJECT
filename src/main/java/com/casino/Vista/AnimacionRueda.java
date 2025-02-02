package com.casino.Vista;

import com.casino.Modelo.ListaNumerosRuleta;
import com.casino.Modelo.NumerosRuleta;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
/**
 * La clase <code>AnimacionRueda</code> gestiona la animación de la rueda de la ruleta, permitiendo simular 
 * un giro de la rueda con transición y calculando el número final ganador.
 * 
 * <p>Esta clase se encarga de la lógica de animación de la rueda, gestionando el ángulo de la rueda durante su giro
 * y calculando el número ganador al final del giro. Utiliza la clase <code>RotateTransition</code> para realizar
 * la animación de rotación de la rueda.</p>
 * 
 * @author [Pedro Castellano]
 * @version 1.0
 * @since [2025]
 */
public class AnimacionRueda {

    private float anguloActual             = 0;
    private static final long TAMAÑO_GIRO  = 1800;
    private float set90Grados              = 0;
    private float anguloFinal              = 0;
    private float anguloNumero             = 0;
    private float nuevoAngulo              = 0;
    private static NumerosRuleta resultado = new NumerosRuleta();
    private ListaNumerosRuleta lista       = new ListaNumerosRuleta();
    private RotateTransition movimiento;
    private static Duration tiempo;

    /**
     * Obtiene el número seleccionado por la ruleta.
     * 
     * @return El objeto `NumerosRuleta` que representa el número seleccionado por la ruleta.
    */
    public static NumerosRuleta getNumero() {
        return resultado;
    }

    /**
     * Obtiene la duración de la animación de la ruleta.
     * 
     * @return La duración de la animación en segundos.
    */
    public static Duration getTiempoAnimacion() {
        return tiempo;
    }

    /**
     * Mueve los ángulos de los números en la ruleta.
     * Este método actualiza el ángulo de cada número en la lista de números de la ruleta.
    */
    public void moverAngulos() {
        for (NumerosRuleta numeros : lista.getListaNumeros()) {
            nuevoAngulo = (numeros.getAnguloRueda() + set90Grados) % 360;
            numeros.setAnguloRueda(nuevoAngulo);
        }
    }

    /**
     * Actualiza el ángulo de la ruleta.
     * Este método incrementa el ángulo actual de la ruleta en 90 grados.
    */
    public void actualizarAnguloRuleta() {
        anguloActual = (anguloActual + set90Grados) % 360;
    }

    /**
     * Configura las propiedades de rotación para la animación de la ruleta.
     * Establece la tasa de la animación, la interpolación, el ángulo de inicio y el ángulo final.
    */
    public void rotacionValores() {
        movimiento.setRate(1);
        movimiento.setInterpolator(Interpolator.EASE_OUT);
        movimiento.setFromAngle(anguloActual);
        movimiento.setToAngle(anguloActual + anguloFinal);
        movimiento.setAxis(Rotate.Z_AXIS);
        movimiento.setAutoReverse(false);
    }

    /**
     * Inicia la animación de la ruleta, configurando el tiempo de animación, seleccionando un número aleatorio,
     * y realizando la rotación de la rueda.
     * 
     * @param rueda El grupo de objetos que representa la ruleta en la escena.
    */
    public void animacionCirculo(Group rueda) {
        // Establece un tiempo aleatorio para la animación entre 5 y 8 segundos.
        tiempo = Duration.seconds((Math.random() * (8 - 5 + 1) + 5));

        // Obtiene un número aleatorio de la ruleta y calcula el ángulo final para la rotación.
        anguloNumero = getNumeroAleatorio(0, 24);
        set90Grados = 270 - anguloNumero;
        set90Grados = (set90Grados + 360) % 360;
        anguloFinal = TAMAÑO_GIRO + set90Grados;

        // Configura la rotación de la ruleta.
        movimiento = new RotateTransition(tiempo, rueda);
        rotacionValores();

        // Actualiza los ángulos de los números y la ruleta.
        moverAngulos();
        actualizarAnguloRuleta();

        // Imprime los resultados de la animación para depuración.
        System.out.println(anguloNumero);
        System.out.println(getNumero().getNumero());

        // Inicia la animación de la ruleta.
        movimiento.play();
    }

    /**
     * Genera un número aleatorio dentro de un rango especificado, seleccionando un número de la lista de la ruleta.
     * 
     * @param min El valor mínimo del rango.
     * @param max El valor máximo del rango.
     * @return El ángulo correspondiente al número aleatorio seleccionado.
    */
    public float getNumeroAleatorio(int min, int max) {
        int indice = (int) (Math.random() * (max - min + 1) + min);
        resultado = lista.getListaNumeros().get(indice);  // Obtiene el número aleatorio de la lista.
        System.out.println("Indice es: " + indice);
        return resultado.getAnguloRueda();
    }
}
