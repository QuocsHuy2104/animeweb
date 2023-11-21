package model;

public class HDCTModel {
	private String maHDCT;
	private float donGia;
	private int soLuong;
	private String maHD, tenSP;
	
	public HDCTModel(String maHDCT, float donGia, int soLuong, String maHD, String tenSP) {
		super();
		this.maHDCT = maHDCT;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.maHD = maHD;
		this.tenSP = tenSP;
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

	public void setTenHD(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getMaSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	
}
