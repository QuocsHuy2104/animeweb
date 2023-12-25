package model;

import java.sql.Date;

public class LichSuModel {
	
	private String maHD, maHDDV, tenKH;
	private Date ngayLap;
	private Double thanhToan;
	private String sdt;
	
	
	public LichSuModel(String maHD, String sdt,Date ngayLap, Double thanhToan, String tenKH) {
		this.maHD = maHD;
		this.tenKH = tenKH;
		this.sdt = sdt;
		this.ngayLap = ngayLap;
		this.thanhToan = thanhToan;
	}

	public LichSuModel(String maHDDV, String tenKH, String sdt, Date ngayLap, Double thanhToan) {
		this.maHDDV = maHDDV;
		this.tenKH = tenKH;
		this.sdt = sdt;
		this.ngayLap = ngayLap;
		this.thanhToan = thanhToan;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getMaHDDV() {
		return maHDDV;
	}

	public void setMaHDDV(String maHDDV) {
		this.maHDDV = maHDDV;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public Double getThanhToan() {
		return thanhToan;
	}

	public void setThanhToan(Double thanhToan) {
		this.thanhToan = thanhToan;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	
}
