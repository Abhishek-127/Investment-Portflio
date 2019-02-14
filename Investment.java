package Abhishek;

public abstract class Investment {

    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;
    private String type;

/*
    public Investment(){
        this.symbol  = "";
        this.name = "";
        this.quantity = 0;
        this.price = 0.0;
        this.bookValue = 0.0;
        this.type = "";
    }
    */




    @Override
    public String toString() {
        return getType() + "\n" +
                getSymbol() + "\n" +
                getName() + "\n" +
                getQuantity() + "\n"+
                getPrice() + "\n" +
                getBookValue();

    }

    /**
     * Getter for symbol
     * @return symbol
     */
    public String getSymbol() {

        return symbol;
        //return new String(this.getSymbol());
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }



    public String getName() {

        return name;
       // return new String(this.getName());
    }

    public void setName(String name) {

        this.name = name;
    }

    public double getPrice() {

       return price;
        //return new Double(this.getPrice());
    }

    public void setPrice(double price) {

        this.price = price;
    }

    public double getBookValue() {

        return bookValue;
        //return new Double(this.getBookValue());
    }

    public int getQuantity() {

       return quantity;
       // return new Integer(this.getQuantity());
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public void setBookValue(double bookValue) {

        this.bookValue = bookValue;
    }

    public abstract String getType();

    public Investment(String Symbol, String Name, int Quantity, double Price, double BookValue ) throws customException{

        if(Symbol.equalsIgnoreCase("")){
            throw new customException("Please enter a symbol!\n");
        }
        if(Name.length() == 0){
            throw new customException("Please enter a name!\n");
        }

        if(Quantity == 0){
            throw new customException("Quantity cannot be zero\n" + "Please enter a positive number\n");
        }
        if(Quantity < 0){
            throw new customException("Quantity cannot be negative\n" + "Please enter a positive number\n");
        }

        if(Price == 0){
            throw new customException("Price cannot be zero\n" +"Please enter a positive price\n");
        }
        if(Price < 0){
            throw new customException("Price cannot be negative\n"+ "Please enter a positive price\n");

        }


        this.symbol = Symbol;
        this.name = Name;
        this.quantity = Quantity;
        this.price = Price;
        this.bookValue = BookValue;

    }



    public class customException extends Exception{
        public customException(String text){
            super(text);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Investment that = (Investment) o;

        if (quantity != that.quantity) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (Double.compare(that.bookValue, bookValue) != 0) return false;
        if (symbol != null ? !symbol.equals(that.symbol) : that.symbol != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = symbol != null ? symbol.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + quantity;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(bookValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
