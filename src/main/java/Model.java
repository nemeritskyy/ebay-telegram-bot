public class Model {
    private long itemId;
    private String title;
    private String galleryURL;
    private String viewItemURL;
    private double currentPrice;
    private String listingType;
    private String startTime;
    private String conditionDisplayName;
    private double shippingServiceCost;
    private String insertTime;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGalleryURL() {
        return galleryURL;
    }

    public void setGalleryURL(String galleryURL) {
        this.galleryURL = galleryURL;
    }

    public String getViewItemURL() {
        return viewItemURL;
    }

    public void setViewItemURL(String viewItemURL) {
        this.viewItemURL = viewItemURL;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getListingType() {
        return listingType;
    }

    public void setListingType(String listingType) {
        this.listingType = listingType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getConditionDisplayName() {
        return conditionDisplayName;
    }

    public void setConditionDisplayName(String conditionDisplayName) {
        this.conditionDisplayName = conditionDisplayName;
    }

    public double getShippingServiceCost() {
        return shippingServiceCost;
    }

    public void setShippingServiceCost(double shippingServiceCost) {
        this.shippingServiceCost = shippingServiceCost;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    @Override
    public String toString() {
        return "Model{" +
                "itemId=" + itemId +
                ", title='" + title + '\'' +
                ", galleryURL='" + galleryURL + '\'' +
                ", viewItemURL='" + viewItemURL + '\'' +
                ", currentPrice=" + currentPrice +
                ", listingType='" + listingType + '\'' +
                ", startTime='" + startTime + '\'' +
                ", conditionDisplayName='" + conditionDisplayName + '\'' +
                ", shippingServiceCost=" + shippingServiceCost +
                ", insertTime='" + insertTime + '\'' +
                '}';
    }
}
