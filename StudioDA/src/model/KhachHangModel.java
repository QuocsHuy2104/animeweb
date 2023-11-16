package model;

public class KhachHangModel {

	private String maKH, tenKH, diachi, sdt;
	private String gioiTinh;
	private String email;
	private boolean trangThai;

	public KhachHangModel(String maKH, String tenKH, String diachi, String sdt, String gioiTinh, String email,
			boolean trangThai) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.diachi = diachi;
		this.sdt = sdt;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.trangThai = trangThai;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	
}
