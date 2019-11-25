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
public class AttendanceBean {
    private String date;
    private String cid;
    private String aid;
    private boolean status;
    
    public AttendanceBean(){
        
    }
     
    public AttendanceBean(String date,String cid,String aid,boolean status){
        this.aid = aid;
        this.cid = cid;
        this.date = date;
        this.status = status;
    }
    
    public String getAid(){
        return aid; 
    }
    
    public void setAid(String aid){
        this.aid = aid;
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
    
    public boolean getStatus(){
        return status; 
    }
    
    public void setStatus(boolean status){
        this.status = status;
    }
    
    @Override
    public String toString(){
        return "Date: " + date + ", Cid: " + cid + ", Aid: " + aid + ", Status: " + status;
    }
}
