package model;

public class DichVuModel {
	
	private String maDV, tenDV;
	private Float giaDV;
	private String moTa;
	private String tenSp;
	
	public DichVuModel(String maDV, String tenDV, Float giaDV, String moTa, String tenSp) {
		super();
		this.maDV = maDV;
		this.tenDV = tenDV;
		this.giaDV = giaDV;
		this.moTa = moTa;
		this.tenSp = tenSp;
	}

	public String getMaDV() {
		return maDV;
	}

	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}

	public String getTenDV() {
		return tenDV;
	}

	public void setTenDV(String tenDV) {
		this.tenDV = tenDV;
	}

	public Float getGiaDV() {
		return giaDV;
	}

	public void setGiaDV(Float giaDV) {
		this.giaDV = giaDV;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getTenSp() {
		return tenSp;
	}

	public void setTenSp(String tenSp) {
		this.tenSp = tenSp;
	}
	
}
