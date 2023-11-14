package application;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicController implements Initializable {
	
	@FXML
	private Button btnPrev, btnNext, btnPlay, btnPause, btnReset;
	
	@FXML
	private ComboBox<String> cbbSpeed;
	
	@FXML
	private Slider volume;
	
	@FXML
	private ProgressBar songPregressBar;
	
	private File directory;
	private File[] files;
	
	private ArrayList<File> songs;
	
	private int songNumber;
	private int[] speed = {
			25, 50, 75, 100, 125, 150, 175, 200
	};
	
	private Timer timer;
	private TimerTask timertask;
	private boolean running;
	
	private Media media;
	private MediaPlayer player;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		songs = new ArrayList<File>();
		directory = new File("Music");
		files = directory.listFiles();
		
		if (files != null) {
			for (File file : files) {
				songs.add(file);
				
			}
		}
		
		media = new Media(songs.get(songNumber).toURI().toString());
		player = new MediaPlayer(media);
		
		for (int i = 0; i < speed.length; i++) {
			cbbSpeed.getItems().add(Integer.toString(speed[i]) + "%");
		}
		
		cbbSpeed.setOnAction(this::changeSpeed);
		volume.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player.setVolume(volume.getValue() * 0.01);
			}
		});
		
		songPregressBar.setStyle("-fx-accent: #b103fc"); 
	}
	
	public void playMedia() {	
		beginTimer();
		changeSpeed(null);
		player.play();
	}
	
	public void pauseMedia() {
		cancelTimer();
		player.pause();
	}
	
	public void reset() {
		songPregressBar.setProgress(0);
		player.seek(Duration.seconds(0));
	}
	
	public void nextMedia() {
		if (songNumber < songs.size() - 1) {
			songNumber++;
			player.stop();
			
			if (running) {
				cancelTimer();
			}
			
			media = new Media(songs.get(songNumber).toURI().toString());
			player = new MediaPlayer(media);
			
			playMedia();
		} else {
			songNumber = 0;
			player.stop();
			
			if (running) {
				cancelTimer();
			}
			
			media = new Media(songs.get(songNumber).toURI().toString());
			player = new MediaPlayer(media);
			playMedia();
		}
	}
	
	public void prevMedia() {
		if (songNumber > 0) {
			songNumber--;
			player.stop();
			
			if (running) {
				cancelTimer();
			}
			
			media = new Media(songs.get(songNumber).toURI().toString());
			player = new MediaPlayer(media);
			
			playMedia();
		} else {
			songNumber = songs.size() - 1;
			player.stop();
			
			if (running) {
				cancelTimer();
			}
			
			media = new Media(songs.get(songNumber).toURI().toString());
			player = new MediaPlayer(media);
			playMedia();
		}
	}
	
	public void changeSpeed(ActionEvent e) {
		
		if (cbbSpeed.getValue() == null) {
			player.setRate(1);
		} else {
			player.setRate(Integer.parseInt(cbbSpeed.getValue().substring(0, cbbSpeed.getValue().length() - 1)) * 0.01);
		}
		
	}
	
	public void beginTimer() {
		timer = new Timer();
		timertask = new TimerTask() {

			@Override
			public void run() {
				running = true;
				double current = player.getCurrentTime().toSeconds();
				double end = media.getDuration().toSeconds();
				songPregressBar.setProgress(current/end);
				if ( current / end == 1) {
					cancelTimer();
				}
			}

		};
		
		timer.scheduleAtFixedRate(timertask, 1000, 1000);
	}
	
	public void cancelTimer() {
		running = false;
		timer.cancel();
	}
	
}
