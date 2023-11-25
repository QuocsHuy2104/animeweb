module StudioDA {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires java.sql;
	requires javafx.media;
	requires javax.mail;
	requires java.xml.bind;
	requires org.hibernate.orm.core;
	requires java.persistence;
	requires spring.data.jpa;
	requires spring.beans;
	requires net.bytebuddy;
	requires org.apache.poi.poi;
	requires org.apache.poi.ooxml;
	requires org.controlsfx.controls;
	
	opens application to javafx.graphics,javafx.fxml,javafx.controls;
	opens model to javafx.base;
	
}