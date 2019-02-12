 package Abhishek;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;


 /**
 * Created by Abhishek on 11/23/2017.
 *
 */
 public class GUI extends JFrame {

     private ArrayList<Investment> investments = new ArrayList<>();
     private int counter = 0;

     private double gain = 0;
     private double stockCommission  = 9.99;
     private double MutualFundCommission = 45.00;
     private Portfolio myPortfolio = new Portfolio();
      String myArgs = "";

     public void getString(String args){
         myArgs = args;
         myPortfolio.readFromFile(args, investments);

     }


    public GUI(){


        super("Investment Portfolio");
        /* setTitle("Investment Portfolio"); */




        setSize(600,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

       // ArrayList<Investment> investments = new ArrayList<>();
        //investments =
       // myPortfolio.readFromFile("myFile.txt", investments);

        JMenuBar myBar = new JMenuBar();
        JMenu commands = new JMenu("Commands");
        JMenuItem buy = new JMenuItem("Buy");
        JMenuItem sell = new JMenuItem("Sell");
        JMenuItem update = new JMenuItem("Update");
        JMenuItem gain =  new JMenuItem("Get Gain");
        JMenuItem search = new JMenuItem("Search");
        JMenuItem quit = new JMenuItem("Quit");


        myBar.add(commands);
        commands.add(buy);
        commands.add(sell);
        commands.add(update);
        commands.add(gain);
        commands.add(search);
        commands.add(quit);



        Font font = new Font("a", Font.BOLD, 16);

        JTextArea welcomeMessage = new JTextArea("\tWelcome to the Investment portfolio\n\n\n" +
                "       Choose a command from the Commands menu to buy or sell\n" +
                "       an investment, update prices for all investments, get gain for\n" +
                "       the portfolio, search for relevant investments, or quit the program");
        welcomeMessage.setFont(font);
        welcomeMessage.setEditable(false);


        this.add(myBar, BorderLayout.NORTH);
        this.add(welcomeMessage, BorderLayout.CENTER);
        final JComponent[] currentPanel = {welcomeMessage};
        buy.addActionListener(e -> {
            remove(currentPanel[0]);
            currentPanel[0] = buy();
            add(currentPanel[0]);

            revalidate();
        });
        sell.addActionListener(e -> {
            remove(currentPanel[0]);
            currentPanel[0] = sell();
            add(currentPanel[0]);

            revalidate();
        });
        update.addActionListener(e -> {
            remove(currentPanel[0]);
            currentPanel[0] = update();
            add(currentPanel[0]);

            revalidate();
        });

        gain.addActionListener(e -> {
            remove(currentPanel[0]);
            currentPanel[0] = gain();
            add(currentPanel[0]);

            revalidate();
        });

        search.addActionListener(e -> {
            remove(currentPanel[0]);
            currentPanel[0] = search();
            add(currentPanel[0]);

            revalidate();
        });

        quit.addActionListener(e ->{

                myPortfolio.writeAllToFile(myArgs,investments);
                System.exit(1);
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                myPortfolio.writeAllToFile(myArgs, investments);
            }
        });


        setVisible(true);

    }

    private JPanel buy(){

        JPanel buyPanel = new JPanel();
        buyPanel.setLayout(new GridLayout(2,2));


        JPanel buyField = new JPanel();
        buyField.setLayout(new GridLayout(1,2));

        JPanel options = new JPanel();
        options.setLayout(new GridLayout(6,2, 0, 5));

        JLabel title = new JLabel(" Currently Buying an Investment");
        JLabel space = new JLabel("  ");
        JLabel type = new JLabel("Type:");
        type.setBorder(new EmptyBorder(0, 20, 0, 0));
        JLabel symbol = new JLabel("Symbol:");
        symbol.setBorder(new EmptyBorder(0, 20, 0, 0));
        JLabel name = new JLabel("Name:");
        name.setBorder(new EmptyBorder(0, 20, 0, 0));
        JLabel quantity = new JLabel("Quantity:");
        quantity.setBorder(new EmptyBorder(0, 20, 0, 0));
        JLabel price = new JLabel("Price:");
        price.setBorder(new EmptyBorder(0, 20, 0, 0));

        JComboBox selectType = new JComboBox();


        Font font =  new Font("Stock", Font.BOLD, 14);

        selectType.setFont(font);

        selectType.addItem("Stock");
        selectType.addItem("Mutual Fund");


        JTextField selectSymbol = new JTextField();

        JTextField selectName = new JTextField();

        JTextField selectQuantity = new JTextField();

        JTextField selectPrice = new JTextField();

        JTextArea information = new JTextArea("Messages...\n");  // moved here from bottom
        information.setFont(font);


        options.add(title);
        options.add(space);
        options.add(type);
        options.add(selectType);
        options.add(symbol);
        options.add(selectSymbol);
        options.add(name);
        options.add(selectName);
        options.add(quantity);
        options.add(selectQuantity);
        options.add(price);
        options.add(selectPrice);

        JPanel button = new JPanel();
        button.setLayout(new GridLayout(2,1));

        JPanel resetPanel = new JPanel(new GridBagLayout());  // mod here


        GridBagConstraints gbc = new GridBagConstraints();   // modded
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.ipady = -10;
        gbc.weighty = -4;



        JPanel buy =  new JPanel(new GridBagLayout());
        GridBagConstraints gbcBuy = new GridBagConstraints();
        gbcBuy.gridx = 1;
        gbcBuy.gridy = 1;
        gbcBuy.gridheight = 1;
        gbcBuy.ipady = -10;
        gbcBuy.weighty = -2;

        JButton reset = new JButton("Reset");
        JButton Buy = new JButton("Buy");
        Buy.setEnabled(true);




        reset.addActionListener(e -> {
            selectSymbol.setText("");
            selectName.setText("");
            selectPrice.setText("");
            selectQuantity.setText("");
            information.setText("");
        });


        reset.setPreferredSize(new Dimension(100,50));
        Buy.setPreferredSize(new Dimension(100, 50));

        resetPanel.add(reset, gbc);

        buy.add(Buy, gbcBuy);

        button.add(resetPanel);
        button.add(buy);

        buyField.add(options);
        buyField.add(button);

        JPanel messageField = new JPanel(new BorderLayout());
        JLabel messageTitle = new JLabel("Messages");
        information.setLineWrap(true);
        information.setEditable(false);
        JScrollPane scroll = new JScrollPane(information, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        messageField.add(messageTitle, BorderLayout.NORTH);
        messageField.add(scroll, BorderLayout.CENTER);

        buyPanel.add(buyField);
        buyPanel.add(messageField);

        Buy.addActionListener((ActionEvent e) -> {

            ArrayList<Investment> dummyArray  = new ArrayList<>(investments);


            myPortfolio.buyNewInvestment(dummyArray, selectType.getSelectedItem().toString(),
                    selectSymbol.getText(), selectName.getText(), selectQuantity.getText(),
                    selectPrice.getText(), information, selectPrice,selectQuantity);
            investments = dummyArray;

            selectName.setText("");
            selectPrice.setText("");
            selectSymbol.setText("");
            selectQuantity.setText("");
                        HashMap<String, ArrayList<Integer>> map;

                        map = myPortfolio.createHashMap(dummyArray);
                        System.out.println(map);

        });


        return buyPanel;

    }

    private JPanel sell(){

        JPanel sellPanel = new JPanel();
        sellPanel.setLayout(new GridLayout(2,1));

        JPanel sellArea = new JPanel();
        sellArea.setLayout(new GridLayout(1,2));

        JPanel sellingOptions = new JPanel();
        sellingOptions.setLayout(new GridLayout(5,1, 0, 7));


        // creating a standard fond here;
        Font font = new Font("a", Font.BOLD, 14);

        Font titleFont = new Font("f", Font.BOLD, 16);

        JLabel sellTitle = new JLabel("Selling an investment");
        JLabel space = new JLabel(" ");
        JLabel symbol = new JLabel(" Symbol");
        JLabel quantity = new JLabel(" Quantity");
        JLabel price = new JLabel(" Price");

        sellTitle.setFont(titleFont);
        symbol.setFont(font);
        quantity.setFont(font);
        price.setFont(font);

        JTextField symbolInput = new JTextField();
        JTextField quantityInput = new JTextField();
        JTextField priceInput = new JTextField();


       sellingOptions.add(sellTitle);
       sellingOptions.add(space);
       sellingOptions.add(symbol);
       sellingOptions.add(symbolInput);
       sellingOptions.add(quantity);
       sellingOptions.add(quantityInput);
       sellingOptions.add(price);
       sellingOptions.add(priceInput);

       JTextArea information = new JTextArea("Messages...\n");  // moved here from bottom
        // information.setFont(font);

        JPanel button = new JPanel();
        button.setLayout(new GridLayout(2,1));

        JPanel resetPanel = new JPanel(new GridBagLayout());
        GridBagConstraints resetGBC = new GridBagConstraints();   // modded
        resetGBC.fill = GridBagConstraints.HORIZONTAL;
        resetGBC.gridx = 1;
        resetGBC.gridy = 1;
        resetGBC.ipady = -10;
        resetGBC.weighty = -4;

        JPanel sellPanelButton = new JPanel(new GridBagLayout());
        GridBagConstraints sellGBC = new GridBagConstraints();
        sellGBC.gridx = 1;
        sellGBC.gridy = 1;
        sellGBC.gridheight = 1;
        sellGBC.ipady = -10;
        sellGBC.weighty = -2;

        JButton resetButton = new JButton("Reset");
        JButton sellButton = new JButton("Sell");

        resetButton.setPreferredSize(new Dimension(100,50));
        sellButton.setPreferredSize(new Dimension(100, 50));

        resetPanel.add(resetButton, resetGBC);
        sellPanelButton.add(sellButton, sellGBC);

        button.add(resetPanel);
        button.add(sellPanelButton);

        sellArea.add(sellingOptions);
        sellArea.add(button);


        JPanel messageField = new JPanel(new BorderLayout());
        JLabel messageTitle = new JLabel("Messages");
        information.setLineWrap(true);
        information.setEditable(false);
        JScrollPane scroll = new JScrollPane(information, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        messageField.add(messageTitle, BorderLayout.NORTH);
        messageField.add(scroll, BorderLayout.CENTER);


        sellPanel.add(sellArea);
        sellPanel.add(messageField);

        resetButton.addActionListener(e -> {
            information.setText("");
            symbolInput.setText("");
            quantityInput.setText("");
            priceInput.setText("");

        });
        sellButton.addActionListener(e -> {
            if(symbolInput.getText().equals("")){
                information.append("Please enter a symbol\n");
            }
            if(quantityInput.getText().equals("")){
                information.append("Please enter a quantity\n");
            }
            if(priceInput.getText().equals("")){
                information.append("Please enter a price\n");
            }

            else{
                ArrayList<Investment> dummyArray = new ArrayList<>(investments);

                myPortfolio.sellInvestment(dummyArray, symbolInput.getText(), quantityInput.getText(),priceInput.getText(),
                        information, quantityInput, priceInput, symbolInput);
                investments = dummyArray;

            }
        });


        return  sellPanel;
    }

    private JPanel update(){
        JPanel updatePanel = new JPanel();
        updatePanel.setLayout(new GridLayout(2, 1));

        JPanel updateArea = new JPanel();
        updateArea.setLayout(new GridLayout(1,2));

        JPanel updateOptions = new JPanel();
        updateOptions.setLayout(new GridLayout(5,1, 0, 10));


        Font font = new Font("a", Font.BOLD, 14);
        Font titleFont = new Font("f", Font.BOLD, 16);

        JLabel updateTitle = new JLabel("Updating Investments");
        updateTitle.setFont(titleFont);  // added recently
        JLabel space = new JLabel(" ");
        JLabel symbol = new JLabel(" Symbol");

        JLabel name = new JLabel(" Name");
        JLabel price = new JLabel(" Price");

        //  set fonts here ;)

        JTextField symbolInput = new JTextField();
        symbolInput.setEditable(false);
        JTextField NameInput = new JTextField();
        NameInput.setEditable(false);
        JTextField priceInput = new JTextField();
        priceInput.setEditable(true);

        updateOptions.add(updateTitle);
        updateOptions.add(space);
        updateOptions.add(symbol);
        updateOptions.add(symbolInput);
        updateOptions.add(name);
        updateOptions.add(NameInput);
        updateOptions.add(price);
        updateOptions.add(priceInput);

        JTextArea information = new JTextArea("Messages...\n");  // moved here from bottom
        // information.setFont(font);

        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(3,1));




        JPanel prevPanel = new JPanel(new GridBagLayout());
        GridBagConstraints prevGBC = new GridBagConstraints();   // modded
        prevGBC.fill = GridBagConstraints.HORIZONTAL;
        prevGBC.gridx = 1;
        prevGBC.gridy = 1;
        prevGBC.ipady = -10;
        prevGBC.weighty = -4;


        JPanel nextPanel = new JPanel(new GridBagLayout());
        GridBagConstraints nextGBC = new GridBagConstraints();
        nextGBC.gridx = 1;
        nextGBC.gridy = 1;
        nextGBC.gridheight = 1;
        nextGBC.ipady = -10;
        nextGBC.weighty = -2;


        JPanel savePanel = new JPanel(new GridBagLayout());

        GridBagConstraints saveGBC = new GridBagConstraints();
        saveGBC.gridx = 1;
        saveGBC.gridy = 1;
        saveGBC.gridheight = 1;
        saveGBC.ipady = -10;
        saveGBC.weighty = -2;

        JButton prevButton = new JButton("Prev");
        JButton nextButton = new JButton("Next");
        JButton saveButton  = new JButton("Save");

        prevButton.setPreferredSize(new Dimension(100, 50));
        nextButton.setPreferredSize(new Dimension(100, 50));
        saveButton.setPreferredSize(new Dimension(100, 50));

        prevPanel.add(prevButton, prevGBC);
        nextPanel.add(nextButton, nextGBC);
        savePanel.add(saveButton, saveGBC);

        buttonArea.add(prevPanel);
        buttonArea.add(nextPanel);
        buttonArea.add(savePanel);

        updateArea.add(updateOptions);
        updateArea.add(buttonArea);


        JPanel messageField = new JPanel(new BorderLayout());
        JLabel messageTitle = new JLabel("Messages");
        information.setLineWrap(true);
        information.setEditable(false);
        JScrollPane scroll = new JScrollPane(information, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        messageField.add(messageTitle, BorderLayout.NORTH);
        messageField.add(scroll, BorderLayout.CENTER);


        updatePanel.add(updateArea);
        updatePanel.add(messageField);

        if(investments.size() == 0 ){
            information.append("You Don't have any investments\n");
            prevButton.setEnabled(false);
            nextButton.setEnabled(false);

        }

        else{
            if(investments.size() == 1){
                prevButton.setEnabled(false);
                nextButton.setEnabled(false);
            }

            int size;
            size = investments.size();

            symbolInput.setText(investments.get(counter).getSymbol());
            NameInput.setText(investments.get(counter).getName());
            if(counter == 0) {
                prevButton.setEnabled(false);
            }
            nextButton.addActionListener(e -> {
                if(counter < size) {
                    prevButton.setEnabled(true);
                    counter++;
                    if(counter >= 1)
                        prevButton.setEnabled(true);

                    if(counter  != size) {
                        symbolInput.setText(investments.get(counter).getSymbol());
                        NameInput.setText(investments.get(counter).getName());

                    }
                    if(counter == size-1){
                        nextButton.setEnabled(false);
                    }
                    if(counter == size){

                        counter--;
                    }
                }

            });

            prevButton.addActionListener(e -> {


                if(counter > 0){
                    nextButton.setEnabled(true);
                    counter--;
                    symbolInput.setText(investments.get(counter).getSymbol());
                    NameInput.setText(investments.get(counter).getName());
                }
                if(counter == 0){
                    prevButton.setEnabled(false);
                }



            });

            saveButton.addActionListener(e -> {
                myPortfolio.updateInvestment(investments, priceInput.getText(), counter, information, nextButton, prevButton,
                        priceInput);

            });

        }


        return updatePanel;

    }

    private JPanel gain(){
        JPanel gainPanel = new JPanel();
        gainPanel.setLayout(new GridLayout(2,1));


        JPanel gainArea = new JPanel();
        gainArea.setLayout(new GridLayout(2,2 , 0, 10));

        JLabel title = new JLabel("Getting total gain");
        JLabel space = new JLabel(" ");
        JLabel totalGain = new JLabel("Total Gain");

        JTextField gainOutput = new JTextField();
        gainOutput.setEditable(false);
        gainOutput.setPreferredSize(new Dimension(100, 100));

        gainArea.add(title);
        gainArea.add(space);
        gainArea.add(totalGain);
        gainArea.add(gainOutput);

        JTextArea information = new JTextArea("Messages...\n");

        JPanel messageField = new JPanel(new BorderLayout());
        JLabel messageTitle = new JLabel("Messages");
        information.setLineWrap(true);
        information.setEditable(false);
        JScrollPane scroll = new JScrollPane(information, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        messageField.add(messageTitle, BorderLayout.NORTH);
        messageField.add(scroll, BorderLayout.CENTER);



        gainPanel.add(gainArea);
        gainPanel.add(messageField);

        gainOutput.setText("Your total gain is $");

        myPortfolio.gainOfInvestment(investments, information, gainOutput);


        return  gainPanel;
    }

    private JPanel search(){

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(2,1 ));

        JPanel searchArea = new JPanel();
        searchArea.setLayout(new GridLayout(1, 2));

        JPanel searchOptions = new JPanel();
        searchOptions.setLayout(new GridLayout(6, 1, 0, 7));

        Font font = new Font("a", Font.BOLD, 14);
        Font titleFont = new Font("f", Font.BOLD, 16);

        JLabel updateTitle = new JLabel("Searching Investments");
        updateTitle.setFont(titleFont);

        JLabel space = new JLabel(" ");
        JLabel symbol = new JLabel(" Symbol");
        JLabel name = new JLabel(" Name/Keywords");

        JLabel lowPrice = new JLabel(" Low price");
        JLabel highPrice = new JLabel(" High price");

        JTextField symbolInput = new JTextField();
        JTextField NameInput = new JTextField();
        JTextField lowPriceInput = new JTextField();
        JTextField highPriceInput = new JTextField();

        searchOptions.add(updateTitle);
        searchOptions.add(space);
        searchOptions.add(symbol);
        searchOptions.add(symbolInput);
        searchOptions.add(name);
        searchOptions.add(NameInput);
        searchOptions.add(lowPrice);
        searchOptions.add(lowPriceInput);
        searchOptions.add(highPrice);
        searchOptions.add(highPriceInput);

        JTextArea information = new JTextArea("Messages...\n");  // moved here from bottom
        information.setFont(font);

        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(2,1));


        JPanel resetPanel = new JPanel(new GridBagLayout());
        GridBagConstraints resetGBC = new GridBagConstraints();   // modded
        resetGBC.fill = GridBagConstraints.HORIZONTAL;
        resetGBC.gridx = 1;
        resetGBC.gridy = 1;
        resetGBC.ipady = -10;
        resetGBC.weighty = -4;


        JPanel sPanel = new JPanel(new GridBagLayout());
        GridBagConstraints sGBC = new GridBagConstraints();
        sGBC.gridx = 1;
        sGBC.gridy = 1;
        sGBC.gridheight = 1;
        sGBC.ipady = -10;
        sGBC.weighty = -2;

        JButton resetButton = new JButton("Reset");
        JButton searchButton = new JButton("Search");

        resetButton.setPreferredSize(new Dimension(100,50));
        searchButton.setPreferredSize(new Dimension(100, 50));

        resetPanel.add(resetButton, resetGBC);
        sPanel.add(searchButton, sGBC);

        buttonArea.add(resetPanel);
        buttonArea.add(sPanel);

        searchArea.add(searchOptions);
        searchArea.add(buttonArea);



        JPanel messageField = new JPanel(new BorderLayout());
        JLabel messageTitle = new JLabel("Messages");
        information.setLineWrap(true);
        information.setEditable(false);
        JScrollPane scroll = new JScrollPane(information, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        messageField.add(messageTitle, BorderLayout.NORTH);
        messageField.add(scroll, BorderLayout.CENTER);


        searchPanel.add(searchArea);
        searchPanel.add(messageField);

        resetButton.addActionListener(e -> {
            symbolInput.setText("");
            NameInput.setText("");
            lowPriceInput.setText("");
            highPriceInput.setText("");
            information.setText("");
        });


        searchButton.addActionListener(e -> {
            if(symbolInput.getText().equals("")
                    && NameInput.getText().equals("")
                    && lowPriceInput.getText().equals("")
                    && highPriceInput.getText().equals("")){
                information.append("Your Portfolio is as Follows:\n");
                for (Investment investment : investments) information.append(investment.toString()+ "\n\n");
            }
            else{

                 ArrayList<Integer> myArray = myPortfolio.searchFunction(NameInput.getText(), myPortfolio.createHashMap(investments));
                 System.out.println(myArray);
                 myPortfolio.apply(information);
                 myPortfolio.displaySearch(symbolInput.getText(), NameInput.getText(), lowPriceInput.getText(), highPriceInput.getText(),
                         investments, information, myArray, lowPriceInput, highPriceInput);
            }
        });


        return searchPanel;
    }


     /**
      *
      * @param symbol, which is the symbol of the investment
      * @return a number depending on the case
      * returns -2 to indicate an empty array List
      * returns -1  for a no match
      * returns a number >= 0 if symbol is present in array List
      */
     private int checkInvestmentSymbol(String symbol){
         if(investments.size() == 0){
             return -2;
         }
         for(int i = 0; i < investments.size(); i++){
             if(investments.get(i).getSymbol().equalsIgnoreCase(symbol)){
                 return i;
             }
         }
         return -1;
     }





 }
