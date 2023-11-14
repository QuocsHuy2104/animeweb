package model;

public class SanPhamModel {
	
	private String maSP, tenSp, maTH;
	private float donGia;
	
	public SanPhamModel(String maSP, String tenSp, String maTH, Float donGia) {
		super();
		this.maSP = maSP;
		this.tenSp = tenSp;
		this.maTH = maTH;
		this.donGia = donGia;
	}
	
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSp() {
		return tenSp;
	}
	public void setTenSp(String tenSp) {
		this.tenSp = tenSp;
	}
	public String getMaTH() {
		return maTH;
	}
	public void setMaTH(String maTH) {
		this.maTH = maTH;
	}
	public Float getDonGia() {
		return donGia;
	}
	public void setDonGia(Float donGia) {
		this.donGia = donGia;
	}
	
}
