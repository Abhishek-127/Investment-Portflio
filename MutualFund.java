package Abhishek;

public class MutualFund extends Investment {

    public MutualFund(String Symbol, String Name,int Quantity, double Price, double BookValue ) throws customException{
        super(Symbol, Name, Quantity, Price, BookValue);
    }

    @Override
    public String getType() {
        return "MUTUAL FUND";
    }

    @Override
    public void setName(String name) {
        super.setName("MUTUAL_FUND_" + name);
    }

    @Override
    public boolean equals(Object o) {
        //return super.equals(o);
        if(o.getClass().equals(getClass()))
            return true;
        else
            return false;
    }


    public void setQuantity(int ... quantities){
        int total = 0;
        for(int q : quantities){
            total += q;
        }
        setQuantity(total);
    }
}
//joptionpane.showmessagedialog