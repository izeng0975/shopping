public class Coupon {
    private Item item;
    private int percentOff;
    
    public Coupon(Item item, int percentOff) {
        this.item = item;
        this.percentOff = percentOff;
    }

    public String toString() {
        return "This coupon gets you " + percentOff + "% off of " + item.getName();
    }

    /**
     * Gets the new item after coupon
     * 
     * @return
     * Returns new item with discount
     * 
     * Precondition: item is not null
     * Precondition: 0 < percentOff < 100
     * 
     * Postcondition:
     * The new item has the coupon applied to its price
     * 
     */
    public Item getNewItem() {
        return new Item(
            item.getName(),
            item.getPrice()*(1-(percentOff/100.)),
            item.getStock(),
            item.getOnSale(),
            item.getFoodValue(),
            item.getWaterValue(),
            item.getTasteValue(),
            item.getGreenhouseGasValue(),
            item.getPlasticValue(),
            item.getBiodegradability()
        );
    }
}
