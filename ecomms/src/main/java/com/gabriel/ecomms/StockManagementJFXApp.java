package com.gabriel.ecomms;
import com.gabriel.ecomms.controller.SplashViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class StockManagementJFXApp extends Application {
@Override
	public void start(Stage stage) throws IOException {
		System.out.println("SplashApp:start ");
		FXMLLoader fxmlLoader = new FXMLLoader(StockManagementJFXApp.class.getResource("splash-view.fxml"));
		Parent root = (Parent)fxmlLoader.load();
		SplashViewController splashViewController= fxmlLoader.getController();
		splashViewController.setStage(stage);
		Scene scene = new Scene(root);
		splashViewController.setSplashScene(scene);
		stage.setTitle("Stock Management!");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
}
