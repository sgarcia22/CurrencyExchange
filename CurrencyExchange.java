/*Name: Samantha Garcia
 *Date: October 27, 2016
 *Description: This program implements different methods in order to simplify banking and the conversion of currency.
 *I mainly used switches and added in an array to further make the program easier to read. The boolean that determined
 *whether the program was withdrawing or depositing was what really brought the program together.
 *
 *This was a class project.
 */

import java.util.Scanner;

public class CurrencyExchange {

	//I made a global variable for converting to USD in order to avoid hard-coding the boolean values in the parameters.
	private static boolean convertUSD = false;
	
	//This global variable was made so that when the user would press 4 to exit the program, the logTransaction() method would have a way of knowing what output to produce.
	private static int exitProgram = 0;
	
    private static double balance = 0;

    public static double getBalance() {
    	
        return balance;
        
    }

    private static boolean updateBalance(double newBalance) {

        balance = Math.round(newBalance * 100) / 100.0;
        
        if (balance >= 0) {
        	
            return true;
            
        } 
        else
        	
            return false;
    }

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        printConversionTable();
        
        //I imported the mainMenuOptionSelector() method here in order to take in user input for the required action.
        int userSelection = mainMenuOptionSelector(input);
        
        //I used a while loop so that the loop would terminate once they pressed 4, which signaled a desire to end the program.
        while(!(userSelection == 4)){
        
        //I believe that switches are one of the most useful functions in Java, thus I regularly use them in my projects. 
        //What the switch does here is take in the user input and determine what actions it should carry out accordingly.
        switch (userSelection) {
        
	    case 1: 
	    	
	    	//This simply calls the getBalance() and updateBalance() method so that the user may check how much money is in their account.
	    	updateBalance(getBalance());
	    	System.out.println("Your current balance is: " + balance);
	    	break;
	    	
		case 2: 
			
			//Here I take in their desired deposit amount and currency and put it through the deposit() method.
			//The deposit method determines if the amount is correct, and later directs it to the logTransaction() method.
			int currencyType = currencyMenuOptionSelector(input);
			if (currencyType != 1){
				
				convertUSD = true;
				
			}
			System.out.println("Please enter the deposit amount: ");
			double depositAmount = input.nextDouble();
			deposit(depositAmount, currencyType);
			break;
			
		case 3: 
			
			int currencyType2 = currencyMenuOptionSelector(input);
			if (currencyType2 != 1){
				
				convertUSD = true;
				
			}
			System.out.println("Please enter the withdraw amount: ");
			double withdrawAmount = input.nextDouble();
			withdraw(withdrawAmount, currencyType2);
			break; 
			
	}
        
        //Since the user wished to stay in the loop, this is put outside of the switch yet within the loop in order to prompt for their response again.
        userSelection = mainMenuOptionSelector(input);
        
    }
        
