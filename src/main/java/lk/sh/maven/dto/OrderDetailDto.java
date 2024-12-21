package lk.sh.maven.dto;

public class OrderDetailDto {
    private int vehicleId;
    private int qty;
    private double price;

    public OrderDetailDto(int vehicleId, int qty, double price) {
        this.vehicleId = vehicleId;
        this.qty = qty;
        this.price = price;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
