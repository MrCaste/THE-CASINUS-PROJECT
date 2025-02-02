package com.casino.Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import com.casino.DAO.Implementacion_DAO;
import com.casino.Modelo.Usuario;
import com.casino.Modelo.usuarioDataBase;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
/**
 * Controlador para la pantalla de inicio de sesión y creación de cuenta en la aplicación.
 * 
 * <p>Esta clase gestiona la lógica de la interfaz de usuario, incluyendo el inicio de sesión,
 * la validación de credenciales y la creación de nuevas cuentas de usuario. También maneja
 * efectos visuales y la inicialización de componentes al cargar la vista.</p>
 * 
 * @author [Pedro Castellano]
 * @version 1.0
 * @since 2025
 */
public class InicioController implements Initializable {

    @FXML
    private Label ErrorPattern;
    @FXML
    private Button botonCerrar;
    @FXML
    private Button botonLogin;
    @FXML
    private Button botonMinimizar;
    @FXML
    private Hyperlink cancelarCrearLink;
    @FXML
    private Hyperlink crearCuentaLink;
    @FXML
    private PasswordField crearPass;
    @FXML
    private PasswordField crearPassRepeat;
    @FXML
    private TextField crearUsuario;
    @FXML
    private ImageView imageBotonLogin;
    @FXML
    private Label labelCrearPass;
    @FXML
    private Label labelCrearPassRepeat;
    @FXML
    private Label labelCrearUsuario;
    @FXML
    private Label labelErrorPass;
    @FXML
    private Label labelErrorUser;
    @FXML
    private Label labelErrorVacio;
    @FXML
    private Label labelPass;
    @FXML
    private Label labelUsuario;
    @FXML
    private Label loginError;
    @FXML
    private ImageView logoView;
    @FXML
    private AnchorPane pane;
    @FXML
    private AnchorPane paneCrearCuenta;
    @FXML
    private Rectangle rectangleCreatePass;
    @FXML
    private Rectangle rectangleCreateUser;
    @FXML
    private Rectangle rectanglePass;
    @FXML
    private Rectangle rectangleRepeatPass;
    @FXML
    private Rectangle rectangleUsuario;
    @FXML
    private Hyperlink registerLink;
    @FXML
    private PasswordField textPass;
    @FXML
    private TextField textUsuario;
    @FXML
    private MediaView viewPrincipal;

    private DropShadow shadow     = new DropShadow();
    protected static Pattern usuarioPatron = Pattern.compile("^(?=[a-z]+)(?=[A-Z]*)(?=\\d*).{3,}$");
    protected static Pattern passPatron    = Pattern.compile("(?=.*[A-Z])(?=.*\\d).{8,}$");
    protected static Usuario usuarioSesion;

    /**
     * Método de inicialización para la vista de la aplicación.
     * 
     * <p>Este método se ejecuta al inicio de la vista y se encarga de inicializar los componentes 
     * necesarios para la creación de cuenta, configurar la cuenta de administrador, establecer 
     * efectos visuales en los elementos de la interfaz, y configurar las interacciones de los 
     * controles de la interfaz de usuario como botones, enlaces, y campos de texto.</p>
     * 
     * <p>También oculta el mensaje de error del login al inicio.</p>
     * 
     * @param location La ubicación de la URL asociada con el archivo FXML (no se usa en este caso).
     * @param resources El recurso que contiene los datos de localización (no se usa en este caso).
    */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniCompCreacionCuenta();
        iniCuentaAdmin();
        iniSombraPaneIzq();
        iniVideoInicio();
        inicializarBotonLogin();
        inicializarLink();
        propiedadRectangulo();

