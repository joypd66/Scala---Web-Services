package models;

public class Stock{
    String ticker;
    float price;
    float change;
    String chartUrlSmall;
    String chartUrlLarge;
    String dateStamp;

    public Stock(){};

    public String getTicker() {
        return ticker;
    }//end getTicker method

    public void setTicker(String ticker) {
        this.ticker = ticker.toUpperCase();
    }//end setTicker method

    public float getPrice() {
        return price;
    }//end getPrice method

    public void setPrice(float price) {
        this.price = price;
    }//end setPrice method

    public float getChange() {
        return change;
    }//end getChange method

    public void setChange(float change) {
        this.change = change;
    }//end setChange method

    public String getChartUrlSmall() {
        return chartUrlSmall;
    }//end chartUrlSmall method

    public void setChartUrlSmall(String chartUrlSmall) {
        this.chartUrlSmall = chartUrlSmall;
    }//end setChartUrlSmall method

    public String getChartUrlLarge() {
        return chartUrlLarge;
    }//end getChartUrlLarge method

    public void setChartUrlLarge(String chartUrlLarge) {
        this.chartUrlLarge = chartUrlLarge;
    }//end setChartUrlLarge method

    public String getDateStamp() {
        return dateStamp;
    }//end getLastUpdated method

    public void setDateStamp(String myDateString) {
        this.dateStamp = myDateString;
    }//end setLastUpdated method
}//end Stock class