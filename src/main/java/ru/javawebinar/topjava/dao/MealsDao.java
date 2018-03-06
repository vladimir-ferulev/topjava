package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsDao {
    private static AtomicInteger countId = new AtomicInteger(1);

    private Map<Integer, Meal> mealsDB = new ConcurrentHashMap<>();

    public MealsDao() {
        create(LocalDateTime.of(2000, 10, 3, 10, 10), "Meal", 1000);
        create(LocalDateTime.of(2000, 10, 3, 10, 10), "Meal", 1050);
        create(LocalDateTime.of(2000, 10, 4, 10, 10), "Meal", 1000);
        create(LocalDateTime.of(2000, 10, 4, 10, 10), "Meal", 1000);
    }

    public List<Meal> getList() {
        return new ArrayList<>(mealsDB.values());
    }

    public void create(LocalDateTime dateTime, String description, int calories) {
        mealsDB.put(countId.get(), new Meal(countId.getAndIncrement(), dateTime, description, calories));
    }

    public Meal getById(int id) {
        return mealsDB.get(id);
    }

    public void delete(int id) {
        mealsDB.remove(id);
    }

    public void update(int id, LocalDateTime dateTime, String description, int calories) {
        mealsDB.get(id).setDateTime(dateTime);
        mealsDB.get(id).setDescription(description);
        mealsDB.get(id).setCalories(calories);
    }


}
