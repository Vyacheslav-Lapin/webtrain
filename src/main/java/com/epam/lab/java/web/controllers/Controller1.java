package com.epam.lab.java.web.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/user")
public class Controller1 extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        response.setContentType("text/html");
//        response.setIntHeader("Content-Length", 100_500);
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        try (PrintWriter writer = response.getWriter()) {
            writer.println(
                    "<http><head><title>Мама мыла раму</title></head><body>Мама мыла раму!</body></http>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
