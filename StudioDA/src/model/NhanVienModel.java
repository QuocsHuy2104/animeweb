package model;

import java.util.Date;

public class NhanVienModel {
	
	private String maNV, tenNV, diaChi, soDT;
	private double luong;
	private Date ngayNhan;
	private String pass;
	private boolean roles;

	public NhanVienModel(String maNV, String tenNV, String diaChi, String soDT, double luong, Date ngayNhan,
			String pass, boolean roles) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.diaChi = diaChi;
		this.soDT = soDT;
		this.luong = luong;
		this.ngayNhan = ngayNhan;
		this.pass = pass;
		this.roles = roles;
	}
	
	public NhanVienModel() {
		
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public boolean isRoles() {
		return roles;
	}

	public void setRoles(boolean roles) {
		this.roles = roles;
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

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public Date getNgayNhan() {
		return ngayNhan;
	}

	public void setNgayNhan(Date ngayNhan) {
		this.ngayNhan = ngayNhan;
	}
	
	

}
