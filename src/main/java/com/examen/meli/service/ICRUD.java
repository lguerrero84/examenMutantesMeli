package com.examen.meli.service;

import java.util.List;

public interface ICRUD <T> {
    T registerEntry(T t);
    T modifyEntry(T t, Integer id);
    T modify(T t);
    void deleteEntry(Integer id);
    T listById(Integer id);
    List<T> listElement();

}
