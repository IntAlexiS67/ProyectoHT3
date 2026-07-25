package org.ad.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.ad.dao.UsuarioDAO;
import org.ad.model.Usuario;
import org.ad.util.SecurityUtil;

public class InicioSesionController implements Initializable {
    
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Button txtIniciarSesion;
    @FXML private Label lblMensaje;
    
    private UsuarioDAO usuarioDAO;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDAO = new UsuarioDAO();
        lblMensaje.setText("");
    }    
    
    @FXML
    public void evetoInicioSesion(ActionEvent evento){
        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();
        
        // Verificación si los datos están vacíos
        if (usuario.isEmpty() || password.isEmpty()) {
            lblMensaje.setStyle("-fx-background-color: #f8d7da; -fx-text-fill: #721c24; -fx-padding: 5px;");
            lblMensaje.setText("Por favor, complete todos los datos");
            return;
        }
        
        String passwordHash = SecurityUtil.hashSHA256(password);
        
        // Llamar al DAO para iniciar sesión
        Usuario usuarioIniciado = usuarioDAO.iniciarSesion(usuario, passwordHash);
        
        if(usuarioIniciado != null){
            lblMensaje.setText("Inicio correcto");
            abrirDashboard(usuarioIniciado, evento);
        } else{
            lblMensaje.setText("Usuario o contraseña incorrecta");
        }
    }

    private void abrirDashboard(Usuario usuario, ActionEvent evento){
        String rutaFXML = "";
        String tituloDashboard = "";
        
        switch(usuario.getRol().toLowerCase()){
            case "admin":
                rutaFXML = "/org/ad/view/AdminDashboardView.fxml";
                tituloDashboard = "Panel de Administración";
                break;
            case "empleado":
                rutaFXML = "/org/ad/view/EmpleadoDashboardView.fxml";
                tituloDashboard = "Panel de Empleado";
                break;
        }
        
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent raiz = cargadorFXML.load();
            
            // Inyectar usuario al controlador correspondiente según el rol
            switch(usuario.getRol().toLowerCase()){
                case "admin":
                    AdminDashboardController ctrlAdmin = cargadorFXML.getController();
                    ctrlAdmin.iniciarUsuario(usuario);
                    break;
                case "empleado":
                    EmpleadoDashboardController ctrlEmpleado = cargadorFXML.getController();
                    ctrlEmpleado.iniciarUsuario(usuario);
                    break;
            }
            
            Stage escenario = new Stage();
            escenario.setScene(new Scene(raiz));
            escenario.setTitle(tituloDashboard);
            escenario.show();
            
            // Cierra la ventana actual usando el evento
            Stage escenaActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();
            escenaActual.close();
            
        } catch (IOException e) {
            System.err.println("Error al cargar la vista : " + e.getMessage());
            lblMensaje.setText("Error interno");
        }
    }
}