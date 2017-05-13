package Domain;

import java.util.Scanner;

public class TRLApplication{
	private static CheckInApp checkIn = new CheckInApp();
	private static CheckOutApp checkOut;
	private static SaleApp saleApp;
	private static Scanner scan = new Scanner(System.in);
	public static void main(String[] args){
		
		System.out.println("Welcome to TRLApplication.");
		System.out.println(helpMessage());
		
		startSelectAPP(scan);
		
		System.out.println("\nPrinting, all transaction logs");
		System.out.println(TransactionLogs.allLogs());
	}
	
	
	public static void startSelectAPP(Scanner scan){
		boolean quit = false;
		do{
			System.out.println(menu());
			String transactionChoice = scan.nextLine();
			switch (transactionChoice)
			{
			case "1":
				checkOut = new CheckOutApp();
				checkOut.doCheckOut(scan);
				break;
			case "2":
				checkIn = new CheckInApp();
				checkIn.doCheckIn(scan);
				break;
			case "3":
				saleApp = new SaleApp();
				saleApp.doSale(scan);
				break;
			case "0":
				System.out.println("Exiting");
				quit = true;
				break;
			}
		}while(!quit);
	}
	
	public static String helpMessage(){
		String message = "Below are the list of Patrons and Copies available for your convinience.\n"
				+ "Patrons availabled:\n"
				+ PatronDataStore.patronIds()
				+"\nCopies availabled:\n"
				+ CopyDataStore.copyIds() +"\n";
		return message;
		
	}
	public static String menu(){
		String menuMessage = "Choose one of the below options to continue\n"
				+ "1 to start check out\n"
				+ "2 to start check in\n"
				+ "3 to start Sale\n"
				+ "0 to close the System\n";
		return menuMessage;
		
	}	
}
