package com.casino.Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import com.casino.DAO.Implementacion_DAO;
import com.casino.Modelo.ApuestasRuleta;
import com.casino.Modelo.Ruleta;
import com.casino.Vista.AnimacionRueda;
import com.casino.Vista.Rueda;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * Controlador de la interfaz de usuario para la vista del juego.
 * 
 * <p>Esta clase gestiona la lógica de la interfaz del usuario para las apuestas y juegos dentro de la aplicación, 
 * incluyendo la ruleta y el BlackJack. También permite la gestión del perfil del usuario.</p>
 * 
 * <h2>Funciones principales:</h2>
 * <ul>
 *   <li>Control de apuestas en distintos colores.</li>
 *   <li>Gestión del perfil del usuario, como actualización del nombre de usuario y contraseña.</li>
 *   <li>Animación y control de la ruleta.</li>
 *   <li>Control del progreso del juego y apuestas en tiempo real.</li>
 *   <li>Mostrar y actualizar la cantidad de fichas del usuario.</li>
 *   <li>Interacción con la interfaz gráfica mediante eventos de botones e hipervínculos.</li>
 * </ul>
 * 
 * <h2>Interfaz gráfica:</h2>
 * <p>Los elementos gráficos están definidos en el archivo FXML asociado e incluyen botones, etiquetas, barras de progreso, 
 * tablas y contenedores gráficos para organizar la interfaz.</p>
 * 
 * <h2>Dependencias:</h2>
 * <p>Esta clase se apoya en otras clases como {@code AnimacionRueda} y {@code Ruleta} para la gestión de la animación y lógica de juego.</p>
 * 
 * @author [Pedro Castellano]
 * @version 1.0
 * @since [2025]
 */
public class InGameController implements Initializable{

    @FXML
    private Hyperlink aplicarCambios;
    @FXML
    private Button apuestaAmarillo;
    @FXML
    private Button apuestaAzul;
    @FXML
    private Button apuestaMorado;
    @FXML
    private Button apuestaRojo;
    @FXML
    private Button apuestaVerde;
    @FXML
    private ProgressBar barraProgreso;
    @FXML
    private Button blackJack;
    @FXML
    private Button botonCerrar;
    @FXML
    private Button botonMasCincuenta;
    @FXML
    private Button botonMasDiez;
    @FXML
    private Button botonMasUno;
    @FXML
    private Button botonMasVeinte;
    @FXML
    private Button botonMinimizar;
    @FXML
    private TextField claveActualInformacion;
    @FXML
    private TextField claveInformacion;
    @FXML
    private AnchorPane configuracionPerfil;
    @FXML
    private Text contador;
    @FXML
    private Label infoApuestaAmarillo;
    @FXML
    private Label infoApuestaAzul;
    @FXML
    private Label infoApuestaMorado;
    @FXML
    private Label infoApuestaRojo;
    @FXML
    private Label infoApuestaVerde;
    @FXML
    private Label errorClave;
    @FXML
    private Label errorClaveActual;
    @FXML
    private Label errorClaves;
    @FXML
    private Label errorNuevoUsuario;
    @FXML
    private Label errorPatron;
    @FXML
    private Label errorVacio;
    @FXML
    private Label fichasInformacion;
    @FXML
    private Label fichasUsuario;
    @FXML
    private Label ganancias;
    @FXML
    private Label labelB;
    @FXML
    private Label labelB1;
    @FXML
    private Label labelR;
    @FXML
    private Hyperlink logOut;
    @FXML
    private TextField nuevaClave;
    @FXML
    private TextField nuevoUsuario;
    @FXML
    private Rectangle ocultarMensaje;
    @FXML
    private Pane paneInferiorB;
    @FXML
    private Pane paneInferiorR;
    @FXML
    private AnchorPane panePrincipal;
    @FXML
    private Pane paneSuperiorB;
    @FXML
    private Pane paneSuperiorP;
    @FXML
    private Pane paneSuperiorR;
    @FXML
    private AnchorPane panelBlackJack;
    @FXML
    private AnchorPane panelRuleta;
    @FXML
    private Button perfil;
    @FXML
    private Circle punteroRuleta;
    @FXML
    private TextField repetirNuevaClave;
    @FXML
    private Button ruleta;
    @FXML
    private TableView<Circle> tabla;
    @FXML
    private TableColumn<Circle, Circle> tablaColores;
    @FXML
    private TextField textoApuesta;
    @FXML
    private Label usuarioInformacion;
    @FXML
    private Label verificacionCambios;

    private static final int TIEMPO_TOTAL = 20;
    private int apuestasAmarillo;
    private int apuestasVerde;
    private int apuestasAzul;
    private int apuestasMorado;
    private int apuestasRojo;
    private int restarApuesta;
    private int index = 0;
    private AnimacionRueda animacion      = new AnimacionRueda();
    private Ruleta juegoRuleta            = new Ruleta();
    private ObservableList<Circle> list   = FXCollections.observableArrayList();

