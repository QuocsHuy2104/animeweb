﻿CREATE DATABASE STUDIO
GO
USE STUDIO
GO

CREATE TABLE NHANVIEN(
	MaNV NVARCHAR(50) PRIMARY KEY NOT NULL,
	TenNV text NOT NULL,
	DIACHI text NOT NULL,
	SDT VARCHAR(10) NOT NULL,
	Email text NOT NULL,
	pass varchar(50) not null,
	vaitro bit not null,
	trangThai bit not null
)
INSERT INTO NHANVIEN VALUES ('KyDV',N'Đặng Vĩnh Kỳ',N'Châu Phú - An Giang','0398948675',N'KyDV@gmail.com','1234',1)
INSERT INTO NHANVIEN VALUES ('TrieuNM',N'Nguyễn Minh Triệu',N'Ninh Kiều - Cần Thơ','0332456768',N'TrieuNM@gmail.com','1234',1)
INSERT INTO NHANVIEN VALUES ('DayNV',N'Nguyễn Vũ Đầy',N'An Phú - An Giang','0398758894',N'DayNV@gmail.com','1234',1)

select * from NHANVIEN

CREATE TABLE KHACHHANG(
	MaKH NVARCHAR(50) PRIMARY KEY NOT NULL,
	TenKH text NOT NULL,
	DIACHI text NOT NULL,
	SDT VARCHAR(10) NOT NULL,
	GioiTinh Bit NOT NULL,
	Email text NOT NULL,
	trangThai bit not null
)

INSERT INTO KHACHHANG VALUES ('KH0001',N'Đinh Văn Phát',N'Thới Bình - Cà Mau',N'0358936498',1,N'PhatDV@gmail.com',1)
INSERT INTO KHACHHANG VALUES ('KH0002',N'Trần Văn Nghĩa',N'22 Ngô Quyền Q5',N'0358936889',1,N'NghiaTV@gmail.com',1)
INSERT INTO KHACHHANG VALUES ('KH0003',N'Lê Văn Tám',N'123 Trần Phú',N'0358939981',1,N'TamLV@gmail.com',1)
INSERT INTO KHACHHANG VALUES ('KH0004',N'Lê Thị Thủy',N'155 Trần Hưng Đạo',N'0398765724',0,N'ThuyLT@gmail.com',0)
INSERT INTO KHACHHANG VALUES ('KH0005',N'Ngô Thanh Sơn',N'20 Trần Phú Q2',N'0397856791',1,N'SonNT@gmail.com',1)


CREATE TABLE HOADON (
	MaHD NVARCHAR(50) PRIMARY KEY NOT NULL,
	NgayLapHD Date NOT NULL,
	ThanhToan FLOAT NOT NULL,
	MaKH NVARCHAR(50) NOT NULL,
	MaNV NVARCHAR(50) NOT NULL,
	FOREIGN KEY(MaKH) REFERENCES KHACHHANG(MaKH),
	FOREIGN KEY(MaNV) REFERENCES NHANVIEN(MaNV),
)

INSERT INTO HOADON VALUES ('HD0001','11/08/2023',1000,'')

CREATE TABLE SANPHAM(
	MaSP NVARCHAR(50) PRIMARY KEY NOT NULL,
	TenSP text NOT NULL,
	DonGia FLOAT NOT NULL,
	MaTH NVARCHAR(50) NOT NULL,
	FOREIGN KEY(MaTH) REFERENCES THUONGHIEU(MATH),
)

INSERT INTO SANPHAM VALUES ('SP0001')



CREATE TABLE HDCT(
	MaHDCT VARCHAR(10) PRIMARY KEY,
	DonGia float not null,
	SoLuong int not null,
	MaHD NVARCHAR(50) NOT NULL,
	MaSP NVARCHAR(50) NOT NULL,
	FOREIGN KEY(MaHD) REFERENCES HOADON(MaHD),
	FOREIGN KEY(MaSP) REFERENCES SANPHAM(MaSP),
)

