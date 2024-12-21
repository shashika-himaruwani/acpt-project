package lk.sh.maven.controller; // Package declaration for organizing the class.

// Required JavaFX imports for handling UI components and events.
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lk.sh.maven.dto.VehicleDto;
import lk.sh.maven.model.VehicleModel;

// Required imports for handling database connectivity.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static java.lang.Class.forName; // Static import for Class.forName().

public class SaveController {

    // FXML annotations to bind JavaFX UI components to this controller.
    @FXML
    private TextField txtBrand; // TextField for vehicle brand.

    @FXML
    private TextField txtId; // TextField for vehicle ID.

    @FXML
    private TextField txtModel; // TextField for vehicle model.

    @FXML
    private TextField txtPrice; // TextField for vehicle price.

    @FXML
    private TextField txtQty; // TextField for vehicle quantity.

    // Event handler for the "Cancel" button.
    @FXML
    void cancel(ActionEvent event) {
        System.exit(0); // Terminates the program when cancel is clicked.
    }

    // Event handler for the "Save" button.
    @FXML
    void save(ActionEvent event) throws ClassNotFoundException {
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
}
