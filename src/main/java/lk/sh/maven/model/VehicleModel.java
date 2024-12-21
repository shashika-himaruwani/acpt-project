package lk.sh.maven.model;

import javafx.collections.FXCollections;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.sh.maven.db.DBConnection;
import lk.sh.maven.dto.VehicleDto;
import lk.sh.maven.tm.VehicleTM;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleModel {

//    DBConnection dbConnection = DBConnection.getDBConnection();
//    DBConnection dbConnection1 = DBConnection.getDBConnection();

    public static boolean saveVehicle(VehicleDto vehicleDto) {
        try {

            //object ekk return wenne
            Connection connection = DBConnection.getDBConnection().getConnection();

            // Prepare an SQL INSERT query to add data to the "vehicle" table.
            PreparedStatement preparedStatement = connection.prepareStatement("insert into vehicle values (?,?,?,?,?)");
            preparedStatement.setObject(1, vehicleDto.getId()); // Set the first placeholder with vehicle ID.
            preparedStatement.setObject(2, vehicleDto.getBrand()); // Set the second placeholder with brand.
            preparedStatement.setObject(3, vehicleDto.getModel()); // Set the third placeholder with model.
            preparedStatement.setObject(4, vehicleDto.getQty()); // Set the fourth placeholder with quantity.
            preparedStatement.setObject(5, vehicleDto.getPrice()); // Set the fifth placeholder with price.

            // Execute the query and get the number of affected rows.
            int i = preparedStatement.executeUpdate();

            // Check if the insertion was successful.
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException();
        }

    }

    public static boolean deleteVehicle(int id) {

        VehicleDto vehicleDto = null;
        try {
            boolean status = false;
            //object ekk return wenne
            Connection connection = DBConnection.getDBConnection().getConnection();

            // Write SQL query for deleting a record
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM vehicle WHERE id=?");

            preparedStatement.setObject(1, id);

            int i = preparedStatement.executeUpdate();

            if (i > 0) {
                status=true;
            }
            return status;


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static String updateVehicle(VehicleDto vehicleDto) {
        try {
            //object ekk return wenne
            Connection connection = DBConnection.getDBConnection().getConnection();

            // Write sql query to update
            PreparedStatement preparedStatement = connection.prepareStatement("update vehicle set brand=? , model=? ,  qty=? , price=?  where id=?");

            preparedStatement.setObject(1, vehicleDto.getBrand()); // Set the second placeholder with brand.
            preparedStatement.setObject(2, vehicleDto.getModel()); // Set the third placeholder with model.
            preparedStatement.setObject(3, vehicleDto.getQty()); // Set the fourth placeholder with quantity.
            preparedStatement.setObject(4, vehicleDto.getPrice()); // Set the fifth placeholder with price.

            preparedStatement.setObject(5, vehicleDto.getId()); // Set the first placeholder with vehicle ID.

            // Execute the query and get the number of affected rows.
            int i = preparedStatement.executeUpdate();

            // Check if the insertion was successful.
            if (i > 0) {
                return "Data Updated";

            }
            else {
                return "Data Not Updated";
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    //collection framework use because out many data
    public static ArrayList<VehicleTM> getAllVehicle() {


        try {
            //object ekk return wenne
            Connection connection = DBConnection.getDBConnection().getConnection();
            //write sql query...
            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicle ");


            ResultSet resultSet = preparedStatement.executeQuery(); // data get -----------> executeQuery

            //Arraylist ----> collection framework
            //VehicleTM ekta table data objects wdyt pass wenw -- Arraylist ekt dgttha data
            ArrayList<VehicleTM> tms = new ArrayList<>();

            while (resultSet.next()) {
                //table row eke data arraylist ekt pass krpu ek
                //Constructor eke parameters wlta argument pass krla tynne .
                tms.add(new VehicleTM(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4), resultSet.getDouble(5)));

            }

            return tms;

            //set data to fxml data
            //configure fx data ---> search javafx table configuration for cell values
//            tblVehicle.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
//            tblVehicle.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("brand"));
//            tblVehicle.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("model"));
//            tblVehicle.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qty"));
//            tblVehicle.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));
//
//
//            tblVehicle.setItems(FXCollections.observableList(tms));

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public static VehicleDto searchVehicle(int id) {
        try {
            VehicleDto vehicleDto = null;
            //object ekk return wenne
            Connection connection = DBConnection.getDBConnection().getConnection();

            //write sql query...
            PreparedStatement preparedStatement = connection.prepareStatement("select * from vehicle where id=?");
            preparedStatement.setObject(1, id); //

            ResultSet resultSet = preparedStatement.executeQuery(); // dataget karanawa


            while (resultSet.next()){
                vehicleDto = new VehicleDto(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4),
                        resultSet.getDouble(5)
                );
            }
            return vehicleDto;

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }



}}
