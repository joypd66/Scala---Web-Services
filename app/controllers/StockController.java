package controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.Stock;

public class StockController {
    public Stock getStock(String symbol) {
        Stock stock = new Stock();
        try
        {
            URL yahoofin = new URL("http://finance.yahoo.com/d/quotes.csv?s=" + symbol + "&f=sl1d1t1c1ohgv&e=.csv");
            URLConnection yc = yahoofin.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;

            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH-mm-ss");
            String dateStamp = sdf.format(date);

            while ((inputLine = in.readLine()) != null) {
                String[] stockInfoArray = inputLine.split(",");
                stock.setTicker(stockInfoArray[0].replaceAll("\"", ""));
                stock.setPrice(Float.valueOf(stockInfoArray[1]));
                stock.setChange(Float.valueOf(stockInfoArray[4]));
                stock.setChartUrlSmall("http://ichart.finance.yahoo.com/t?s=" + stock.getTicker());
                stock.setChartUrlLarge("http://chart.finance.yahoo.com/w?s=" + stock.getTicker());
                stock.setDateStamp(dateStamp);
                break;
            }//end while loop
            in.close();
        }//end try block

        catch (
                Exception ex
                )

        {
            //send a message that there was an error.
            //log.error("Unable to get stockinfo for: " + symbol + ex);
            ex.printStackTrace();
        }//end catch block

        return stock;
    }
}