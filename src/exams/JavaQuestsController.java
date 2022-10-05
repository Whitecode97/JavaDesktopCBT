/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exams;

import alerts.AlertTemplates;
import aptech_soft.DBTemplates.DatabaseTemp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.effects.JFXDepthManager;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author EVELYN
 */
public class JavaQuestsController implements Initializable {
    
     static boolean state = true;
    static int seconds = 59;
    static int minutes = 2 ;
    static int hours = 0;
//    int questionNum = 0 ;
    
    @FXML
    private Text fxHours;
    @FXML
    private Text fxMinutes;
    @FXML
    private Text Seconds ;
    
    @FXML
    private Pane pagpane;
    @FXML
    private Pane opane;
    @FXML
    private Label Abox;
    @FXML
    private Label Bbox;
    @FXML
    private Label Cbox;
    @FXML
    private JFXRadioButton optnA;
    @FXML
    private ToggleGroup options;
    @FXML
    private JFXRadioButton optnB;
    @FXML
    private JFXRadioButton optnC;
    @FXML
    private Label Qbox;
    @FXML
    private ImageView prevarr;
//     RadioButton rad = (RadioButton) options.getSelectedToggle();
//        String radbtn = rad.getText();

    /**
     * Initializes the controller class.
     */
    
    AlertTemplates at = new AlertTemplates();
    DatabaseTemp dbt = new DatabaseTemp();
     @FXML
    private Pane loadpane;
    @FXML
    private AnchorPane cshapanchor;
    @FXML
    private JFXButton c1;
    @FXML
    private JFXButton c2;
    @FXML
    private JFXButton c3;
    @FXML
    private JFXButton c4;
    @FXML
    private JFXButton c5;
    @FXML
    private JFXButton c6;
    @FXML
    private JFXButton c7;
    @FXML
    private JFXButton c8;
    @FXML
    private JFXButton c9;
    @FXML
    private JFXButton c10;
    @FXML
    private Label numr;  
    @FXML
    private Button sbuttn;
     int scores1 = 0;
    String qid;
    int questNum =1;
 
