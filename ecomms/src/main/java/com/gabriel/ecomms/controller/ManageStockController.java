package com.gabriel.ecomms.controller;
import com.gabriel.ecomms.StockManagementJFXApp;
import com.gabriel.ecomms.model.Stock;
import com.gabriel.ecomms.service.StockService;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Window;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.Setter;

public class ManageStockController extends GenericStockController {
	@Setter
	Stage stage;

	@Setter
	Scene createViewScene;

	@Setter
	Scene editViewScene;

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
	private TableView<Stock> lvEcommerces;
	private TableColumn<Stock, String> id, name, description, product, category, status, available, inventory, price;
	final private TableColumn[] columns = {id, name, description, product, category, status, available, inventory, price};

		public void refresh() {
			Stock[] stocks = StockService.getService().getAll();
			lvEcommerces.getItems().clear();
			lvEcommerces.getItems().addAll(stocks);
			enableFields(false);
		}

	@Override
	public void init() {
		id = new TableColumn<>("ID");
		name = new TableColumn<>("Name");
		description = new TableColumn<>("Description");
		product = new TableColumn<>("Product Type");
		category = new TableColumn<>("Category");
		status = new TableColumn<>("Status");
		available = new TableColumn<>("Available Units");
		inventory = new TableColumn<>("Inventory Loc");
		price = new TableColumn<>("Price");
		id.setCellValueFactory(s -> new ReadOnlyStringWrapper(Integer.toString(s.getValue().getId())));
		name.setCellValueFactory(s -> new ReadOnlyStringWrapper(s.getValue().getName()));
		description.setCellValueFactory(s -> new ReadOnlyStringWrapper(s.getValue().getDescription()));
		product.setCellValueFactory(s -> new ReadOnlyStringWrapper(s.getValue().getProductName()));
		category.setCellValueFactory(s -> new ReadOnlyStringWrapper(s.getValue().getCategoryName()));
		status.setCellValueFactory(s -> new ReadOnlyStringWrapper(s.getValue().getStatusName()));
		available.setCellValueFactory(s -> new ReadOnlyStringWrapper(Double.toString(s.getValue().getQuantityAvailable())));
		inventory.setCellValueFactory(s -> new ReadOnlyStringWrapper(s.getValue().getInventoryName()));
		price.setCellValueFactory(s -> new ReadOnlyStringWrapper(String.format("%.2f", s.getValue().getPrice())));
		lvEcommerces.getColumns().addAll(id, name, description, product, category, status, available, inventory, price);
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
