package com.ssafy.home.model.service;

import com.ssafy.home.model.dao.AddressDao;
import com.ssafy.home.model.dto.AddressDto;

import java.sql.SQLException;
import java.util.List;

public class AddressService {
    private final AddressDao dao = new AddressDao();

    public void addAddress(AddressDto dto) throws SQLException {
        dao.insert(dto);
    }

    public List<AddressDto> getAddressesByMember(int mno) throws SQLException {
        return dao.selectAllByMno(mno);
    }

    public void removeAddress(int ano) throws SQLException {
        dao.delete(ano);
    }
}