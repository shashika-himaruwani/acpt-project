package lk.sh.maven.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.sh.maven.model.VehicleModel;
import lk.sh.maven.tm.VehicleTM;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

// application ek open weddima data display krnn ---> Intializable interface
public class LoadController implements Initializable {

    @FXML
    private TableView<VehicleTM> tblVehicle;

    @FXML
    void load(ActionEvent event) {


//        try {
//            //load driver class to ram   ,,,,,,,,,,,allowing the Java program to communicate with the database.
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            //Creates a connection to the database
//            // Establish a connection to the MySQL database.
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clz", "root", "2001");
//
//            //write sql query...
//            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicle ");
//
//
//            ResultSet resultSet = preparedStatement.executeQuery(); // data get -----------> executeQuery
//
//            //Arraylist ----> collection framework
//            //VehicleTM ekta table data objects wdyt pass wenw -- Arraylist ekt dgttha data
//            ArrayList<VehicleTM> tms = new ArrayList<>();
//
//            while (resultSet.next()) {
//                //table row eke data arraylist ekt pass krpu ek
//                //Constructor eke parameters wlta argument pass krla tynne .
//                tms.add(new VehicleTM(resultSet.getInt(1) , resultSet.getString(2) , resultSet.getString(3),
//                        resultSet.getInt(4) , resultSet.getDouble(5)));
//
//            }
//
//            //set data to fxml data
//            //configure fx data ---> search javafx table configuration for cell values
//            tblVehicle.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
//            tblVehicle.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
//            tblVehicle.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
//            tblVehicle.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
//            tblVehicle.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));
//
//
//            tblVehicle.setItems(FXCollections.observableList(tms));
//
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

    }

    @Override
    //Page ek load wedima run wenwa
    //initializable interface eken implement wena override method ek initialize
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<VehicleTM> tms = VehicleModel.getAllVehicle();

        tblVehicle.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblVehicle.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
        tblVehicle.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
        tblVehicle.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblVehicle.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));

        tblVehicle.setItems(FXCollections.observableArrayList(tms));
    }
}

