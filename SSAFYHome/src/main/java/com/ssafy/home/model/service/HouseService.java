package com.ssafy.home.model.service;

import com.ssafy.home.model.dao.HouseDao;
import com.ssafy.home.model.dto.HouseDealDto;
import com.ssafy.home.model.dto.HouseInfoDto;

import java.sql.SQLException;
import java.util.List;

public class HouseService {
    private final HouseDao dao = new HouseDao();

    public List<HouseInfoDto> getAllHouseInfos() throws SQLException {
        return dao.getAllHouseInfos();
    }

    public List<HouseDealDto> getDealsByAptSeq(String aptSeq) throws SQLException {
        return dao.getDealsByAptSeq(aptSeq);
    }
} 
