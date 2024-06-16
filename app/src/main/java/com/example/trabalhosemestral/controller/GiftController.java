package com.example.trabalhosemestral.controller;

import com.example.trabalhosemestral.model.Gift;
import com.example.trabalhosemestral.persistence.GiftDao;

import java.sql.SQLException;
import java.util.List;

public class GiftController implements IController<Gift>{
    private final GiftDao gDao;

    public GiftController(GiftDao gDao) {
        this.gDao = gDao;
    }

    @Override
    public void insert(Gift gift) throws SQLException {
        if (gDao.open() == null){
            gDao.open();
        }
        gDao.insert(gift);
        gDao.close();
    }

    @Override
    public void update(Gift gift) throws SQLException {
        if (gDao.open() == null){
            gDao.open();
        }
        gDao.update(gift);
        gDao.close();
    }

    @Override
    public void delete(Gift gift) throws SQLException {
        if (gDao.open() == null){
            gDao.open();
        }
        gDao.delete(gift);
        gDao.close();
    }

    @Override
    public Gift findOne(Gift gift) throws SQLException {
        if (gDao.open() == null){
            gDao.open();
        }
       return gDao.findOne(gift);
    }

    @Override
    public List<Gift> findAll() throws SQLException {
        if (gDao.open() == null){
            gDao.open();
        }
        return gDao.findAll();
    }
}
