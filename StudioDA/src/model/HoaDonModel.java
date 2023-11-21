package model;

import java.sql.Date;

public class HoaDonModel {
	
	private String mahd;
	private Date ngay;
	private float thanhToan;
	private String tenKH; // id khach hang
	private String tenNV ; // id nhan vien
	private String trangThai;
	
	public HoaDonModel(String mahd, Date ngay, float thanhToan, String tenKH, String tenNV, String trangThai) {
		super();
		this.mahd = mahd;
		this.ngay = ngay;
		this.thanhToan = thanhToan;
		this.tenKH = tenKH;
		this.tenNV = tenNV;
		this.trangThai = trangThai;
	}

	public String getMahd() {
		return mahd;
	}

	public void setMahd(String mahd) {
		this.mahd = mahd;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public float getThanhToan() {
		return thanhToan;
	}

	public void setThanhToan(float thanhToan) {
		this.thanhToan = thanhToan;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	
	
}
