package model;

public class HDCTModel {
	private String maHDCT;
	private float donGia;
	private int soLuong;
	private String maHD, tenSP;
	
	private String masp, trangThai;
	private float thanhTien;
	
	public HDCTModel(float donGia, int soLuong, String tenSP, String masp, String trangThai, float thanhTien) {
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.tenSP = tenSP;
		this.masp = masp;
		this.trangThai = trangThai;
		this.thanhTien = thanhTien;
	}

	public HDCTModel(String maHDCT, float donGia, int soLuong, String maHD, String masp) {
		super();
		this.maHDCT = maHDCT;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.maHD = maHD;
		this.masp = masp;
	}

	public String getMaHDCT() {
		return maHDCT;
	}

	public void setMaHDCT(String maHDCT) {
		this.maHDCT = maHDCT;
	}

	public float getDonGia() {
		return donGia;
	}

	public void setDonGia(float donGia) {
		this.donGia = donGia;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getMasp() {
		return masp;
	}

	public void setMasp(String masp) {
		this.masp = masp;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public float getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(float thanhTien) {
		this.thanhTien = thanhTien;
	}

}
