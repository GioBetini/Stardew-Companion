package com.example.trabalhosemestral.persistence;

import java.sql.SQLException;

public interface ICropDao {
    public CropDao open() throws SQLException;
    public void close();

}
