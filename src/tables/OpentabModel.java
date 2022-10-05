/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author EVELYN
 */
public class OpentabModel {
    private final SimpleStringProperty qid;
     private final SimpleStringProperty quest;
      private final SimpleStringProperty op;
       private final SimpleStringProperty opi;
       private final SimpleStringProperty opii;
       private final SimpleStringProperty answer;
       
       public OpentabModel(String Qid,String Quest,String Op1,String Op2,String Op3,String Answer){
        this.qid =new SimpleStringProperty(Qid);
        this.quest=new SimpleStringProperty(Quest);
        this.op = new SimpleStringProperty(Op1);
        this.opi= new SimpleStringProperty(Op2);
        this.opii = new SimpleStringProperty(Op3);
        this.answer= new SimpleStringProperty(Answer);
        
       }

    public String getQid() {
        return qid.get();
    }

    public String getQuest() {
        return quest.get();
    }

    public String getOp() {
        return op.get();
    }

    public String getOpi() {
        return opi.get();
    }

    public String getOpii() {
        return opii.get();
    }

    public String getAnswer() {
       return answer.get();
    }

    public SimpleStringProperty getQidProperty() {
        return qid;
    }

    public SimpleStringProperty getQuestProperty() {
        return quest;
    }

    public SimpleStringProperty getOpProperty() {
        return op;
    }

    public SimpleStringProperty getOpiProperty() {
        return opi;
    }

    public SimpleStringProperty getOpiiProperty() {
        return opii;
    }

    public SimpleStringProperty getAnswerProperty() {
        return answer;
    }
    
}
