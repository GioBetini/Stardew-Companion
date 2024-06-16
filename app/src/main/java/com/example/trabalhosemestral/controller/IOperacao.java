package com.example.trabalhosemestral.controller;

import java.util.List;

public interface IOperacao<T> {
    public void presentear(T t);

    public List<T> listar();
}
