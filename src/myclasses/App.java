/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myclasses;

import entity.Buyer;
import entity.History;
import entity.Model;
import entity.Shop;
import interfaces.Keeping;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import tools.SaverToBase;
import tools.SaverToFiles;

/**
 *
 * @author User
 */
public class App {
    public static boolean toFile = false;
    private Scanner scanner = new Scanner(System.in);
    private List<Model> models = new ArrayList<>();
    private List<Buyer> buyers = new ArrayList<>();
    private List<History> histories = new ArrayList<>();
    private Shop shop = new Shop();
    private Keeping keeper = new SaverToBase();
    
    public App(){
        if (toFile) {
            keeper = new SaverToFiles();
        }else{
            keeper = new SaverToBase();
        }
            
        models = keeper.loadModels();
        buyers = keeper.loadBuyers();
        histories = keeper.loadHistories();
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
            System.out.println("6: Shops income all time");
            System.out.println("7: Add money to buyer");
            System.out.println("8: Change the model");
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
                    shopIncome();
                    break;
                case 7:
                    addMoney();
                    break;
                case 8:
                    changeModel();
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
        models.add(model);
        keeper.saveModels(models);
        
    }

    private void listModel() {
        System.out.println("List of models that are in stock");
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i) != null 
                    && models.get(i).getCount() > 0
                    && models.get(i).getCount() < models.get(i).getQuantity() + 1) {
                System.out.printf("%d. Brand: %s Name: %s Size: %d Price: %d Qauantity: %d.%n"
                        ,i+1
                        ,models.get(i).getBrand()
                        ,models.get(i).getModelName()
                        ,models.get(i).getSize()
                        ,models.get(i).getPrice()
                        ,models.get(i).getQuantity()
                );
            }
        }
    }

    private void addBuyer() {
        Buyer buyer = new Buyer();
        System.out.println("First name of the buyer: ");
        buyer.setFirstName(scanner.nextLine());
        System.out.println("Last name of the buyer: ");
        buyer.setLastName(scanner.nextLine());
        System.out.println("Phone number of the buyer: ");
        buyer.setTel(scanner.nextInt());
        buyer.setMoney(0);
        System.out.println("Buyer: "+buyer.toString());
        buyers.add(buyer);
        keeper.saveBuyers(buyers);
    }

    private void listBuyer() {
        System.out.println("List of registered buyers");
        for (int i = 0; i < buyers.size(); i++) {
            if (buyers.get(i) != null) {
                System.out.printf("%d. %s %s Tel: %d  Money: %d%n"
                        ,i+1
                        ,buyers.get(i).getFirstName()
                        ,buyers.get(i).getLastName()
                        ,buyers.get(i).getTel()
                        ,buyers.get(i).getMoney()
                );
            }
        }
    }

    private void soldShoe() {
        System.out.println("----------- Sell ----------------");
        System.out.println("The List of purchaseable shoes");
        int n = 0;
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i) != null && models.get(i).getCount() > 0) {
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
        if (n < 1) {
            System.out.println("No shoes in stock");
            return;
        }
        System.out.println("Choose what shoes do you want to sell");
        int numberModel = scanner.nextInt();scanner.nextLine();
        System.out.println("List of buyers");
        for (int i = 0; i < buyers.size(); i++) {
            if (buyers.get(i) != null) {
                System.out.printf("%d. %s %s. Tel: %d Money: %d%n"
                        ,i+1
                        ,buyers.get(i).getFirstName()
                        ,buyers.get(i).getLastName()
                        ,buyers.get(i).getTel()
                        ,buyers.get(i).getMoney()
                );
            }
        }
        System.out.println("Choose who do you want to sell it to");
        int numberBuyer = scanner.nextInt();scanner.nextLine();
        History history = new History();
        history.setModel(models.get(numberModel - 1));
        history.setBuyer(buyers.get(numberBuyer - 1));
        Calendar c = new GregorianCalendar();
        history.setSoldShoes(c.getTime());
        history.getModel().setCount(history.getModel().getCount() - 1);
        histories.add(history);
        for (int i = 0; i < histories.size(); i++) {
            if (histories.get(i) != null) {
                System.out.printf("%d. %s %s bought %s for %d euros, on %s.%n"
                        ,i+1
                        ,histories.get(i).getBuyer().getFirstName()
                        ,histories.get(i).getBuyer().getLastName()
                        ,histories.get(i).getModel().getModelName()
                        ,histories.get(i).getModel().getPrice()
                        ,histories.get(i).getSoldShoes()
                );
            }
            shop.setIncome(models.get(numberModel - 1).getPrice()+shop.getIncome());
        }
        keeper.saveModels(models);
        keeper.saveHistories(histories);
        System.out.println("----------------");
    }

    private void shopIncome() {
        System.out.println("Income of the shop");
        System.out.printf("%d euros.%n"
                ,shop.getIncome()
        );
        
    }

    private void addMoney() {
        System.out.println("List of registered buyers");
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

    private void changeModel() {
        System.out.println("Choose what shoes do you want to change");
        int numberModel = scanner.nextInt();scanner.nextLine();
        System.out.println("The List of purchaseable shoes");
        int n = 0;
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i) != null && models.get(i).getCount() > 0) {
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
        
    }
    
}
