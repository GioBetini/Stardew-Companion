package com.example.trabalhosemestral.model;

import androidx.annotation.NonNull;

public class Vegetable extends Crop{

    public Vegetable(int id, String name, int days, int price, String season){
        super(id,name,days,price,season);
    }
    public Vegetable(){

    }
    @Override
    public void preserve() {
        String mensagem = "Uau! ao ser colocada na Jarra de Conserva você conseguiu produzir picles de " + getName() + "!\n Que pode ser vendido por " + getPrice() * 2.25 + " moedas!";
    }
    @NonNull
    @Override
    public String toString(){
        return "Você abre a " + getId()+ "º página do grimório.\n" + "O vegetal " + getName() + " demora " + getDays() + " dias para estar pronto para a colheita\n" + " Sua estação é: "
                + getSeason() + " e pode ser vendido por: " + getPrice() + " moedas se for de qualidade normal, " + (int)(getPrice()*1.5) + " Moedas se for de qualidade Prata, " +
                (int)(getPrice() * 1.75) + " Moedas se for de qualidade Ouro, " + getPrice() * 2 + " Se for de irídio!\n";

    }
}