        //Since the user wishes to exit the program, I made it so the global variable would equal to the if statement in the logTransaction() method that terminates the program.
        exitProgram = 4;
        logTransaction(0, 0, false);
        
    }
    
    //This method determines if the deposit is valid. It is directed to the logTransaction() method that will print a string depending on the results.
    public static boolean deposit(double amount, int currencyType) {
    	
    	//If the user chooses a negative or 0 number it will return a Logging Error.
    	if (amount <= 0) {
    		
    		System.out.println(logTransaction(-1, currencyType, true));
    		return false;
    		
    	}
    	
    	//The following code is for when the deposit succeeds.
    	else {
    	
    		double convertedDeposit = convertCurrency(amount, currencyType, convertUSD);
    		balance += convertedDeposit;
    		System.out.println(logTransaction(amount, currencyType, true));
    		return true;
    	}
    	
    }
  
     //This method determines if the withdraw is valid. It is directed to the logTransaction() method that will print a string depending on the results.
    public static boolean withdraw(double amount, int currencyType) {
    	 
    	//If the user attempts to withdraw more than they have in their account there will be an Insufficient Funds error along with a Logging Error.
        if (convertCurrency(amount, currencyType, false) > balance) {
        	System.out.println("Error: Insufficient funds. ");
        	System.out.println(logTransaction(-1, currencyType, false));
        	return false;
        	
        }
        
        //If the user chooses a negative or 0 number it will return a Logging Error.
        else if (amount <= 0) {
        	System.out.println(logTransaction(-1, currencyType, false));
        	return false;
        }
        
      //The following code is for when the withdrawal succeeds.
        else {
        
			double withdrawAmount = convertCurrency(amount, currencyType, convertUSD);
    		balance -= withdrawAmount;
        	System.out.println(logTransaction(amount, currencyType, false));
        	return true;
        	
        }
        
      }

    //Here is where the currency is converted using a switch statement to determine the currency type.
    //If the boolean is true then the program will be directed to the mathematical function for depositing money,
    //and if false then the mathematical function for withdrawing (including the fee) will be processed instead.
    public static double convertCurrency(double amount, int currencyType, boolean isConvertToUSD) {
    	
    	//Here I declare all of the double values for currencies beforehand in order to simplify the process.
    	double usd = 1.00;
    	double eur = 0.89;
    	double bri = 0.78;
    	double inr = 66.53;
    	double aus = 1.31;
    	double cnd = 1.31;
    	double sid = 1.37;
    	double swf = 0.97;
    	double mar = 4.12;
    	double jpy = 101.64;
    	double cyr = 6.67;
    	
    	
    	if (isConvertToUSD == true) {
	    		
	        switch (currencyType) {
	        
	        case 1:
	        	
	        	amount /= usd;
	        	break;
	        	
	        case 2:
	        	
	        	amount /= eur;
	        	break;
	        	
	        case 3:
	        	
	        	amount /= bri;
	        	break;
	        	
	        case 4:
	        	
	        	amount /= inr;
	        	break;
	        	
	        case 5: 
	        	
	        	amount /= aus;
	        	break;
	        	
	        case 6:
	        	
	        	amount /= cnd;
	        	break;
	        	
	        case 7:
	        	
	        	amount /= sid;
	        	break;
	        	
	        case 8:
	        	
	        	amount /= swf;
	        	break;
	        	
	        case 9:
	        	
	        	amount /= mar;
	        	break;
	        	
	        case 10:
	        	
	        	amount /= jpy;
	        	break;
	        	
	        case 11:
	        	
	        	amount /= cyr;
	        	break;
	        	
	        }
    	}
    	
    	//The withdrawal fee is included in the calculation.
	    if (isConvertToUSD = false) {
	    	
	        switch (currencyType) {
	        
	        case 1:
	        	
	        	amount = (usd)/(usd);
	        	break;
	        	
	        case 2:
	        	
	        	amount = (eur)/(eur*(1+0.005));
	        	break;
	        	
	        case 3:
	        	
	        	amount = (bri)/(bri*(1+0.005));
	        	break;
	        	
	        case 4:
	        	
	        	amount = (inr)/(inr*(1+0.005));
	        	break;
	        	
	        case 5: 
	        	
	        	amount = (aus)/(aus*(1+0.005));
	        	break;
	        	
	        case 6:
	        	
	        	amount = (cnd)/(cnd*(1+0.005));
	        	break;
	        	
	        case 7:
	        	
	        	amount = (sid)/(sid*(1+0.005));
	        	break;
	        	
	        case 8:
	        	
	        	amount = (swf)/(swf*(1+0.005));
	        	break;
	        	
	        case 9:
	        	
	        	amount = (mar)/(mar*(1+0.005));
	        	break;
	        	
	        case 10:
	        	
	        	amount = (jpy)/(jpy*(1+0.005));
	        	break;
	        	
	        case 11: 
	        	
	        	amount = (cyr)/(cyr*(1+0.005));
	        	break;
	        	
	        	}
	    	}
	    
        return amount;
        
    }

    //The logTransaction() method prints out a String that says if the deposit/withdrawal was valid, and if so how much did the user choose to deposit/withdraw.
    private static String logTransaction(double amount, int currencyType, boolean isDeposit) {
    	
    	//I used an array to assign a specific index to the currency String I wished to use.
    	String[] currencyString = new String[11];
    	
    	currencyString [0] = "U.S. Dollars";
    	currencyString [1] = "Euros";
    	currencyString [2] = "British Pounds";
    	currencyString [3] = "Indian Rupees";
    	currencyString [4] = "Australian Dollars";
    	currencyString [5] = "Canadian Dollars";
    	currencyString [6] = "Singapore Dollars";
    	currencyString [7] = "Swiss Francs";
    	currencyString [8] = "Malaysian Riggits";
    	currencyString [9] = "Japanese Yen";
    	currencyString [10] = "Chinese Yuan Renminbi";
    
      if (exitProgram == 4) {
     	   
		  if (balance > 0) {
			   
		   	System.out.println("You successfully withdrew " + (balance) + " U.S. Dollars");
		    	
		  }
		   
		  else {
		 	   
		    System.out.println("Your remaining balance is " + (balance) + " U.S. dollars");
		        	
		 }
		 
		        System.out.println("Goodbye");
		System.exit(0);
        }	
      //If the user decides to put in the value 0 a logging error will occur.
      if (amount <= 0) {
    	  
    	  return "Logging Error";
    	  
      }
      
      //If the user does not select a valid currency type a logging error will occur.
      if (currencyType > 11 || currencyType < 1) {
    	  
    	  return "Logging Error";
    	  
      }

       //If the deposit succeeded, the amount and currency type will be displayed. 
      if (isDeposit == true) {
    	   
    	  updateBalance(getBalance());
    	  return ("You successfully deposited " + amount + " " + currencyString[currencyType - 1]);
        	
        }
       
        //If the withdrawal succeeded, the amount and currency type will be displayed. 
       if (isDeposit == false) {
        	
    	   if (convertCurrency(amount,currencyType, false) <= amount) {
        		
	        	updateBalance(getBalance());
	        	return ("You successfully withdrew " + amount + " " + currencyString[currencyType - 1]);   
	        	
        		
        	}
        	
        	//If the user decided to withdraw more than what was in their balance (or the fee did not let them), an error will occur.
    	   if (convertCurrency(amount,currencyType, false) > amount) {
        		
        		return "Logging Error";
        		
        	}
        	
        else {
        		
	        	updateBalance(getBalance());
	        	return ("You successfully withdrew " + amount + " " + currencyString[currencyType - 1]);   
	        	
        	}
        }
        
        //This was just placed here just in case.
        return "Logging Error";
       
    }

    //Here the currency types are printed out so that the user may choose between the options. 
    private static int currencyMenuOptionSelector(Scanner input) {
        
    	while (true) {

	    	System.out.println("Please select the currency type: ");
	        System.out.println("1. U.S. Dollars ");
	        System.out.println("2. Euros");
	        System.out.println("3. British Pounds ");
	        System.out.println("4. Indian Rupees ");
	        System.out.println("5. Australian Dollars ");
	        System.out.println("6. Canadian Dollars ");
	        System.out.println("7. Singapore Dollars ");
	        System.out.println("8. Swiss Francs ");
	        System.out.println("9. Malaysian Ringgits ");
	        System.out.println("10. Japanese Yen ");
	        System.out.println("11. Chinese Yuan Renminbi");
	        int currencyMenuSelection = input.nextInt();
	        
	        //If they enter anything outside of the range the validation fails and they are forced to pick again.
	        if (currencyMenuSelection > 11 || currencyMenuSelection <= 0) {
	        	
	        	System.out.println("Input failed validation. Please try again. ");
	        	System.out.println("");
	        	//The continue function is used to hop to the beginning of the loop if they enter wrong input outside of the range.
	        	continue;
	        	
	        }
	        
	        return currencyMenuSelection;
	        
       }
    
    }

    //This method takes in the user input for what main menu option they wish to choose. A while loop is used to restate the options if they do not select a correct value within the range.
    private static int mainMenuOptionSelector(Scanner input) {
    	
	    System.out.println("");
	    System.out.println("Please select an option from the list below: ");
	    System.out.println("1. Check the balance of your account ");
	    System.out.println("2. Make a deposit ");
	    System.out.println("3. Withdraw an amount in a specific currency ");
	    System.out.println("4. End your session (and withdraw all remaining currency in U.S. Dollars) ");
    
	    int mainMenuSelection = input.nextInt();
    	
	    while(mainMenuSelection > 4 || mainMenuSelection < 1){
	    	
	    	System.out.println("Input failed validation. Please try again.");
	    	
		    System.out.println("");
		    System.out.println("Please select an option from the list below: ");
		    System.out.println("1. Check the balance of your account ");
		    System.out.println("2. Make a deposit ");
		    System.out.println("3. Withdraw an amount in a specific currency ");
		    System.out.println("4. End your session (and withdraw all remaining currency in U.S. Dollars) ");
	    
	    	mainMenuSelection = input.nextInt();
	    	
	    	
	    }
    	
    	return mainMenuSelection;
    }


    //This prints out the conversion table for currency amounts. 
    private static void printConversionTable() {
    	
	    System.out.println("Welcome to Currency Exchange 2.0");
	    System.out.println("");
	    System.out.println("Current rates are as follows: ");
	    System.out.println("");
	    System.out.println("1 - U.S. Dollar - 1.00");
	    System.out.println("2 - Euro - 0.89");
	    System.out.println("3 - British Pound - 0.78");
	    System.out.println("4 - Indian Rupee - 66.53");
	    System.out.println("5 - Australian Dollar - 1.31");
	    System.out.println("6 - Canadian Dollar - 1.31");
	    System.out.println("7 - Singapore Dollar - 1.37");
	    System.out.println("8 - Swiss Franc - 0.97");
	    System.out.println("9 - Malaysian Ringgit - 4.12");
	    System.out.println("10 - Japanese Yen - 101.64");
	    System.out.println("11 - Chinese Yuan Renminbi - 6.67");
	    
    }
    
}
