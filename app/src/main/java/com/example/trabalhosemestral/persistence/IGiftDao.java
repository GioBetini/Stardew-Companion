package com.example.trabalhosemestral.persistence;

import java.sql.SQLException;

public interface IGiftDao {
    public GiftDao open() throws SQLException;
    public void close();
}
