/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import alerts.AlertTemplates;
import aptech_soft.DBTemplates.DatabaseTemp;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tables.OpentabModel;

/**
 * FXML Controller class
 *
 * @author EVELYN
 */


public class ManageOpenController implements Initializable {

    @FXML
    private JFXButton erase;
    
     @FXML
    private AnchorPane anchorP;

    @FXML
    private AnchorPane manaopenanchor;

    DatabaseTemp dbt = new DatabaseTemp();
    ObservableList<OpentabModel> tab = FXCollections.observableArrayList();
    AlertTemplates at = new AlertTemplates();
    ObservableList<String> course = FXCollections.observableArrayList("Select Course", "C#", "Java");
    @FXML
    private ComboBox<String> selectcourse;
    @FXML
    private TableColumn<OpentabModel, String> Sno;
    @FXML
    private TableColumn<OpentabModel, String> Quest;
    @FXML
    private TableColumn<OpentabModel, String> opa;
    @FXML
    private TableColumn<OpentabModel, String> Opb;
    @FXML
    private TableColumn<OpentabModel, String> Opc;
    @FXML
    private TableColumn<OpentabModel, String> Ans;
    @FXML
    public TableView<OpentabModel> Qtable;
    @FXML
    private ImageView backtomana;
    @FXML
    private FontAwesomeIconView close;
    @FXML
    private Button Confirm;
    @FXML
    private Button nope;

    /**
     *
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO
        initColumn();
        selectcourse.setItems(course);
        selectcourse.setValue("Select Course");
    }

    private void initColumn() {
        Sno.setCellValueFactory(new PropertyValueFactory<>("Qid"));
        Quest.setCellValueFactory(new PropertyValueFactory<>("Quest"));
        opa.setCellValueFactory(new PropertyValueFactory<>("op"));
        Opb.setCellValueFactory(new PropertyValueFactory<>("opi"));
        Opc.setCellValueFactory(new PropertyValueFactory<>("opii"));
        Ans.setCellValueFactory(new PropertyValueFactory<>("answer"));
    }

    @FXML
    private void eraseAll(ActionEvent event) throws SQLException, IOException {

        if (selectcourse.getValue().equals("C#")) {
               anchorP.setVisible(true);
     
        } else {
            at.alertWarning("Could not delete. Something went wrong.");
        }
        
         if (selectcourse.getValue().equals("Java")) {
               anchorP.setVisible(true);
     
        } else {
            at.alertWarning("Could not delete. Something went wrong.");
        }
    }
//           String qery = "select from csharp";
//             dbt.updates(qery);

    @FXML
    private void openquest(ActionEvent event) {

        if (selectcourse.getValue().equals("Java")) {
            try {
                tab.removeAll(tab) ;
                ResultSet rs;
                String query = "Select * from javaquest";
                rs = dbt.readFromDB(query);

                while (rs.next()) {
                    
                    String QuestionNum = rs.getString("questid");
                    String Question = rs.getString("questions");
                    String Option1 = rs.getString("opta");
                    String Option2 = rs.getString("optb");
                    String Option3 = rs.getString("optc");
                    String Ansr = rs.getString("answers");
                    
                    tab.add(new OpentabModel(QuestionNum, Question, Option1, Option2, Option3, Ansr));
                    Qtable.getItems().setAll(tab);

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        if (selectcourse.getValue().equals("C#")) {
            try {
                tab.removeAll(tab) ;
                ResultSet rs;
                String query = "Select * from csharp";
                rs = dbt.readFromDB(query);
                while (rs.next()) {
                    
                    String QuestionNum = rs.getString("questid");
                    String Question = rs.getString("questions");
                    String Option1 = rs.getString("opta");
                    String Option2 = rs.getString("optb");
                    String Option3 = rs.getString("optc");
                    String Ansr = rs.getString("answers");
                    tab.add(new OpentabModel(QuestionNum, Question, Option1, Option2, Option3, Ansr));
                    Qtable.getItems().setAll(tab);

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

//        
    }

    @FXML
    private void handleback(MouseEvent event) throws IOException {
        FXMLLoader ld = new FXMLLoader();
        ld.setLocation(getClass().getResource("/admin/ManaQuest.fxml"));
        AnchorPane an = ld.load();
        Stage st = new Stage();
        st.setScene(new Scene(an));
        st.setTitle("Manage Questions");
        st.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png")));
        st.setResizable(false);
        st.show();
        Stage sa = (Stage) manaopenanchor.getScene().getWindow();
        sa.close();

    }
    
   

    @FXML
    private void paulson(MouseEvent event) {
    }

    @FXML
    private void handlePopcl(MouseEvent event) {
         Stage stage = (Stage)anchorP.getScene().getWindow();
       stage.close();
    }

    @FXML
    private void handleCon(ActionEvent event) {
         
        if(selectcourse.getValue().equals("C#")){
            String query = "delete from csharp";  
        try {
            if(dbt.updates(query)){
                 tab.removeAll(tab);
                at.alertConfirm("success");
                Qtable.getItems().setAll();
            }
            else{
                at.alertWarning("Could not delete. Something went wrong.");
            }
        } catch (SQLException ex) {
          at.alertWarning(ex.getMessage());
        }}
        anchorP.setVisible(false);
        
         if(selectcourse.getValue().equals("Java")){
            String query = "delete from csharp";  
        try {
            if(dbt.updates(query)){
                tab.removeAll(tab);
                at.alertConfirm("success");
                Qtable.getItems().setAll();
            }
            else{
                at.alertWarning("Could not delete. Something went wrong.");
            }
        } catch (SQLException ex) {
          at.alertWarning(ex.getMessage());
        }}
        anchorP.setVisible(false);
        
        
    }

    @FXML
    private void handleNope(ActionEvent event) {
        anchorP.setVisible(false);
    }


}
