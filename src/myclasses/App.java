/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Buyer;
import entity.History;
import entity.Model;
import facade.BuyerFacade;
import facade.HistoryFacade;
import facade.ModelFacade;
import interfaces.Keeping;
import static java.lang.reflect.Array.set;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import tools.SaverToBase;
import tools.SaverToFiles;

/**
 *
 * @author User
 */
public class App {
    public static boolean toFile = false;
    private Scanner scanner = new Scanner(System.in);
    private BuyerFacade buyerFacade;
    private ModelFacade modelFacade;
    private HistoryFacade historyFacade;
//   private List<Model> models = new ArrayList<>();
//   private List<Buyer> buyers = new ArrayList<>();
//   private List<History> histories = new ArrayList<>();
//   private Keeping keeper = new SaverToBase();
    
    public App(){
//        if (toFile) {
//            keeper = new SaverToFiles();
//        }else{
//            keeper = new SaverToBase();
//        }
//            
//        models = keeper.loadModels();
//        buyers = keeper.loadBuyers();
//        histories = keeper.loadHistories();
        buyerFacade = new BuyerFacade(Buyer.class);
        modelFacade = new ModelFacade(Model.class);
        historyFacade = new HistoryFacade(History.class);

    }
    
    public void run() {
       String repeat = "yes";
        do {            
            System.out.println("Number of the task: ");
            System.out.println("0: Close program");
            System.out.println("1: Add model of a shoe");
            System.out.println("2: List of purchuseable models");
            System.out.println("3: Add a buyer");
            System.out.println("4: List of registered buyers");
            System.out.println("5: Sell shoes");
            System.out.println("6: Income");
            System.out.println("7: Add money to buyer");
            System.out.println("8: Change the model");
            System.out.println("9: Change the user");
            System.out.println("10: Income per month");
            int task = scanner.nextInt(); scanner.nextLine();
            switch (task) {
                case 0:
                  repeat = "no";
                  break;
                case 1:
                    addModel();
                    break;
                case 2:
                    listModel();
                    break;
                case 3:
                    addBuyer();
                    break;
                case 4:
                    listBuyer();
                    break;
                case 5:
                    soldShoe();
                    break;
                case 6:
                    income();
                    break;
                case 7:
                    addMoney();
                    break;
                case 8:
                    changeModel();
                    break;
                case 9:
                    changeBuyer();
                    break;
                case 10:
                    incomePerMonth();
                    break;
                default:
                    System.out.println("Choose the number from the list");
            }
        } while ("yes".equals(repeat));
        System.out.println("Bye!!!");
    }

    private void addModel() {
        Model model = new Model();
        System.out.println("Name of the brand: ");
        model.setBrand(scanner.nextLine());
        System.out.println("Name of the shoe: ");
        model.setModelName(scanner.nextLine());
        System.out.println("Size of the shoe: ");
        model.setSize(scanner.nextInt());
        System.out.println("Price of the shoe: ");
        model.setPrice(scanner.nextInt());
        System.out.println("Amount of shoes in stock");
        model.setQuantity(scanner.nextInt());scanner.nextLine();
        model.setCount(model.getQuantity());
        System.out.println("Shoe: "+model.toString());
        modelFacade.create(model);
//      models.add(model);
//      keeper.saveModels(models);
        
    }

