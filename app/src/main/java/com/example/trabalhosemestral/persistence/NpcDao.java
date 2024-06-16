package com.example.trabalhosemestral.persistence;

import com.example.trabalhosemestral.model.Npc;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NpcDao implements  INpcDao, ICRUDDao<Npc>{
    private final Context context;
    private GenericDAO gDao;
    private SQLiteDatabase database;

    public NpcDao(Context context){
        this.context = context;
    }

    @Override
    public NpcDao open() throws SQLException {
        gDao = new GenericDAO(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    private static ContentValues getContentValues(Npc npc){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", npc.getId() );
        contentValues.put("nome",npc.getName() );
        contentValues.put("solteiro", String.valueOf(npc.isSolteiro()));

        return contentValues;
    }
    @Override
    public void insert(Npc npc) throws SQLException {

        database.insert("npc", null, getContentValues(npc));

    }

    @Override
    public int update(Npc npc) throws SQLException {
        return database.update("npc",  getContentValues(npc), "id = " + npc.getId(), null);
    }

    @Override
    public void delete(Npc npc) throws SQLException {
        database.delete("npc", "id = " +npc.getId(), null);
    }

    @SuppressLint("Range")
    @Override
    public Npc findOne(Npc npc) throws SQLException {
        String sql = "SELECT id, nome, solteiro FROM npc WHERE id =" + npc.getId();
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()) {
            npc.setId(cursor.getInt(cursor.getColumnIndex("id")));
            npc.setName(cursor.getString(cursor.getColumnIndex("nome")));
            npc.setSolteiro(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("solteiro"))));
        }
        cursor.close();
        return npc;
    }

    @SuppressLint("Range")
    @Override
    public List<Npc> findAll() throws SQLException {
        List<Npc> npcs = new ArrayList<>();
        String sql = "SELECT id, nome, casado FROM npc";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            Npc npc = new Npc();
            npc.setId(cursor.getInt(cursor.getColumnIndex("id")));
            npc.setName(cursor.getString(cursor.getColumnIndex("nome")));
            npc.setSolteiro(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("solteiro"))));
            npcs.add(npc);
            cursor.moveToNext();
        }
        cursor.close();
        return null;
    }


}