        loginError.setVisible(false); 
    }

    /**
     * Verifica si el texto en el campo de texto proporcionado cumple con el patrón de usuario válido.
     * 
     * <p>Este método utiliza una expresión regular para validar el texto ingresado en el campo de texto de usuario.
     * Retorna {@code true} si el texto coincide con el patrón, indicando que el usuario es válido, de lo contrario, 
     * retorna {@code false}.</p>
     * 
     * @param field El campo de texto que contiene el texto del usuario.
     * @return {@code true} si el texto es válido según el patrón, {@code false} en caso contrario.
    */
    public static boolean isValidoUser(TextField field) {
        return usuarioPatron.matcher(field.getText()).matches();
    }

    /**
     * Verifica si el texto en el campo de texto proporcionado cumple con el patrón de contraseña válido.
     * 
     * <p>Este método utiliza una expresión regular para validar el texto ingresado en el campo de texto de contraseña.
     * Retorna {@code true} si el texto coincide con el patrón, indicando que la contraseña es válida, de lo contrario, 
     * retorna {@code false}.</p>
     * 
     * @param field El campo de texto que contiene la contraseña.
     * @return {@code true} si el texto es válido según el patrón, {@code false} en caso contrario.
    */
    public static boolean isValidoPass(TextField field) {
        return passPatron.matcher(field.getText()).matches();
    }

    /**
     * Configura los cambios visuales de los rectángulos asociados a los campos de texto cuando estos ganan o pierden foco.
     * 
     * <p>Este método establece un listener en las propiedades de enfoque (focus) de los campos de texto relacionados 
     * con el usuario, la contraseña, y su repetición. Cuando el campo recibe o pierde el foco, se actualizan los 
     * bordes de los rectángulos correspondientes con colores específicos.</p>
     * 
     * <p>Se utiliza para mejorar la experiencia visual del usuario al interactuar con los campos de entrada.</p>
    */
    public void propiedadRectangulo() {
        textPass.focusedProperty().addListener((observable, oldValue, newValue)        -> actualizarRectangulo(textPass, rectanglePass, Color.BLACK, Color.TRANSPARENT));
        textUsuario.focusedProperty().addListener((observable, oldValue, newValue)     -> actualizarRectangulo(textUsuario, rectangleUsuario, Color.BLACK, Color.TRANSPARENT));
        crearUsuario.focusedProperty().addListener((observable, oldValue, newValue)    -> actualizarRectangulo(crearUsuario, rectangleCreateUser, Color.valueOf("#151515"), Color.TRANSPARENT));
        crearPass.focusedProperty().addListener((observable, oldValue, newValue)       -> actualizarRectangulo(crearPass, rectangleCreatePass, Color.valueOf("#151515"), Color.TRANSPARENT));
        crearPassRepeat.focusedProperty().addListener((observable, oldValue, newValue) -> actualizarRectangulo(crearPassRepeat, rectangleRepeatPass, Color.valueOf("#151515"), Color.TRANSPARENT));
    }

    /**
     * Inicializa los componentes de la pantalla de creación de cuenta.
     * 
     * <p>Este método configura el estado inicial de los componentes de la interfaz de usuario para la creación de cuenta, 
     * asegurándose de que los enlaces y mensajes de error estén correctamente configurados. Además, 
     * oculta los paneles de creación de cuenta y los mensajes de error hasta que sea necesario mostrarlos.</p>
     * 
     * <p>Se utiliza para preparar la vista de creación de cuenta de una manera ordenada y funcional.</p>
    */
    public void iniCompCreacionCuenta() {
        crearCuentaLink.setUnderline(false);
        crearCuentaLink.setFocusTraversable(false);
        cancelarCrearLink.setUnderline(false);
        cancelarCrearLink.setFocusTraversable(false);
        labelErrorUser.setVisible(false);
        labelErrorPass.setVisible(false);
        labelErrorVacio.setVisible(false);
        paneCrearCuenta.setVisible(false);
        ErrorPattern.setVisible(false);
    }

    /**
     * Inicializa el enlace de registro configurando su subrayado y la capacidad de enfoque.
     * 
     * <p>Este método desactiva el subrayado del enlace de registro y asegura que no pueda recibir el foco, 
     * lo que proporciona un estilo visual consistente con el resto de la interfaz.</p>
    */
    public void inicializarLink() {
        registerLink.setUnderline(false);
        registerLink.setFocusTraversable(false);
    }

    /**
     * Inicializa el botón de inicio de sesión y configura los listeners para los campos de usuario y contraseña.
     * 
     * <p>Este método establece el enfoque inicial en el campo de texto de usuario y configura el estado 
     * del botón de login. El botón de login se habilita solo cuando ambos campos de usuario y contraseña son 
     * válidos. Además, se actualiza el botón en función de los cambios en los campos de texto.</p>
    */
    public void inicializarBotonLogin() {
        Platform.runLater(() -> { textUsuario.requestFocus(); }); 

        botonLogin.setDisable(true);
        textPass.textProperty().addListener((observable, oldValue, newValue)    -> actualizarBoton(textPass, textUsuario, botonLogin));
        textUsuario.textProperty().addListener((observable, oldValue, newValue) -> actualizarBoton(textPass, textUsuario, botonLogin));
    }

    /**
     * Inicializa la sombra del panel izquierdo.
     * 
     * <p>Este método configura la sombra que se aplica al panel izquierdo de la interfaz de usuario. La sombra 
     * tiene un color oscuro, un radio de difusión de 35 píxeles y un desplazamiento de 10 píxeles en el eje X. 
     * Esto mejora el aspecto visual de la interfaz y crea una sensación de profundidad.</p>
    */
    public void iniSombraPaneIzq() {
        shadow.setColor(Color.rgb(0, 0, 0, 0.7));
        shadow.setRadius(35);
        shadow.setOffsetX(10);
        pane.setEffect(shadow);
    }

    /**
     * Inicializa el video de inicio que se reproduce en el fondo de la interfaz de usuario.
     * 
     * <p>Este método carga y configura el video que se reproduce cuando el usuario accede a la aplicación. 
     * El video se ajusta para reproducirse de forma continua e indefinida en el fondo sin preservar la relación 
     * de aspecto, lo que permite un control visual atractivo en la interfaz.</p>
    */
    public void iniVideoInicio() {
        Media videoInicio      = new Media(getClass().getResource("/video/videoInicio3.0.2.mp4").toExternalForm());
        MediaPlayer videoLogin = new MediaPlayer(videoInicio);

        viewPrincipal.setMediaPlayer(videoLogin);
        viewPrincipal.getMediaPlayer().setAutoPlay(true);
        viewPrincipal.getMediaPlayer().setCycleCount(MediaPlayer.INDEFINITE);
        viewPrincipal.setPreserveRatio(false);
    }

    /**
     * Inicializa la cuenta de administrador en la base de datos.
     * 
     * <p>Este método agrega un usuario administrador con un nombre de usuario y una contraseña predefinidos a 
     * la base de datos. Es útil para configurar una cuenta inicial de administrador para el sistema.</p>
    */
    public void iniCuentaAdmin() {
        usuarioDataBase.agregarUsuario("Caste", "Byadmin22");
    }

    /**
     * Actualiza el estado del botón de login según el contenido de los campos de usuario y contraseña.
     * 
     * <p>Este método habilita o deshabilita el botón de login en función de los valores ingresados en los 
     * campos de texto de usuario y contraseña. El botón se habilita solo cuando ambos campos tienen texto válido,
     * y se aplica un estilo diferente según el estado.</p>
     * 
     * @param textPass El campo de texto de contraseña.
     * @param textUsuario El campo de texto de usuario.
     * @param botonLogin El botón de inicio de sesión.
    */
    public void actualizarBoton(TextField textPass, TextField textUsuario, Button botonLogin) {
        if ( textPass.getText().isEmpty() || textUsuario.getText().isEmpty() || textUsuario.getLength() < 3 ) {
            botonLogin.setDisable(true);
            botonLogin.setStyle("rounded-login-button");
        } else if ( !textPass.getText().isEmpty() && !textUsuario.getText().isEmpty() && textUsuario.getLength() >= 3 ) {
            botonLogin.setDisable(false);
            botonLogin.setStyle("-fx-background-color: red;");
        }
    }

    /**
     * Actualiza el color del borde del rectángulo asociado a un campo de texto cuando recibe o pierde foco.
     * 
     * <p>Este método cambia el color de un rectángulo que rodea un campo de texto dependiendo de si el campo 
     * tiene o no el foco. Si el campo está enfocado, el color del rectángulo se actualiza al primer color proporcionado; 
     * si no tiene el foco, se cambia al segundo color.</p>
     * 
     * @param text El campo de texto cuyo enfoque se está verificando.
     * @param rectangulo El rectángulo que rodea el campo de texto.
     * @param color1 El color que se aplicará al rectángulo cuando el campo esté enfocado.
     * @param color2 El color que se aplicará al rectángulo cuando el campo no tenga foco.
    */
    public void actualizarRectangulo(TextField text, Rectangle rectangulo, Color color1, Color color2){
        if ( text.isFocused() ){
            rectangulo.setFill(color1);
        }
        if ( !text.isFocused() ){
            rectangulo.setFill(color2);
        }
    }

    /**
     * Obtiene el usuario de la sesión actual.
     * 
     * <p>Este método devuelve el objeto {@link Usuario} que representa al usuario actualmente autenticado
     * en la sesión. Puede utilizarse para acceder a los detalles del usuario durante el ciclo de vida de la aplicación.</p>
     * 
     * @return El usuario de la sesión actual.
    */
    public static Usuario getUsuarioSesion(){
        return usuarioSesion;
    }

    /**
     * Cierra la aplicación.
     * 
     * <p>Este método cierra la ventana de la aplicación actual. Al invocar este método, la ventana 
     * asociada al botón de cierre (botónCerrar) se cerrará, terminando la ejecución de la aplicación.</p>
     * 
     * @param event El evento de acción que activó el cierre de la aplicación.
    */
    @FXML
    void cerrarApp(ActionEvent event) {
        Stage stage = (Stage) botonCerrar.getScene().getWindow();
        stage.close();
    }

    /**
     * Minimiza la aplicación.
     * 
     * <p>Este método minimiza la ventana de la aplicación actual al hacerla invisible en la pantalla, 
     * moviéndola a la barra de tareas o a la bandeja del sistema.</p>
     * 
     * @param event El evento de acción que activó la minimización de la aplicación.
    */
    @FXML
    void minimizarApp(ActionEvent event) {
        Stage stage = (Stage) botonCerrar.getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Realiza el proceso de inicio de sesión de un usuario.
     * 
     * <p>Este método valida las credenciales de usuario y contraseña proporcionadas a través de los campos 
     * de texto. Si las credenciales son correctas, se inicia sesión y se redirige al usuario a la pantalla 
     * del juego. Si las credenciales son incorrectas, se muestra un mensaje de error.</p>
     * 
     * @param event El evento de acción que activó el inicio de sesión.
    */
     @FXML
     void logeo(ActionEvent event) {
         Implementacion_DAO sql = new Implementacion_DAO();
         if ( usuarioDataBase.validarUsuario(textUsuario.getText(), textPass.getText()) || sql.validarLogin(textUsuario.getText(), textPass.getText()) ) {
             loginError.setVisible(false);
             usuarioSesion = new Usuario( textUsuario.getText(), textPass.getText() );
             TransicionesControll.abrirVentana("/fxml/InGame.fxml", "", event);
         } else {
             loginError.setVisible(true);
         }
     }

    /**
     * Muestra la pantalla para la creación de una nueva cuenta.
     * 
     * <p>Este método oculta la vista principal de la aplicación y muestra el panel para la creación de una nueva 
     * cuenta de usuario. También elimina cualquier efecto visual aplicado al pane principal.</p>
     * 
     * @param event El evento de acción que activó la apertura del formulario de creación de cuenta.
    */
    @FXML
    void creacionCuenta(ActionEvent event) {
        loginError.setVisible(false);
        viewPrincipal.setVisible(false);
        paneCrearCuenta.setVisible(true);
        pane.setEffect(null);
    }

    /**
     * Crea un nuevo usuario en la base de datos.
     * 
     * <p>Este método valida los campos de usuario y contraseña proporcionados por el usuario para la creación 
     * de una nueva cuenta. Verifica si los campos no están vacíos, si las contraseñas coinciden, si los valores 
     * cumplen con los patrones establecidos, y si el nombre de usuario ya existe. Si todas las validaciones son 
     * correctas, el usuario es agregado a la base de datos.</p>
     * 
     * @param event El evento de acción que activó la creación de la cuenta.
    */
    @FXML
    void crearSQL(ActionEvent event) {
        Implementacion_DAO sql = new Implementacion_DAO();

        if ( crearPass.getText().isEmpty() || crearUsuario.getText().isEmpty() || crearPassRepeat.getText().isEmpty() ) {
            labelErrorVacio.setVisible(true);
        } else if ( !isValidoPass(textPass) || !isValidoUser(textUsuario) ) {
            labelErrorVacio.setVisible(false);
            ErrorPattern.setVisible(true);
        } else if ( !crearPass.getText().equals(crearPassRepeat.getText()) ) {
            labelErrorVacio.setVisible(false);
            ErrorPattern.setVisible(false);
            labelErrorPass.setVisible(true);
        } else if ( usuarioDataBase.ifExist(crearUsuario.getText()) || sql.validarCreacion(crearUsuario.getText()) ) {
            labelErrorVacio.setVisible(false);
            ErrorPattern.setVisible(false);
            labelErrorPass.setVisible(false);
            labelErrorUser.setVisible(true);
        } else {
            labelErrorVacio.setVisible(false);
            ErrorPattern.setVisible(false);
            labelErrorPass.setVisible(false);
            labelErrorUser.setVisible(false);
            sql.insert(crearUsuario.getText(), crearPass.getText());
        }
    }

    /**
     * Cancela la creación de la cuenta y vuelve a la vista principal.
     * 
     * <p>Este método oculta el panel de creación de cuenta y muestra nuevamente la vista principal de la aplicación. 
     * Además, limpia cualquier mensaje de error y aplica el efecto de sombra al panel principal.</p>
     * 
     * @param event El evento de acción que activó la cancelación de la creación de cuenta.
    */
    @FXML
    void cancelarSQL(ActionEvent event) {
        viewPrincipal.setVisible(true);
        paneCrearCuenta.setVisible(false);
        labelErrorVacio.setVisible(false);
        labelErrorUser.setVisible(false);
        labelErrorPass.setVisible(false);
        pane.setEffect(shadow);
    }
}

