/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alerts;

import javafx.scene.control.Alert;

/**
 *
 * @author Mykeplus
 */
public class AlertTemplates {
    
    Alert a;
    
    public void alertError(String context){
        a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Error");
        a.setContentText(context);
        a.show();
    }
    
    public void alertWarning(String context){
        a = new Alert(Alert.AlertType.WARNING);
        a.setHeaderText("Warning");
        a.setContentText(context);
        a.show();
    }
    
    public void alertInfo(String context){
        a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("Info");
        a.setContentText(context);
        a.show();
    }
    
    public void alertConfirm(String context){
        a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setHeaderText("confirm");
        a.setContentText(context);
        a.show();
    }
    
}