CREATE TABLE THUONGHIEU(
	MaTH NVARCHAR(50) NOT NULL PRIMARY KEY,
	TenTH TEXT NOT NULL
)

INSERT INTO THUONGHIEU VALUES ('TH0001',N'Joli Poli')
INSERT INTO THUONGHIEU VALUES ('TH0002',N'Calla – Calla Bridal')
INSERT INTO THUONGHIEU VALUES ('TH0003',N'Lumière Bridal')
INSERT INTO THUONGHIEU VALUES ('TH0004',N'Linh Nga Bridal')
INSERT INTO THUONGHIEU VALUES ('TH0005',N'WEDDINGBOOK SAIGON')
INSERT INTO THUONGHIEU VALUES ('TH0006',N'IDY Wedding')
INSERT INTO THUONGHIEU VALUES ('TH0007',N'Vanilla House')
INSERT INTO THUONGHIEU VALUES ('TH0008',N'Chung Thanh Phong')
INSERT INTO THUONGHIEU VALUES ('TH0009',N'Ciel de Gia')
INSERT INTO THUONGHIEU VALUES ('TH0010',N'Truong Thanh Hai')


CREATE TABLE DICHVU(
	MaDV NVARCHAR(50) NOT NULL PRIMARY KEY,
	TenDV TEXT NOT NULL,
	GiaDV FLOAT NOT NULL,
	MoTaDV TEXT NOT NULL,
	MaSP NVARCHAR(50) NOT NULL,
	FOREIGN KEY(MaSP) REFERENCES SANPHAM(MaSP),
)

CREATE TABLE HOADONDV(
	MaHDDV NVARCHAR(50) PRIMARY KEY NOT NULL,
	NgayLapHDDV DATE NOT NULL,
	THANHTOAN FLOAT NOT NULL,
	MaKH NVARCHAR(50) NOT NULL,
	MaNV NVARCHAR(50) NOT NULL,
	FOREIGN KEY(MaKH) REFERENCES KHACHHANG(MaKH),
	FOREIGN KEY(MaNV) REFERENCES NHANVIEN(MaNV),
)

CREATE TABLE CHITIETHOADONDV(
	MaCTHDDV NVARCHAR(50) NOT NULL PRIMARY KEY,
	NgayThue DATE NOT NULL,
	NgayTraDK DATE NOT NULL,
	NgayTraCT DATE NOT NULL,
	MaDV NVARCHAR(50) NOT NULL,
	MaHDDV NVARCHAR(50) NOT NULL,
	FOREIGN KEY(MaDV) REFERENCES DICHVU(MaDV),
	FOREIGN KEY(MaHDDV) REFERENCES HOADONDV(MaHDDV),
)


--Số lượng sản phẩm
Create or alter FUNCTION TONGSLSP()
RETURNS INT
BEGIN
	RETURN(SELECT COUNT(MaSP) from SANPHAM)
END

print 'Tổng số lượng sản phẩm là: '+ convert(VARCHAR,dbo.TONGSLSP())

--Số lượng khách hàng
Create or alter FUNCTION TONGSLKH()
RETURNS INT
BEGIN
	RETURN(SELECT COUNT(MaKH) from KHACHHANG)
END

print 'Tổng số lượng khách hàng là: '+ convert(VARCHAR,dbo.TONGSLKH())

--Số lượng Hóa đơn
Create or alter FUNCTION TONGSLHD()
RETURNS INT
BEGIN
	RETURN(SELECT COUNT(MaHD) from HOADON)
END

print 'Tổng số lượng hóa đơn là: '+ convert(VARCHAR,dbo.TONGSLHD())

--Số lượng nhân viên
Create or alter FUNCTION TONGSLNV()
RETURNS INT
BEGIN
	RETURN(SELECT COUNT(MaNV) from NHANVIEN)
END

print N'Tổng số lượng nhân viên là: '+ convert(VARCHAR,dbo.TONGSLNV())

SELECT COUNT(MaHD) FROM HOADON
INNER JOIN KHACHHANG ON KHACHHANG.ID_KhachHang = HOADON.ID_KhachHang


