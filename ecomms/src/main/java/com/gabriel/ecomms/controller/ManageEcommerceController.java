package com.gabriel.ecomms.controller;
import com.gabriel.ecomms.ManageEcommerceJFXApp;
import com.gabriel.ecomms.model.Ecommerce;
import com.gabriel.ecomms.service.EcommerceService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Window;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Setter;

public class ManageEcommerceController extends GenericEcommerceController{
	@Setter
	Stage stage;

	@Setter
	Scene createViewScene;

	@Setter
	Scene editViewScene;

	@Setter
	Scene deleteViewScene;

	public ImageView ecommerceImage;
	@FXML
	public Button btnCreate;

	@FXML
	public Button btnEdit;

	@FXML
	public Button btnDelete;

	@FXML
	public Button btnClose;

	@FXML
	public Button imageButton;
	Ecommerce selectedItem;

	@FXML
	private ListView<Ecommerce> lvEcommerces;

		public void refresh() {
			Ecommerce[] ecommerces = EcommerceService.getService().getAll();
			lvEcommerces.getItems().clear();
			lvEcommerces.getItems().addAll(ecommerces);
			enableFields(false);
		}

	@Override
	public void init() {
		try {
			refresh();
		}
		catch (Exception e){
			showErrorDialog("Message: ", e.getMessage());
		}
	}

	public void onAction(MouseEvent mouseEvent) {
		GenericEcommerceController.selectedItem = lvEcommerces.getSelectionModel().getSelectedItem();
		if(GenericEcommerceController.selectedItem == null) {
			return;
		}
		setFields("Manage");
	}
	public void onCreate(ActionEvent actionEvent)  throws Exception {
		Node node = ((Node) (actionEvent.getSource()));
		Scene currentScene = node.getScene();
		Window window = currentScene.getWindow();
		window.hide();
		if(createViewScene == null){
			FXMLLoader fxmlLoader = new FXMLLoader(ManageEcommerceJFXApp.class.getResource("create-ecom-view.fxml"));
			Parent root = fxmlLoader.load();
			CreateEcommerceController controller = fxmlLoader.getController();
			controller.setStage(stage);
			createViewScene = new Scene(root);
			controller.setManageEcommerceController(this);
			controller.setManageScene(manageScene);
			controller.setSplashScene(splashScene);
		}
		stage.setTitle("Create Stock");
		stage.setScene(createViewScene);
		stage.show();
	}


	public void onEdit(ActionEvent actionEvent)  throws Exception {
		if(GenericEcommerceController.selectedItem == null){
			showErrorDialog("Please select a stock from the list", "Cannot edit");
		return;
		}
		Node node = ((Node) (actionEvent.getSource()));
		Scene currentScene = node.getScene();
		Window window = currentScene.getWindow();
		window.hide();
		if(editViewScene == null){
			FXMLLoader fxmlLoader = new FXMLLoader(ManageEcommerceJFXApp.class.getResource("edit-ecom-view.fxml"));
			Parent root = fxmlLoader.load();
			EditEcommerceController controller = fxmlLoader.getController();
			controller.setStage(stage);
			editViewScene = new Scene(root);
			controller.setManageEcommerceController(this);
			controller.setManageScene(manageScene);
			controller.setSplashScene(splashScene);
		}
		stage.setTitle("Edit Stock");
		stage.setScene(editViewScene);
		stage.show();
	}


	public void onDelete(ActionEvent actionEvent)  throws Exception {
		if(GenericEcommerceController.selectedItem == null){
			showErrorDialog("Please select a stock from the list", "Cannot delete");
		return;
		}
//		Node node = ((Node) (actionEvent.getSource()));
//		Scene currentScene = node.getScene();
//		Window window = currentScene.getWindow();
//		window.hide();
//		if(deleteViewScene == null){
//			FXMLLoader fxmlLoader = new FXMLLoader(ManageEcommerceJFXApp.class.getResource("delete-ecom-view.fxml"));
//			Parent root = fxmlLoader.load();
//			DeleteEcommerceController controller = fxmlLoader.getController();
//			controller.setStage(stage);
//			deleteViewScene = new Scene(root);
//			controller.setManageEcommerceController(this);
//			controller.setManageScene(manageScene);
//			controller.setSplashScene(splashScene);
//		}
//		stage.setTitle("Delete Stock");
//		stage.setScene(deleteViewScene);
//		stage.show();
		Ecommerce ecommerce = toObject(true);
		EcommerceService.getService().delete(ecommerce.getId());
		refresh();
	}


	public void onClose(ActionEvent actionEvent) {
		super.onClose(actionEvent);
	}


}
