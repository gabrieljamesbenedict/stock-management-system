package com.gabriel.ecomms.controller;
import com.gabriel.ecomms.model.Stock;
import com.gabriel.ecomms.model.Product;
import com.gabriel.ecomms.service.ProductService;
import com.gabriel.ecomms.model.Category;
import com.gabriel.ecomms.service.CategoryService;
import com.gabriel.ecomms.model.Status;
import com.gabriel.ecomms.service.StatusService;
import com.gabriel.ecomms.model.Inventory;
import com.gabriel.ecomms.service.InventoryService;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.Setter;
import javafx.util.StringConverter;
import java.net.URL;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.Locale;

public class GenericStockController implements Initializable{
	@Setter
	CreateStockController createEcommerceController;

	@Setter
	EditStockController editEcommerceController;

	@Setter
	ManageStockController manageEcommerceController;

	@Setter
	Stage stage;

	@Setter
	Scene splashScene;

	@Setter
	Scene manageScene;

	@Setter
	public ListView<Stock> lvEcommerces;

	@Setter
	public static Stock selectedItem;
	public TextField txtId;
	public TextField txtName;
	public TextField txtDescription;
	public ComboBox<Product> cmbProduct;
	public TextField txtProductName;
	public ComboBox<Category> cmbCategory;
	public TextField txtCategoryName;
	public ComboBox<Status> cmbStatus;
	public TextField txtStatusName;
	public TextField txtQuantityAvailable;
	public ComboBox<Inventory> cmbInventory;
	public TextField txtInventoryName;
	public TextField txtPrice;


	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		Product[] products =  (Product[]) ProductService.getService().getAll();
		cmbProduct.getItems().addAll(products);
		StringConverter<Product> productConverter = new StringConverter<Product>() {
			@Override
			public String toString(Product product) {
			if(product==null)
				return "";
			else
				return product.toString();
			}
			@Override
			public Product fromString(String s) {
				if(!s.isEmpty()){
					for (Product product : products) {
						if (s.equals(product.getName())){
							return product;
						}
					}
				}
				return null;
			}
		};
		cmbProduct.setConverter(productConverter);


		Category[] categorys =  (Category[]) CategoryService.getService().getAll();
		cmbCategory.getItems().addAll(categorys);
		StringConverter<Category> categoryConverter = new StringConverter<Category>() {
			@Override
			public String toString(Category category) {
			if(category==null)
				return "";
			else
				return category.toString();
			}
			@Override
			public Category fromString(String s) {
				if(!s.isEmpty()){
					for (Category category : categorys) {
						if (s.equals(category.getName())){
							return category;
						}
					}
				}
				return null;
			}
		};
		cmbCategory.setConverter(categoryConverter);


		Status[] statuss =  (Status[]) StatusService.getService().getAll();
		cmbStatus.getItems().addAll(statuss);
		StringConverter<Status> statusConverter = new StringConverter<Status>() {
			@Override
			public String toString(Status status) {
			if(status==null)
				return "";
			else
				return status.toString();
			}
			@Override
			public Status fromString(String s) {
				if(!s.isEmpty()){
					for (Status status : statuss) {
						if (s.equals(status.getName())){
							return status;
						}
					}
				}
				return null;
			}
		};
		cmbStatus.setConverter(statusConverter);


		Inventory[] inventorys =  (Inventory[]) InventoryService.getService().getAll();
		cmbInventory.getItems().addAll(inventorys);
		StringConverter<Inventory> inventoryConverter = new StringConverter<Inventory>() {
			@Override
			public String toString(Inventory inventory) {
			if(inventory==null)
				return "";
			else
				return inventory.toString();
			}
			@Override
			public Inventory fromString(String s) {
				if(!s.isEmpty()){
					for (Inventory inventory : inventorys) {
						if (s.equals(inventory.getName())){
							return inventory;
						}
					}
				}
				return null;
			}
		};
		cmbInventory.setConverter(inventoryConverter);


