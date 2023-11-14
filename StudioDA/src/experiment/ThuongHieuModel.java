package experiment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "THUONGHIEU")
public class ThuongHieuModel {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MaTH")
	private String maTH;
	
	@Column(name = "TenTH")
	private String tenTH;


	public ThuongHieuModel() {
	}

	public ThuongHieuModel(String tenTH) {
		super();
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

	@Override
	public String toString() {
		return "Employee [MaTH=" + maTH + ", TenTH=" + tenTH + "]";
	}
}
