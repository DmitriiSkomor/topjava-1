package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class MealsUtil {

    public static List<Meal> MEAL_LIST = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    );

    public static List<MealWithExceed> getMealsWithExceed(List<Meal> mealList, int caloriesPerDay) {
        return getFilteredMealsWithExceed(mealList, LocalTime.MIN, LocalTime.MAX, caloriesPerDay);
    }

    public static List<MealWithExceed> getFilteredMealsWithExceed(List<Meal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> caloriesSumPerDate = new HashMap<>();

        for (Meal userMeal : mealList) {
            LocalDate date = userMeal.getDateTime().toLocalDate();
            caloriesSumPerDate.put(date, caloriesSumPerDate.getOrDefault(date, 0) + userMeal.getCalories());
        }
        List<MealWithExceed> result = new ArrayList<>();

        for (Meal userMeal : mealList) {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                result.add(new MealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(),
                        caloriesSumPerDate.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay)
                );
            }
        }

        return result;
    }
}