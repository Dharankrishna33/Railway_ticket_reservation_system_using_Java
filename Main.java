package train_project;
import java.util.*;
public class Main {
	static Scanner sc=new Scanner(System.in);
	
	static void bookTicket1(Passengers p) {
		
		Ticket_booking booking=new Ticket_booking();
		
		if(Ticket_booking.availableWl==0) {
			
			System.out.println("No tickets Available");
		}
		
		
		else if(p.birthPreference.equals("U")&& Ticket_booking.availableUpperBirth>0||p.birthPreference.equals("L")&&Ticket_booking.availableLowerBirth>0||
				p.birthPreference.equals("M")&&Ticket_booking.availableMiddleBirth>0) {
			
			if(p.allotedBirth!="RAC") 
				System.out.println("Your preference is available");
			
			if(p.birthPreference.equals("U")) {
				
				System.out.println("UpperBirth is given");
				booking.ticketBooking(p,Ticket_booking.upperBirthPosition.get(0),"U");
				
				Ticket_booking.availableUpperBirth--;
				Ticket_booking.upperBirthPosition.remove(0);
				
			}
			
			if(p.birthPreference.equals("M")) {
				
				System.out.println("MiddleBirth is given");
				booking.ticketBooking(p,Ticket_booking.middleBirthPosition.get(0),"M");
				
				Ticket_booking.availableMiddleBirth--;
				Ticket_booking.middleBirthPosition.remove(0);
			}
			
			if(p.birthPreference.equals("L")) {
				
				System.out.println("LowerBirth is given");
				booking.ticketBooking(p,Ticket_booking.lowerBirthPosition.get(0),"L");
				
				Ticket_booking.availableLowerBirth--;
				Ticket_booking.lowerBirthPosition.remove(0);
			}
			
		}
		
		else if(Ticket_booking.availableLowerBirth>0) {
			System.out.println("LowerBirth is given");
			booking.ticketBooking(p,Ticket_booking.lowerBirthPosition.get(0),"L");
			
			Ticket_booking.availableLowerBirth--;
			Ticket_booking.lowerBirthPosition.remove(0);
		}
		
		else if(Ticket_booking.availableUpperBirth>0) {
			System.out.println("UpperBirth is given");
			booking.ticketBooking(p,Ticket_booking.upperBirthPosition.get(0),"U");
			
			Ticket_booking.availableUpperBirth--;
			Ticket_booking.upperBirthPosition.remove(0);
		}
		
		else if(Ticket_booking.availableMiddleBirth>0) {
			System.out.println("MiddleBirth is given");
			booking.ticketBooking(p,Ticket_booking.middleBirthPosition.get(0),"M");
			
			Ticket_booking.availableMiddleBirth--;
			Ticket_booking.middleBirthPosition.remove(0);
		}
		
		else if(Ticket_booking.availableRac>0) {
			booking.racBooking(p,Ticket_booking.racPosition.get(0),"RAC");
			
			Ticket_booking.availableRac--;
			Ticket_booking.racPosition.remove(0);
		}
		
		else if(Ticket_booking.availableWl>0) {
			booking.wlBooking(p,Ticket_booking.wlPosition.get(0),"WL");
			
			Ticket_booking.availableWl--;
			Ticket_booking.wlPosition.remove(0);
		}
	}
	
	public static void cancel() {
		
		System.out.println("Enter your passenger id:");
		int check=sc.nextInt();
		
		if(!Ticket_booking.commonList.containsKey(check)) {
			System.out.println("Invalid id");
		}
		else {
			Ticket_booking.cancelTicket(Ticket_booking.commonList.get(check));
		}
	}
	

	public static void main(String[] args) {
		
		
		boolean loop=true;
		
		while(loop) {
			System.out.println("1.Book Tickets\n2.See Availability\n3.Cancel Ticket\n4.See Passengers List\n5.Exit");
			int choice=sc.nextInt();
			
			switch(choice) {
			case 1:
				
				Passengers passenger=new Passengers();
				bookTicket1(passenger);
				System.out.println();
				break;
				
			case 2:
				
				Ticket_booking booking =new Ticket_booking();
				booking.displayAvailable();
				break;
				
			case 3:
				
				cancel();
				break;
				
			case 4:
				
				for(Passengers p: Ticket_booking.commonList.values()) {
					
					System.out.println("Passenger Name :"+p.passengerName);
					System.out.println("Passenger id :"+p.passengerId);
					System.out.println("Berth/RAC/WL :"+p.allotedBirth);
					System.out.println("Berth no :"+p.berthNumber);
				}
				System.out.println("------------------------------------------------------------------------------------------------------------");
				break;
				
			case 5:
				System.out.println("------------------------------------------------------------------------------------------------------------");
				loop=false;
				break;
			}
		}
		
	}

}
