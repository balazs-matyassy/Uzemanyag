package hu.progmatic.model;

public class FuelPriceChange {
    public static final double EURHUF = 307.7;

    private String date;
    private int gasPrice;
    private int dieselPrice;
    private int year, month, day;

    public FuelPriceChange() {

    }

    public FuelPriceChange(String date, int gasPrice, int dieselPrice) {
        setDate(date);
        this.gasPrice = gasPrice;
        this.dieselPrice = dieselPrice;
    }

    public FuelPriceChange(String line) {
        String[] parts = line.split(";");
        setDate(parts[0]);
        this.gasPrice = Integer.parseInt(parts[1]);
        this.dieselPrice = Integer.parseInt(parts[2]);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;

        String[] parts = date.split("\\.");
        this.year = Integer.parseInt(parts[0]);
        this.month = Integer.parseInt(parts[1]);
        this.day = Integer.parseInt(parts[2]);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public boolean isLeapDay() {
        return year % 4 == 0
                && month == 2
                && day == 24;
    }

    public int getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(int gasPrice) {
        this.gasPrice = gasPrice;
    }

    public double getGasPriceEUR() {
        return gasPrice / EURHUF;
    }

    public int getDieselPrice() {
        return dieselPrice;
    }

    public void setDieselPrice(int dieselPrice) {
        this.dieselPrice = dieselPrice;
    }

    public double getDieselPriceEUR() {
        return dieselPrice / EURHUF;
    }

    public int getPriceDifference() {
        // return Math.abs(gasPrice - dieselPrice);
        return gasPrice >= dieselPrice
                ? gasPrice - dieselPrice
                : dieselPrice - gasPrice;
    }

    public static int getDaysBetween(FuelPriceChange change1, FuelPriceChange change2) {
        int[] monthLengths = {31,28,31,30,31,30,31,31,30,31,30,31};

        if (change1.year % 4 == 0) {
            monthLengths[1] = 29;
        }

        // max 1 hónap különbség lehet
        if (change1.month == change2.month) {
            return change2.day - change1.day;
        } else {
            return monthLengths[change1.month - 1] - change1.day + change2.day;
        }
    }
}
