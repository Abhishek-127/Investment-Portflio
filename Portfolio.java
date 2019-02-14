package Abhishek;

import javax.swing.*;
import java.io.*;
import java.util.*;

import static java.lang.System.exit;


public class Portfolio {
    private double gain =  0.0;
    private double stockCommission  = 9.99;
    private double MutualFundCommission = 45.00;


    /**
     *
     * @param myArray, a copy of the array List of investments
     * @param quantity_str, a string representing the quantity
     * @param price_str, a string representing the price
     * @param position, the position in the array List where to add quantity
     * @param info, textArea to display warnings and messages to the user
     */
    public void addValueToInvestment(ArrayList<Investment> myArray, String quantity_str, String price_str,
                                     int position, JTextArea info, String type, JTextField priceField, JTextField qtyField){
         int quantity;
         double price;
         int priceFlag = 0;
         int qtyFlag = 0;
         double newBookValue;
        try{
            quantity = Integer.parseInt(quantity_str);
        }
        catch (NumberFormatException e){
            info.append("Invalid Quantity Input\n");
            info.append("Please enter a valid positive quantity\n\n");
            quantity = -1;
            qtyFlag++;
            qtyField.setText("");
        }

        try{
            price = Double.parseDouble(price_str);
        }
        catch (NumberFormatException e){
            info.append("Invalid Price Input\n");
            info.append("Please enter a valid price\n\n");
            price = -1;
            priceFlag++;
            priceField.setText("");
        }

        if(price > 0 && quantity > 0){
            int newQty;
            newQty = myArray.get(position).getQuantity() + quantity;

            if(myArray.get(position) instanceof Stock){
                newBookValue = myArray.get(position).getBookValue() + (quantity * price) + stockCommission;
            }
            else{
                newBookValue = myArray.get(position).getBookValue() + (quantity * price);
            }

            if(myArray.get(position).getType().equalsIgnoreCase(type)){
                myArray.get(position).setQuantity(newQty);
                myArray.get(position).setPrice(price);
                myArray.get(position).setBookValue(newBookValue);

                info.append("Successfully purchased " + quantity+ "Additional " +myArray.get(position).getSymbol()
                + " "+ myArray.get(position).getType() + "for $"+price +"each" +"\n\n" );
            }
            else{

                info.append("Symbol already exists!\n");
                info.append("please Re-Enter\n\n");
            }
        }
        else{
            if(quantity == 0 && qtyFlag == 0){
                info.append("Quantity cannot be 0\n");
                info.append("Please enter a positive quantity\n\n");
                qtyField.setText("");
            }
            if(quantity < 0){
                info.append("Quantity cannot be negative\n");
                info.append("Please enter a positive quantity\n\n");
                qtyField.setText("");
            }
            if(price == 0 && priceFlag == 0){
                info.append("Price cannot be 0\n");
                info.append("Please enter a positive price\n\n");
                priceField.setText("");
            }
            if(price  < 0 && priceFlag  == 0){
                info.append("Price cannot be negative\n");
                info.append("Please enter a positive price\n\n");
                priceField.setText("");
            }
        }



    }

