package model;

public class ThongKeModel {
	private String masp, mahd;
	private int soluong;
	private float giaBan;
	
	public ThongKeModel(String masp, String mahd, int soluong, float giaBan) {
		super();
		this.masp = masp;
		this.mahd = mahd;
		this.soluong = soluong;
		this.giaBan = giaBan;
	}

	public String getMasp() {
		return masp;
	}

	public void setMasp(String masp) {
		this.masp = masp;
	}

	public String getMahd() {
		return mahd;
	}

	public void setMahd(String mahd) {
		this.mahd = mahd;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public float getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}
	
}
