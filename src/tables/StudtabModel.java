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
public class StudtabModel {
    private final SimpleStringProperty rno;
    private final SimpleStringProperty snam;
    private final SimpleStringProperty cos;
    private final SimpleStringProperty scr;
//    private final SimpleStringProperty grad;
   public StudtabModel(String rno,String snam,String cos,String scr)
    {
       this.rno = new SimpleStringProperty(rno);
       this.snam = new SimpleStringProperty(snam);
       this.cos = new SimpleStringProperty(cos);
       this.scr = new SimpleStringProperty(scr);
//       this.grad = new SimpleStringProperty(grad);
    }

    public String getRno() {
       return rno.get();
       
    }

    public String getSnam() {
        return snam.get();
    }

    public String getCos() {
        return cos.get();
    }

    public String getScr() {
        return scr.get();
    }

//    public String getGrad() {
//        return grad.get();
//    }

    public SimpleStringProperty getRnoProperty() {
        return rno;
    }

    public SimpleStringProperty getSnamProperty() {
        return snam;
    }

    public SimpleStringProperty getCosProperty() {
        return cos;
    }

    public SimpleStringProperty getScrProperty() {
        return scr;
    }

//    public SimpleStringProperty getGradProperty() {
//        return grad;
//    }
   
   
}