    private Set<Integer> listModel() {
        Set<Integer> setNumberModels = new HashSet<>();
        List<Model> models = modelFacade.findAll();
        System.out.println("List of models that are in stock");
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i) != null 
                    && models.get(i).getCount() > 0
                    && models.get(i).getCount() < models.get(i).getQuantity() + 1) {
                System.out.printf("%d. Brand: %s Name: %s Size: %d Price: %d Qauantity: %d.%n"
                        ,models.get(i).getId()
                        ,models.get(i).getBrand()
                        ,models.get(i).getModelName()
                        ,models.get(i).getSize()
                        ,models.get(i).getPrice()
                        ,models.get(i).getQuantity()
                );
                setNumberModels.add(models.get(i).getId().intValue());
            }
        }
        if(setNumberModels.isEmpty()){
            System.out.println("The list is empty");
        }
        return setNumberModels;
    }

    private void addBuyer() {
        Buyer buyer = new Buyer();
        System.out.println("First name of the buyer: ");
        buyer.setFirstName(scanner.nextLine());
        System.out.println("Last name of the buyer: ");
        buyer.setLastName(scanner.nextLine());
        System.out.println("Phone number of the buyer: ");
        buyer.setTel(scanner.nextInt());
        System.out.println("Money that the buyer has");
        buyer.setMoney(scanner.nextInt());
        System.out.println("Buyer: "+buyer.toString());
        buyerFacade.create(buyer);
//        buyers.add(buyer);
//        keeper.saveBuyers(buyers);
    }

    private Set<Integer> listBuyer() {
        System.out.println("List of registered buyers");
        Set<Integer> setNumberBuyers = new HashSet<>();
        List<Buyer> buyers = buyerFacade.findAll();
        for (int i = 0; i < buyers.size(); i++) {
            if (buyers.get(i) != null) {
                System.out.printf("%d. %s %s Tel: %d  Money: %d%n"
                        ,buyers.get(i).getId()
                        ,buyers.get(i).getFirstName()
                        ,buyers.get(i).getLastName()
                        ,buyers.get(i).getTel()
                        ,buyers.get(i).getMoney()
                );
                setNumberBuyers.add(buyers.get(i).getId().intValue());
            }
        }
        if (setNumberBuyers.isEmpty()) {
            System.out.println("The list is empty");
        }
        return setNumberBuyers;
    }

    private void soldShoe() {
        System.out.println("----------- Sell ----------------");
        System.out.println("The List of purchaseable shoes");
        Set<Integer> setNumberModels = new HashSet<>();
        List<Model> models = modelFacade.findAll();
        int n = 0;
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i) != null && models.get(i).getCount() > 0) {
                System.out.printf("%d. Brand: %s Name: %s Size: %d Price: %d Qauantity: %d.%n"
                        ,models.get(i).getId()
                        ,models.get(i).getBrand()
                        ,models.get(i).getModelName()
                        ,models.get(i).getSize()
                        ,models.get(i).getPrice()
                        ,models.get(i).getQuantity()
                ); 
                n++;
                setNumberModels.add(models.get(i).getId().intValue());
            }
        }
        if (n < 1) {
            System.out.println("No shoes in stock");
            return;
        }
        System.out.println("Choose what shoes do you want to sell");
        Set<Integer> setNumberBuyers = new HashSet<>();
        List<Buyer> buyers = buyerFacade.findAll();
        int numberModel = scanner.nextInt();scanner.nextLine();
        System.out.println("List of buyers");
        for (int i = 0; i < buyers.size(); i++) {
            if (buyers.get(i) != null) {
                System.out.printf("%d. %s %s. Tel: %d Money: %d%n"
                        ,buyers.get(i).getId()
                        ,buyers.get(i).getFirstName()
                        ,buyers.get(i).getLastName()
                        ,buyers.get(i).getTel()
                        ,buyers.get(i).getMoney()
                );
                setNumberBuyers.add(buyers.get(i).getId().intValue());
            }
        }
        System.out.println("Choose who do you want to sell it to");
        Set<Integer> setNumberSoldShoes = new HashSet<>();
        History history = new History();
        int numberBuyer = scanner.nextInt();scanner.nextLine();
        List<History> histories = historyFacade.findListSoldShoes();
        Model model = modelFacade.find((long)numberModel);
        history.setModel(model);
        history.setBuyer(buyerFacade.find((long)numberBuyer));
        Calendar c = new GregorianCalendar();
        history.setSoldShoes(c.getTime());
        model.setCount(model.getCount() - 1);
        modelFacade.edit(model);
        historyFacade.edit(history);
        for (int i = 0; i < histories.size(); i++) {
            System.out.printf("%d. %s %s bought %s for %d euros, on %s.%n"
                    ,histories.get(i).getId()
                    ,histories.get(i).getBuyer().getFirstName()
                    ,histories.get(i).getBuyer().getLastName()
                    ,histories.get(i).getModel().getModelName()
                    ,histories.get(i).getModel().getPrice()
                    ,histories.get(i).getSoldShoes()
            );
            setNumberSoldShoes.add(histories.get(i).getId().intValue()); 
        }
        System.out.println("----------------");
    }

    private void income() {
        System.out.println("Income of the shop");
        List<History> histories = historyFacade.findAll();
        double income = 0;
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i) != null) {
                income += histories.get(i).getModel().getPrice();
            }
        }
        System.out.println(income + " Euros");
    }
    

    private void addMoney() {
        System.out.println("List of registered buyers");
        List<Buyer> buyers = buyerFacade.findAll();
        for (int i = 0; i < buyers.size(); i++) {
            if (buyers.get(i) != null) {
                System.out.printf("%d. %s %s Tel: %d Money: %d%n"
                        ,i+1
                        ,buyers.get(i).getFirstName()
                        ,buyers.get(i).getLastName()
                        ,buyers.get(i).getTel()
                        ,buyers.get(i).getMoney()
                );
            }
        }
        System.out.print("Choose number of the buyer: ");
        int numberUser=scanner.nextInt();scanner.nextLine();
        System.out.print("Enter the money that he has: ");
        int numberMoney=scanner.nextInt();scanner.nextLine();
        buyers.get(numberUser-1).setMoney(buyers.get(numberUser-1).getMoney()+numberMoney);
    }
    private int getNumber(){
        int number;
        do {
            String strNumber = scanner.nextLine();
            try {
                number = Integer.parseInt(strNumber);
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Entered \""+strNumber+"\" pick the model");
            }
        } while (true);
        
    }
    
    private int insertNumber(Set<Integer> setNumbers){
        int number = 0;
        do{
            number = getNumber();
            if (setNumbers.contains(number)) {
                break;
            }
            System.out.println("Try again: ");
        }while(true);
        return number;
    }

    private void changeModel() {
        System.out.println("Choose what shoes do you want to change");
        System.out.println("The List of purchaseable shoes");
        List<Model> models = modelFacade.findAll();
        int n = 0;
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i) != null && models.get(i).getQuantity()> 0) {
                System.out.printf("%d. Brand: %s Name: %s Size: %d Price: %d Qauantity: %d.%n"
                        ,i+1
                        ,models.get(i).getBrand()
                        ,models.get(i).getModelName()
                        ,models.get(i).getSize()
                        ,models.get(i).getPrice()
                        ,models.get(i).getQuantity()
                ); 
                n++;
            }
        }
        if (n<1) {
            System.out.println("No shoes in stock");
            return;
        }
        System.out.print("Choose the shoe that you want to change: ");
        int numberModel = getNumber();
        String repeat="yes";
        do{
            System.out.println("0: Exit");
            System.out.println("1: Change brand name");
            System.out.println("2: Change model name");
            System.out.println("3: Change size of the shoe");
            System.out.println("4: Change the price of the shoe");
            System.out.println("5: Change the quantity in stock");
            int num = getNumber();
            switch (num) {
                case 0:
                    repeat = "no";
                    break;
                case 1:
                    System.out.println("Enter a new brand name: ");
                    String newBrand = scanner.nextLine();
                    models.get(numberModel - 1).setBrand(newBrand);
                    break;
                case 2:
                    System.out.println("Enter a new model name: ");
                    String newModel = scanner.nextLine();
                    models.get(numberModel - 1).setModelName(newModel);
                    break;
               case 3:
                    System.out.println("Enter new size of the shoe: ");
                    int newSize = scanner.nextInt();
                    models.get(numberModel - 1).setSize(newSize);
                    break;
                case 4:
                    System.out.println("Enter new price of the shoe: ");
                    int newPrice = scanner.nextInt();
                    models.get(numberModel - 1).setPrice(newPrice);
                    break;
                case 5:
                    System.out.println("Enter new quanity: ");
                    int newQuantity = scanner.nextInt();
                    models.get(numberModel - 1).setQuantity(newQuantity);
                    break;
            }
            }while ("yes".equals(repeat));           
            
        
        
    }

    private void changeBuyer() {
        System.out.println("Choose what user you want to change: ");
        System.out.println("List of registered buyers");
        List<Buyer> buyers = buyerFacade.findAll();
        int n = 0;
        for (int i = 0; i < buyers.size(); i++) {
            if (buyers.get(i) != null) {
                System.out.printf("%d. %s %s Tel: %d  Money: %d%n"
                        ,i+1
                        ,buyers.get(i).getFirstName()
                        ,buyers.get(i).getLastName()
                        ,buyers.get(i).getTel()
                        ,buyers.get(i).getMoney()
                );
            }n++;
        }
        if (n<1) {
            System.out.println("No buyer");
            return;
        }
        System.out.print("Choose the shoe that you want to change: ");
        int numberBuyer = getNumber();
        String repeat="yes";
        do {
            System.out.println("0: Exit");
            System.out.println("1: Change name of the buyer");
            System.out.println("2: Change last name of the buyer");
            System.out.println("3: Change telephone number");
            System.out.println("4: Change money");
            int num = getNumber();
            switch (num) {
                case 0:
                    repeat = "no";
                    break;
                case 1:
                    System.out.println("Enter new first name: ");
                    String newFirstName = scanner.nextLine();
                    buyers.get(numberBuyer - 1).setFirstName(newFirstName);
                    break;
                case 2:
                    System.out.println("Enter new last name: ");
                    String newLastName = scanner.nextLine();
                    buyers.get(numberBuyer - 1).setLastName(newLastName);
                    break;
                case 3:
                    System.out.println("Enter new telephone number: ");
                    int newTel = scanner.nextInt();
                    buyers.get(numberBuyer - 1).setTel(newTel);
                    break;
                case 4:
                    System.out.println("Enter new amount of money: ");
                    int newMoney = scanner.nextInt();
                    buyers.get(numberBuyer - 1).setMoney(newMoney);
                    break;
            }
        } while ("yes".equals(repeat));
        
    }

    private void incomePerMonth() {
        double income = 0;
        System.out.println("Pick what year of income do you want to see: ");
        int years = getNumber();
        System.out.println("Pick what month of the year: ");
        List<History> histories = historyFacade.findAll();
        int chosenMonth = getNumber() - 1;
        for (int i = 0; i < histories.size(); i++) {
            Date date = histories.get(i).getSoldShoes();
            boolean toSum = summa (date, chosenMonth, years);
            if (histories.get(i) != null & toSum) {
                income += histories.get(i).getModel().getPrice();
            }
        }
        System.out.println("Income for the month that you picked");
        System.out.println(income +" Euros");
    }

    private boolean summa(Date date, int chosenMonth, int years) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        if (month == chosenMonth & year == years) {
            return true;
        }else{
            return false;
        }
    }

    
}
