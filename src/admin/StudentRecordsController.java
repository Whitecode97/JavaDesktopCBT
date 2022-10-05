/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import alerts.AlertTemplates;
import aptech_soft.DBTemplates.DatabaseTemp;
import com.jfoenix.effects.JFXDepthManager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tables.StudtabModel;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StudentRecordsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private AnchorPane studanchor;

   
      AlertTemplates at = new AlertTemplates();
    
    DatabaseTemp dt = new DatabaseTemp();
    @FXML
    private Pane okpane;
    @FXML
    private ComboBox<String> selectcourse;
    @FXML
    private ImageView back;
    @FXML
    private TableView<StudtabModel> studTable;
    @FXML
    private TableColumn<StudtabModel,String> colReg;
    @FXML
    private TableColumn<StudtabModel, String> colNam;
    @FXML
    private TableColumn<StudtabModel, String> colCourse;
    @FXML
    private TableColumn<StudtabModel, String> colScores;
//    @FXML
//    private TableColumn<StudtabModel, String> colGrade;
    @FXML
    private AnchorPane anchorP;
    @FXML
    private FontAwesomeIconView close;
    @FXML
    private Button Confirm;
    @FXML
    private Button nope;
    
    ObservableList<StudtabModel>list = FXCollections.observableArrayList();
    ObservableList<String> course = FXCollections.observableArrayList("Select Course", "C#", "Java");
    @FXML
    private ImageView cleer;
    
     @FXML
    void back(MouseEvent event) throws IOException {
     FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/admin/AdminMenu.fxml"));
        AnchorPane ap = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(ap));
        stage.setTitle("Admin-Menu");
        stage.setResizable(false);
        stage.show();
        Stage s = (Stage)studanchor.getScene().getWindow();
        s.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
      //To change body of generated methods, choose Tools | Templates.
        JFXDepthManager.setDepth(okpane,2);
        JFXDepthManager.setDepth(studTable,2);
         selectcourse.setItems(course);
        selectcourse.setValue("Select Course");
        cleer.setVisible(false);
        initColumn();
    }

    @FXML
    private void openRec(ActionEvent event) {
        String cs ="C#";
        
        String coss ="Java";
        
        if(selectcourse.getValue().equals("C#")){
            
        try {
            list.removeAll(list);
            ResultSet rs;
            String query = "Select * from student_signup where course = '"+cs+"' ";
            rs = dt.readFromDB(query);
            while (rs.next()){
                String regis = rs.getString("regno");
                String names = rs.getString("studname");
                String cors = rs.getString("course");
                String sco = rs.getString("scores");
//                String gr = rs.getString("grade");
                
                list.add(new StudtabModel(regis, names, cors, sco));
                studTable.getItems().setAll(list);  
//                return;
            }
           // selectcourse.setValue("Select Course");
        } catch (SQLException ex) {
            at.alertError(ex.getMessage());
        }
        
        }
        
       if(selectcourse.getValue().equals("Java")){
            
        try {
            list.removeAll(list);
            ResultSet rs;
            String query = "Select * from student_signup where course = '"+coss+"'";
//             String query = "Select course from student_signup group by regno having count(1)>1";
            rs = dt.readFromDB(query);
            while (rs.next()){
                
                String regis = rs.getString("regno");
                String names = rs.getString("studname");
                String cors = rs.getString("course");
                String sco = rs.getString("scores");
//                String gr = rs.getString("grade"); 
                list.add(new StudtabModel(regis, names, cors, sco));
                studTable.getItems().setAll(list);               
//                return;
            }
           // selectcourse.setValue("Select Course");
        } catch (SQLException ex) {
            at.alertError(ex.getMessage());
        }
        
        }
               
        
    }

    @FXML
    private void handlePopcl(MouseEvent event) {
    }

    @FXML
    private void handleCon(ActionEvent event) {
    }

    @FXML
    private void handleNope(ActionEvent event) {
    }

    
    
    private void initColumn() {
     colReg.setCellValueFactory(new PropertyValueFactory<>("rno"));
     colNam.setCellValueFactory(new PropertyValueFactory<>("snam"));
     colCourse.setCellValueFactory(new PropertyValueFactory<>("cos"));
     colScores.setCellValueFactory(new PropertyValueFactory<>("scr"));
//     colGrade.setCellValueFactory(new PropertyValueFactory<>("grad"));
    }

    @FXML
    private void clearTable(MouseEvent event) {
        list.removeAll(list);
        studTable.getItems().removeAll(list);
    }
    
    
    
}

   
    

