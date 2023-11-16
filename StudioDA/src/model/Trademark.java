package model;

public class Trademark {
	
	private String maTH, tenTH;

	public Trademark(String maTH, String tenTH) {
		super();
		this.maTH = maTH;
		this.tenTH = tenTH;
	}

	public String getMaTH() {
		return maTH;
	}

	public void setMaTH(String maTH) {
		this.maTH = maTH;
	}

	public String getTenTH() {
		return tenTH;
	}

	public void setTenTH(String tenTH) {
		this.tenTH = tenTH;
	}

}
