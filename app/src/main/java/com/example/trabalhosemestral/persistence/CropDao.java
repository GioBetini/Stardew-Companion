package com.example.trabalhosemestral.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trabalhosemestral.model.Crop;
import com.example.trabalhosemestral.model.Fruit;
import com.example.trabalhosemestral.model.Vegetable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CropDao implements ICropDao, ICRUDDao<Crop>{
    private final Context context;
    private GenericDAO gDao;
    private SQLiteDatabase database;

    public CropDao(Context context){
        this.context = context;
    }
    @Override
    public CropDao open() throws SQLException {
        gDao = new GenericDAO(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }

    private static ContentValues getContentValues(Crop crop, boolean isFruit){
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", crop.getId() );
        contentValues.put("nome", crop.getName() );
        contentValues.put("dias", crop.getDays() );
        contentValues.put("preco", crop.getPrice() );
        contentValues.put("estacao", crop.getSeason());
        if(isFruit) {
            contentValues.put("tipo", "fruta" );
        } else {

            contentValues.put("tipo", "vegetal" );
        }
        return contentValues;
    }
    @Override
    public void insert(Crop crop) throws SQLException {
        if (crop instanceof Fruit){
            database.insert("crop", null, getContentValues(crop, true));
        } else {
            database.insert("crop", null, getContentValues(crop, false));
        }
    }

    @Override
    public int update(Crop crop) throws SQLException {
        int ret;
        if (crop instanceof Fruit){
            ret = database.update("crop",getContentValues(crop, true), "id = " + crop.getId(), null);
        } else {
            ret =  database.update("crop", getContentValues(crop, false),"id = " + crop.getId(), null);
        }
        return ret;
    }

    @Override
    public void delete(Crop crop) throws SQLException {
        database.delete("crop", "id = " + crop.getId(),null );
    }

    @SuppressLint("Range")
    @Override
    public Crop findOne(Crop crop) throws SQLException {
        String sql = "SELECT id, nome, dias, preco, estacao, tipo FROM crop WHERE id =" + crop.getId();
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (!cursor.isAfterLast()) {
            String tipo = cursor.getString(cursor.getColumnIndex("tipo"));
            if (tipo.equals("fruta")){
                crop = new Fruit();
            } else {
                crop = new Vegetable();
            }
            crop.setId(cursor.getInt(cursor.getColumnIndex("id")));
            crop.setName(cursor.getString(cursor.getColumnIndex("nome")));
            crop.setDays(cursor.getInt(cursor.getColumnIndex("dias")));
            crop.setPrice(cursor.getInt(cursor.getColumnIndex("preco")));
            crop.setSeason(cursor.getString(cursor.getColumnIndex("estacao")));
        }
        cursor.close();
        return crop;
    }

    @SuppressLint("Range")
    @Override
    public List<Crop> findAll() throws SQLException {
        List<Crop> crops = new ArrayList<>();
        String sql = "SELECT id, nome, dias, preco, estacao, tipo FROM crop";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()) {
            String tipo = cursor.getString(cursor.getColumnIndex("tipo"));
            Crop crop;
            if (tipo.equals("fruta")){
                crop = new Fruit();
            } else {
                crop = new Vegetable();
            }
            crop.setId(cursor.getInt(cursor.getColumnIndex("id")));
            crop.setName(cursor.getString(cursor.getColumnIndex("nome")));
            crop.setDays(cursor.getInt(cursor.getColumnIndex("dias")));
            crop.setPrice(cursor.getInt(cursor.getColumnIndex("preco")));
            crop.setSeason(cursor.getString(cursor.getColumnIndex("estacao")));

            crops.add(crop);
            cursor.moveToNext();
        }
        cursor.close();
        return crops;
    }
}
