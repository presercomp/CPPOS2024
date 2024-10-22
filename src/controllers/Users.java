package controllers;

public class Users {
    private int id;
    private String nickname;
    private String secret;
    private boolean active;
    
    public Users(){
        //nothing to do here
    }
    
    public Users(String n, String s){
        this.nickname = n;
        this.secret = s;
    }
    public Users(int i, String n, String s, boolean a){
        this.id = i;
        this.nickname = n;
        this.secret = s;
        this.active = a;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}