    /**
     * Inicializa los componentes y configura el estado inicial de la interfaz de usuario al cargar la ventana o escena.
     * 
     * <p>Este método es llamado automáticamente al inicializar la vista controlada por el controlador, 
     * asegurando que todos los componentes estén correctamente configurados y listos para interactuar con el usuario.</p>
     * 
     * <p>Las acciones realizadas por este método incluyen:</p>
     * <ul>
     *   <li>Mostrar el nombre de usuario en la interfaz de usuario.</li>
     *   <li>Deshabilitar la información de apuestas.</li>
     *   <li>Ocultar mensajes en la tabla de apuestas.</li>
     *   <li>Inicializar la tabla de apuestas.</li>
     *   <li>Inicializar las fichas del usuario.</li>
     *   <li>Actualizar componentes en tiempo real.</li>
     *   <li>Inicializar y mostrar los mensajes de error correspondientes.</li>
     *   <li>Configurar las líneas visibles de la interfaz de usuario.</li>
     *   <li>Inicializar los paneles del juego (Ruleta, BlackJack, etc.).</li>
     *   <li>Inicializar la ruleta.</li>
     *   <li>Iniciar las tareas de animación asociadas a la interfaz.</li>
     * </ul>
     * 
     * <p>Este método debe ser ejecutado cuando la vista se carga completamente para asegurar que los elementos visuales 
     * y los datos estén preparados para su uso.</p>
     * 
     * @param location La ubicación de la URL en la que se encuentra el archivo FXML.
     * @param resources El conjunto de recursos utilizados por la vista.
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        usuarioInformacion.setText(InicioController.getUsuarioSesion().getNombreUsuario());

        deshabilitarInfoApuestas();
        ocultarMensajeTabla();
        inizializarTabla();
        inicializarFichas();
        actualizarComponentesTiempoReal();
        inicializarErroresYComponentes();
        inicializarLineas();
        inicializarPanelesJuegos();
        inicializarRueleta();
        tareaAnimacion();  
    }

    /**
     * Método que controla el progreso de una barra de progreso y un contador visual.
     * 
     * <p>Este método ejecuta un bucle que reduce progresivamente el tiempo restante en intervalos de 10 milisegundos.
     * Durante cada iteración, actualiza la interfaz gráfica mediante {@code Platform.runLater()} para reflejar 
     * el progreso en la barra y el contador.</p>
     * 
     * <p>El proceso continúa hasta que el tiempo restante llega a cero. En caso de interrupción del hilo,
     * se captura la excepción {@code InterruptedException} y se imprime la traza del error.</p>
     *
    */
    public void procesoBarra() {
        int tiempoRestante = TIEMPO_TOTAL * 100;
        contador.setText(String.valueOf(tiempoRestante / 100));
        while(tiempoRestante > 0) {
            try {
                Thread.sleep(10);
                tiempoRestante--;
                final int tiempoActual = tiempoRestante;
                Platform.runLater(() -> {
                    contador.setText(String.valueOf(tiempoActual / 100));
                    barraProgreso.setProgress(tiempoActual / (double)(TIEMPO_TOTAL * 100));
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Inicia el proceso de animación de la ruleta.
     * 
     * <p>Este método ejecuta la animación del círculo de la ruleta en el hilo de la interfaz gráfica 
     * utilizando {@code Platform.runLater()}. Esto garantiza que la actualización de la UI 
     * se realice de manera segura dentro del hilo principal de JavaFX.</p>
    */
    public void procesoRuleta() {
        Platform.runLater(() -> {
            animacion.animacionCirculo(Rueda.getRueda());
        });
    }

    /**
     * Controla el ciclo de animación y lógica del juego de ruleta.
     * 
     * <p>Este método ejecuta un bucle infinito en el que se gestionan las fases del juego de ruleta. 
     * Realiza las siguientes acciones en orden:</p>
     * <ul>
     *   <li>Habilita las apuestas.</li>
     *   <li>Oculta elementos visuales indicando que el proceso está en curso.</li>
     *   <li>Inicia la cuenta regresiva con la barra de progreso.</li>
     *   <li>Ejecuta la animación de la ruleta.</li>
     *   <li>Deshabilita las apuestas.</li>
     *   <li>Espera 8 segundos para determinar el resultado.</li>
     *   <li>Calcula y asigna las ganancias al usuario en sesión.</li>
     *   <li>Deshabilita la información de las apuestas.</li>
     *   <li>Espera 3 segundos antes de agregar el color a la lista de historial.</li>
     * </ul>
     * 
     * <p>En caso de que el hilo sea interrumpido, se captura la excepción {@code InterruptedException} 
     * y se imprime la traza del error.</p>
     * 
     * <p><strong>Nota:</strong> Este método contiene un bucle infinito {@code while(true)}, lo que significa 
     * que se ejecutará indefinidamente a menos que se interrumpa externamente.</p>
     * 
    */
    public void procesoAnimacion() {
        while(true) { 
            try {
                habilitarApuestas();
                hacerInvisibleEnProceso();
                procesoBarra();
                procesoRuleta();
                deshabilitarApuestas();
                Thread.sleep(8000);
                deshabilitarInfoApuestas();
                juegoRuleta.Ganar(InicioController.getUsuarioSesion(), ganancias);
                Thread.sleep(3000);
                agregarColorLista();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Inicia la animación del juego en un hilo en segundo plano.
     * 
     * <p>Este método crea y ejecuta un nuevo hilo para gestionar la animación del juego de ruleta. 
     * El hilo ejecuta el método {@code procesoAnimacion()} y se establece como un <em>daemon</em>, 
     * lo que significa que se detendrá automáticamente cuando finalice la aplicación.</p>
     * 
     * <p>Este enfoque permite que la animación se ejecute sin bloquear el hilo principal de la aplicación.</p>
    */
    public void tareaAnimacion() {
        Thread procesoBarra = new Thread(() -> { procesoAnimacion(); });
        procesoBarra.setDaemon(true);
        procesoBarra.start();
    }

    /**
     * Deshabilita los botones de apuesta en la interfaz gráfica.
     * 
     * <p>Este método desactiva todos los botones de apuesta para evitar que el usuario realice nuevas apuestas 
     * mientras el juego está en curso. La actualización de la interfaz gráfica se ejecuta dentro de 
     * {@code Platform.runLater()} para garantizar que se realice en el hilo principal de JavaFX.</p>
    */
    public void deshabilitarApuestas() {
        Platform.runLater(() -> {
            apuestaAmarillo.setDisable(true);
            apuestaVerde.setDisable(true);
            apuestaAzul.setDisable(true);
            apuestaMorado.setDisable(true);
            apuestaRojo.setDisable(true);
        });
    }

    /**
     * Habilita los botones de apuesta en la interfaz gráfica.
     * 
     * <p>Este método activa todos los botones de apuesta para permitir que el usuario realice nuevas apuestas. 
     * La actualización de la interfaz gráfica se ejecuta dentro de {@code Platform.runLater()} para garantizar 
     * que se realice en el hilo principal de JavaFX.</p>
    */
    public void habilitarApuestas() {
        Platform.runLater(() -> {
            apuestaAmarillo.setDisable(false);
            apuestaVerde.setDisable(false);
            apuestaAzul.setDisable(false);
            apuestaMorado.setDisable(false);
            apuestaRojo.setDisable(false);
        });
    }

    /**
     * Muestra u oculta un mensaje en la tabla según el estado de la lista.
     * 
     * <p>Este método verifica si la lista de elementos está vacía. Si la lista está vacía, 
     * se muestra el mensaje de "tabla vacía" estableciendo {@code ocultarMensaje.setVisible(true)}. 
     * Si la lista contiene elementos, el mensaje se oculta.</p>
    */
    public void ocultarMensajeTabla() {
        if (list.isEmpty()) {
            ocultarMensaje.setVisible(true);
        } else {
            ocultarMensaje.setVisible(false);
        }
    }

    /**
     * Agrega un nuevo color a la lista y lo muestra en la interfaz gráfica.
     * 
     * <p>Este método verifica el tamaño de la lista de colores:</p>
     * <ul>
     *   <li>Si la lista está vacía, agrega un nuevo círculo con el color correspondiente.</li>
     *   <li>Si la lista tiene menos de 15 elementos, agrega el nuevo círculo en la posición indicada por {@code index}.</li>
     *   <li>Si la lista tiene 15 elementos, elimina el círculo más antiguo (en la posición 14) y agrega el nuevo círculo en la posición indicada por {@code index}.</li>
     * </ul>
     * <p>La actualización de la interfaz gráfica se realiza dentro de {@code Platform.runLater()} para asegurar que se ejecute en el hilo principal de JavaFX.</p>
    */
    public void agregarColorLista() {
        Platform.runLater(() -> {
            if (list.isEmpty()) {
                Circle circulo = new Circle(10, AnimacionRueda.getNumero().getColor());
                list.add(circulo);
            } else if (list.size() < 15) {
                Circle circulo = new Circle(10, AnimacionRueda.getNumero().getColor());
                list.add(index, circulo);
            } else {
                list.remove(14);
                Circle circulo = new Circle(10, AnimacionRueda.getNumero().getColor());
                list.add(index, circulo);
            }
            
        });
    }

    /**
     * Oculta el elemento de ganancias en la interfaz gráfica.
     * 
     * <p>Este método establece la visibilidad del componente {@code ganancias} en {@code false}, 
     * haciéndolo invisible para el usuario. La actualización de la interfaz gráfica se ejecuta dentro de 
     * {@code Platform.runLater()} para garantizar que se realice en el hilo principal de JavaFX.</p>
    */
    public void hacerInvisibleEnProceso() {
        Platform.runLater(() -> {
            ganancias.setVisible(false);
        });
    }

    /**
     * Oculta la información de las apuestas en la interfaz gráfica.
     * 
     * <p>Este método desactiva la visibilidad de los elementos que muestran la información de las apuestas 
     * para cada color, haciéndolos invisibles para el usuario.</p>
    */
    public void deshabilitarInfoApuestas() {
        infoApuestaAmarillo.setVisible(false);
        infoApuestaAzul.setVisible(false);
        infoApuestaMorado.setVisible(false);
        infoApuestaRojo.setVisible(false);
        infoApuestaVerde.setVisible(false);
    }

    /**
     * Inicializa la tabla de colores en la interfaz gráfica.
     * 
     * <p>Este método configura la tabla asignando un {@code CellValueFactory} para establecer 
     * los valores de las celdas a partir de los elementos de la lista de colores. 
     * También aplica un estilo CSS para centrar el contenido y carga los datos en la tabla.</p>
    */
    public void inizializarTabla() {
        tablaColores.setCellValueFactory(param -> new SimpleObjectProperty<>(param.getValue()));
        tablaColores.setStyle("-fx-alignment: CENTER;");
        tabla.setItems(list);
    }

    /**
     * Inicializa la ruleta y la agrega al panel correspondiente en la interfaz gráfica.
     * 
     * <p>Este método crea una nueva instancia de la clase {@code Rueda}, agrega el componente de la ruleta 
     * al contenedor {@code panelRuleta}, y llama al método {@code inicializarRueda()} para configurar 
     * la ruleta.</p>
    */
    public void inicializarRueleta() {
        Rueda ruleta = new Rueda();
        panelRuleta.getChildren().addAll(Rueda.getRueda());
        ruleta.inicializarRueda();
    }

    /**
     * Actualiza los componentes de la interfaz gráfica en tiempo real con listeners.
     * 
     * <p>Este método establece varios listeners para los componentes de la interfaz gráfica, lo que permite 
     * realizar actualizaciones dinámicas basadas en eventos como el cambio de estado de los controles de 
     * la ruleta, blackjack y perfil. Los eventos incluyen cambios de hover y foco, y se gestionan a través de 
     * los métodos {@code botonesHover()} y {@code botonesFoco()}.</p>
     * 
     * <p>Además, se configura un formateador para el campo de texto de apuesta y se valida cada cambio en 
     * las apuestas a través del método {@code validarApuesta()}. También se agrega un listener para la lista 
     * de colores, que actualiza el mensaje en la tabla cuando se produce un cambio. Finalmente, se vinculan 
     * las propiedades de fichas del usuario a los componentes de la interfaz para mantenerlos actualizados.</p>
    */
    public void actualizarComponentesTiempoReal() {
        ruleta.hoverProperty().addListener( (observable, oldValue, newValue)       -> botonesHover(ruleta, paneSuperiorR, paneInferiorR) );
        blackJack.hoverProperty().addListener( (observable, oldValue, newValue)    -> botonesHover(blackJack, paneSuperiorB, paneInferiorB) );
        perfil.hoverProperty().addListener( (observable, oldValue, newValue)       -> botonesHover(perfil, paneSuperiorP, null));
        ruleta.focusedProperty().addListener( (observable, oldValue, newValue)     -> botonesFoco(paneSuperiorR, paneInferiorR, newValue));
        blackJack.focusedProperty().addListener( (observable, oldValue, newValue)  -> botonesFoco(paneSuperiorB, paneInferiorB, newValue) );
        perfil.focusedProperty().addListener( (observable, oldValue, newValue)     -> botonesFoco(paneSuperiorP, null, newValue));
        textoApuesta.setTextFormatter(new TextFormatter<>(Change                   -> validarApuesta(Change)));
        list.addListener((ListChangeListener<Circle>) change                       -> ocultarMensajeTabla());
        fichasUsuario.textProperty().bind(InicioController.getUsuarioSesion().fichasProperty().asString());
        fichasInformacion.textProperty().bind(InicioController.getUsuarioSesion().fichasProperty().asString());
    }

    /**
     * Inicializa las fichas del usuario al valor predeterminado.
     * 
     * <p>Este método asigna un valor inicial de 1000 fichas al usuario actual, 
     * estableciendo la cantidad de fichas con la que comenzará la sesión.</p>
    */
    public void inicializarFichas() {
        InicioController.getUsuarioSesion().setFichas(1000);
    }

    /**
     * Inicializa los componentes de la interfaz gráfica y oculta los mensajes de error.
     * 
     * <p>Este método oculta todos los mensajes de error relacionados con la clave, el usuario, y otros 
     * posibles errores de validación, así como los elementos relacionados con la visualización de ganancias. 
     * Además, desactiva la interacción con los botones de aplicar cambios y cerrar sesión, eliminando 
     * el subrayado y deshabilitando la capacidad de navegación mediante el teclado.</p>
    */
    public void inicializarErroresYComponentes() {
        errorClave.setVisible(false);
        errorClaveActual.setVisible(false);
        errorClaves.setVisible(false);
        errorNuevoUsuario.setVisible(false);
        errorVacio.setVisible(false);
        errorPatron.setVisible(false);
        verificacionCambios.setVisible(false);
        aplicarCambios.setUnderline(false);
        aplicarCambios.setFocusTraversable(false);
        logOut.setUnderline(false);
        logOut.setFocusTraversable(false);
        ganancias.setVisible(false);
    }

    /**
     * Inicializa los paneles de los juegos en la interfaz gráfica.
     * 
     * <p>Este método establece el panel de la ruleta como visible y enfoca el componente de la ruleta. 
     * Al mismo tiempo, oculta los paneles de blackjack y de configuración del perfil, para asegurarse 
     * de que solo el panel de ruleta sea visible al inicio.</p>
    */
    public void inicializarPanelesJuegos() {
        Platform.runLater(() -> ruleta.requestFocus() );
        panelRuleta.setVisible(true);
        panelBlackJack.setVisible(false);
        configuracionPerfil.setVisible(false);
    }

    /**
     * Inicializa la visibilidad de las líneas en la interfaz gráfica.
     * 
     * <p>Este método oculta todos los paneles de líneas superiores e inferiores para los juegos de ruleta, blackjack 
     * y perfil, asegurando que no sean visibles al inicio.</p>
    */
    public void inicializarLineas() {
        paneSuperiorR.setVisible(false);
        paneSuperiorB.setVisible(false);
        paneInferiorR.setVisible(false);
        paneInferiorB.setVisible(false);
        paneSuperiorP.setVisible(false);
    }

    /**
     * Compara las contraseñas ingresadas con la contraseña actual del usuario.
     * 
     * <p>Este método verifica si alguna de las contraseñas ingresadas en los campos de texto 
     * ({@code claveInformacion} o {@code claveActualInformacion}) coincide con la contraseña almacenada 
     * en la sesión actual del usuario. Si alguna de las contraseñas coincide, retorna {@code true}, 
     * de lo contrario, retorna {@code false}.</p>
     * 
     * @return {@code true} si alguna de las contraseñas ingresadas es correcta, {@code false} en caso contrario.
    */
    public boolean compararClaves() {
        if ( claveInformacion.getText().equals(InicioController.getUsuarioSesion().getClave())
                || claveActualInformacion.getText().equals(InicioController.getUsuarioSesion().getClave()) ) {
                    return true;
                }
            return false;
    }

    /**
     * Verifica si los campos de nuevo usuario o clave están vacíos.
     * 
     * <p>Este método comprueba si el campo para el nuevo usuario o el campo de la clave 
     * están vacíos. Si cualquiera de estos campos está vacío, retorna {@code true}, 
     * indicando que se necesita completar la información. En caso contrario, retorna {@code false}.</p>
     * 
     * @return {@code true} si alguno de los campos está vacío, {@code false} si ambos campos contienen información.
    */
    public boolean cambioUsuarioVacio() {
        if( nuevoUsuario.getText().isEmpty() || claveInformacion.getText().isEmpty() ) {
            return true;
        }
        return false;
    }

    /**
     * Verifica si los campos de la clave actual o las nuevas claves están vacíos.
     * 
     * <p>Este método comprueba si el campo de la clave actual, la nueva clave, o la confirmación de la nueva clave 
     * están vacíos. Si cualquiera de estos campos está vacío, retorna {@code true}, indicando que es necesario 
     * completar toda la información. En caso contrario, retorna {@code false}.</p>
     * 
     * @return {@code true} si alguno de los campos está vacío, {@code false} si todos los campos contienen información.
    */
    public boolean cambioClaveVacia() {
        if( claveActualInformacion.getText().isEmpty() || nuevaClave.getText().isEmpty() || repetirNuevaClave.getText().isEmpty() ) {
            return true;
        }
        return false;
    }

    /**
     * Controla la visibilidad de los paneles cuando un componente recibe o pierde el foco.
     * 
     * <p>Este método gestiona la visibilidad de los paneles superiores e inferiores según 
     * el estado de foco de un componente. Si el componente recibe el foco ({@code newValue = true}), 
     * se muestran los paneles correspondientes. Si el componente pierde el foco ({@code newValue = false}), 
     * los paneles se ocultan. El comportamiento varía si el panel inferior es {@code null} o no.</p>
     * 
     * @param panelSuperiorVisible El panel superior que se muestra u oculta dependiendo del foco.
     * @param panelInferiorVisible El panel inferior que se muestra u oculta, o {@code null} si no hay.
     * @param newValue El valor de foco del componente: {@code true} si el componente tiene foco, {@code false} si no.
    */
    public void botonesFoco(Pane panelSuperiorVisible, Pane panelInferiorVisible, boolean newValue) {
        if (newValue && panelInferiorVisible != null) {
            panelInferiorVisible.setVisible(true);
            panelSuperiorVisible.setVisible(true);
        } else if (newValue && panelInferiorVisible == null) {
            panelSuperiorVisible.setVisible(true);
        } else if (!newValue && panelInferiorVisible != null) {
            panelInferiorVisible.setVisible(false);
            panelSuperiorVisible.setVisible(false);
            panelSuperiorVisible.setVisible(false);
        } else if (!newValue && panelInferiorVisible == null) {
            panelSuperiorVisible.setVisible(false);
        }
    }

    /**
     * Controla la visibilidad de los paneles cuando un botón está siendo hoverizado.
     * 
     * <p>Este método gestiona la visibilidad de los paneles superiores e inferiores en función de 
     * si un botón está siendo hoverizado o no, y si el botón tiene el foco. Si el botón está siendo hoverizado 
     * y no tiene el foco, se muestran los paneles correspondientes. Si el botón no está siendo hoverizado y 
     * no tiene el foco, los paneles se ocultan. El comportamiento varía si el panel inferior es {@code null} o no.</p>
     * 
     * @param boton El botón que se está hoverizando.
     * @param panelSuperior El panel superior que se muestra u oculta dependiendo del hover.
     * @param panelInferior El panel inferior que se muestra u oculta, o {@code null} si no hay.
    */
    public void botonesHover(Button boton, Pane panelSuperior, Pane panelInferior) {
        if (boton.isHover() && !boton.isFocused() && panelInferior == null) {
            panelSuperior.setVisible(true);
        } else if (!boton.isHover() && !boton.isFocused() && panelInferior == null) {
            panelSuperior.setVisible(false);
        } else if (boton.isHover() && !boton.isFocused() && panelInferior != null) {
            panelSuperior.setVisible(true);
            panelInferior.setVisible(true);
        } else if (!boton.isHover() && !boton.isFocused() && panelInferior != null) {
            panelSuperior.setVisible(false);
            panelInferior.setVisible(false);
        }
    }

    /**
     * Valida el texto ingresado en un campo de apuesta para asegurarse de que solo contenga dígitos.
     * 
     * <p>Este método verifica si el texto ingresado en el campo de apuesta es un número válido, permitiendo 
     * solo dígitos (sin caracteres no numéricos). Si el texto cumple con esta condición, el cambio es aceptado 
     * y se retorna el objeto {@code change}. En caso contrario, se retorna {@code null} para rechazar el cambio.</p>
     * 
     * @param change El objeto {@code TextFormatter.Change} que representa el cambio en el texto del campo.
     * @return El objeto {@code change} si el texto es válido (solo dígitos), o {@code null} si el texto contiene caracteres no numéricos.
    */
    public Change validarApuesta(TextFormatter.Change change) {
        String newText = change.getControlNewText();
        if (newText.matches("\\d*")) {
            return change;
        }
        return null;
    }

    /**
     * Controla la información y validación de una apuesta en la ruleta.
     * 
     * <p>Este método verifica si el monto de la apuesta ingresada es menor o igual que las fichas disponibles 
     * del usuario. Si la apuesta es válida, actualiza el valor de la apuesta para el color correspondiente, 
     * mostrando la cantidad apostada en el {@code colorLabel}. Además, hace visible el {@code colorLabel} 
     * para mostrar el valor de la apuesta en la interfaz.</p>
     * 
     * @param apuesta El valor de la apuesta actual para el color.
     * @param colorLabel El {@code Label} donde se muestra la cantidad apostada.
     * @param getApuestaColor El valor de la apuesta actual para el color específico.
     * @param apuestaColores El objeto que gestiona las apuestas por color.
     * @param color El color asociado con la apuesta.
    */
    public void controlInfoApuesta(int apuesta, Label colorLabel, int getApuestaColor, ApuestasRuleta apuestaColores, String color) {
        if (Integer.parseInt(textoApuesta.getText()) <= InicioController.getUsuarioSesion().getFichas()) {
            apuesta  = getApuestaColor;
            apuesta += Integer.parseInt(textoApuesta.getText());
            apuestaColores.seteoGeneral(apuesta, color);
            colorLabel.setText("+" + String.valueOf(apuesta));
            colorLabel.setVisible(true);
        }
    }

    /**
     * Resta una apuesta y la registra en el juego de ruleta.
     * 
     * <p>Este método obtiene el valor de la apuesta ingresada en el campo de texto, lo convierte a un número entero 
     * y lo pasa al método {@code apostar()} del objeto {@code juegoRuleta}, junto con la sesión del usuario actual, 
     * para realizar la apuesta.</p>
    */
    public void controlRestarApuesta() {
        restarApuesta = Integer.parseInt(textoApuesta.getText());
        juegoRuleta.apostar(restarApuesta, InicioController.getUsuarioSesion());
    }

    /**
     * Establece el valor de la apuesta a 50 cuando se activa el evento.
     * 
     * <p>Este método es llamado cuando se produce un evento (como un clic) en el botón asociado, 
     * y establece el valor del campo de texto de apuesta ({@code textoApuesta}) a 50.</p>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón).
    */
    @FXML
    void sumarCincuenta(ActionEvent event) {
        textoApuesta.setText(String.valueOf(50));
    }

    /**
     * Establece el valor de la apuesta a 10 cuando se activa el evento.
     * 
     * <p>Este método es llamado cuando se produce un evento (como un clic) en el botón asociado, 
     * y establece el valor del campo de texto de apuesta ({@code textoApuesta}) a 10.</p>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón).
    */
    @FXML
    void sumarDiez(ActionEvent event) {
        textoApuesta.setText(String.valueOf(10));
    }

    /**
     * Establece el valor de la apuesta a 1 cuando se activa el evento.
     * 
     * <p>Este método es llamado cuando se produce un evento (como un clic) en el botón asociado, 
     * y establece el valor del campo de texto de apuesta ({@code textoApuesta}) a 1.</p>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón).
    */
    @FXML
    void sumarUno(ActionEvent event) {
        textoApuesta.setText(String.valueOf(1));
    }

    /**
     * Establece el valor de la apuesta a 25 cuando se activa el evento.
     * 
     * <p>Este método es llamado cuando se produce un evento (como un clic) en el botón asociado, 
     * y establece el valor del campo de texto de apuesta ({@code textoApuesta}) a 25.</p>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón).
    */
    @FXML
    void sumarVeintiCinco(ActionEvent event) {
        textoApuesta.setText(String.valueOf(25));
    }

    /**
     * Realiza la apuesta al color amarillo si el campo de texto no está vacío.
     * 
     * <p>Este método es llamado cuando se activa un evento (como un clic) en el botón asociado 
     * para realizar una apuesta al color amarillo. Verifica que el campo de texto de la apuesta no esté vacío, 
     * y luego actualiza la apuesta para el color amarillo, usando el método {@code controlInfoApuesta()} 
     * y ajustando las fichas con {@code controlRestarApuesta()}.</p>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón).
    */
    @FXML
    void apuestaAmarillo(ActionEvent event) {
        if (!textoApuesta.getText().isEmpty()) {
            controlInfoApuesta(apuestasAmarillo, infoApuestaAmarillo, 
                    juegoRuleta.getApuestas().getApuestaAmarillo(), juegoRuleta.getApuestas(), "amarillo");
            controlRestarApuesta();
        }
    }

    /**
     * Realiza la apuesta al color azul si el campo de texto no está vacío.
     * 
     * <p>Este método es llamado cuando se activa un evento (como un clic) en el botón asociado 
     * para realizar una apuesta al color azul. Verifica que el campo de texto de la apuesta no esté vacío, 
     * y luego actualiza la apuesta para el color azul, usando el método {@code controlInfoApuesta()} 
     * y ajustando las fichas con {@code controlRestarApuesta()}.</p>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón).
    */
    @FXML
    void apuestaAzul(ActionEvent event) {
        if (!textoApuesta.getText().isEmpty()) {
            controlInfoApuesta(apuestasAzul, infoApuestaAzul, 
                    juegoRuleta.getApuestas().getApuestaAzul(), juegoRuleta.getApuestas(), "azul");
            controlRestarApuesta();
        }
    }

    /**
     * Realiza la apuesta al color morado si el campo de texto no está vacío.
     * 
     * <p>Este método es llamado cuando se activa un evento (como un clic) en el botón asociado 
     * para realizar una apuesta al color morado. Verifica que el campo de texto de la apuesta no esté vacío, 
     * y luego actualiza la apuesta para el color morado, usando el método {@code controlInfoApuesta()} 
     * y ajustando las fichas con {@code controlRestarApuesta()}.</p>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón).
    */
    @FXML
    void apuestaMorado(ActionEvent event) {
        if (!textoApuesta.getText().isEmpty()) {
            controlInfoApuesta(apuestasMorado, infoApuestaMorado, 
                    juegoRuleta.getApuestas().getApuestaMorado(), juegoRuleta.getApuestas(), "morado");
            controlRestarApuesta();
        }
    }

    /**
     * Realiza la apuesta al color rojo si el campo de texto no está vacío.
     * 
     * <p>Este método es llamado cuando se activa un evento (como un clic) en el botón asociado 
     * para realizar una apuesta al color rojo. Verifica que el campo de texto de la apuesta no esté vacío, 
     * y luego actualiza la apuesta para el color rojo, usando el método {@code controlInfoApuesta()} 
     * y ajustando las fichas con {@code controlRestarApuesta()}.</p>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón).
    */
    @FXML
    void apuestaRojo(ActionEvent event) {
        if (!textoApuesta.getText().isEmpty()) {
            controlInfoApuesta(apuestasRojo, infoApuestaRojo, 
                    juegoRuleta.getApuestas().getApuestaRojo(), juegoRuleta.getApuestas(), "rojo");
            controlRestarApuesta();
        }
    }

    /**
     * Realiza la apuesta al color verde si el campo de texto no está vacío.
     * 
     * <p>Este método es llamado cuando se activa un evento (como un clic) en el botón asociado 
     * para realizar una apuesta al color verde. Verifica que el campo de texto de la apuesta no esté vacío, 
     * y luego actualiza la apuesta para el color verde, usando el método {@code controlInfoApuesta()} 
     * y ajustando las fichas con {@code controlRestarApuesta()}.</p>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón).
    */
    @FXML
    void apuestaVerde(ActionEvent event) {
        if (!textoApuesta.getText().isEmpty()) {
            controlInfoApuesta(apuestasVerde, infoApuestaVerde, 
                    juegoRuleta.getApuestas().getApuestaVerde(), juegoRuleta.getApuestas(), "verde");
            controlRestarApuesta();
        }
    }

    /**
     * Cierra la aplicación cuando se activa el evento.
     * 
     * <p>Este método es llamado cuando se activa el evento (como un clic) en el botón de cierre de la ventana. 
     * Obtiene el {@link Stage} (ventana) actual y la cierra.</p>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón de cerrar).
    */
    @FXML
    void cerrarApp(ActionEvent event) {
        Stage stage = (Stage) panePrincipal.getScene().getWindow();
        stage.close();
    }

    /**
     * Minimiza la aplicación cuando se activa el evento.
     * 
     * <p>Este método es llamado cuando se activa el evento (como un clic) en el botón de minimizar la ventana. 
     * Obtiene el {@link Stage} (ventana) actual y la minimiza.</p>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón de minimizar).
    */
    @FXML
    void minimizarApp(ActionEvent event) {
        Stage stage = (Stage) panePrincipal.getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Muestra el menú de BlackJack y oculta los otros menús.
     * 
     * <p>Este método es llamado cuando se activa el evento (como un clic) en el botón de BlackJack. 
     * Oculta el panel de ruleta y la configuración del perfil, mostrando el panel de BlackJack.</p>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón de BlackJack).
    */
    @FXML
    void fijarMenuBlackJack(ActionEvent event) {
        panelRuleta.setVisible(false);
        panelBlackJack.setVisible(true);
        configuracionPerfil.setVisible(false);
    }

    /**
     * Muestra el menú de Ruleta y oculta los otros menús.
     * 
     * <p>Este método es llamado cuando se activa el evento (como un clic) en el botón de Ruleta. 
     * Oculta el panel de BlackJack y la configuración del perfil, mostrando el panel de ruleta.</p>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón de Ruleta).
    */
    @FXML
    void fijarMenuRuleta(ActionEvent event) {
        panelRuleta.setVisible(true);
        panelBlackJack.setVisible(false);
        configuracionPerfil.setVisible(false);
    }

    /**
     * Muestra el menú de perfil y oculta los otros menús.
     * 
     * <p>Este método es llamado cuando se activa el evento (como un clic) en el botón de perfil. 
     * Oculta el panel de BlackJack y el de ruleta, mostrando el panel de configuración del perfil.</p>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón de perfil).
    */
    @FXML
    void fijarMenuPerfil(ActionEvent event) {
        configuracionPerfil.setVisible(true);
        panelBlackJack.setVisible(false);
        panelRuleta.setVisible(false);
    }

    /**
     * Realiza el cierre de sesión y redirige al usuario a la ventana de inicio.
     * 
     * <p>Este método es llamado cuando se activa el evento (como un clic) en el botón de cerrar sesión. 
     * Utiliza la clase {@link TransicionesControll} para abrir la ventana de inicio.</p>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón de cerrar sesión).
    */
    @FXML
    void logOut(ActionEvent event) {
        TransicionesControll.abrirVentana("/fxml/Inicio.fxml", "", event);
    }

    /**
     * Aplica los cambios realizados en el perfil del usuario, validando los datos introducidos.
     * 
     * <p>Este método es llamado cuando se activa el evento de aplicar cambios en el perfil del usuario. Realiza una serie de validaciones para asegurarse de que los datos introducidos (como el nombre de usuario y la contraseña) son correctos. Si alguna de las validaciones falla, muestra el mensaje de error correspondiente. Si todo es válido, se aplican los cambios y se actualizan los datos del usuario en la base de datos.</p>
     * 
     * <ul>
     *   <li>Verifica si los campos de usuario y contraseña están vacíos.</li>
     *   <li>Verifica si el nuevo nombre de usuario y la nueva contraseña siguen los patrones establecidos.</li>
     *   <li>Valida si el nuevo nombre de usuario ya existe en la base de datos.</li>
     *   <li>Verifica que la contraseña actual introducida sea correcta antes de permitir cambios.</li>
     *   <li>Valida que las contraseñas nuevas coincidan.</li>
     *   <li>Si todo es correcto, se aplican los cambios y se actualiza la información del usuario.</li>
     * </ul>
     * 
     * @param event El evento que activa el método (por ejemplo, un clic en el botón de aplicar cambios).
    */
    @FXML
    void aplicarCambios(ActionEvent event) {
        Implementacion_DAO sql = new Implementacion_DAO();

        if ( cambioUsuarioVacio() && cambioClaveVacia() ) {
            verificacionCambios.setVisible(false);
            errorVacio.setVisible(true);
        } else if ( !cambioUsuarioVacio() && !InicioController.isValidoUser(nuevoUsuario) || !cambioClaveVacia() && !InicioController.isValidoPass(nuevaClave) ) { 
            verificacionCambios.setVisible(false);
            errorVacio.setVisible(false);
            errorPatron.setVisible(true);
        } else if( !cambioUsuarioVacio() && sql.validarCreacion(nuevoUsuario.getText()) ) {
            errorVacio.setVisible(false);
            errorPatron.setVisible(false);
            verificacionCambios.setVisible(false);
            errorNuevoUsuario.setVisible(true);
        } else if ( !cambioUsuarioVacio() && !InicioController.getUsuarioSesion().getClave().equals(claveInformacion.getText()) ) {
            errorVacio.setVisible(false);
            errorPatron.setVisible(false);
            verificacionCambios.setVisible(false);
            errorClave.setVisible(true);
        } else if ( !cambioClaveVacia() && !InicioController.getUsuarioSesion().getClave().equals(claveActualInformacion.getText()) ) {
            errorVacio.setVisible(false);
            errorPatron.setVisible(false);
            verificacionCambios.setVisible(false);
            errorClaveActual.setVisible(true);
        } else if ( !cambioClaveVacia() && !nuevaClave.getText().equals(repetirNuevaClave.getText()) ) {
            errorVacio.setVisible(false);
            errorPatron.setVisible(false);
            verificacionCambios.setVisible(false);
            errorClaves.setVisible(true);
        } else if ( !cambioUsuarioVacio() && InicioController.isValidoUser(nuevoUsuario) && compararClaves() ) {
            errorVacio.setVisible(false);
            errorPatron.setVisible(false);
            verificacionCambios.setVisible(true);
            nuevoUsuario.clear();
            claveInformacion.clear();
            sql.update(InicioController.getUsuarioSesion().getNombreUsuario(), nuevoUsuario.getText(), InicioController.getUsuarioSesion().getClave());
            InicioController.getUsuarioSesion().setNombreUsuario(nuevoUsuario.getText());
            usuarioInformacion.setText(InicioController.getUsuarioSesion().getNombreUsuario());    
        } else if ( !cambioClaveVacia() && InicioController.isValidoPass(nuevaClave) && compararClaves() ) {
            errorVacio.setVisible(false);
            errorPatron.setVisible(false);
            errorClave.setVisible(false);
            errorClaveActual.setVisible(false);
            errorClaves.setVisible(false);
            errorNuevoUsuario.setVisible(false);
            verificacionCambios.setVisible(true);
            claveActualInformacion.clear();
            nuevaClave.clear();
            repetirNuevaClave.clear();
            sql.update(InicioController.getUsuarioSesion().getNombreUsuario(), InicioController.getUsuarioSesion().getNombreUsuario(), nuevaClave.getText());
            InicioController.getUsuarioSesion().setClave(nuevaClave.getText());
        } else {
            errorVacio.setVisible(false);
            errorPatron.setVisible(false);
            errorClave.setVisible(false);
            errorClaveActual.setVisible(false);
            errorClaves.setVisible(false);
            errorNuevoUsuario.setVisible(false);
            verificacionCambios.setVisible(true);
            nuevoUsuario.clear();
            claveInformacion.clear();
            claveActualInformacion.clear();
            nuevaClave.clear();
            repetirNuevaClave.clear();
            sql.update(InicioController.getUsuarioSesion().getNombreUsuario(), nuevoUsuario.getText(), nuevaClave.getText());
            InicioController.getUsuarioSesion().setNombreUsuario(nuevoUsuario.getText());
            InicioController.getUsuarioSesion().setClave(nuevaClave.getText());
            usuarioInformacion.setText(InicioController.getUsuarioSesion().getNombreUsuario());
        }
    }
}
