import java.util.*;
import java.text.*;

/**
   This program allows a user to order pizza(s)
*/

public class PizzaOrder 
{
	public static void main(String [] args){		
		Scanner keyboard = new Scanner(System.in);	//Create a Scanner object to read input
		boolean discount = false;     		// flag, true if user is eligible for discount		
		char choice;                  		// user's choice
		String input;                 		// user input		   
		String[] orders = new String[10];	// full info about order(s)
		int numOfOrders = 0;				// number of pizzas ordered

		// Welcome message
		System.out.println("====================================");
		System.out.println("Welcome to \"Eat&Chat\" Pizza Order!");
		System.out.println("====================================");
		
		System.out.print("Today is: ");
		printCurrentDate();		// prints current date (today)
		System.out.println();
		System.out.print("> Is it your BIRTHDAY? (10% discount available on presenting ID)  (Y/N):  ");
		input = keyboard.nextLine();

      	// Determine if user is eligible for discount by having birthday today
      	if (input.equals("y") || input.equals("Y"))
      		discount = true;	// Convert discount to true if the answer is yes

		orders[numOfOrders++] = orderPizza();	// get first order
		previewOrder(orders);	// view order info

      	// Keep displaying the menu options until user is done
		do { // Prints orders until the maximum (10) or until command 1 or 3 is pressed
		printMenu();	// print action menu options

		input = keyboard.nextLine();
		choice = input.charAt(0);
		
		switch(choice){

			// Complete order
			case '1': 
				confirmOrder(orders, discount);			// complete order
				System.exit(0);

			// Add another pizza
			case '2':					
				orders[numOfOrders++] = orderPizza();	// save new order
				previewOrder(orders);					// view order info
				break;

			// Exit
			case '3': 
				System.out.println("Good bye!");
				System.exit(0);							// exit program

			default: 
				System.exit(0);
		}
	}while (numOfOrders < 10);
		
	}

	/**
	Prints the action menu
	*/
	public static void printMenu(){		
		//prompt user for the next operation
		System.out.println("------------MENU-------------");
		System.out.println("1 - Complete");
		System.out.println("2 - Add another order");
		System.out.println("3 - Exit"); 
		System.out.print("> Choose one of the above (Enter a number): ");
	}

	/**
	The method is used to order one single pizza.
	Gets user preferences, saves all the detailed info as one String, and returns it.
	*/
	public static String orderPizza(){ 
		Scanner keyboard = new Scanner(System.in);
		String input;                 	//user input
		char choice;                  	//user's choice
		int size;                   	//size of the pizza 
		int cost = 0;          			//cost of the pizza
		int numberOfToppings = 0;     	//number of toppings
		String toppings = "Cheese";  	//list of toppings
		String result = "";				// resultant String object to be returned
		final int toppingCost = 200;	// cost for one topping

		//prompt user and get pizza size choice
		System.out.println("-----------------------------");
		System.out.println("Pizza Size (cm)      Cost");
		System.out.println("       20            1000 T");
		System.out.println("       30            1500 T");
		System.out.println("       40            2000 T");
		System.out.println("What size pizza would you like?"); 
		System.out.print("> 20, 30, or 40 (enter the number only): ");
		size = keyboard.nextInt();

		// Set the price based on the size of pizza ordered
		if (size == 20) // Costs given depending on the size
			cost = 1000;
		else if (size == 30)
			cost = 1500;
		else if (size == 40)
			cost = 2000;
		    
		//consume the remaining newline character
		keyboard.nextLine(); 
		                
		//prompt user and get topping choices one at a time 
		System.out.println("-----------------------------");              
		System.out.println("All pizzas come with cheese."); 
		System.out.println("Additional toppings are 200T each," + " choose from:");
		System.out.println("- Meat, Sausage, Vegetables, Mushroom");

		//if topping is desired, add to topping list and number of toppings
		System.out.print("> Do you want Meat?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Meat";
		}
		System.out.print("> Do you want Sausage?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Sausage";
		}
		System.out.print("> Do you want Vegetables?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Vegetables";
		}
		System.out.print("> Do you want Mushroom?  (Y/N):  ");
		input = keyboard.nextLine();
		choice = input.charAt(0);
		if (choice == 'Y' || choice == 'y')
		{
			numberOfToppings += 1;
			toppings = toppings + " + Mushroom";
		}

		// Add additional toppings cost to cost of pizza
		cost += toppingCost * numberOfToppings; // Additional cost

		//save the order information
		result += size + "cm pizza, " + toppings;
		// add the cost to result
		result += ":" + cost;
		return result;
   	}

