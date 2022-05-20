package com.model;

public class UserSecret {
    private String accno;
    private String secret;
    public UserSecret(String accno,String secret){
        this.accno = accno;
        this.secret = secret;
    }
    public void setAccno(String accno){
        this.accno=accno;
    }
    public void setSecret(String secret){
        this.secret=secret;
    }
    public String getAccno(){
        return accno;
    }
    public String getSecret(){
        return secret;
    }
}
