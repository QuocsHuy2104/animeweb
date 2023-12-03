package model;

import java.sql.Date;

public class ChiTietDVModel {
	
	private String maHDCTDV;
	private float giaDV;
	private Date ngayThue, ngayTraDK, ngayTraCT;
	private String tenDV;
	private String maHDDV;
	private String maDV;
	private float thanhTien;
	
	// contructor fill table
	public ChiTietDVModel(String maDV, String tenDV, float giaDV, Date ngayThue, Date ngayTraCT, float thanhTien) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.giaDV = giaDV;
		this.ngayThue = ngayThue;
		this.ngayTraCT = ngayTraCT;
		this.thanhTien = thanhTien;
	}

	// contructor insert database
	public ChiTietDVModel(String maHDCTDV, float giaDV, Date ngayThue, Date ngayTraDK, Date ngayTraCT, String tenDV,
			String maHDDV) {
		super();
		this.maHDCTDV = maHDCTDV;
		this.giaDV = giaDV;
		this.ngayThue = ngayThue;
		this.ngayTraDK = ngayTraDK;
		this.ngayTraCT = ngayTraCT;
		this.tenDV = tenDV;
		this.maHDDV = maHDDV;
	}

	public String getMaHDCTDV() {
		return maHDCTDV;
	}

	public void setMaHDCTDV(String maHDCTDV) {
		this.maHDCTDV = maHDCTDV;
	}

	public float getGiaDV() {
		return giaDV;
	}

	public void setGiaDV(float giaDV) {
		this.giaDV = giaDV;
	}

	public Date getNgayThue() {
		return ngayThue;
	}

	public void setNgayThue(Date ngayThue) {
		this.ngayThue = ngayThue;
	}

	public Date getNgayTraDK() {
		return ngayTraDK;
	}

	public void setNgayTraDK(Date ngayTraDK) {
		this.ngayTraDK = ngayTraDK;
	}

	public Date getNgayTraCT() {
		return ngayTraCT;
	}

	public void setNgayTraCT(Date ngayTraCT) {
		this.ngayTraCT = ngayTraCT;
	}

	public String getTenDV() {
		return tenDV;
	}

	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}

	public String getMaHDDV() {
		return maHDDV;
	}

	public void setMaHDDV(String maHDDV) {
		this.maHDDV = maHDDV;
	}

	public String getMaDV() {
		return maDV;
	}

	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}

	public float getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(float thanhTien) {
		this.thanhTien = thanhTien;
	}
	
}
