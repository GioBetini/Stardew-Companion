package com.example.trabalhosemestral.persistence;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trabalhosemestral.model.Fruit;
import com.example.trabalhosemestral.model.Gift;
import com.example.trabalhosemestral.model.Npc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GiftDao implements IGiftDao, ICRUDDao<Gift>{

    private final Context context;
    private GenericDAO gDao;
    private SQLiteDatabase database;

    public GiftDao(Context context){
        this.context = context;
    }
    @Override
    public GiftDao open() throws SQLException {
        gDao = new GenericDAO(context);
        database = gDao.getWritableDatabase();
        return this;
    }

    @Override
    public void close() {
        gDao.close();
    }
    private static ContentValues getContentValues(Gift gift) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("cropId", gift.getCropGift().getId());
        contentValues.put("npcId", gift.getNpcGift().getId());
        contentValues.put("reacao", gift.getReaction());
        return contentValues;
    }

    @Override
    public void insert(Gift gift) throws SQLException {
       database.insert("gift", null, getContentValues(gift));
    }

    @Override
    public int update(Gift gift) throws SQLException {
        return database.update("gift", getContentValues(gift), "cropId = " +
                gift.getCropGift().getId() + "AND npcId = " + gift.getNpcGift().getId(), null);
    }

    @Override
    public void delete(Gift gift) throws SQLException {
        database.delete("gift","cropId = " + gift.getCropGift().getId() + " AND npcId " + gift.getNpcGift().getId(),null);
    }

    @SuppressLint("Range")
    @Override
    public Gift findOne(Gift gift) throws SQLException {
        String sql = "SELECT c.id As pag_crop, c.nome AS nome_crop, c.dias AS dias_crop, " +
                "c.preco AS preco_crop, c.estacao AS estacao_crop, c.tipo AS tipo_crop, " +
                "n.id AS id_npc, n.nome AS nome_npc, n.solteiro AS solteiro_npc, " +
                "g.cropId AS pagcrop_gift, g.npcId AS idnpc_gift, g.reacao AS reacao_gift " +
                "FROM crop c, npc n, gift g " +
                "WHERE c.id = g.pag_crop AND g.pag_crop = " + gift.getCropGift().getId()+
                "AND n.id = g.id_npc AND g.id_npc = " + gift.getNpcGift().getId();
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        if(!cursor.isAfterLast()){
            Fruit crop = new Fruit();
            crop.setId(cursor.getInt(cursor.getColumnIndex("pag_crop")));
            crop.setName(cursor.getString(cursor.getColumnIndex("nome_crop")));
            crop.setDays(cursor.getInt(cursor.getColumnIndex("dias_crop")));
            crop.setPrice(cursor.getInt(cursor.getColumnIndex("preco_crop")));
            crop.setSeason(cursor.getString(cursor.getColumnIndex("estacao_crop")));
            Npc npc = new Npc();
            npc.setId(cursor.getInt(cursor.getColumnIndex("id_npc")));
            npc.setName(cursor.getString(cursor.getColumnIndex("nome_npc")));
            gift.setCropGift(crop);
            gift.setNpcGift(npc);
        }
        cursor.close();
        return gift;
    }

    @SuppressLint("Range")
    @Override
    public List<Gift> findAll() throws SQLException {
        List<Gift> gifts = new ArrayList<>();
        String sql = "SELECT c.id As pag_crop, c.nome AS nome_crop, c.dias AS dias_crop, " +
                "c.preco AS preco_crop, c.estacao AS estacao_crop, c.tipo AS tipo_crop, " +
                "n.id AS id_npc, n.nome AS nome_npc, n.solteiro AS solteiro_npc, " +
                "g.cropId AS pagcrop_gift, g.npcId AS idnpc_gift, g.reacao AS reacao_gift " +
                "FROM crop c, npc n, gift g " +
                "WHERE c.id = g.pag_crop "+
                "AND n.id = g.id_npc ";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        while (!cursor.isAfterLast()){
            Gift gift = new Gift();
            Fruit crop = new Fruit();
            crop.setId(cursor.getInt(cursor.getColumnIndex("pag_crop")));
            crop.setName(cursor.getString(cursor.getColumnIndex("nome_crop")));
            crop.setDays(cursor.getInt(cursor.getColumnIndex("dias_crop")));
            crop.setPrice(cursor.getInt(cursor.getColumnIndex("preco_crop")));
            crop.setSeason(cursor.getString(cursor.getColumnIndex("estacao_crop")));
            Npc npc = new Npc();
            npc.setId(cursor.getInt(cursor.getColumnIndex("id_npc")));
            npc.setName(cursor.getString(cursor.getColumnIndex("nome_npc")));
            gift.setCropGift(crop);
            gift.setNpcGift(npc);

            gifts.add(gift);
            cursor.moveToNext();
        }
        cursor.close();
        return gifts;
    }


}
