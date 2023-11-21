package model;

public class SanPhamModel {
	
	private String maSP, tenSp;
	private float donGia;
	private String tenTH;
	
	public SanPhamModel(String maSP, String tenSp, float donGia, String tenTH) {
		super();
		this.maSP = maSP;
		this.tenSp = tenSp;
		this.donGia = donGia;
		this.tenTH = tenTH;
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

	public float getDonGia() {
		return donGia;
	}

	public void setDonGia(float donGia) {
		this.donGia = donGia;
	}

	public String getTenTH() {
		return tenTH;
	}

	public void setTenTH(String tenTH) {
		this.tenTH = tenTH;
	}
	
	
}
