package Domain;

import java.util.Scanner;

import Domain.CheckInControl.CopyHasNotBeedCheckedOut;

public class CheckInApp {

	private static CheckInControl checkIn;
	private static Patron currentPatron;

	
	public void doCheckIn(Scanner scan) {
		System.out.println("Please enter Patron Id for CheckIn");
		
		String pID = scan.nextLine();
		
		try {
			startTransaction(pID);
			System.out.println("Check In Strarted for Patron Id "+ pID +"\n");
			enterCopyForCheckIn(scan);
		} catch (UnknownPatron e) {
			System.out.println("Patron Id "+ pID + " is Invalid ID\n");
		}
		
		completeCheckOut();		
	}
	
	
	private void startTransaction(String pID) {
		checkIn = new CheckInControl();
		checkIn.startCheckIn(pID);
		currentPatron = checkIn.getCurrentPatron();
	}
	
	
	private void completeCheckOut(){
		if(currentPatron!=null){
			System.out.println("Check in completed");
			System.out.println("\n"+checkIn.receipt());
			checkIn.completeCheckIn();
			checkIn = null;
			currentPatron=null;
		}
	}
	
	
	public void enterCopyForCheckIn(Scanner scan) {
		System.out.println("Enter copyID to check in. Enter 0, to complete check in");
		String copyId = scan.nextLine();
		while (!copyId.equals("0")){
			try {
				checkIn.addForCheckIn(copyId);
				System.out.println("adding copyId for check in " + copyId);
				System.out.println("\n"+checkIn.receipt());
			} catch (UnknownCopy e){
				System.out.println(copyId + " is Unknown copy, try another copy");
			} catch (CopyHasNotBeedCheckedOut e){
				System.out.println(copyId + " can NOT be checked. Copy was not checked out.");
			}
			System.out.println("Enter copyID to check in. Enter 0, to complete check in");
			copyId = scan.nextLine();
		}
	}

}
