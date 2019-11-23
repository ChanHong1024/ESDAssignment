/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.bean;

/**
 *
 * @author Chan Wai Hong / Chu Shing Fung
 */
public class AccountBean {
    private String aid;
    private String cid;
    private String role;
    private String firstname;
    private String lastname;
    private String password;
    
    public AccountBean(){
        
    }
    
    public AccountBean(String aid,String cid,String role,String firstname,String lastname,String password){
        this.aid = aid;
        this.cid = cid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.role = role;
    }
    
    public void setAid(String aid){
        this.aid = aid;  
    }
    public void setCid(String cid){
        this.cid = cid;
    }
    public void setFirstName(String firstname){
        this.firstname = firstname;
    }
    public void setLastName(String lastname){
        this.lastname = lastname;
    }
    public void setPassword(String password){
        this.password = password;  
    }
    public void setRole(String role){
        this.role = role;
    }
    public String getAid(){
        return aid;
    }
    public String getCid(){
        return cid;
    }
    public String getFirstName(){
        return firstname;
    }
    public String getLastName(){
        return lastname;
    }
    public String getPassword(){
        return password;
    }
    public String getRole(){
        return role;
    }
    
    @Override
    public String toString(){
        return "Aid: "+aid+", Cid: "+cid+", First Name: "+firstname+", Last Name: "+lastname+"Password: "+password;
    }
}