		init();
	}


	protected void init(){
		System.out.println("Invoked from Generic Controller");
	}


	protected Stock toObject(boolean isEdit){
		Stock stock = new Stock();
		try {
			if(isEdit) {
				stock.setId(Integer.parseInt(txtId.getText()));
			}
			stock.setName(txtName.getText());
			stock.setDescription(txtDescription.getText());
			Product product = cmbProduct.getSelectionModel().getSelectedItem();
			stock.setProductId(product.getId());
			stock.setProductName(product.getName());
			Category category = cmbCategory.getSelectionModel().getSelectedItem();
			stock.setCategoryId(category.getId());
			stock.setCategoryName(category.getName());
			Status status = cmbStatus.getSelectionModel().getSelectedItem();
			stock.setStatusId(status.getId());
			stock.setStatusName(status.getName());
			stock.setQuantityAvailable(Double.parseDouble(txtQuantityAvailable.getText()));
			Inventory inventory = cmbInventory.getSelectionModel().getSelectedItem();
			stock.setInventoryId(inventory.getId());
			stock.setInventoryName(inventory.getName());
			stock.setPrice(Double.parseDouble(txtPrice.getText()));
		}catch (Exception e){
			showErrorDialog("Error" ,e.getMessage());
		}
		return stock;
	}


	protected void setFields(String action){
		String formattedDate;
		Stock stock = GenericStockController.selectedItem;
		SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
		txtId.setText(Integer.toString(stock.getId()));
		txtName.setText(stock.getName());
		txtDescription.setText(stock.getDescription());
		Product product = ProductService.getService().get(stock.getProductId());
		cmbProduct.getSelectionModel().select(product);
		if(action.equals("Create") || action.equals("Edit")){
			cmbProduct.setVisible(true);
			txtProductName.setVisible(false);
			cmbProduct.getSelectionModel().select(product);
		}
		else{
			cmbProduct.setVisible(false);
			txtProductName.setVisible(true);
			txtProductName.setText(product.getName());
		}
		txtProductName.setText(stock.getProductName());
		Category category = CategoryService.getService().get(stock.getCategoryId());
		cmbCategory.getSelectionModel().select(category);
		if(action.equals("Create") || action.equals("Edit")){
			cmbCategory.setVisible(true);
			txtCategoryName.setVisible(false);
			cmbCategory.getSelectionModel().select(category);
		}
		else{
			cmbCategory.setVisible(false);
			txtCategoryName.setVisible(true);
			txtCategoryName.setText(category.getName());
		}
		txtCategoryName.setText(stock.getCategoryName());
		Status status = StatusService.getService().get(stock.getStatusId());
		cmbStatus.getSelectionModel().select(status);
		if(action.equals("Create") || action.equals("Edit")){
			cmbStatus.setVisible(true);
			txtStatusName.setVisible(false);
			cmbStatus.getSelectionModel().select(status);
		}
		else{
			cmbStatus.setVisible(false);
			txtStatusName.setVisible(true);
			txtStatusName.setText(status.getName());
		}
		txtStatusName.setText(stock.getStatusName());
		txtQuantityAvailable.setText(Double.toString(stock.getQuantityAvailable()));
		Inventory inventory = InventoryService.getService().get(stock.getInventoryId());
		cmbInventory.getSelectionModel().select(inventory);
		if(action.equals("Create") || action.equals("Edit")){
			cmbInventory.setVisible(true);
			txtInventoryName.setVisible(false);
			cmbInventory.getSelectionModel().select(inventory);
		}
		else{
			cmbInventory.setVisible(false);
			txtInventoryName.setVisible(true);
			txtInventoryName.setText(inventory.getName());
		}
		txtInventoryName.setText(stock.getInventoryName());
		txtPrice.setText(Double.toString(stock.getPrice()));
	}


	protected void clearFields(String action){
		txtId.setText("");
		//txtName.setText("");
		//txtDescription.setText("");
		cmbProduct.getSelectionModel().clearSelection();
		txtProductName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbProduct.setVisible(true);
			txtProductName.setVisible(false);
		}
		else{
			cmbProduct.setVisible(false);
			txtProductName.setVisible(true);
		}
		//txtProductName.setText("");
		cmbCategory.getSelectionModel().clearSelection();
		txtCategoryName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbCategory.setVisible(true);
			txtCategoryName.setVisible(false);
		}
		else{
			cmbCategory.setVisible(false);
			txtCategoryName.setVisible(true);
		}
		//txtCategoryName.setText("");
		cmbStatus.getSelectionModel().clearSelection();
		txtStatusName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbStatus.setVisible(true);
			txtStatusName.setVisible(false);
		}
		else{
			cmbStatus.setVisible(false);
			txtStatusName.setVisible(true);
		}
		//txtStatusName.setText("");
		//txtQuantityAvailable.setText("");
		cmbInventory.getSelectionModel().clearSelection();
		txtInventoryName.setText("");
		if(action.equals("Create") || action.equals("Edit")){
			cmbInventory.setVisible(true);
			txtInventoryName.setVisible(false);
		}
		else{
			cmbInventory.setVisible(false);
			txtInventoryName.setVisible(true);
		}
		//txtInventoryName.setText("");
		//txtPrice.setText("");
	}


	protected void enableFields(boolean enable){
		txtName.editableProperty().set(enable);
		txtDescription.editableProperty().set(enable);
		cmbProduct.editableProperty().set(enable);
		txtProductName.editableProperty().set(enable);
		txtProductName.editableProperty().set(enable);
		cmbCategory.editableProperty().set(enable);
		txtCategoryName.editableProperty().set(enable);
		txtCategoryName.editableProperty().set(enable);
		cmbStatus.editableProperty().set(enable);
		txtStatusName.editableProperty().set(enable);
		txtStatusName.editableProperty().set(enable);
		txtQuantityAvailable.editableProperty().set(enable);
		cmbInventory.editableProperty().set(enable);
		txtInventoryName.editableProperty().set(enable);
		txtInventoryName.editableProperty().set(enable);
		txtPrice.editableProperty().set(enable);
	}


	public int getId(){
		return Integer.parseInt(txtId.getText());
	}


	protected void showErrorDialog(String message, String expandedMessage){
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setHeaderText(message);
		alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(expandedMessage)));
		alert.showAndWait();
	}


	public void onBack(ActionEvent actionEvent) {
		Node node = ((Node) (actionEvent.getSource()));
		Window window = node.getScene().getWindow();
		window.hide();
		stage.setScene(manageScene);
		stage.show();
	}


	public void onClose(ActionEvent actionEvent) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Exit and loose changes? " , ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			Platform.exit();
		}
	}


	LocalDate toLocalDate(Date date){
		Instant instant = date.toInstant();
		ZoneId z = ZoneId.of("Singapore");
		ZonedDateTime zdt = instant.atZone( z );
		return zdt.toLocalDate();
	}


	protected Date toDate(LocalDate ld){
		ZoneId z = ZoneId.of("Singapore");
		ZonedDateTime zdt = ld.atStartOfDay(z);
		Instant instant  = zdt.toInstant();
		return Date.from(instant);
	}


}

