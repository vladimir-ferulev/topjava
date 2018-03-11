package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealsDao;
import ru.javawebinar.topjava.dao.MealsDaoLocal;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
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
    private static DateTimeFormatter formatter;
    private MealsDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        dao = new MealsDaoLocal();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String action = request.getParameter("action");

        if (action != null && action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(request.getParameter("id"));
            dao.delete(id);
            forward = "/meals.jsp";
        } else if (action != null && action.equalsIgnoreCase("add")) {
            forward = "/mealAdd.jsp";
        } else if (action != null && action.equalsIgnoreCase("edit")) {
            int idEdit = Integer.parseInt(request.getParameter("id"));
            Meal meal = dao.getById(idEdit);
            request.setAttribute("mealedit", meal);
            forward = "/mealUpdate.jsp";
        } else {
            forward = "/meals.jsp";
        }

        List<MealWithExceed> listMeals = MealsUtil.getFilteredWithExceeded(dao.getList(), LocalTime.MIN, LocalTime.MAX, 2000);

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
            dao.update(new Meal(idEdit, localDateTime, description, calories));
        } else {
            dao.create(new Meal(0, localDateTime, description, calories));
        }


        List<MealWithExceed> listMeals = MealsUtil.getFilteredWithExceeded(dao.getList(), LocalTime.MIN, LocalTime.MAX, 2000);

        request.setAttribute("listMeals", listMeals);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);

    }

}
