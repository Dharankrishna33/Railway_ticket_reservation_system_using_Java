package train_project;
import java.util.*;
public class Ticket_booking {
	
	  static int availableUpperBirth=1;
	  static int availableLowerBirth=1;
	  static int availableMiddleBirth=1;
	  static int availableRac=1;
	  static int availableWl=1;
	  
	  static ArrayList<Integer> allotedTicketsList=new ArrayList<>();
	  static Queue<Integer>racList=new LinkedList<>();
	  static Queue<Integer>wlList=new LinkedList<>();
	  
	  
	  static ArrayList<Integer>upperBirthPosition =new ArrayList<>(Arrays.asList(1));
	  static ArrayList<Integer>lowerBirthPosition =new ArrayList<>(Arrays.asList(1));
	  static ArrayList<Integer>middleBirthPosition =new ArrayList<>(Arrays.asList(1));
	  static ArrayList<Integer>wlPosition =new ArrayList<>(Arrays.asList(1));
	  static ArrayList<Integer>racPosition =new ArrayList<>(Arrays.asList(1));
	  
	  static LinkedHashMap<Integer,Passengers> commonList=new LinkedHashMap<>();
	  
	  
	  public void ticketBooking(Passengers p, int position ,String birth) {
		  
		  p.allotedBirth=birth;
		  p.berthNumber=position;
		  
		  allotedTicketsList.add(p.passengerId);
		  
		  commonList.put(p.passengerId, p);
		  System.out.println("Your Passenger Id is "+p.passengerId);
		  System.out.println("Ticket booked successfully.......................");
		  System.out.println("------------------------------------------------------------------------------------------------------------");
	  }
	  
      public void racBooking(Passengers p, int position ,String birth) {
		  
		  p.allotedBirth=birth;
		  p.berthNumber=position;
		  
		  racList.offer(p.passengerId);
		  
		  commonList.put(p.passengerId, p);
		  
		  System.out.println("Your Passenger Id is "+p.passengerId);
		  System.out.println("RAC given.......................");
		  System.out.println("------------------------------------------------------------------------------------------------------------");
	  }


      public void wlBooking(Passengers p, int position ,String birth) {
	  
		  p.allotedBirth=birth;
		  p.berthNumber=position;
		  
		  wlList.offer(p.passengerId);
		  
		  commonList.put(p.passengerId, p);
		  
		  System.out.println("Your Passenger Id is "+p.passengerId);
		  System.out.println("You are on the Waiting list.......................");
		  System.out.println("------------------------------------------------------------------------------------------------------------");
      }
      
      static public void cancelTicket(Passengers p) {
    	  
    	  allotedTicketsList.remove(Integer.valueOf(p.passengerId));
    	  commonList.remove(Integer.valueOf(p.passengerId));
    	  
    	  int cancelled_berthNumber=p.berthNumber;
    	  
    	  if(p.allotedBirth.equals("L")) 
          { 
            availableLowerBirth++;
            lowerBirthPosition.add(cancelled_berthNumber);
          }
          else if(p.allotedBirth.equals("M"))
          { 
            availableMiddleBirth++;
            middleBirthPosition.add(cancelled_berthNumber);
          }
          else if(p.allotedBirth.equals("U"))
          { 
            availableUpperBirth++;
            upperBirthPosition.add(cancelled_berthNumber);
            
          }
    	  
    	  if(racList.size()>0)
          {
    		  System.out.println(racList.size());
              Passengers passengerFromRAC = commonList.get(racList.poll());
              int positionRac = passengerFromRAC.berthNumber;
              racPosition.add(positionRac);
              racList.remove(Integer.valueOf(passengerFromRAC.passengerId));
              availableRac++;
              
              

              //check if any WL is there
              if(wlList.size()>0)
              {
                  //take the passenger from WL and add them to RAC , increase the free space in waiting list and 
                  //increase available WL and decrease available RAC by 1
              	
              	
                  Passengers passengerFromWaitingList = commonList.get(wlList.poll());
                  int positionWL = passengerFromWaitingList.berthNumber;
                  wlPosition.add(positionWL);
                  wlList.remove(Integer.valueOf(passengerFromWaitingList.passengerId));

                  passengerFromWaitingList.berthNumber = racPosition.get(0);
                  passengerFromWaitingList.allotedBirth = "RAC";
                  racPosition.remove(0);
                  racList.add(passengerFromWaitingList.passengerId);
                  
                  availableWl++;
                  availableRac--;
              }
              // now we have a passenger from RAc to whom we can book a ticket, 
              //so book the cancelled ticket to the RAC passenger
              
              System.out.println("Moving the First RAC passenger to the Normal Passenger list");
              Main.bookTicket1(passengerFromRAC);
          }
    	  
    	  System.out.println("------------------------------------------------------------------------------------------------------------");
      }
      
      public void displayAvailable() {
    	  
    	  System.out.println("Available UpperBerth :"+availableUpperBirth);
    	  System.out.println("Available LowerBerth :"+availableLowerBirth);
    	  System.out.println("Available MiddleBerth :"+availableMiddleBirth);
    	  System.out.println("Available RAC :"+availableRac);
    	  System.out.println("Available Wlist :"+availableWl);
    	  System.out.println("------------------------------------------------------------------------------------------------------------");
      }
      
}
