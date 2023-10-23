package model;

public class SanPhamModel {
	
	private String maSP, tenSp, thuongHieu;
	private float giaDichVu;
	private String moTa, dichVu;
	private int maNV;
	
	public SanPhamModel(String maSP, String tenSp, String thuongHieu, float giaDichVu, String moTa,
			String dichVu, int maNV) {
		super();
		this.maSP = maSP;
		this.tenSp = tenSp;
		this.thuongHieu = thuongHieu;
		this.giaDichVu = giaDichVu;
		this.moTa = moTa;
		this.dichVu = dichVu;
		this.maNV = maNV;
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

	public String getThuongHieu() {
		return thuongHieu;
	}

	public void setThuongHieu(String thuongHieu) {
		this.thuongHieu = thuongHieu;
	}

	public float getGiaDichVu() {
		return giaDichVu;
	}

	public void setGiaDichVu(float giaDichVu) {
		this.giaDichVu = giaDichVu;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getDichVu() {
		return dichVu;
	}

	public void setDichVu(String dichVu) {
		this.dichVu = dichVu;
	}

	public int getMaNV() {
		return maNV;
	}

	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}
	
}