    ResultSet rs = null;
    @FXML
    private Pane resultpane;
    @FXML
    private Label gradelabel;
    @FXML
    private FontAwesomeIconView thumbsup;
    @FXML
    private Label scorelabel;
    @FXML
    private FontAwesomeIconView thumbsdown;
    @FXML
    private Button nextbtn;
    @FXML
    private Button backbtn;
    @FXML
    private Pane timerpane;
    Thread t ;
    boolean shouldThreadStop = false;
    @FXML
    private FontAwesomeIconView closit;
    private void timer(){
        state = true;
        t = new Thread(){
            @Override
            public void run(){
               
                for(;;){
                     if(state==true ){
                 try
                 {
                     sleep(1000);
                     
                     
                     if(seconds<0)
                     {
                         
                         seconds=59;
                        minutes--;
                     }
                     if(minutes<0)
                     {
                         
                         seconds = 59;
                         minutes = 59;
                         hours--;
                     }
                     if(minutes<10){
                         fxMinutes.setText("0" + minutes);
                     }
                     if(hours < 0){
                         shouldThreadStop = true;
                         backbtn.setVisible(false);
                         nextbtn.setVisible(false);
                         sbuttn.setVisible(true);
                         optnA.setDisable(false);
                         Platform.runLater(()->at.alertInfo("Time up! \nPlease hit the submit button..."));
                         optnA.setDisable(true);
                         optnB.setDisable(true);
                         optnC.setDisable(true);
                         
                         
                         break;
                     }  
                     Seconds.setText("" + seconds);
                     if(seconds<10){
                         Seconds.setText("0" + seconds);
                     }
                     
                     
                     seconds--;
                     fxMinutes.setText("0" + minutes);
                     fxHours.setText("0" + hours);
                 }
                 catch(InterruptedException e)
                 {
                     at.alertWarning(e.getMessage());
                 }
                } else{
                         break;
                     }
                     if (shouldThreadStop == true){
                         break;
                     }
                     if (resultpane.isVisible()){break;}
                }
                }
            
        };
        t.start();
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
         
        timer();
        
        JFXDepthManager.setDepth(opane, 5);
        JFXDepthManager.setDepth(pagpane, 5);
        questNum = 1 ;
        try {
         
            String query = "SELECT * from javaquest";
            rs = dbt.readFromDB(query);
            rs.next();
            //c1.setStyle("-fx-bc1order-color:red");
            //String answ = "update csharp set studans ='"+radbtn+"' where questid = '"+questNum+"' ";
               //dbt.updates(answ);
                qid = rs.getString("questid");
                String q = rs.getString("questions");
                String opt1 = rs.getString("opta");
                String opt2 = rs.getString("optb");
                String opt3 = rs.getString("optc");                
                numr.setText(rs.getString("questid"));
                Qbox.setText(q);
                Abox.setText(opt1);
                Bbox.setText(opt2);
                Cbox.setText(opt3);
                this.pageCount(questNum);
        } catch (SQLException ex) {
            at.alertError(ex.getMessage());
        }
       
    }  
    int x = 10 ;
    @FXML
    private void next(ActionEvent event) throws SQLException {   
        String selectedAnswer = "";
        
        if (optnA.isSelected()) {
            selectedAnswer = "A";
        }
        else if(optnB.isSelected()){
            selectedAnswer = "B";            
        }
        else if(optnC.isSelected()){
            selectedAnswer = "C";
        }
        else{selectedAnswer = " ";}
        String query = "update Javaquest set student_ans ='"+selectedAnswer+"' where questid = '"+questNum+"' ";   
        
        try{
            if(this.dbt.updates(query)){
                questNum ++ ;
                String fetch = "select * from javaquest where questid = '"+questNum+"'" ;
                
                try{
                    ResultSet rs = dbt.readFromDB(fetch) ;
                    questNum--;
                    
                    if(rs.next()){
                        String q = rs.getString("questions");
                        String opt1 = rs.getString("opta");
                        String opt2 = rs.getString("optb");
                        String opt3 = rs.getString("optc");
                        numr.setText(rs.getString("questid"));
                        Qbox.setText(q);
                        Abox.setText(opt1);
                        Bbox.setText(opt2);
                        Cbox.setText(opt3);
                        questNum++;
                        this.pageCount(questNum);
                    }
                    else{at.alertInfo("you are done! please submit!"); sbuttn.setVisible(true);}
                }catch(SQLException ex){
                    System.err.println("this is what happened 257 " + ex.getMessage());
                }
            }
        }catch(SQLException ex)
        {
            System.err.println("this is what happened 262 " + ex.getMessage());           
        }
    }

    
    @FXML
    private void prev(ActionEvent event) throws SQLException {
        String selectedAnswer = "" ;
        String query = "update javaquest set student_ans ='"+selectedAnswer+"' where questid = '"+qid+"' ";   
        
        if (optnA.isSelected()) {
            selectedAnswer = "A";
        }
        else if(optnB.isSelected()){
            selectedAnswer = "B";            
        }
        else if(optnC.isSelected()){
            selectedAnswer = "C";
        }
        
        try{
            if(this.dbt.updates(query)){
                this.questNum -- ;
                String fetch = "select * from csharp where questid = '"+questNum+"'" ;
                
                try{
                    ResultSet rs = dbt.readFromDB(fetch) ;
                    
                    if(rs.next()){
                        String q = rs.getString("questions");
                        String opt1 = rs.getString("opta");
                        String opt2 = rs.getString("optb");
                        String opt3 = rs.getString("optc");
                        numr.setText(rs.getString("questid"));
                        Qbox.setText(q);
                        Abox.setText(opt1);
                        Bbox.setText(opt2);
                        Cbox.setText(opt3);
                        this.pageCount(questNum);
                    }
                }catch(SQLException ex){
                    System.err.println("this is what happened 307 " + ex.getMessage());
                }
            }
        }catch(SQLException ex)
        {
            System.err.println("this is what happened 312 " + ex.getMessage());           
        }
    }

