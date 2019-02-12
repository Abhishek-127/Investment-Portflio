package Abhishek;

public class Stock extends Investment {

    public Stock(String Symbol, String Name,int Quantity, double Price, double BookValue) throws customException{
        super(Symbol, Name, Quantity, Price, BookValue);
    }

    @Override
    public String getType() {
        return "STOCK";
    }
}
