package com.example.trabalhosemestral.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Gift {

    private Crop cropGift;
    private Npc npcGift;
    private String[] reactions = {"Ah...Vou colocar isso ali...", "Valeu (eu acho...)", "Obrigado!", "Uau! Que bacana!"} ;

    public Gift(){
        super();
        }

        public String getReaction(){
            Random r = new Random();
            return reactions[r.nextInt(reactions.length)];
        }
        public Crop getCropGift(){
            return cropGift;
        }

        public void setCropGift(Crop cropGift){
            this.cropGift = cropGift;
        }

        public Npc getNpcGift(){
            return npcGift;
        }

        public void setNpcGift(Npc npcGift){
            this.npcGift = npcGift;
        }
    }

