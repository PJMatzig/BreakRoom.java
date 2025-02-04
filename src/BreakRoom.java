import java.util.Scanner;

public class BreakRoom {
    public static void main(String[] args){
        //Setting up the two vending machine objects
        VendingMachine v1 = new VendingMachine("KitKat Bar", 1.25,10);
        VendingMachine v2 = new VendingMachine("CocaCola", 2.25,20);
        //Setting up the scanner object for user input
        Scanner keyboardOBJ = new Scanner(System.in);
        //setting up variables used to get input from the user or to process said input
        String userInput="";//the generic string input/processing variable. must be initialized
        int genericIntegerInput;//the generic integer input/processing variable
        double genericDoubleInput=0.0;//the generic double input/processing variable. must be initialized
        //This while loop is why userInput must be initialized to any value other than "5"
        while (!userInput.equals("5")) {
            System.out.printf("\n**Welcome to the Break Room!**\nThere are %1$d vending machines here.\n\n", VendingMachine.machineCount);
            System.out.printf("Vending Machine 1: %1$s. $%2$.2f each. There are %3$d units remaining. Current credit: $%4$.2f.\n",
                    v1.getItemName(), v1.getUnitCost(), v1.getUnitsRemaining(),v1.getCurrentCredit());
            System.out.printf("Vending Machine 2: %1$s. $%2$.2f each. There are %3$d units remaining. Current credit: $%4$.2f.\n",
                    v2.getItemName(), v2.getUnitCost(), v2.getUnitsRemaining(),v2.getCurrentCredit());
            System.out.println("\n***************************************");
            System.out.println("Make a choice from the following menu:");
            System.out.println("1. Insert Coin.");
            System.out.println("2. Coin Return.");
            System.out.println("3. Purchase Item.");
            System.out.println("4. Vendor Menu.");
            System.out.println("5. Exit.");
            System.out.println("***************************************");
            System.out.print("Enter your choice: ");
            userInput=keyboardOBJ.next();
            /*this block attempts to convert the userInput String value into an integer. If it throws an exception,
            this is regarded as invalid input and the menuChoice is set to 0 which triggers the default clause
            in the switch block.
             */
            try{
               genericIntegerInput=Integer.parseInt(userInput);
            }catch(Exception e){
                genericIntegerInput=0;
            }
            //this switch block handles menu choice functionality. only 1 or 2 is valid input for choosing between
            //vending machines. all other input is treated as invalid. this is true for each menu option
            switch(genericIntegerInput){
                case 1: //insert coin option. user must enter a valid option to successfully add credit to a machine.
                    while(true) {
                        System.out.println("You may add credit in denominations of $2.00, $1.00, $0.25, $0.10 " +
                                "or $0.05.");
                        System.out.print("Enter an amount: $");
                        userInput=keyboardOBJ.next();
                        //if the userInput doesn't convert to a double, it's invalid
                        try{
                            genericDoubleInput= Double.parseDouble(userInput);
                        }catch (Exception e){
                            System.out.println("Invalid selection.");
                        }
                        System.out.println();
                        //only values that match a commonly used Canadian coin will allow egress from the loop
                        if (genericDoubleInput == 2.00 || genericDoubleInput == 1.00 || genericDoubleInput == 0.25 ||
                                genericDoubleInput == 0.10 || genericDoubleInput == 0.05) {
                            break;
                        } else {
                            System.out.println("Invalid selection.");
                        }
                    }
                    //the while loop is used to get valid input
                    while(true){
                      System.out.print("Which machine to add credit to, select 1 or 2: ");
                      userInput= keyboardOBJ.next();
                      if (userInput.equals("1")){
                          v1.setCurrentCredit(genericDoubleInput, "add");
                          System.out.println("Credit added.");
                          break;
                      }else if (userInput.equals("2")){
                          v2.setCurrentCredit(genericDoubleInput,"add");
                          System.out.println("Credit added.");
                          break;
                      }else{
                          System.out.println("Invalid selection. Choose 1  or 2.");
                      }
                    }
                    break;
                case 2: //return coin option. returns all currentCredit to the user and resets currentCredit to zero.
                    while(true){
                        System.out.print("What machine to return coins from? Select 1 or 2: ");
                        userInput= keyboardOBJ.next();
                        if (userInput.equals("1") && v1.getCurrentCredit()>0){
                            System.out.println("Change returned: $"+v1.getCurrentCredit());
                            v1.setCurrentCredit(v1.getCurrentCredit(), "subtract");
                            break;
                        }else if (userInput.equals("1") && v1.getCurrentCredit()<=0){
                            System.out.println("No change to return!");
                            break;
                        }else if (userInput.equals("2") && v2.getCurrentCredit()>0){
                            System.out.println("Change returned: "+v2.getCurrentCredit());
                            v2.setCurrentCredit(v2.getCurrentCredit(),"subtract");
                            break;
                        }else if(userInput.equals("2")&&v2.getCurrentCredit()<=0){
                            System.out.println("No change to return!");
                            break;
                        }else{
                            System.out.println("Invalid selection. Choose 1 or 2.");
                        }
                    }
                    break;
                case 3: //purchase item option. successfully purchasing an item reduces the currentCredit instance variable
                        // and increases the totalRevenue instance variable.
                    while(true) {
                        System.out.print("\nPurchasing item. From which machine, (enter 1 or 2): ");
                        userInput = keyboardOBJ.next();
                        if (userInput.equals("1") && v1.getUnitsRemaining()>0 && v1.getCurrentCredit()>=v1.getUnitCost()) {
                            System.out.println("Vending a " + v1.getItemName() + ". Enjoy!");
                            v1.setUnitsRemaining(1, "subtract");
                            v1.setTotalRevenue(v1.getUnitCost(),"add");
                            v1.setCurrentCredit(v1.getUnitCost(), "subtract");
                            break;
                        }else if(userInput.equals("1") && v1.getCurrentCredit()<v1.getUnitCost()) {
                            System.out.println("Error! Not enough credit to purchase a "+v1.getItemName()+". Insert some coins!");
                            break;
                        }else if(userInput.equals("1") && v1.getUnitsRemaining()==0){
                            System.out.println("Unable to vend a "+v1.getItemName()+". Machine is empty. Try again later.");
                            break;
                        } else if (userInput.equals("2") && v2.getUnitsRemaining()>0 && v2.getCurrentCredit()>=v2.getUnitCost()) {
                            System.out.println("Vending a "+v2.getItemName()+". Enjoy!");
                            v2.setUnitsRemaining(1, "subtract");
                            v2.setTotalRevenue(v2.getUnitCost(),"add");
                            v2.setCurrentCredit(v2.getUnitCost(), "subtract");
                            break;
                        } else if(userInput.equals("2") && v2.getCurrentCredit()<v2.getUnitCost()){
                            System.out.println("Error! Not enough credit to purchase a "+v2.getItemName()+". Insert some coins!");
                            break;
                        }else if(userInput.equals("2")&& v2.getUnitsRemaining()==0){
                            System.out.println("Unable to vend a "+v2.getItemName()+". Machine is empty. Try again later.");
                            break;
                        }else {
                            System.out.println("Invalid selection. Choose 1 or 2.");
                        }
                    }
                    break;
                case 4: //vendor menu option.
                    while (genericIntegerInput!=6) {
                        System.out.println("\n********************");
                        System.out.println("Vendor Menu");
                        System.out.println("1. Empty Money.");
                        System.out.println("2. Change Product Amount.");
                        System.out.println("3. Change Product Name.");
                        System.out.println("4. Change Unit Price.");
                        System.out.println("5. Status Report.");
                        System.out.println("6. Exit Vendor Menu.");
                        System.out.println("********************");
                        System.out.print("Enter your choice: ");
                        userInput= keyboardOBJ.next();
                        keyboardOBJ.nextLine();
                        try{ //checking for a proper integer value. value set to 0 if input is bad
                            genericIntegerInput=Integer.parseInt(userInput);
                        }catch(Exception e){
                            genericIntegerInput=0;
                        }
                        switch (genericIntegerInput) {
                            case 1:  //vendor option to empty coin box. resets totalRevenue instance variable to zero
                                while(true){
                                    System.out.print("Which machine to empty, choose 1 or 2: ");
                                    userInput= keyboardOBJ.next();
                                    if (userInput.equals("1")){
                                        System.out.println("Vending machine 1 emptied of $"+v1.getTotalRevenue()
                                                +". Don't spend it all in one place!.");
                                        v1.setTotalRevenue(0.00,"reset");
                                        break;
                                    }else if (userInput.equals("2")){
                                        System.out.println("Vending machine 2 emptied of $"+v2.getTotalRevenue()
                                                +". The love of money is the root of all evil!.");
                                        v2.setTotalRevenue(0.00,"reset");
                                        break;
                                    }else{
                                        System.out.println("Invalid selection. Choose 1  or 2.");
                                    }
                                }
                                break;
                            case 2: //vendor option to change the number of items remaining
                                while(true){
                                    System.out.print("Which machine to change, choose 1 or 2: ");
                                    userInput= keyboardOBJ.next();
                                    if (userInput.equals("1")){
                                        System.out.print("What is the new amount: ");
                                        userInput= keyboardOBJ.next();
                                        try{ //if converting from a string to double throws an exception, the user input
                                            // is treated as invalid and previous value is maintained. the try/catch block
                                            //in option 2 is similar
                                            genericIntegerInput=Integer.parseInt(userInput);
                                        }catch(Exception e){
                                            System.out.println("Invalid input. Amount automatically set to "
                                                    +v1.getUnitsRemaining()+". Update if needed.");
                                            genericIntegerInput=v1.getUnitsRemaining();
                                        }
                                        v1.setUnitsRemaining(genericIntegerInput, "reset");
                                        System.out.println("Vending machine 1 inventory is now: "+v1.getUnitsRemaining()+".");
                                        break;
                                    }else if (userInput.equals("2")){
                                        System.out.print("What is the new amount: ");
                                        keyboardOBJ.nextLine();
                                        userInput= keyboardOBJ.nextLine();
                                        try{
                                            genericIntegerInput=Integer.parseInt(userInput);
                                        }catch(Exception e){
                                            System.out.println("Invalid input. Amount automatically set to "
                                                    +v2.getUnitsRemaining()+" . Update if needed.");
                                            genericIntegerInput=v2.getUnitsRemaining();
                                        }
                                        v2.setUnitsRemaining(genericIntegerInput, "reset");
                                        System.out.println("Vending machine 2 inventory is now: "+v2.getUnitsRemaining()+".");
                                        break;
                                    }else{
                                        System.out.println("Invalid selection. Choose 1  or 2.");
                                    }
                                }
                                break;
                            case 3: //vendor option to change item name. will accept ANY valid String input as a name
                                while(true){
                                    System.out.print("Change product name in which machine, choose 1 or 2: ");
                                    userInput= keyboardOBJ.next();
                                    keyboardOBJ.nextLine();
                                    if (userInput.equals("1")){
                                        System.out.print("What is the new product name: ");
                                        userInput= keyboardOBJ.nextLine();
                                        v1.setItemName(userInput);
                                        System.out.println("Vending machine 1 product name updated. It is now: "+v1.getItemName());
                                        System.out.println("Don't forget to update the inventory amount and unit price.");
                                        break;
                                    }else if (userInput.equals("2")){
                                        System.out.print("What is the new product name: ");
                                        userInput= keyboardOBJ.nextLine();
                                        v2.setItemName(userInput);
                                        System.out.println("Vending machine 2 product name updated. It is now: "+v2.getItemName());
                                        System.out.println("Don't forget to update the inventory amount and unit price.");
                                        break;
                                    }else{
                                        System.out.println("Invalid selection. Choose 1  or 2.");
                                        }
                                }
                                break;
                            case 4: //vendor option to change unit price. Only 1 and 2 are valid entries
                                while(true){
                                    System.out.print("Change unit price in which machine, choose 1 or 2: ");
                                    userInput= keyboardOBJ.next();
                                    keyboardOBJ.nextLine();
                                    if (userInput.equals("1")){ //code for option 1
                                        System.out.print("What is the new unit price: $");
                                        userInput= keyboardOBJ.nextLine();
                                        try { //this code attempts to convert the userInput String to a double
                                            // (genericDoubleInput). If unsuccessful, input treated as invalid and the
                                            //previous value is maintained. try/catch block for option 2 is similar
                                            genericDoubleInput=Double.parseDouble(userInput);
                                        }catch (Exception e){
                                            System.out.println("Invalid entry. Unit price automatically set to $"
                                                    +v1.getUnitCost()+". " +"Update if needed.");
                                            genericDoubleInput=v1.getUnitCost();
                                        }
                                        v1.setUnitCost(genericDoubleInput);
                                        System.out.println("Unit price set to: $"+v1.getUnitCost());
                                        break;
                                    }else if (userInput.equals("2")){
                                        System.out.print("What is the new unit price: $");
                                        userInput= keyboardOBJ.nextLine();
                                        try {
                                            genericDoubleInput=Double.parseDouble(userInput);
                                        }catch (Exception e){
                                            System.out.println("Invalid entry. Unit price automatically set to $"
                                                    +v2.getUnitCost()+". Update if needed.");
                                            genericDoubleInput=v2.getUnitCost();
                                        }
                                        v2.setUnitCost(genericDoubleInput);
                                        System.out.println("Unit price set to: $"+v2.getUnitCost());
                                        break;
                                    }else{
                                        System.out.println("Invalid selection. Choose 1 or 2.");
                                    }
                                }
                                break;
                            case 5: //vendor option to display results of .toString() method
                                System.out.println("\nVending Machine Report");
                                System.out.println(v1.toString());
                                System.out.println(v2.toString());
                                break;
                            case 6: // vendor option to exit back to the primary menu
                                System.out.println("Leaving Vendor Menu");
                                break;
                            default:
                                System.out.println("Invalid selection. Enter a number between 1 and 6.");
                        }
                    }
                    break;
                case 5: //exit primary menu to end program
                    System.out.println("Good bye!");
                    break;
                default:
                    System.out.println("Invalid selection. Enter a number between 1 and 5.");

            }
        }
    }
}
