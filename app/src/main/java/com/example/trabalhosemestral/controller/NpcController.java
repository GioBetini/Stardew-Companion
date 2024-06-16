package com.example.trabalhosemestral.controller;

import com.example.trabalhosemestral.model.Npc;
import com.example.trabalhosemestral.persistence.NpcDao;

import java.sql.SQLException;
import java.util.List;

public class NpcController implements IController<Npc> {
    private final NpcDao nDao;
    public NpcController(NpcDao nDao){
        this.nDao = nDao;
    }
    @Override
    public void insert(Npc npc) throws SQLException {
        if (nDao.open() == null){
            nDao.open();
        }
        nDao.insert(npc);
        nDao.close();
    }

    @Override
    public void update(Npc npc) throws SQLException {
        if (nDao.open() == null){
            nDao.open();
        }
        nDao.update(npc);
        nDao.close();
    }

    @Override
    public void delete(Npc npc) throws SQLException {
        if (nDao.open() == null){
            nDao.open();
        }
        nDao.delete(npc);
        nDao.close();
    }

    @Override
    public Npc findOne(Npc npc) throws SQLException {
        if (nDao.open() == null){
            nDao.open();
        }
        return nDao.findOne(npc);
    }

    @Override
    public List<Npc> findAll() throws SQLException {
        if (nDao.open() == null){
            nDao.open();
        }
        return nDao.findAll();
    }
}
