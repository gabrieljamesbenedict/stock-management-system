package com.gabriel.ecomms.controller;
import com.gabriel.ecomms.model.Ecommerce;
import com.gabriel.ecomms.service.EcommerceService;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Window;
import javafx.scene.image.ImageView;

public class DeleteEcommerceController extends GenericEcommerceController {
	public ImageView imgEcommerce;
	@Override
	public void init() {
		setFields("Delete");
		enableFields(false);
	}
	public void onSubmit(ActionEvent actionEvent) {
		try {
			Ecommerce ecommerce = toObject(true);
			EcommerceService.getService().delete(ecommerce.getId());
			manageEcommerceController.refresh();
			Node node = ((Node) (actionEvent.getSource()));
			Window window = node.getScene().getWindow();
			window.hide();
			stage.setTitle("Stock Management");
			stage.setScene(manageScene);
			stage.show();
		}
		catch (Exception e){
			showErrorDialog("Error encountered creating ecommerce", e.getMessage());
		}
	}
	public void onClose(ActionEvent actionEvent) {
		super.onClose(actionEvent);
	}
}
