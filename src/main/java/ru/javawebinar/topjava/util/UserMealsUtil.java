package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceededStream(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        getFilteredWithExceededLoops(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);

//        .toLocalDate();
//        .toLocalTime();
    }

    // Stream
    public static List<UserMealWithExceed>  getFilteredWithExceededStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> sumCalories = mealList.stream()
                .collect(Collectors.groupingBy(userMeal -> userMeal.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

        List<UserMealWithExceed> resultList = mealList.stream()
                .filter(n -> TimeUtil.isBetween(n.getDateTime().toLocalTime(), startTime, endTime))
                .map(n -> new UserMealWithExceed(n.getDateTime(), n.getDescription(), n.getCalories(), sumCalories.get(n.getDateTime().toLocalDate()) >= caloriesPerDay))
                .collect(Collectors.toList());

        return resultList;
    }

    // Loop
    public static List<UserMealWithExceed> getFilteredWithExceededLoops(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> sumCalories = new HashMap<>();
        for (UserMeal u : mealList) {
            sumCalories.merge(u.getDateTime().toLocalDate(), u.getCalories(), Integer::sum);
        }

        List<UserMealWithExceed> resultList = new ArrayList<>();
        for (UserMeal um : mealList) {
            if (TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime)) {
                resultList.add(new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(), sumCalories.get(um.getDateTime().toLocalDate()) >= caloriesPerDay));
            }
        }

        return resultList;
    }
}
