package com.ssafy.home.model.dao;

import com.ssafy.home.model.dto.AddressDto;
import com.ssafy.home.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDao {
    private final DBUtil util = DBUtil.getUtil();

    public void insert(AddressDto dto) throws SQLException {
        String sql = "INSERT INTO address (mno, title, address, detail_address, x, y) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, dto.getMno());
            ps.setString(2, dto.getTitle());
            ps.setString(3, dto.getAddress());
            ps.setString(4, dto.getDetailAddress());
            ps.setString(5, dto.getX());
            ps.setString(6, dto.getY());
            ps.executeUpdate();
        }
    }

    public List<AddressDto> selectAllByMno(int mno) throws SQLException {
        List<AddressDto> list = new ArrayList<>();
        String sql = "SELECT * FROM address WHERE mno = ?";
        try (Connection conn = util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, mno);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AddressDto dto = new AddressDto(
                        rs.getInt("ano"),
                        rs.getInt("mno"),
                        rs.getString("title"),
                        rs.getString("address"),
                        rs.getString("detail_address"),
                        rs.getString("x"),
                        rs.getString("y")
                    );
                    list.add(dto);
                }
            }
        }
        return list;
    }

    public void delete(int ano) throws SQLException {
        String sql = "DELETE FROM address WHERE ano = ?";
        try (Connection conn = util.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ano);
            ps.executeUpdate();
        }
    }
}