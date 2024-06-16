package com.example.trabalhosemestral.controller;

import com.example.trabalhosemestral.model.Crop;
import com.example.trabalhosemestral.persistence.CropDao;

import java.sql.SQLException;
import java.util.List;

public class CropController implements  IController<Crop>{

    private final CropDao cDao;
    public CropController(CropDao cDao){
        this.cDao = cDao;
    }
    @Override
    public void insert(Crop crop) throws SQLException {
        if (cDao.open() == null){
                cDao.open();
        }
        cDao.insert(crop);
        cDao.close();
    }

    @Override
    public void update(Crop crop) throws SQLException {
        if (cDao.open() == null){
            cDao.open();
        }
        cDao.update(crop);
        cDao.close();
    }

    @Override
    public void delete(Crop crop) throws SQLException {
        if (cDao.open() == null){
            cDao.open();
        }
        cDao.delete(crop);
        cDao.close();
    }

    @Override
    public Crop findOne(Crop crop) throws SQLException {
        if (cDao.open() == null){
            cDao.open();
        }
       return cDao.findOne(crop);
    }

    @Override
    public List findAll() throws SQLException {
        if (cDao.open() == null){
            cDao.open();
        }
        return cDao.findAll();
    }
}
