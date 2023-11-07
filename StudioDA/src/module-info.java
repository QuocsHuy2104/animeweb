module StudioDA {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.desktop;
	requires java.sql;
	requires javafx.media;
	
	opens application to javafx.graphics,javafx.fxml,javafx.controls;
	opens model to javafx.base;
	
}