public class Item {
    private String name;
    private double price;
    private int stock;
    private boolean onSale;

    private int foodValue;
    private int waterValue;
    private int tasteValue;

    private int greenhouseGasValue;
    private int plasticValue;
    private int biodegradability;

    /**
     * Constructor for Item
     * 
     * @param name
     * Item name
     * 
     * @param price
     * Price of item
     * 
     * @param stock
     * Stock left of the item
     * 
     * @param onSale
     * Signals if item is on sale
     */
    public Item(String name, double price, int stock, boolean onSale, int foodValue, int waterValue, int tasteValue, int greenhouseGasValue, int plasticValue, int biodegradability) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.onSale = onSale;
        this.foodValue = foodValue;
        this.waterValue = waterValue;
        this.tasteValue = tasteValue;
        this.greenhouseGasValue = greenhouseGasValue;
        this.plasticValue = plasticValue;
        this.biodegradability = biodegradability;
    }

    /**
     * Getter for name
     * 
     * @return
     * item name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for price
     * 
     * @return
     * item price
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Getter for stock
     * 
     * @return
     * item stock
     */
    public int getStock() {
        return this.stock;
    }

    /**
     * Getter for onSale
     * 
     * @return
     * item onSale or not
     */
    public boolean getOnSale() {
        return this.onSale;
    }

    /**
     * Getter for foodValue
     * 
     * @return
     * foodValue
     */
    public int getFoodValue() {
        return this.foodValue;
    }

    /**
     * Getter for waterValue
     * 
     * @return
     * waterValue
     */
    public int getWaterValue() {
        return this.waterValue;
    }

    /**
     * Getter for tasteValue
     * 
     * @return
     * tasteValue
     */
    public int getTasteValue() {
        return this.tasteValue;
    }

    /**
     * Getter for greenhouseGasValue
     * 
     * @return
     * greenhouseGasValue
     */
    public int getGreenhouseGasValue() {
        return this.greenhouseGasValue;
    }

    /**
     * Getter for plasticValue
     * 
     * @return
     * plasticValue
     */
    public int getPlasticValue() {
        return this.plasticValue;
    }

    /**
     * Getter for biodegradability
     * 
     * @return
     * biodegradability
     */
    public int getBiodegradability() {
        return this.biodegradability;
    }

    /**
     * Setter for price
     * 
     * @param newPrice
     * The new price
     */
    public void setPrice(double newPrice) {
        this.price = newPrice;
    }

    /**
     * Setter for stock
     * 
     * @param newStock
     * The new stock
     */
    public void setStock(int newStock) {
        this.stock = newStock;
    }

    /**
     * Setter for onSale
     * 
     * @param newOnSale
     * The new onSale
     */
    public void setOnSale(boolean newOnSale) {
        this.onSale = newOnSale;
    }

    /**
     * toString method for Item
     * 
     * @return
     * The string representation of the item
     */
    public String toString() {
        return String.format("%-12s%-12s%12s", name, "$"+String.format("%.2f", price), stock);
    }

    private void putOnSale() {
        this.onSale = true;
    }

    public void promotionDeal() {
        if (Math.random() < .5) {
            this.putOnSale();
            this.price = this.price/1.2;
        }
    }
}