package lk.sh.maven.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.sh.maven.dto.OrderDetailDto;
import lk.sh.maven.dto.OrderDto;
import lk.sh.maven.dto.VehicleDto;
import lk.sh.maven.model.OrderModel;
import lk.sh.maven.model.VehicleModel;
import lk.sh.maven.tm.ItemTM;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class OrderController implements Initializable {

    @FXML
    private TableView<ItemTM> tblOrder;

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
    private TextField txtQtyOnHand;

    @FXML
    private Label txtSubTotal;

    //default value null
    private List<ItemTM> itemTMS;

    private ArrayList<OrderDetailDto> orderDetailDtos;

    private double subtotal =0.0;

    @FXML
    void addToCart(ActionEvent event) {
        String brand = txtBrand.getText();
        String model = txtModel.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtPrice.getText());
        double total = qty * unitPrice;

        subtotal += total;
        txtSubTotal.setText(String.valueOf(subtotal));

        int id = Integer.parseInt(txtId.getText());

        orderDetailDtos.add(new OrderDetailDto(id ,qty , total)  );

        itemTMS.add(new ItemTM(brand,model,qty,unitPrice,total));
        tblOrder.setItems(FXCollections.observableArrayList(itemTMS));


    }

    @FXML
    void placeOrder(ActionEvent event)  {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd ");
        Date date = new Date();
        String format = dateFormat.format(date);

        try {
            boolean orderdPlaced = OrderModel.placeOrder(new OrderDto(format, subtotal, orderDetailDtos));
            if (orderdPlaced){
                System.out.println("Order Placed");
            }
            else {
                System.out.println("Order Not Placed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void search(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());  //user input eka ganna e integer ek string ekkkt convert karagnnawa

        VehicleDto vehicle = VehicleModel.searchVehicle(id); // Example ID
        if (vehicle != null) {
            txtBrand.setText(vehicle.getBrand());
            txtModel.setText(vehicle.getModel());
            txtQtyOnHand.setText(String.valueOf(vehicle.getQty()));
            txtPrice.setText(String.valueOf(vehicle.getPrice()));
        } else {
            System.out.println("Vehicle not found.");
        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrder.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));

        //super class reference variable ekt subtype object assign
        itemTMS= new ArrayList<>();

        orderDetailDtos = new ArrayList<>();
    }
}
