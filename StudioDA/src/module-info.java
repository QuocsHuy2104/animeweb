module StudioDA {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires java.sql;
	requires javax.mail;
	requires java.xml.bind;
	requires java.persistence;
	requires org.controlsfx.controls;
	requires aspose.pdf;
	requires org.apache.poi.poi;
	requires org.apache.poi.ooxml;
	requires org.hibernate.orm.core;
	
	opens application to javafx.graphics,javafx.fxml,javafx.controls;
	opens model to javafx.base;
}