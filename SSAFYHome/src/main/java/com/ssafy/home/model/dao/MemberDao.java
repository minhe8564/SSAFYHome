package com.ssafy.home.model.dao;

import com.ssafy.home.model.dto.MemberDto;
import com.ssafy.home.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class MemberDao {
    private final DBUtil dbUtil = DBUtil.getUtil();

    public MemberDto loginByName(String name, String password) throws SQLException {
        String sql = "SELECT * FROM member WHERE name = ? AND password = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    MemberDto dto = mapResultSetToMember(rs);
                    return dto;
                }
            }
        }
        return null;
    }

    public void insert(MemberDto dto) throws SQLException {
        String sql = "INSERT INTO member (name, email, password, role, profile) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getEmail());
            pstmt.setString(3, dto.getPassword());
            pstmt.setString(4, dto.getRole());
            pstmt.setBytes(5, dto.getProfile());

            pstmt.executeUpdate();
        }
    }


    public List<MemberDto> selectAll() throws SQLException {
        List<MemberDto> list = new ArrayList<>();
        String sql = "SELECT * FROM member";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapResultSetToMember(rs));
            }
        }
        return list;
    }

    public MemberDto select(String email) throws SQLException {
        String sql = "SELECT * FROM member WHERE email = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToMember(rs);
                }
            }
        }
        return null;
    }

    public void update(MemberDto dto) throws SQLException {
        String sql = "UPDATE member SET name = ?, email = ?, password = ?, profile = ? WHERE mno = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dto.getName());
            pstmt.setString(2, dto.getEmail());
            pstmt.setString(3, dto.getPassword());
            pstmt.setBytes(4, dto.getProfile());
            pstmt.setInt(5, dto.getMno());
            pstmt.executeUpdate();
        }
    }

    public void delete(int mno) throws SQLException {
        String sql = "DELETE FROM member WHERE mno = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, mno);
            pstmt.executeUpdate();
        }
    }

    private MemberDto mapResultSetToMember(ResultSet rs) throws SQLException {
        MemberDto dto = new MemberDto();
        dto.setMno(rs.getInt("mno"));
        dto.setName(rs.getString("name"));
        dto.setEmail(rs.getString("email"));
        dto.setPassword(rs.getString("password"));
        dto.setRole(rs.getString("role"));

        byte[] profile = rs.getBytes("profile");
        if (profile != null) {
            dto.setProfile(profile);
            String encoded = Base64.getEncoder().encodeToString(profile);
            dto.setProfileBase64(encoded);
        }
        return dto;
    }
}
