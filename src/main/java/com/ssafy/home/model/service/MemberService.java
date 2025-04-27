package com.ssafy.home.model.service;

import com.ssafy.home.model.dao.MemberDao;
import com.ssafy.home.model.dto.MemberDto;

import java.sql.SQLException;
import java.util.List;

public class MemberService {
    private final MemberDao dao = new MemberDao();

    public MemberDto loginByName(String name, String password) throws SQLException {
        return dao.loginByName(name, password);
    }

    public void register(MemberDto dto) throws SQLException {
        dao.insert(dto);
    }

    public List<MemberDto> listMembers() throws SQLException {
        return dao.selectAll();
    }

    public MemberDto getDetail(String email) throws SQLException {
        return dao.select(email);
    }

    public void update(MemberDto dto) throws SQLException {
        dao.update(dto);
    }

    public void delete(int mno) throws SQLException {
        dao.delete(mno);
    }
} 