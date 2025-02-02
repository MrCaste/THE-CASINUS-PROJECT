package com.casino.Controlador;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * Clase encargada de gestionar las transiciones entre ventanas en la aplicación.
 * 
 * <p>Esta clase permite abrir nuevas ventanas en la aplicación mediante la carga de archivos FXML y 
 * establecer propiedades como el título de la ventana y la posibilidad de arrastrarla libremente.</p>
 * 
 * <h2>Funciones principales:</h2>
 * <ul>
 *   <li>Abrir nuevas ventanas especificando el archivo FXML y el título.</li>
 *   <li>Habilitar la funcionalidad de arrastrar la ventana mediante eventos de ratón.</li>
 * </ul>
 * 
 * <h2>Uso:</h2>
 * <p>Para abrir una nueva ventana, se debe invocar el método {@code abrirVentana()} pasando la ruta del FXML, 
 * el título de la ventana y el evento que la activa.</p>
 * 
 * <h2>Ejemplo de uso:</h2>
 * <pre>
 * {@code
 * TransicionesControll.abrirVentana("vista.fxml", "Nueva Ventana", event);
 * }
 * </pre>
 * 
 * @author [Pedro Castellano]
 * @version 1.0
 * @since [2025]
 */
public class TransicionesControll {

    protected static double offsetX = 0;
    protected static double offsetY = 0;

    /**
     * Abre una nueva ventana en la aplicación cargando una vista desde un archivo FXML especificado.
     * 
     * <p>Este método gestiona la transición entre vistas, cargando la nueva escena desde un archivo 
     * FXML, configurando la escena y mostrando la ventana correspondiente. También permite que la 
     * ventana sea arrastrable por el usuario.</p>
     * 
     * @param fxml El archivo FXML que contiene el diseño de la nueva ventana a cargar.
     * @param titulo El título que se establecerá para la nueva ventana.
     * @param event El evento que se produce al invocar la transición, usado para obtener el 
     *              escenario actual de la ventana.
    */
    public static void abrirVentana(String fxml,String titulo,ActionEvent event){
        
        try {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(TransicionesControll.class.getResource(fxml));
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            makeStageDraggable(stage, root);
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permite que la ventana sea arrastrable por el usuario.
     * 
     * <p>Este método habilita el arrastre de la ventana utilizando el ratón, permitiendo que 
     * el usuario pueda mover la ventana por la pantalla. Se calcula el desplazamiento de la 
     * ventana según la posición del ratón al hacer clic y arrastrar.</p>
     * 
     * @param stage La ventana que se desea hacer arrastrable.
     * @param root El nodo raíz de la escena, necesario para detectar los eventos de ratón 
     *             y mover la ventana.
    */
    private static void makeStageDraggable(Stage stage, Parent root) {

        
        root.setOnMousePressed((event) -> {
            offsetX = event.getScreenX() - stage.getX();
            offsetY = event.getScreenY() - stage.getY();
        });

        
        root.setOnMouseDragged((event) -> {
            stage.setX(event.getScreenX() - offsetX);
            stage.setY(event.getScreenY() - offsetY);
        });
    }
}
