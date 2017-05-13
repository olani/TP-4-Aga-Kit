package Domain;

import java.util.Scanner;

public class SaleApp {

	private static SaleControl sale;
	private static Patron currentPatron;

	
	public  void doSale(Scanner scan){
		System.out.println("Please enter Patron Id for Sale");
		
		String pID = scan.nextLine();
		
		try {
			startTransaction(pID);
			System.out.println("Check In Strarted for Patron Id "+ pID +"\n");
			enterCopyForSale(scan);
		} catch (Domain.UnknownPatron e) {
			System.out.println("Patron Id "+ pID + " is Invalid ID\n");
		}
		completeSale();
		
	}
	private void startTransaction(String pID) {
		sale = new SaleControl();
		sale.startSale(pID);
		currentPatron = sale.getCurrentPatron();
	}
	private void completeSale(){
		if(currentPatron!=null){
			System.out.println("Sale completed");
			System.out.println("\n"+sale.receipt());
			sale.completeSale();
			sale = null;
			currentPatron=null;
		}
	}
	
	public void enterCopyForSale(Scanner scan) {
		System.out.println("Enter copyID to sale. Enter 0, to complete sale");
		String copyId = scan.nextLine();
		while (!copyId.equals("0")){
			try {
				sale.addCopyForSale(copyId);
				System.out.println("adding copyId for sale " + copyId);
				System.out.println("\n"+sale.receipt());
			} catch (UnknownCopy e){
				System.out.println(copyId + " is Unknown copy, try another copy");
			} catch (Domain.SaleControl.CopyNotForSale e){
				System.out.println(copyId + " is not a copy for Sale, try another copy");
			}
			
			System.out.println("Enter copyID to sale. Enter 0, to complete sale");
			copyId = scan.nextLine();
		}
	}

	
}
