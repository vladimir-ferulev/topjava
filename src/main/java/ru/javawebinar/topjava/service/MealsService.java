package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealsDao;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public class MealsService {

    private MealsDao dao;

    public MealsService() {
        dao = new MealsDao();
    }

    public List<Meal> getList() {
        return dao.getList();
    }

    public void create(LocalDateTime dateTime, String description, int calories) {
        dao.create(dateTime, description, calories);
    }

    public Meal getById(int id) {
        return dao.getById(id);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public void update(int id, LocalDateTime dateTime, String description, int calories) {
        dao.update(id, dateTime, description, calories);
    }


}
