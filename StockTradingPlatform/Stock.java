public class Stock {

    private int id;
    private String stockName;
    private double price;
    private int quantity;
    private double total;

    // Default Constructor
    public Stock() {

    }

    // Parameterized Constructor
    public Stock(String stockName, double price, int quantity, double total) {
        this.stockName = stockName;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getStockName() {
        return stockName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return total;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}