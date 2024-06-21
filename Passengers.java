package train_project;
import java.util.*;
public class Passengers {
	
	static int id=1;
	int passengerId;
	String passengerName;
	int age;
	String birthPreference;
	String allotedBirth;
	int berthNumber;
	
	
	Passengers(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your name :");
		this.passengerName=sc.nextLine();
		System.out.println("Enter your age :");
		this.age=sc.nextInt();
		System.out.print("Enter your Birth Preference :(U,M,L)");
		this.birthPreference=sc.next();
		this.allotedBirth="";
		this.berthNumber=0;
		this.passengerId=id++;
	}
}
