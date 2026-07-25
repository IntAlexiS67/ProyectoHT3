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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.ad.model.Usuario;

public class CajeroDashboardController implements Initializable {

    @FXML private Label lblBienvenida;
    @FXML private Button btnSalir;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización básica si es necesaria
    }    

    public void iniciarUsuario(Usuario usuario) {
        if (usuario != null) {
            lblBienvenida.setText("Bienvenido cajero " + usuario.getUsername());
        }
    }

    @FXML
    private void eventoCerrarSesion(ActionEvent evento) {
        try {
            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/org/ad/view/InicioSesionView.fxml"));
            Parent raiz = cargadorFXML.load();
            
            Stage escenario = new Stage();
            escenario.setScene(new Scene(raiz));
            escenario.setTitle("Inicio de Sesión");
            escenario.show();

            // Cerrar la ventana actual
            Stage escenaActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();
            escenaActual.close();

        } catch (IOException e) {
            System.err.println("Error al regresar a la vista de login: " + e.getMessage());
        }
    }
}
