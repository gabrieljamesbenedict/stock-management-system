package com.gabriel.ecomms.controller;
import com.gabriel.ecomms.StockManagementJFXApp;
import com.gabriel.ecomms.model.Stock;
import com.gabriel.ecomms.service.StockService;
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

public class ManageStockController extends GenericStockController {
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
	Stock selectedItem;

	@FXML
	private ListView<Stock> lvEcommerces;

		public void refresh() {
			Stock[] stocks = StockService.getService().getAll();
			lvEcommerces.getItems().clear();
			lvEcommerces.getItems().addAll(stocks);
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
		GenericStockController.selectedItem = lvEcommerces.getSelectionModel().getSelectedItem();
		if(GenericStockController.selectedItem == null) {
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
			FXMLLoader fxmlLoader = new FXMLLoader(StockManagementJFXApp.class.getResource("create-ecom-view.fxml"));
			Parent root = fxmlLoader.load();
			CreateStockController controller = fxmlLoader.getController();
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
		if(GenericStockController.selectedItem == null){
			showErrorDialog("Please select a stock from the list", "Cannot edit");
		return;
		}
		Node node = ((Node) (actionEvent.getSource()));
		Scene currentScene = node.getScene();
		Window window = currentScene.getWindow();
		window.hide();
		if(editViewScene == null){
			FXMLLoader fxmlLoader = new FXMLLoader(StockManagementJFXApp.class.getResource("edit-ecom-view.fxml"));
			Parent root = fxmlLoader.load();
			EditStockController controller = fxmlLoader.getController();
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
		if(GenericStockController.selectedItem == null){
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
		Stock stock = toObject(true);
		StockService.getService().delete(stock.getId());
		refresh();
	}


	public void onClose(ActionEvent actionEvent) {
		super.onClose(actionEvent);
	}


}
