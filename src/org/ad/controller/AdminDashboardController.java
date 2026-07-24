
package org.ad.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.ad.model.Usuario;


public class AdminDashboardController implements Initializable {
    @FXML  private Label lblBienvenida;
    private Usuario UsuarioActual;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void iniciarUsuario(Usuario usuario){
      this.UsuarioActual = usuario;
              lblBienvenida.setText("Bienvenido administrador "+ usuario.getUsername());
    }
    
    
}
