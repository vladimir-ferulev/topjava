package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsDaoLocal implements MealsDao {
    private static AtomicInteger countId = new AtomicInteger(1);

    private Map<Integer, Meal> mealsDB = new ConcurrentHashMap<>();

    public MealsDaoLocal() {
        create(new Meal(0, LocalDateTime.of(2000, 10, 3, 10, 10), "Meal", 1000));
        create(new Meal(0, LocalDateTime.of(2000, 10, 3, 10, 10), "Meal", 1050));
        create(new Meal(0, LocalDateTime.of(2000, 10, 4, 10, 10), "Meal", 1000));
        create(new Meal(0, LocalDateTime.of(2000, 10, 4, 10, 10), "Meal", 1000));
    }

    public List<Meal> getList() {
        return new ArrayList<>(mealsDB.values());
    }

    public void create(Meal meal) {
        mealsDB.put(countId.get(), new Meal(countId.getAndIncrement(), meal.getDateTime(), meal.getDescription(), meal.getCalories()));
    }

    public Meal getById(int id) {
        return mealsDB.get(id);
    }

    public void delete(int id) {
        mealsDB.remove(id);
    }

    public void update(Meal meal) {
        mealsDB.get(meal.getId()).setDateTime(meal.getDateTime());
        mealsDB.get(meal.getId()).setDescription(meal.getDescription());
        mealsDB.get(meal.getId()).setCalories(meal.getCalories());
    }


}
