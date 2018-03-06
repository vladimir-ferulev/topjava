package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.service.MealsService;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MealServlet extends HttpServlet {
    private MealsService service;
    private List<MealWithExceed> listMeals;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    private int idEdit;

    public MealServlet() {
        service = new MealsService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String action = request.getParameter("action");

        if (action != null && action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            service.delete(id);
            forward = "/meals.jsp";
        } else if (action != null && action.equalsIgnoreCase("add")) {
            forward = "/mealAdd.jsp";
        } else if (action != null && action.equalsIgnoreCase("edit")) {
            idEdit = Integer.parseInt(request.getParameter("id"));
            Meal meal = service.getById(idEdit);
            request.setAttribute("mealedit", meal);
            forward = "/mealUpdate.jsp";
        } else {
            forward = "/meals.jsp";
        }

        listMeals = MealsUtil.getFilteredWithExceeded(service.getList(), LocalTime.MIN, LocalTime.MAX, 2000);

        request.setAttribute("listMeals", listMeals);
        request.getRequestDispatcher(forward).forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        LocalDateTime localDateTime = LocalDateTime.parse(request.getParameter("datetime"), formatter);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        String id = request.getParameter("id");
        if(id != null) {
            Integer idEdit = Integer.parseInt(id);
            service.update(idEdit, localDateTime, description, calories);
        } else {
            service.create(localDateTime, description, calories);
        }


        listMeals = MealsUtil.getFilteredWithExceeded(service.getList(), LocalTime.MIN, LocalTime.MAX, 2000);

        request.setAttribute("listMeals", listMeals);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);

    }

}
