package com.casino.Lanzador;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 * Clase principal de la aplicación que extiende {@code Application} para iniciar la interfaz gráfica.
 * 
 * <p>Esta clase es el punto de entrada de la aplicación JavaFX, encargada de lanzar y gestionar la 
 * ventana principal.</p>
 * 
 * <h2>Características:</h2>
 * <ul>
 *   <li>Inicia la aplicación mediante el método {@code launch}.</li>
 *   <li>Gestiona el desplazamiento de la ventana para hacerla arrastrable.</li>
 * </ul>
 * 
 * @author [Pedro Castellano]
 * @version 1.0
 * @since [2025]
 */
public class Main extends Application{
    private double xOffset = 0;
    private double yOffset = 0;
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Inicializa y configura la ventana principal de la aplicación.
     * 
     * <p>Este método se ejecuta al iniciar la aplicación. Se carga la interfaz gráfica desde un archivo FXML, 
     * se configura la ventana para ser transparente y movible, y se aplica un tema de estilo. Además, 
     * establece un icono y previene que la ventana sea redimensionable.</p>
     * 
     * @param primaryStage La etapa principal de la aplicación, representando la ventana de la interfaz gráfica.
    */
    @Override
public void start(Stage primaryStage) {

    try {
        // Carga la interfaz de usuario desde el archivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Inicio.fxml"));
        
        // Configura el estilo de la ventana como transparente
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        // Permite mover la ventana arrastrándola
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        
        // Crea la escena con el contenido cargado desde el FXML
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        
        // Aplica el tema de estilo desde un archivo CSS
        scene.getStylesheets().add("/css/TemaInicio.css");
        
        // Configura la escena y la ventana principal
        primaryStage.setScene(scene);
        primaryStage.setTitle("");  // Establece un título vacío
        primaryStage.setResizable(false);  // La ventana no es redimensionable
        
        // Establece el icono de la ventana
        primaryStage.getIcons().add(new Image(getClass().getResource("/imagenes/bufon.jpg").toExternalForm()));
        
        // Muestra la ventana principal
        primaryStage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}