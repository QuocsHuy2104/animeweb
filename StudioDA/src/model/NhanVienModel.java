package model;

public class NhanVienModel {
	private String maNV, tenNV, diaChi, sdt, email, matKhau;
	private boolean vaiTro;
	private boolean trangThai;
	
	public NhanVienModel(String maNV, String tenNV, String diaChi, String sdt, String email, String matKhau,
			boolean vaiTro, boolean trangThai) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.email = email;
		this.matKhau = matKhau;
		this.vaiTro = vaiTro;
		this.trangThai = trangThai;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public boolean isVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(boolean vaiTro) {
		this.vaiTro = vaiTro;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}
	
}
