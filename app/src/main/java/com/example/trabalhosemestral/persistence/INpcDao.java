package com.example.trabalhosemestral.persistence;

import java.sql.SQLException;

public interface INpcDao {
    public NpcDao open() throws SQLException;
    public void close();
}
