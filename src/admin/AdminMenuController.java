/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author EVELYN
 */
public class AdminMenuController implements Initializable {
    @FXML
    public static AnchorPane AdMenu;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;

    public static AnchorPane AdMenup;
    @FXML
    public static AnchorPane anchorP;
    @FXML
    private FontAwesomeIconView close1;
    @FXML
    private Button Confirm;
    @FXML
    private Button nope;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         AdMenu = AdMenup;
           
        try {
            
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(AdminMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            transition.setRate(transition.getRate()*-1);
            transition.play();
            
            if(drawer.isShown())
            {
                drawer.close();
            }else
                drawer.open();
        });  
    }    

    @FXML
    private void handlePopcl(MouseEvent event) {
        anchorP.setVisible(false);
    }

    @FXML
    private void handleCon(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleNope(ActionEvent event) {
        anchorP.setVisible(false);
    }
    
}
