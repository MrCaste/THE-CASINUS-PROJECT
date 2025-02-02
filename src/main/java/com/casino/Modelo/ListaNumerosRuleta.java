package com.casino.Modelo;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 * La clase <code>ListaNumerosRuleta</code> representa una lista de números de la ruleta, 
 * donde cada número está asociado a un color y a unas coordenadas específicas en el tablero de la ruleta.
 * Los números en la lista son instanciados automáticamente al crear un objeto de esta clase.
 * 
 * <p>Esta clase almacena una colección de objetos de tipo <code>NumerosRuleta</code>, cada uno de los cuales 
 * representa un número en la ruleta con su valor, color y coordenadas correspondientes. El constructor 
 * de la clase agrega números a la lista con valores predeterminados.</p>
 * 
 * <p>Los objetos de la clase <code>ListaNumerosRuleta</code> permiten acceder a la lista de números de la ruleta 
 * a través del método <code>getListaNumeros</code>.</p>
 * 
 * @author [Pedro Castellano]
 * @version 1.0
 * @since [2025]
 */
public class ListaNumerosRuleta {

    private List<NumerosRuleta> setNumeros = new ArrayList<>();

    public ListaNumerosRuleta() {
        setNumeros.add(new NumerosRuleta(20, Color.RED, 180f, 270f));
        setNumeros.add(new NumerosRuleta(1, Color.GOLD, 165.6f, 255.6f));
        setNumeros.add(new NumerosRuleta(3, Color.GREEN, 151.2f, 241.2f));
        setNumeros.add(new NumerosRuleta(1, Color.GOLD, 136.8f, 226.8f));
        setNumeros.add(new NumerosRuleta(5, Color.BLUE, 122.4f, 212.4f));
        setNumeros.add(new NumerosRuleta(1, Color.GOLD, 108, 198));
        setNumeros.add(new NumerosRuleta(3, Color.GREEN, 93.6f, 183.6f));
        setNumeros.add(new NumerosRuleta(1, Color.GOLD, 79.2f, 169.2f));
        setNumeros.add(new NumerosRuleta(10, Color.VIOLET, 64.8f, 154.8f));
        setNumeros.add(new NumerosRuleta(1, Color.GOLD, 50.4f, 140.4f));
        setNumeros.add(new NumerosRuleta(3, Color.GREEN, 36, 126));
        setNumeros.add(new NumerosRuleta(1, Color.GOLD, 21.6f, 111.6f));
        setNumeros.add(new NumerosRuleta(5, Color.BLUE, 7.2f, 97.2f));
        setNumeros.add(new NumerosRuleta(1, Color.GOLD, 352.8f, 82.8f));
        setNumeros.add(new NumerosRuleta(5, Color.BLUE, 338.4f, 68.4f));
        setNumeros.add(new NumerosRuleta(3, Color.GREEN, 324f, 54f));
        setNumeros.add(new NumerosRuleta(1, Color.GOLD, 309.6f, 39.6f));
        setNumeros.add(new NumerosRuleta(10, Color.VIOLET, 295.2f, 25.2f));
        setNumeros.add(new NumerosRuleta(1, Color.GOLD, 280.8f, 10.8f));
        setNumeros.add(new NumerosRuleta(3, Color.GREEN, 266.4f, 356.4f));
        setNumeros.add(new NumerosRuleta(1, Color.GOLD, 252, 342));
        setNumeros.add(new NumerosRuleta(5, Color.BLUE, 237.6f, 327.6f));
        setNumeros.add(new NumerosRuleta(1, Color.GOLD, 223.2f, 313.2f));
        setNumeros.add(new NumerosRuleta(3, Color.GREEN, 208.8f, 298.8f));
        setNumeros.add(new NumerosRuleta(1, Color.GOLD, 194.4f, 284.4f));
    }

    public List<NumerosRuleta> getListaNumeros() {
        return setNumeros;
    }
}
