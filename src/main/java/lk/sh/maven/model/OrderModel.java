package lk.sh.maven.model;

import lk.sh.maven.db.DBConnection;
import lk.sh.maven.dto.OrderDetailDto;
import lk.sh.maven.dto.OrderDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderModel {

    public static boolean placeOrder(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDBConnection().getConnection();

        // Disable auto-commit feature
        connection.setAutoCommit(false);

        // Insert data into orders table
        PreparedStatement stm1 = connection.prepareStatement(
                "INSERT INTO orders(order_date, amount) VALUES (?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        );
        stm1.setObject(1, orderDto.getOrderDate());
        stm1.setObject(2, orderDto.getSubTotal());

        int ordersave = stm1.executeUpdate();

        if (ordersave > 0) {
            // Get the generated order ID
            ResultSet generatedKeys = stm1.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);

                // Insert into order_details
                for (OrderDetailDto dto : orderDto.getOrderDetails()) {
                    PreparedStatement stm2 = connection.prepareStatement(
                            "INSERT INTO order_details(oid, vid, qty, price) VALUES (?, ?, ?, ?)"
                    );
                    stm2.setObject(1, id);
                    stm2.setObject(2, dto.getVehicleId());
                    stm2.setObject(3, dto.getQty());
                    stm2.setObject(4, dto.getPrice());

                    int orderdetailsave = stm2.executeUpdate();

                    if (orderdetailsave > 0) {
                        // Update vehicle quantity
                        PreparedStatement stm3 = connection.prepareStatement(
                                "UPDATE vehicle SET qty = qty - ? WHERE id = ?"
                        );
                        stm3.setObject(1, dto.getQty());
                        stm3.setObject(2, dto.getVehicleId());

                        int vehicleQtyUpdate = stm3.executeUpdate();

                        if (vehicleQtyUpdate <= 0) {
                            connection.rollback();
                            connection.setAutoCommit(true);
                            return false;
                        }
                    } else {
                        connection.rollback();
                        connection.setAutoCommit(true);
                        return false;
                    }
                }
            }
            // Commit transaction
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } else {
            // Rollback transaction
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }

}
