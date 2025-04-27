package com.ssafy.home.controller;

import com.ssafy.home.model.dto.AddressDto;
import com.ssafy.home.model.service.AddressService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/address")
public class AddressController extends HttpServlet {
    private final AddressService aService = new AddressService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        try {
            switch (act) {
                case "list" -> list(request, response);
                case "add" -> add(request, response);
                case "delete" -> delete(request, response);
                default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int mno = Integer.parseInt(request.getParameter("mno"));
        List<AddressDto> list = aService.getAddressesByMember(mno);
        request.setAttribute("addressList", list);
        request.getRequestDispatcher("/pages/address/address-list.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        AddressDto dto = new AddressDto(
                0,
                Integer.parseInt(request.getParameter("mno")),
                request.getParameter("title"),
                request.getParameter("address"),
                request.getParameter("detail_address"),
                request.getParameter("x"),
                request.getParameter("y")
        );
        aService.addAddress(dto);
        response.sendRedirect("address?act=list&mno=" + dto.getMno());
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int ano = Integer.parseInt(request.getParameter("ano"));
        aService.removeAddress(ano);
        int mno = Integer.parseInt(request.getParameter("mno"));
        response.sendRedirect("address?act=list&mno=" + mno);
    }
}