    /**
     *
     * @param myArray, which is a copy if the original investment array, to prevent privacy leaks
     * @param type, stock or mutual fund
     * @param symbol, the symbol of the investment
     * @param name, the name of the investment
     * @param quantity_str, string representing the desired quantity
     * @param price_str, string representing the price
     * @param info, the information text area, where warnings and messages are displayed to the user
     */
    public void buyNewInvestment(ArrayList<Investment> myArray ,String type, String symbol, String name,
                                 String quantity_str, String price_str, JTextArea info, JTextField priceField, JTextField qtyField){


        double bookValue;
        int quantity;
        double price;

        int check;

        int priceflag = 0;
        int qtyflag = 0;

        symbol = symbol.trim().replaceAll(" +", " ");
        name = name.trim().replaceAll(" +", " ");
        quantity_str = quantity_str.trim().replaceAll(" +", " ");
        price_str = price_str.trim().replaceAll(" +", " ");

        if(symbol.equals("")){
            info.append("Please enter a symbol\n");
        }
        if(name.equals("")){
            info.append("Please enter a name\n");
        }
        if(quantity_str.equals("")){
            info.append("Please enter a quantity\n");
        }
        if(price_str.equals("")){
            info.append("Please enter a quantity\n");
        }

        else {

            check = checkInvestmentSymbol(myArray, symbol);

            if(check >= 0){
                addValueToInvestment(myArray, quantity_str, price_str, check, info, type, priceField, qtyField);
            }

            else {

                try {
                    quantity = Integer.parseInt(quantity_str);
                } catch (NumberFormatException e) {
                    info.append("Invalid Quantity Input!\n");
                    info.append("Please Re-Enter Quantity\n\n");
                    quantity = -1;
                    qtyField.setText("");
                    qtyflag++;
                }
                try {
                    price = Double.parseDouble(price_str);

                } catch (NumberFormatException e) {
                    info.append("Invalid Price Input\n");
                    info.append("Please Re-Enter the price\n\n");
                    price = -1;
                    priceField.setText("");
                    priceflag++;

                }
                if (price > 0 && quantity > 0) {
                    if (type.equalsIgnoreCase("Stock")) {
                        bookValue = quantity * price;
                        bookValue += stockCommission;
                        try {
                            Stock s = new Stock(symbol, name, quantity, price, bookValue);
                            myArray.add(s);
                        } catch (Investment.customException e) {
                            info.append(e.getMessage());
                        }

                        info.append("Book Updated Successfully\n");
                    } else {
                        bookValue = quantity * price;
                        try {
                            MutualFund mf = new MutualFund(symbol, name, quantity, price, bookValue);
                            myArray.add(mf);
                        } catch (Investment.customException e) {
                            info.append(e.getMessage());
                        }
                        info.append("Book Updated Successfully\n\n");
                    }

                }
                else{

                    if(quantity == 0 && qtyflag == 0){
                        info.append("Quantity cannot be 0\n");
                        info.append("Please enter a positive quantity\n\n");
                        qtyField.setText("");
                    }
                    if(quantity < 0 && qtyflag == 0){
                        info.append("Quantity cannot be Negative\n");
                        info.append("Please enter a positive quantity\n\n");
                        qtyField.setText("");
                    }
                    if(price == 0 && priceflag != 1){
                        info.append("Price Cannot be 0\n");
                        info.append("Please Enter a positive price\n\n");
                        priceField.setText("");
                    }
                    if(price < 0 && priceflag != 1){
                        info.append("Price Cannot be negative\n");
                        info.append("Please enter a positive price\n\n");
                        priceField.setText("");
                    }
                }

            }


        }


    }


    /**
     *
     * @param myArray, copy of the arrayList of investments
     * @param symbol, the symbol to look for in the array
     * @return a number
     *          -1 for a no match
     *          a number >= 0 for a match found (The position in the array)
     *          -2 for an empty arrayList
     */

