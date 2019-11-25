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
public class ClassBean {
    private String cid;
    private String className;
    private int population=0;
    
    public ClassBean(String cid, String className){
        this.cid = cid;
        this.className = className;
    }

    public ClassBean() {
        
    }
    
    public String getCid(){
        return cid; 
    }
    
    public void setCid(String cid){
        this.cid = cid;
    }
    
    public int getPopulation(){
        return population; 
    }
    
    public void setPopulation(int population){
        this.population = population;
    }
    
    public String getClassName(){
        return className; 
    }
    
    public void setClassName(String className){
        this.className = className;
    }

    @Override
    public String toString(){
        return "Class name: " + className + ", Cid: " + cid ;
    }
}