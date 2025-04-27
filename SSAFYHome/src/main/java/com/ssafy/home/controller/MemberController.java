package com.ssafy.home.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import com.ssafy.home.model.dto.MemberDto;
import com.ssafy.home.model.service.MemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/member")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5) // 최대 5MB
public class MemberController extends HttpServlet {
    private final MemberService service = new MemberService();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String act = request.getParameter("act");
        try {
            switch (act) {
                case "login-form" -> request.getRequestDispatcher("/member/member-login.jsp").forward(request, response);
                case "regist-member-form" -> request.getRequestDispatcher("/member/member-regist.jsp").forward(request, response);
                case "login" -> login(request, response);
                case "logout" -> logout(request, response);
                case "regist" -> register(request, response);
                case "list" -> list(request, response);
                case "detail" -> detail(request, response);
                case "update" -> update(request, response);
                case "delete" -> delete(request, response);
                default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String saveName = request.getParameter("saveName");

        if (saveName != null) {
            Cookie cookie = new Cookie("saveName", name);
            cookie.setMaxAge(60 * 60 * 3);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        } else {
            Cookie cookie = new Cookie("saveName", "");
            cookie.setMaxAge(0); 
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }

        MemberDto member = service.loginByName(name, password);
        if (member != null) {
            request.getSession().setAttribute("loginUser", member);
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("member?act=login-form&error=1");
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            MemberDto existing = service.getDetail(email);

            if (existing != null) {
                request.setAttribute("error", "이미 사용 중인 이메일입니다.");
                request.getRequestDispatcher("/member/member-regist.jsp").forward(request, response);
                return;
            }

            MemberDto dto = new MemberDto(
                request.getParameter("name"),
                request.getParameter("email"),
                request.getParameter("password")
            );

            Part profilePart = request.getPart("profile");
            if (profilePart != null && profilePart.getSize() > 0) {
                dto.setProfile(profilePart.getInputStream().readAllBytes());
            }

            service.register(dto);
            response.sendRedirect("member?act=login-form");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<MemberDto> list = service.listMembers();
        request.setAttribute("members", list);
        request.getRequestDispatcher("/member/member-list.jsp").forward(request, response);
    }

    private void detail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String email = request.getParameter("email");
        MemberDto member = service.getDetail(email);

        if (member.getProfile() != null) {
            String encoded = Base64.getEncoder().encodeToString(member.getProfile());
            member.setProfileBase64(encoded);
        }
        System.out.println(member.toString());
        request.setAttribute("member", member);

        request.getRequestDispatcher("/member/member-detail.jsp").forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        MemberDto dto = new MemberDto();
        dto.setMno(Integer.parseInt(request.getParameter("mno")));
        dto.setName(request.getParameter("name"));
        dto.setEmail(request.getParameter("email"));
        dto.setRole(request.getParameter("role"));

        String newPassword = request.getParameter("newPassword");
        if (newPassword == null || newPassword.isBlank()) {
            MemberDto original = service.getDetail(dto.getEmail());
            dto.setPassword(original.getPassword());
        } else {
            dto.setPassword(newPassword);
        }

        Part profilePart = request.getPart("profile");
        if (profilePart != null && profilePart.getSize() > 0) {
            byte[] profileBytes = profilePart.getInputStream().readAllBytes();
            dto.setProfile(profileBytes);
        }
        
        service.update(dto);
        response.sendRedirect("member?act=detail&email=" + dto.getEmail());
    }


    private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int mno = Integer.parseInt(request.getParameter("mno"));
        service.delete(mno);
        response.sendRedirect("index.jsp");
    }
}