    public int checkInvestmentSymbol(ArrayList<Investment>myArray,String symbol){
        if(myArray.size() == 0){
            return -2;
        }
        for(int i = 0; i < myArray.size(); i++){
            if(myArray.get(i).getSymbol().equalsIgnoreCase(symbol)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Function that sells investment
     * It updates quantity and book value
     * deletes an investment is quantity becomes 0 after the sale
     * @param myArray, a copy of the arrayList of investment
     * @param symbol, symbol if the investment to sell
     * @param quantity_str, string representing the quantity to sell
     * @param price_str, string representing the selling price
     * @param info, JTextArea where messages and warnings are displayed to the user
     * @param qtyField, JTextField which is set to blank whenever a mistake is made
     * @param priceField, JTextField which is set to blank whenever a mistake is made
     * @param symbolInput, JTextField which is set to blank whenever a mistake is made
     */
    public void sellInvestment(ArrayList<Investment>myArray, String symbol, String quantity_str, String price_str, JTextArea info,
                               JTextField qtyField, JTextField priceField, JTextField symbolInput){

        int quantity;
        double price;

        int priceFlag = 0;
        int qtyFlag = 0;
        double newBookValue;

        symbol = symbol.trim().replaceAll(" +", " ");
        quantity_str = quantity_str.trim().replaceAll(" +", " ");
        price_str = price_str.trim().replaceAll(" +", " ");

        int check;

           check = checkInvestmentSymbol(myArray, symbol);

       if(check == -2){
           info.append("You Don't Currently Own any Investments\n\n");
           return;

       }
       else if(check == -1){
           if(symbol.length() != 0) {
               info.append("The Symbol you entered doesn't match any investments you own\n");
               info.append("Please try again\n\n");
               symbolInput.setText("");
           }
       }

      // else {

           try {
               quantity = Integer.parseInt(quantity_str);
           } catch (NumberFormatException e) {
               quantity = -1;
               info.append("Invalid quantity Input\n");
               info.append("Please enter again\n\n");
               qtyFlag++;

               qtyField.setText("");
           }
           try {
               price = Double.parseDouble(price_str);
           } catch (NumberFormatException e) {
               info.append("Invalid Price Input\n");
               info.append("Please enter price again\n\n");
               price = -1;
               priceFlag++;
               priceField.setText("");
           }

           if (quantity == 0 && qtyFlag != 1) {
               info.append("Quantity cannot be zero\n");
               info.append("Please Re-Enter  quantity\n\n");
               qtyField.setText("");
           }
           if (quantity < 0 && qtyFlag != 1) {
               info.append("Quantity cannot be negative\n");
               info.append("Please Re-Enter quantity\n\n");
               qtyField.setText("");
           }
           if (price == 0 && priceFlag != 1) {
               info.append("Price cannot be 0\n");
               info.append("Please Re-Enter price\n\n");
               priceField.setText("");
           }
           if (price < 0 && priceFlag != 1) {
               info.append("Price cannot be Negative\n");
               info.append("Please Re-Enter Price\n\n");
               priceField.setText("");
           }

        if(check == -1) {
            return;
        }
            if (quantity > myArray.get(check).getQuantity()) {
                info.append("Desired quantity is not available\n");
                info.append("You Currently own " + myArray.get(check).getQuantity() + " " + myArray.get(check).getSymbol()
                        + " " + myArray.get(check).getType() + "\n");
            }



           else if (qtyFlag != 1 && priceFlag != 1 && quantity > 0 && price > 0) {
               int newQty;
               newQty = myArray.get(check).getQuantity() - quantity;
               myArray.get(check).setPrice(price);
                /**
                 * Add Approp commissions here//
                 */
               newBookValue = ((double) newQty / (double) myArray.get(check).getQuantity()) * myArray.get(check).getBookValue();
               myArray.get(check).setQuantity(newQty);
               myArray.get(check).setBookValue(newBookValue);

               if (newQty == 0) {
                   info.append("You Sold all of your " + myArray.get(check).getSymbol() + " " + myArray.get(check).getType() + "\n");
                   info.append("It is Now Removed from your Portfolio\n");
                   myArray.remove(check);
               } else {
                   info.append("Sale Successful!\n");
                   info.append("You now have " + newQty + " " + myArray.get(check).getSymbol() + " " + myArray.get(check).getType() + "\n");
               }
           }


    }

    /**
     *
     * @param myArray
     * @param price_str
     * @param counter
     * @param info
     * @param next
     * @param prev
     * @param priceInput
     */
    public void updateInvestment(ArrayList<Investment>myArray, String price_str, int counter, JTextArea info,
                                 JButton next, JButton prev, JTextField priceInput){


        double price;
        int priceFlag = 0;
        try{
            price = Double.parseDouble(price_str);
        }
        catch (NumberFormatException e){
            info.append("Invalid Price!\n");
            info.append("Please Re-Enter\n\n");
            price = -1;
            priceInput.setText("");
            priceFlag++;
        }

        if(price == 0 && priceFlag == 0){
            info.append("Price cannot be 0\n");
            info.append("Please Re-Enter Price\n\n");
            priceInput.setText("");
            return;

        }
        if(price < 0 && priceFlag == 0){
            info.append("Price cannot be Negative\n");
            info.append("Please Re-Enter Price\n\n");
            priceInput.setText("");
            return;
        }

        else if(price > 0 && priceFlag != 1){
            myArray.get(counter).setPrice(price);
            info.append(myArray.get(counter).getSymbol()+ " " + myArray.get(counter).getType()+" Price Updated Successfully\n");
            info.append("New Price is $" +price);
        }





    }

    /**
     * Function that calculates the total gain of the portfolio and individual gains
     * @param myArray, a copy of the arrayList of investments
     * @param info, JTextArea to display the gains
     * @param gainField, TextField to display the total gain
     */
    public void gainOfInvestment(ArrayList<Investment>myArray, JTextArea info, JTextField gainField){

        //System.out.println("Your total gain is $" +gain);
        double finalGain = 0;
        double indiGain = 0.0;
        if(myArray.size() == 0){
            info.append("You Don't own any Investments\n");
            return;
        }
        else{
            for(int i = 0; i < myArray.size(); i++){
                //// (quantity * price) -  BookValue - commission;
                if(myArray.get(i) instanceof Stock) {
                    indiGain =  (myArray.get(i).getPrice() * (double) myArray.get(i).getQuantity())  - myArray.get(i).getBookValue()
                            -stockCommission;
                    finalGain += indiGain;
                    info.append(myArray.get(i).getSymbol() + " " + myArray.get(i).getType()
                    + " Gain is $" +indiGain + "\n");
                }
                else{
                    indiGain = (myArray.get(i).getPrice() * myArray.get(i).getQuantity()) - myArray.get(i).getBookValue()
                            -MutualFundCommission;
                    finalGain += indiGain;
                    info.append(myArray.get(i).getSymbol() + " " + myArray.get(i).getType()
                            + " Gain is $" +indiGain + "\n");
                }
            }
        }
        gainField.setText("$" +String.valueOf(finalGain));
    }



    /**
     *
     * @param str, user string from the command line
     *             uses the string as filename
     * @param inv, copy of the arrayList of Investments
     */
    public void writeAllToFile(String str, ArrayList<Investment> inv){
        String filename = str;

        Stock s = null;
        BufferedWriter writer;
        try{
            writer = new BufferedWriter(new FileWriter(filename));
            for(int i = 0; i < inv.size(); i++){
                if(inv.get(i) instanceof Stock){

                    s = (Stock)inv.get(i);
                    writer.write(s.toString() + "\n" );

                }
                else if(inv.get(i) instanceof MutualFund){
                    MutualFund mf = (MutualFund)inv.get(i);
                    writer.write(mf.toString() + "\n");
                }
            }
            writer.close();
        }
        catch(IOException e){
            System.out.println("Error Creating the file");
        }

    }

    /**
     * Reads the data from the file and populated the arrayList
     * checks if the filename exists, if not, creates the file
     * @param str, the filename to open or create
     * @param inv, the investment arrayList to fill in
     */
    public void readFromFile(String str, ArrayList<Investment> inv){

        String type = "";
        String symbol;
        String name = "";
        int quantity = 0;
        double price;
        double bookValue;
        String line = "";

        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(str));
            while(line != null){

                line = reader.readLine();
                if(line == null){
                    break;
                }

                type = line;

                line = reader.readLine();
                symbol = line;

                line = reader.readLine();
                name = line;

                line = reader.readLine().trim();
                quantity = Integer.parseInt(line);

                line  = reader.readLine().trim();
                price = Double.parseDouble(line);

                line = reader.readLine().trim();
                bookValue = Double.parseDouble(line);

                if(type.equalsIgnoreCase("stock")){
                    try {
                        Stock s = new Stock(symbol, name, quantity, price, bookValue);
                        inv.add(s);
                    }
                    catch (Investment.customException e){
                        System.out.println(e.getMessage());
                    }
                }
                else if(type.equalsIgnoreCase("Mutual Fund")){
                    try {
                        MutualFund mf = new MutualFund(symbol, name, quantity, price, bookValue);
                        inv.add(mf);
                    }
                    catch (Investment.customException e){

                    }
                }
            }

            reader.close();
        }
        catch (IOException fne ){
            System.out.println("File Doesn't Exist and has been created");
            writeAllToFile(str, inv);
            // System.exit(1);
        }
    }

    /**
     * Function that creates the hash map
     * @return myMap, which is the hash map that has been created
     */
    public HashMap createHashMap(ArrayList<Investment>myArray) {


        HashMap<String, ArrayList<Integer>> MyMap = new HashMap<>();
       // String key;

        for (int i = 0; i < myArray.size(); i++) {
            StringTokenizer str = new StringTokenizer(myArray.get(i).getName(), " ");
            String token;

            while (str.hasMoreElements()) {
                token = str.nextToken().toUpperCase();
                ArrayList<Integer> temp = MyMap.get(token);

                if(temp == null){
                    temp = new ArrayList<Integer>();
                    temp.add(i);
                    MyMap.put(token.toUpperCase(), temp);
                }
                else if(!temp.contains(i)){
                    temp.add(i);
                }

            }

        }

        return MyMap;

    }


    /**
     *
     * @param symbol, symbol to look for
     * @param keyword, keywords to look for
     * @param num1_str, string that represents the low price
     * @param num2_str, string that represents the high price
     * @param myArray, a copy of the arrayList of Investments
     * @param info, JTextArea
     * @param matchArray
     */
    public void displaySearch(String symbol, String keyword, String num1_str, String num2_str,
                              ArrayList<Investment> myArray, JTextArea info, ArrayList<Integer>matchArray,
                              JTextField low, JTextField high){
        int flag1;
        int flag2;
        int flag3;
        double num1;
        double num2;

        int num1Flag = 0;
        int num2Flag = 0;
        int printed = 0;
        symbol = symbol.trim().replaceAll(" +"," ");
        keyword = keyword.trim().replaceAll(" +", " ");
        num1_str = num1_str.trim().replaceAll(" +", " ");
        num2_str = num2_str.trim().replaceAll(" +", " ");

        if(symbol.length() == 0){
            flag1 = 0;
        }
        else{
            flag1 = 1;
        }

        if(keyword.length() == 0){
            flag2 = 0;
        }
        else{
            flag2 = 1;
        }

        if(num1_str.length() == 0 && num2_str.length() == 0){
            flag3 = 0;
        }
        else{
            flag3 = 1;
        }


        if(num1_str.equals("")){
            num1 = 0;

        }
        else{
            try{
                num1 = Double.parseDouble(num1_str);
            }
            catch (NumberFormatException e){
                info.append("Incorrect Low Price Input\n");
                info.append("Please Re-Enter\n\n");
                num1 = -2;
                num1Flag++;
                low.setText("");
            }

        }
        if(num2_str.equals("")){
            num2 = 999999999;
        }
        else{
            try{
                num2 = Double.parseDouble(num2_str);
            }
            catch (NumberFormatException e){
                info.append("Incorrect High price Input\n");
                info.append("Please Re-Enter\n\n");
                num2 = -1;
                num2Flag++;
                high.setText("");
            }
        }



if(num1Flag == 0 && num2Flag == 0)

        if(flag1 == 0 && flag2 == 0 && flag3 == 0 ){
            for(int i = 0; i < myArray.size(); i++) {

                info.append(myArray.get(i).toString() +  "\n\n");
                printed++;
            }
            if(printed == 0){
                //System.out.println("You Don't own any Investments");
                info.append("You Don't own any Investments\n");
            }
        }
        else if(flag1  == 1 && flag2 == 0 && flag3 == 0){
            // symbol only
            for (int i = 0; i < myArray.size(); i++) {
                if (myArray.get(i).getSymbol().equalsIgnoreCase(symbol)) {

                    info.append(myArray.get(i).toString() + "\n\n");
                    printed++;

                }
            }
            if(printed == 0){
                //System.out.println("No Match Found");
                info.append("No Match Found!" + "\n");
            }
        }
        // search ky keyword
        else if(flag1 == 0 && flag2 == 1 && flag3 == 0){
            if(matchArray != null) {
                for (int i = 0; i < matchArray.size(); i++) {
                    ///System.out.println(myArray.get(i).toString());
                    info.append(myArray.get(matchArray.get(i)).toString() + "\n\n");
                }
            }
            else{

                info.append("No Match Found!\n");
            }
        }
        // search by price range

        else if(flag1 == 0 && flag2 == 0 && flag3 == 1){

            for (int i = 0; i < myArray.size(); i++) {
                if (myArray.get(i).getPrice() >= num1 && myArray.get(i).getPrice() <= num2) {
                   // System.out.println(myArray.get(i).toString());
                    info.append(myArray.get(i).toString() + "\n\n");
                    printed++;
                }
            }
            if(printed == 0){
                //System.out.println("No Match Found!");
                info.append("No Match Found!\n");
            }
        }
        //symbol and price
        else if(flag1 == 1 && flag2 == 0 && flag3 == 1){

            for (int i = 0; i < myArray.size(); i++) {
                if (myArray.get(i).getSymbol().equalsIgnoreCase(symbol) &&
                        myArray.get(i).getPrice() >= num1
                        && myArray.get(i).getPrice() <= num2) {
                    //System.out.println(myArray.get(i).toString());
                    info.append(myArray.get(i).toString() + "\n");
                    printed++;
                }
            }
            if(printed == 0){
                //System.out.println("No Match Found!");
                info.append("No Match Found!\n");
            }
        }
        /* Symbol + Keywords; */
        else if(flag1 == 1 && flag2 == 1 && flag3 == 0){
            if(matchArray.size() != 0){

                for (int i = 0; i < matchArray.size(); i++) {
                    if (myArray.get(matchArray.get(i)).getSymbol().equalsIgnoreCase(symbol)) {
                        //System.out.println(investments.get(myArray.get(i)).toString());
                        info.append(myArray.get(matchArray.get(i)).toString() + "\n\n");
                        printed++;
                    }
                }
                if (printed == 0) {
                    //System.out.println("No match found!");
                    info.append("No Match Found!\n");
                }

            }
            else{
                //System.out.println("No Match Found");
                info.append("No Match Found!\n");
            }
        }
        /* search by keywords and price */
        else if(flag1 == 0 && flag2 == 1 && flag3 == 1){
            if(matchArray.size() != 0){
                for(int i = 0; i < matchArray.size(); i++){
                    if(myArray.get(matchArray.get(i)).getPrice() >= num1
                            && myArray.get(matchArray.get(i)).getPrice() <= num2){
                        //System.out.println(investments.get(myArray.get(i)).toString());
                        info.append(myArray.get(matchArray.get(i)).toString() + "\n\n");
                        printed++;
                    }
                }
                if(printed == 0){
                    //System.out.println("No Match Found!");
                    info.append("No Match Found\n");
                }
            }
            else{
                //System.out.println("No Match Found!");
                info.append("No Match Found!\n");
            }
        }

        //search by symbol, keywords and price range
        else if(flag1 == 1 && flag2 == 1 && flag3 == 1){
            if(matchArray.size() != 0){
                for(int i = 0; i < matchArray.size(); i++){
                    if(myArray.get(matchArray.get(i)).getSymbol().equalsIgnoreCase(symbol)
                            && myArray.get(matchArray.get(i)).getPrice() >= num1
                            && myArray.get(matchArray.get(i)).getPrice() <= num2){
                        //System.out.println(myArray.get(matchArray.get(i)).toString());
                        info.append(myArray.get(matchArray.get(i)).toString() + "\n\n");

                        printed++;
                    }
                }
                if(printed == 0){
                    //System.out.println("No Match Found!");
                    info.append("No Match Found!\n");
                }
            }
            else{
                //System.out.println("No Match Found!");
                info.append("No Match Found!\n");
            }
        }


    }


    public ArrayList<Integer> searchFunction(String Keyword, HashMap<String, ArrayList<Integer>> map){

        Set<HashMap.Entry<String, ArrayList<Integer>>> entries = map.entrySet();
        Iterator<HashMap.Entry<String, ArrayList<Integer>>> iter = entries.iterator();

        String name = Keyword;


        String[] array = name.toUpperCase().split(" ");
        ArrayList<Integer> a;

        a = map.get(array[0]);
        for(int i = 1; i < array.length; i++){
            ArrayList<Integer> b = map.get(array[i]);
            if(b == null){
                //return;
            }
            try {
                a.retainAll(b);
            }catch (NullPointerException npe){

            }
        }


        return a;

    }

    public void apply(JTextArea myArea){
        //myArea.append("IT WORDS\n");
    }

    public boolean checkEntryFields(String symbol, String name, String quantity, String price, JTextArea info){
        if(symbol.length() == 0){
            info.append("Please enter a Symbol!\n");
        }
        if(name.length() == 0){
            info.append("Please enter a Name!\n");

        }
        if(quantity.length() == 0){
            info.append("Please enter a quantity!\n");
        }
        if(price.length() == 0){
            info.append("Please enter a price!\n");
        }
        if(symbol.length() != 0 && name.length() != 0 && quantity.length() != 0 && price.length() != 0){
            return true;
        }
        else{
            return  false;
        }
    }




}
