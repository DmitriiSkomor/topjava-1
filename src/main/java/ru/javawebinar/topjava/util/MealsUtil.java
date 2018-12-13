package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealList;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class MealsUtil {

    public static List<MealWithExceed> getMealsWithExceed() {

        Map<LocalDate, Integer> caloriesSumPerDate = new HashMap<>();

        List<Meal> mealList = MealList.getMealList();
        for (Meal userMeal : mealList) {
            LocalDate date = userMeal.getDateTime().toLocalDate();
            caloriesSumPerDate.put(date, caloriesSumPerDate.getOrDefault(date, 0) + userMeal.getCalories());
        }
        List<MealWithExceed> result = new ArrayList<>();

        for (Meal userMeal : mealList) {
            result.add(new MealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(),
                    caloriesSumPerDate.get(userMeal.getDateTime().toLocalDate()) > MealList.getCaloriesPerDay())
            );
        }

        return result;
    }
}