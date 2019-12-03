/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

import java.sql.Date;

/**
 *
 * @author chush
 */
public class SchoolDayBean {
    private String cid;
    private Date date;
    
    public SchoolDayBean(){
    }
    
    public SchoolDayBean(String cid, Date date){
        this.cid = cid;
        this.date = date;
    }
    
    public String getCid(){
        return cid; 
    }
    
    public void setCid(String cid){
        this.cid = cid;
    }
    
    public Date getDate(){
        return date; 
    }
    
    public void setDate(Date date){
        this.date = date;
    }

    @Override
    public String toString(){
        return "Cid: " + cid + ", Date: " + date;
    }
}
