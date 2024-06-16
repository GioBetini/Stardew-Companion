package com.example.trabalhosemestral.model;

import com.example.trabalhosemestral.controller.INpc;

public class Npc implements INpc {
    private String name;
    private int id;
    private boolean solteiro;

    public Npc(){
        super();
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    public boolean isSolteiro() {
        return solteiro;
    }

    public void setSolteiro(boolean solteiro) {
        this.solteiro = solteiro;
    }


    @Override
    public void receiveGift() {

    }

}
