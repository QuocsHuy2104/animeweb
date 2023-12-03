package model;

import java.sql.Date;

public class HDDVModel {
	
	private String maHDDV;
	private Date ngayLap;
	private float thanhToan;
	private String tenKhachHang, tenNhanVien;
	private String trangThai; // int in database
	
	public HDDVModel(String maHDDV, Date ngayLap, float thanhToan ,String tenKhachHang, String tenNhanVien, String trangThai) {
		this.maHDDV = maHDDV;
		this.ngayLap = ngayLap;
		this.thanhToan = thanhToan;
		this.tenKhachHang = tenKhachHang;
		this.tenNhanVien = tenNhanVien;
		this.trangThai = trangThai;
	}

	public String getMaHDDV() {
		return maHDDV;
	}

	public void setMaHDDV(String maHDDV) {
		this.maHDDV = maHDDV;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}
	
	public float getThanhToan() {
        return thanhToan;
    }
	
	public void setThanhToan(float thanhToan) {
        this.thanhToan = thanhToan;
    }

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
}