	/**
	Processes the orders array, prints full info about each order and the total cost at the end
	*/
	public static void previewOrder(String[] orders){
		System.out.println("-----------------------------");
		System.out.println("Your order: ");

		// Print individual order info
		for (int i = 0; i < orders.length; i++) {
			if (orders[i] != null) // Prints only given order, if not given it will print null
				System.out.println((i + 1) + ") " + orders[i]);
		}
		
		// print total cost
		System.out.println("Total: " + getTotalCost(orders) + " T");
	}

	/**
	Parses the orders array to calculate the total cost 
	*/
	public static int getTotalCost(String[] orders){
		// return total cost
		int cost = 0;
		int totalCost = 0;
		for (int i = 0; i < orders.length; i++) {
			if (orders[i] != null) { // Calculates cost only of given orders(the basic case of String is null, just like 0 with int)
				cost = Integer.parseInt(orders[i].substring(orders[i].length() - 4)); // Parsing the 4-digit number cost
				totalCost += cost; // Adding cost to the totalCost
			}
		}
		return totalCost; // Return the totalCost
	}

	/**
	Order confirmation: prints full order info, calculates discount (if applicable), 
	and displays other details like date, time and order ID
	*/
	public static void confirmOrder(String[] orders, boolean discount){
		final int DISCOUNT_AMOUNT = 10;	// discount amount in percentage

		//display order confirmation
		System.out.println("#############################################");
		previewOrder(orders);

		// calculate total cost
		int cost = getTotalCost(orders);

		// Apply discount only if user is eligible
		// update and print the cost with discount
		if (discount) { // This applies only if the user is eligible for discount
			System.out.println("-----------------------------");
			System.out.println("TOTAL with DISCOUNT (on presenting ID only!):");
			System.out.println((int)(cost * (1 - (DISCOUNT_AMOUNT/100.0))) + " T"); // Equivalent to 0.9 * cost or 90% of the cost
		}
		
		System.out.println("-----------------------------");
		System.out.println("Your order will be ready for pickup in 30 minutes.");

		System.out.print("Date: ");
		printCurrentDate();				// prints current date

		System.out.print("\tTime: ");
		printCurrentTime();				// prints current time
		System.out.println();

		System.out.println("Order ID: " + generateCode());	// generates random ID
	}

	/**
	Prints the current system date in DD.MM.YYYY format
	*/
	public static void printCurrentDate(){
		// print current date 
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy"); // From site
		Date date = new Date(System.currentTimeMillis());
		System.out.print(formatter.format(date));  
	} 

	/**
	Prints the current system time in HH:MM format
	*/
	public static void printCurrentTime(){
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm"); // From site
		Date date = new Date(System.currentTimeMillis());
		System.out.print(formatter.format(date));
	}

	/**
	Generates a random 4-digit number and returns as a String consisting of 4 digits fills with leading zeros if necessary
	Ex: "1097", "0083"
	*/
	public static String generateCode(){ // Generating 4 random numbers and combining it into a 4-digit number String
		// return 4-digit random code
		int a = (int)(Math.random() * 10);
		int b = (int)(Math.random() * 10);
		int c = (int)(Math.random() * 10);
		int d = (int)(Math.random() * 10);
		String id = a + "" + b + "" + c + "" + d + "";
		return id;
	}
}
