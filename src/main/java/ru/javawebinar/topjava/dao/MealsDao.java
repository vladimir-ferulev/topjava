package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealsDao {

    public List<Meal> getList();

    public void create(Meal meal);

    public Meal getById(int id);

    public void delete(int id);

    public void update(Meal meal);

}

