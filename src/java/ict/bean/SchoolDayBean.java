/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author chush
 */
public class SchoolDayBean {
    private String cid;
    private String date;
    
    public SchoolDayBean(){
    }
    
    public SchoolDayBean(String cid, String date){
        this.cid = cid;
        this.date = date;
    }
    
    public String getCid(){
        return cid; 
    }
    
    public void setCid(String cid){
        this.cid = cid;
    }
    
    public String getDate(){
        return date; 
    }
    
    public void setDate(String date){
        this.date = date;
    }

    @Override
    public String toString(){
        return "Cid: " + cid + ", Date: " + date;
    }
}
