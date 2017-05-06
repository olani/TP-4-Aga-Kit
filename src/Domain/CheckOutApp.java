package Domain;

import java.util.Scanner;

public class CheckOutApp {
	private static CheckOutControl checkOut;
	private static Patron currentPatron;
	private static Scanner scan = new Scanner(System.in);

	public void doCheckOut() {
		System.out.println("Please enter Patron Id for CheckOut");
		
		String pID = scan.nextLine();
		
		try {
			checkOut = new CheckOutControl();
			checkOut.startCheckOut(pID);
			currentPatron = checkOut.getCurrentPatron();
			System.out.println("Check out Strarted for Patron Id "+ pID +"\n");
			enterCopyForCheckOUt();
		} catch (UnknownPatron e) {
			System.out.println("Patron Id "+ pID + " is Invalid ID\n");
		} catch (AccountHasHold e){
			System.out.println("Check out denied, Patron Id "+ pID + " has hold on account.\n");
		}
		
		if(currentPatron!=null){
			System.out.println("Check out completed");
			System.out.println("\n"+checkOut.receipt());
			checkOut.completeCheckOut();
			checkOut = null;
			currentPatron=null;
		}
		
		
	}

	public void enterCopyForCheckOUt() {
		System.out.println("Enter copyID to check out. Enter 0, to complete check out");
		String copyId = scan.nextLine();
		while (!copyId.equals("0")){
			try {
				checkOut.addForCheckOut(copyId);
				System.out.println("adding copyId for checkOut " + copyId);
				System.out.println("\n"+checkOut.receipt());
			} catch (UnknownCopy e){
				System.out.println(copyId + " is Unknown copy, try another copy");
			} catch(CopyAlreadyCheckedOut e){
				System.out.println(copyId + " is NOT added for checkout. Copy is already out to another Patron");
			}
			System.out.println("Enter copyID to check out. Enter 0, to complete check out");
			copyId = scan.nextLine();
		}
	}
	
	
}
