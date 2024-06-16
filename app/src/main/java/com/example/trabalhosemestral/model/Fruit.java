package com.example.trabalhosemestral.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Fruit extends Crop{

    public Fruit(int id, String name, int days, int price, String season){
        super(id,name,days,price,season);
    }
    public Fruit(){

    }

    @Override
    public void preserve() {
        String mensagem = "Uau! ao ser colocada na Jarra de Conserva você conseguiu produzir suco de " + getName() + "!\n Que pode ser vendido por " + getPrice() * 2.5 + " moedas!";
    }

    @NonNull
    @Override
    public String toString(){
        return "Você abre a " + getId()+ "º página do grimório.\n" + "A fruta " + getName() + " demora " + getDays() + " dias para estar pronta para a colheita.\n " + "Sua estação é: "
                + getSeason() + " e pode ser vendida por: " + getPrice() + " moedas se for de qualidade normal, " + (int)(getPrice()*1.5) + "Moedas se for de qualidade Prata, " +
                (int)(getPrice() * 1.75) + " Moedas se for de qualidade Ouro, " + getPrice() * 2 + "Se for de irídio!";

    }


}
