package com.casino.Vista;

import com.casino.Modelo.ListaNumerosRuleta;
import com.casino.Modelo.NumerosRuleta;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
/**
 * La clase <code>Rueda</code> representa la rueda de la ruleta en el juego.
 * Contiene la información sobre el grupo que visualiza la rueda y los números que la componen.
 * 
 * <p>Esta clase gestiona la rueda de la ruleta, su ángulo de inicio, y proporciona acceso al grupo visual que
 * representa la rueda en la interfaz de usuario. Además, contiene la lista de los números que aparecen en la rueda.</p>
 * 
 * @author [Pedro Castellano]
 * @version 1.0
 * @since [2025]
 */
public class Rueda {

    private static final double ANGULO = 14.4f;
    private static final double ANGULO_INICIO = 82.8f;
    private static Group rueda = new Group();
    private ListaNumerosRuleta listaNumeros = new ListaNumerosRuleta();

     /**
     * Obtiene el grupo que representa la rueda de la ruleta.
     * 
     * @return El grupo de la rueda de la ruleta.
     */
    public static Group getRueda() {
        return rueda;
    }

    /**
     * Inicializa la rueda de la ruleta, creando los segmentos coloreados en la rueda.
     * Los segmentos son círculos parciales (arcos) que tienen diferentes colores dependiendo de su posición.
     */
    public void inicializarRueda() {
        double anguloInicio = ANGULO_INICIO;  // Ángulo inicial para comenzar la creación de segmentos
        Group segmentos = new Group();  // Contenedor para los segmentos de la rueda

        // Itera para crear 25 segmentos en la rueda
        for (int i = 0; i < 25; i++) {
            Arc arco = new Arc();  // Crea un nuevo arco que representará un segmento de la rueda

            // Asigna un color específico dependiendo del índice del segmento
            if (i == 0) {
                arco.setFill(Color.RED);  // El primer segmento es de color rojo
            } else if (i == 1 || i == 3 || i == 5 || i == 7 || i == 9 || i == 11 || i == 13 || 
                        i == 16 || i == 18 || i == 20 || i == 22 || i == 24) {
                arco.setFill(Color.GOLD);  // Segmentos con índices impares son dorados
            } else if (i == 2 || i == 6 || i == 10 || i == 15 || i == 19 || i == 23) {
                arco.setFill(Color.GREEN);  // Segmentos con ciertos índices son verdes
            } else if (i == 4 || i == 12 || i == 14 || i == 21) {
                arco.setFill(Color.BLUE);  // Segmentos con ciertos índices son azules
            } else if (i == 8 || i == 17) {
                arco.setFill(Color.VIOLET);  // Segmentos con ciertos índices son violetas
            }

            // Configura las propiedades del arco (segmento de la rueda)
            arco.setCenterX(566f);  // Coordenada X del centro de la rueda
            arco.setCenterY(322f);  // Coordenada Y del centro de la rueda
            arco.setRadiusX(200.0f);  // Radio en el eje X del arco
            arco.setRadiusY(200.0f);  // Radio en el eje Y del arco
            arco.setStartAngle(anguloInicio);  // Ángulo de inicio del arco
            arco.setLength(ANGULO);  // Longitud del arco (ángulo del segmento)
            arco.setType(ArcType.ROUND);  // Tipo de arco (redondeado)
            
            // Añade el arco (segmento) a la lista de segmentos
            segmentos.getChildren().add(arco);

            // Incrementa el ángulo de inicio para el siguiente segmento
            anguloInicio += ANGULO;
        }

        // Añade todos los segmentos a la rueda
        rueda.getChildren().add(segmentos);

        // Llama al método para agregar los números a la rueda
        numeros();
    }

    /**
     * Añade los números a la rueda de la ruleta, posicionándolos en los segmentos correspondientes.
     * Los números se colocan en ángulos específicos y se ajustan para que estén orientados correctamente.
     */
    public void numeros() {
        Group texto = new Group();  // Contenedor para los textos (números) en la rueda

        // Itera sobre los números en la lista de números
        for (NumerosRuleta numero : listaNumeros.getListaNumeros()) {
            // Crea un nodo de texto para mostrar el número
            Text textoNodo = new Text(String.valueOf(numero.getNumero()));
            int centrarX = 566;  // Coordenada X del centro de la rueda
            int centrarY = 306;  // Coordenada Y del centro de la rueda
            int radio = 170;  // Radio para posicionar el número en el segmento
            double anguloTexto = (180 + numero.getAnguloNumero());  // Ángulo en el que se coloca el texto

            // Configura las propiedades del texto
            textoNodo.setFill(Color.BLACK);  // Color del texto
            textoNodo.setFont(Font.font("Tahoma", 20));  // Fuente y tamaño del texto

            // Calcula las coordenadas X e Y para colocar el número en su posición
            double x = centrarX + radio * (Math.cos((numero.getAnguloNumero() + 88) * Math.PI / 180f));
            double y = centrarY + radio * (Math.sin((numero.getAnguloNumero() + 88) * Math.PI / 180f));

            // Coloca el número en la ubicación calculada
            textoNodo.relocate(x, y);
            
            // Rota el texto para que esté correctamente orientado
            textoNodo.getTransforms().add(new Rotate(anguloTexto));

            // Añade el número a la lista de textos
            texto.getChildren().add(textoNodo);
        }

        // Añade todos los números a la rueda
        rueda.getChildren().add(texto);
    }
}
