package com.ssafy.home.model.dao;

import com.ssafy.home.model.dto.HouseDealDto;
import com.ssafy.home.model.dto.HouseInfoDto;
import com.ssafy.home.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HouseDao {
    private final DBUtil dbUtil = DBUtil.getUtil();

    public List<HouseInfoDto> getAllHouseInfos() throws SQLException {
        List<HouseInfoDto> list = new ArrayList<>();
        String sql = "SELECT * FROM houseinfos";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                HouseInfoDto dto = new HouseInfoDto();
                dto.setAptSeq(rs.getString("apt_seq"));
                dto.setSggCd(rs.getString("sgg_cd"));
                dto.setUmdCd(rs.getString("umd_cd"));
                dto.setUmdNm(rs.getString("umd_nm"));
                dto.setJibun(rs.getString("jibun"));
                dto.setRoadNmSggCd(rs.getString("road_nm_sgg_cd"));
                dto.setRoadNm(rs.getString("road_nm"));
                dto.setRoadNmBonbun(rs.getString("road_nm_bonbun"));
                dto.setRoadNmBubun(rs.getString("road_nm_bubun"));
                dto.setAptNm(rs.getString("apt_nm"));
                dto.setBuildYear(rs.getInt("build_year"));
                dto.setLatitude(rs.getString("latitude"));
                dto.setLongitude(rs.getString("longitude"));
                list.add(dto);
            }
        }
        return list;
    }

    public List<HouseDealDto> getDealsByAptSeq(String aptSeq) throws SQLException {
        List<HouseDealDto> list = new ArrayList<>();
        String sql = "SELECT * FROM housedeals WHERE apt_seq = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, aptSeq);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    HouseDealDto dto = new HouseDealDto();
                    dto.setNo(rs.getInt("no"));
                    dto.setAptSeq(rs.getString("apt_seq"));
                    dto.setAptDong(rs.getString("apt_dong"));
                    dto.setFloor(rs.getString("floor"));
                    dto.setDealYear(rs.getInt("deal_year"));
                    dto.setDealMonth(rs.getInt("deal_month"));
                    dto.setDealDay(rs.getInt("deal_day"));
                    dto.setExclusiveUseArea(rs.getBigDecimal("exclu_use_ar"));
                    dto.setDealAmount(rs.getString("deal_amount"));
                    list.add(dto);
                }
            }
        }
        return list;
    }
} 