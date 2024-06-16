package com.example.trabalhosemestral.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GenericDAO extends SQLiteOpenHelper {
    private static final String DATABASE = "STARDEW.DB";
    private static final int DATABASE_VER = 1;
    private static final String CREATE_TABLE_CROP =
            "CREATE TABLE crop ("+
                    "id INT NOT NULL PRIMARY KEY, "+
                    "nome VARCHAR(15) NOT NULL, " +
                    "dias INT NOT NULL, " +
                    "preco INT NOT NULL, " +
                    "estacao VARCHAR(9) NOT NULL, " +
                    "tipo VARCHAR(9) NOT NULL);";
    private static final String CREATE_TABLE_NPC =
            "CREATE TABLE npc ("+
                    "id INT NOT NULL PRIMARY KEY, " +
                    "nome VARCHAR(15) NOT NULL, "+
                    "solteiro VARCHAR(5) DEFAULT 'true');";
    private static final String CREATE_TABLE_GIFT =
            "CREATE TABLE gift (" +
                    "cropId INTEGER NOT NULL, " +
                    "npcId INTEGER NOT NULL, " +
                    "reacao VARCHAR(100) NOT NULL, " +
                    "FOREIGN KEY (cropId) REFERENCES crop (id)," +
                    "FOREIGN KEY (npcId) REFERENCES npc (id),"+
                    "PRIMARY KEY (cropId, npcId));";



    public GenericDAO(Context context){
        super(context, DATABASE, null, DATABASE_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_CROP);
        database.execSQL(CREATE_TABLE_NPC);
        database.execSQL(CREATE_TABLE_GIFT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            database.execSQL("DROP TABLE IF EXISTS crop");
            database.execSQL("DROP TABLE IF EXISTS npc");
            database.execSQL("DROP TABLE IF EXISTS gift");

            onCreate(database);
        }
    }
}
