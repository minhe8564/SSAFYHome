package com.ssafy.home.model.dto;

import java.util.Arrays;

public class MemberDto {
    private int mno;
    private String name;
    private String email;
    private String password;
    private String role;
    private byte[] profile;
    private String profileBase64;

    public MemberDto() {}

    public MemberDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public MemberDto(int mno, String name, String email, String password, String role, byte[] profile) {
        this.mno = mno;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.profile = profile;
    }

    public int getMno() {
        return mno;
    }

    public void setMno(int mno) {
        this.mno = mno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public byte[] getProfile() {
        return profile;
    }

    public void setProfile(byte[] profile) {
        this.profile = profile;
    }
    
    public String getProfileBase64() {
        return profileBase64;
    }

    public void setProfileBase64(String profileBase64) {
        this.profileBase64 = profileBase64;
    }

	@Override
	public String toString() {
		return "MemberDto [mno=" + mno + ", name=" + name + ", email=" + email + ", password=" + password + ", role="
				+ role + ", profile=" + Arrays.toString(profile) + ", profileBase64=" + profileBase64 + "]";
	}
    
}
