package com.casino.Lanzador;
/**
 * Clase encargada de lanzar la aplicación.
 * 
 * <p>Esta clase actúa como un punto de entrada alternativo para iniciar la aplicación, 
 * delegando la ejecución a la clase principal {@code Main}. Es útil en ciertos entornos 
 * donde ejecutar directamente una aplicación JavaFX puede causar problemas.</p>
 * 
 * <h2>Propósito:</h2>
 * <ul>
 *   <li>Evitar problemas con ciertos entornos que requieren un punto de entrada explícito.</li>
 *   <li>Delegar la ejecución a la clase {@code Main}.</li>
 * </ul>
 * 
 * <h2>Ejemplo de ejecución:</h2>
 * <pre>
 * {@code
 * public static void main(String[] args) {
 *     Main.main(args);
 * }
 * }
 * </pre>
 * 
 * @author [Pedro Castellano]
 * @version 1.0
 * @since [2025]
 */
public class Launcher {
    public static void main(String[] args) {
        Main.main(args);
    }
}