    public void pageCount(int page){
                if (questNum == 1)
                    c1.setStyle("-fx-border-color:red");else c1.setStyle("");
                if (questNum == 2)
                    c2.setStyle("-fx-border-color:red"); else c2.setStyle("");
                if (questNum == 3)
                    c3.setStyle("-fx-border-color:red");else c3.setStyle("");
                if (questNum == 4)
                    c4.setStyle("-fx-border-color:red");else c4.setStyle("");
                if (questNum == 5)
                    c5.setStyle("-fx-border-color:red");else c5.setStyle("");
                if (questNum == 6)
                    c6.setStyle("-fx-border-color:red");else c6.setStyle("");
                if (questNum == 7)
                    c7.setStyle("-fx-border-color:red");else c7.setStyle("");
                if (questNum == 8)
                    c8.setStyle("-fx-border-color:red");else c8.setStyle("");
                if (questNum == 9)
                    c9.setStyle("-fx-border-color:red");else c9.setStyle("");
                if (questNum == 10){
                    c10.setStyle("-fx-border-color:red");
                    this.sbuttn.setVisible(true);
                }
                else c10.setStyle("");
                
                if(page > 1){
                    this.backbtn.setVisible(true);
                }else{
                    this.backbtn.setVisible(false);                    
                }
                if(page == 10){
                    this.sbuttn.setVisible(false);                    
                }
                
                optnA.setSelected(false);
                optnB.setSelected(false);
                optnC.setSelected(false);
    }
    public void checkAnswer(String a, String b){
//        System.out.println("yahh I ran");
        if(a.equals(b)){
            this.scores1 +=2 ;
        }
    }
    
    @FXML
    private void sumit(ActionEvent event) {
//        if(questNum >9){
        sbuttn.setVisible(false);
            // calculate scores
            try{
                ResultSet rs = dbt.readFromDB("select * from javaquest") ;
                while(rs.next()){
                    String studAns = rs.getString("answers") ;
                    String corAns = rs.getString("student_ans") ;
                    this.checkAnswer(studAns, corAns);
                }System.out.println("score is " + scores1);
            }catch(SQLException ex){
                System.out.println(" this is the error " + ex.getMessage());
            }
            
//        }
        loadpane.setVisible(true);
        PauseTransition transition = new PauseTransition();
        transition.setDuration(Duration.seconds(10));
        transition.setOnFinished(e -> {
            
                loadpane.setVisible(false);
                 try {
             
           String student = InstructionPageController.reg;
           
            String scoresupd = "update student_signup set scores = '"+scores1+"' where regno = '"+student+"'";
            rs.next();
            dbt.updates(scoresupd);
            //at.alertInfo("your scores:"+" "+scores);
            timerpane.setVisible(false);
            nextbtn.setVisible(false);
            backbtn.setVisible(false);
            resultpane.setVisible(true);
        
        String selscore = "select scores from SecureBell.student_signup where regno ='"+student+"'";
       // String getscores = rs.getString("scores");
         dbt.readFromDB(selscore);
        
        if (scores1==18 || scores1>=20){
            String sc = String.valueOf(scores1);
            scorelabel.setText(sc);
            gradelabel.setText("A");
            thumbsup.setVisible(true);  /*||scores==15||scores==16*/
        }else 
        if(scores1==16){
            String sc = String.valueOf(scores1);
            scorelabel.setText(sc);
            gradelabel.setText("B");
            thumbsup.setVisible(true);
        }else 
           if(scores1==10||scores1==12||scores1==14) {
               String sc = String.valueOf(scores1);
         scorelabel.setText(sc);
        gradelabel.setText("C");
        thumbsup.setVisible(true);
        }else 
               if(scores1==0||scores1==2||scores1==4||scores1==6||scores1==8) {
          String sc = String.valueOf(scores1);
         scorelabel.setText(sc);
        gradelabel.setText("FAILED");
        thumbsup.setVisible(false);
               thumbsdown.setVisible(true);}else{scorelabel.setText("0");
        gradelabel.setText("FAILED");
        thumbsup.setVisible(false);
               thumbsdown.setVisible(true);}
        } catch (SQLException ex) {
            at.alertWarning(ex.getMessage());
        }


            });
        transition.play();
        
       
    }

    @FXML
    void quitbtn(ActionEvent event) throws IOException {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/SignIn/Sign_Inp.fxml"));
                AnchorPane ap = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(ap));
                stage.setTitle("Sign_In");
                stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/2-2-education-picture.png" )));
                stage.setResizable(false);
                stage.show();
                Stage s = (Stage)cshapanchor.getScene().getWindow();
                s.close();
    }

    @FXML
    private void closeit(MouseEvent event) {
        resultpane.setVisible(false);
    }
    
    
    
    }