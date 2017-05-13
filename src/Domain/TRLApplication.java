package Domain;

import java.util.Scanner;

public class TRLApplication{
	private static CheckInApp checkIn = new CheckInApp();
	private static CheckOutApp checkOut;
	private static SaleApp saleApp;
	private static Scanner scan = new Scanner(System.in);
	public static void main(String[] args){
		
		System.out.println("Welcome to TRLApplication.");
		System.out.println("Below are the list of Patrons and Copies available for your convinience.\n");
		System.out.println("Patrons availabled:");
		System.out.println(PatronDataStore.patronIds());
		System.out.println("\nCopies availabled:");
		System.out.println(CopyDataStore.copyIds());
		System.out.println();
		
		boolean quit = false;
		do{
			displayMenu();
			String transactionChoice = scan.nextLine();
			switch (transactionChoice)
			{
			case "1":
				checkOut = new CheckOutApp();
				checkOut.doCheckOut(scan);
				break;
			case "2":
				checkIn = new CheckInApp();
				checkIn.doCheckIn();
				break;
			case "3":
				saleApp = new SaleApp();
				saleApp.doSale();
				break;
			case "0":
				System.out.println("Exiting");
				quit = true;
				break;
			}
		}while(!quit);
		
		System.out.println("\nPrinting, all transaction logs");
		System.out.println(TransactionLogs.allLogs());
	}
	
	public static void displayAvailablePatrons(){
		
	}
	private static void displayMenu(){
		System.out.println("Choose one of the below options to continue");
		System.out.println("1 to start check out");
		System.out.println("2 to start check in");
		System.out.println("3 to start Sale");
		System.out.println("0 to close the System\n");
		
	}	
}
