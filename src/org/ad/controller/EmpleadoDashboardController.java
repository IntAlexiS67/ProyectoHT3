package org.ad.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent; 
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.ad.model.Usuario;

public class EmpleadoDashboardController implements Initializable {

    @FXML private Label lblBienvenida;
    @FXML private Button btnSalir;

    private Usuario usuarioActual;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ...
    }    

    public void iniciarUsuario(Usuario usuario) {
        this.usuarioActual = usuario;
        if (lblBienvenida != null && usuario != null) {
            lblBienvenida.setText("Bienvenido empleado " + usuario.getUsername());
        }
    }

    @FXML
    public void eventoCerrarSesion(ActionEvent event) {
        System.out.println("Cerrando sesión de empleado...");
    }
}