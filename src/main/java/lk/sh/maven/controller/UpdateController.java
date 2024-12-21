package lk.sh.maven.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.sh.maven.dto.VehicleDto;
import lk.sh.maven.model.VehicleModel;
import javafx.scene.control.Button;

import java.sql.*;

public class UpdateController {

    @FXML
    private Button loadAll;
    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    void Update(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText()); // Convert ID to an integer.
        String model = txtModel.getText(); // Get the model as a string.
        double price = Double.parseDouble(txtPrice.getText()); // Convert price to double.
        int qty = Integer.parseInt(txtQty.getText()); // Convert quantity to an integer.
        String brand = txtBrand.getText(); // Get the brand as a string.

        String upd = VehicleModel.updateVehicle(new VehicleDto(id,brand,model,qty,price));


}

    @FXML
    void delete(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText()); // Get user input and convert it to an integer

        boolean  status = VehicleModel.deleteVehicle(id);

        if (status) {
            System.out.println("delete success");
        }
        else {
            System.out.println("delete failed");
        }

    }
    @FXML
    void save(ActionEvent event) {

        // Retrieve and parse input values from the text fields.
        int id = Integer.parseInt(txtId.getText()); // Convert ID to an integer.
        String model = txtModel.getText(); // Get the model as a string.
        double price = Double.parseDouble(txtPrice.getText()); // Convert price to double.
        int qty = Integer.parseInt(txtQty.getText()); // Convert quantity to an integer.
        String brand = txtBrand.getText(); // Get the brand as a string.

        // (id,brand,model,qty,price) filled data object
        boolean b = VehicleModel.saveVehicle(new VehicleDto(id,brand,model,qty,price));

        //alert
    }

    @FXML
    void cancel(ActionEvent event) {
        System.exit(0); // Terminates the program when cancel is clicked.
    }
    @FXML


    void loadall(ActionEvent event) {
        try {
            // Navigate to Save Book Data Form
            Stage stage = (Stage) loadAll.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("save-book-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @FXML
    void search(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());  //user input eka ganna e integer ek string ekkkt convert karagnnawa

        VehicleDto vehicleDto = VehicleModel.searchVehicle(id);

        if(vehicleDto!=null){
            txtBrand.setText(vehicleDto.getBrand());
            txtModel.setText(vehicleDto.getModel());
            txtQty.setText(String.valueOf(vehicleDto.getQty()));
            txtPrice.setText(String.valueOf(vehicleDto.getPrice()));
        }



    }

}
