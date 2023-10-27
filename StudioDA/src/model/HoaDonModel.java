package model;

import java.util.Date;

public class HoaDonModel {
	
	private String mahd;
	private String ngay;
	private float thanhToan;
	private int ID_KhachHang;
	private int ID_NV ;
	
	public HoaDonModel(String mahd, String ngay, float thanhToan, int iD_KhachHang, int iD_NV) {
		super();
		this.mahd = mahd;
		this.ngay = ngay;
		this.thanhToan = thanhToan;
		this.ID_KhachHang = iD_KhachHang;
		this.ID_NV = iD_NV;
	}

	public String getMahd() {
		return mahd;
	}

	public void setMahd(String mahd) {
		this.mahd = mahd;
	}

	public String getNgay() {
		return ngay;
	}

	public void setNgay(String ngay) {
		this.ngay = ngay;
	}

	public float getThanhToan() {
		return thanhToan;
	}

	public void setThanhToan(float thanhToan) {
		this.thanhToan = thanhToan;
	}

	public int getID_KhachHang() {
		return ID_KhachHang;
	}

	public void setID_KhachHang(int iD_KhachHang) {
		ID_KhachHang = iD_KhachHang;
	}

	public int getID_NV() {
		return ID_NV;
	}

	public void setID_NV(int iD_NV) {
		ID_NV = iD_NV;
	}
	
}
