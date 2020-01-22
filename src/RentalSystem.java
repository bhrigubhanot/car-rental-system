import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class RentalSystem {
	static ArrayList<Car> carlist = new ArrayList<Car>();
	static ArrayList<Car> alreadybookedCarList = new ArrayList<Car>();
	static boolean flag = false;
	static boolean flag2 = false;
	static boolean flag3 = false;
	
	
	public static void main(String[] args) {
		carlist.add(new Car("Creta", 5, "Gurgaon", 100, 16,null));
		carlist.add(new Car("Baleno", 5, "Delhi", 100, 12,null));
		carlist.add(new Car("Duster", 5, "Gurgaon", 100, 15,null));
		carlist.add(new Car("Desire", 5, "Chennai", 100, 10,null));
		
		Scanner sc= new Scanner(System.in);

		//customerInfo();
		String next=null;
		do{
			
			//System.out.println("carlist"+carlist);
			
//			if(next.equalsIgnoreCase("Y")) {
				flag = false;
				System.out.println("Do you want to Rent/Drop");
				String option = sc.nextLine();
				//System.out.println("option: "+option);
				if(option.equalsIgnoreCase("Rent")) {
					customerInfo();
				}else if(option.equalsIgnoreCase("Drop")){
					try {
						addCarBackToList();
					}catch(ConcurrentModificationException e) {
						//System.out.println(e.getMessage());
					}
				}else {
					System.out.println("Please choose correct information!");
				}
				
//			}
//			else {
//				break;
//			}
				System.out.println("Serving next customer (Y/N)");
				next = sc.nextLine();
				
		} while(next.equalsIgnoreCase("Y"));
	}
	private static void addCarBackToList() {
		Scanner input= new Scanner(System.in);
		System.out.println("Enter Drop Location where you want to drop: ");
		String dropLoc = input.nextLine();
		System.out.println("Enter Car name which you want to drop: ");
		String carName = input.nextLine();
		
		Iterator itr1=alreadybookedCarList.iterator();  
		flag2 = false;
		while(itr1.hasNext()){  
		    Car st= (Car)itr1.next(); 
		    if(st.getCarName().equalsIgnoreCase(carName) && st.getLocationAvailable().equalsIgnoreCase(dropLoc)) {
		    	flag2 = true;
		    	carlist.add(st);
		    	alreadybookedCarList.remove(st);
		    	System.out.println("Thankyou for choosing us! See you soon.");
		    }
		}
		
		if(flag2 == false) {
			System.out.println("Please Enter correct information!");
		}
	}
	private static void customerInfo() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter Pickup Location: ");
		String pickLoc = sc.nextLine();
		
		System.out.println("Enter Pickup Time in dd/MM/yyyy HH:MM format : ");
		String pickTime = sc.nextLine();
		
		System.out.println("Enter Drop off Time in dd/MM/yyyy HH:MM format : ");
		String dropTime = sc.nextLine();
		
		//System.out.println(" Pickup Location is: "+pickLoc);
		try {
			Date pickTimeConverted =new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(pickTime);
			//System.out.println(" Pickup Time is: "+pickTimeConverted);

			Date dropTimeConverted=new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dropTime);
			//System.out.println(" Pickup Time is: "+dropTimeConverted);
			double timeInHours;
			
			if(pickTimeConverted.compareTo(dropTimeConverted) < 0) {
				long differenceInMS = Math.abs(dropTimeConverted.getTime() - pickTimeConverted.getTime());
				//System.out.println(differenceInMS);
				//timeInHours = TimeUnit.MILLISECONDS.toHours(differenceInMS);
				timeInHours = (differenceInMS / (1000.0 * 60.0 * 60.0));
				System.out.println("duration in hours: "+timeInHours);
				getCarListBasedOnLocation(pickLoc,timeInHours);
				if(flag == true) {
					System.out.println("Which car do you wanna book and for how many kms? example: baleno,10.0");
					String response = sc.nextLine();
					try {
						bookACar(response);	
					}catch(ConcurrentModificationException e) {
						//System.out.println(e.getMessage());
					}catch(ArrayIndexOutOfBoundsException e) {
						System.out.println("Please enter correct format like baleno,10.0");
					}catch(Exception e) {
						System.out.println("Please enter correct format like baleno,10.0");
					}
				}
			
			}else {
				System.out.println("Pickup Time should be less than Drop off Time!");
			}
			
				
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage()+"-> Please enter date in dd/MM/yyyy HH:MM format");
		}  
			
	}

	private static void getCarListBasedOnLocation(String pickLoc,double timeInHours) {
		Iterator itr=carlist.iterator();  
		
        while(itr.hasNext()){  
            Car st= (Car)itr.next();  
            //System.out.println("in getCarListBasedOnLocation() :"+carlist);
            if((st.getLocationAvailable()).equalsIgnoreCase(pickLoc)) {
            	//System.out.println("in getCarListBasedOnLocation() :"+st.getCarName());

            	flag = true;
            	getHeader();
            	double kmsavailable1 = timeInHours*5;
            	double price1 = st.getBasePrice() + kmsavailable1*st.getPricePerKM();
            	double kmsavailable2 = timeInHours*15;
            	double price2 = st.getBasePrice() + kmsavailable2*st.getPricePerKM();
            	double kmsavailable3 = timeInHours*25;
            	double price3 = st.getBasePrice() + kmsavailable3*st.getPricePerKM();
            	Map map = new HashMap();
            	map.put(kmsavailable1, price1);
            	map.put(kmsavailable2, price2);
            	map.put(kmsavailable3, price3);
            	st.setMap(map);
            	
            
            	//System.out.println("map: "+map);
            	System.out.println(st.getCarName() + "          " + st.getNoOfSeats() + "             " + st.getLocationAvailable());
            	System.out.println("Kms_given       Price"); 
            	
            	 Set set=map.entrySet();//Converting to Set so that we can traverse  
            	    Iterator setitr=set.iterator();  
            	    //System.out.println("set: "+set);
            	    while(setitr.hasNext()){  
            	    	
            	        //Converting to Map.Entry so that we can get key and value separately  
            	        Map.Entry entry=(Map.Entry)setitr.next();  
            	        //System.out.println("map: "+entry);
            	         
            	        System.out.println(entry.getKey()+"km"+"            "+ "Rs."+entry.getValue());  
            	    }  
            	  }
            System.out.println("-------------------------------------------------------------------------");
        }
        
        if(flag == false) {
        	System.out.println("Sorry! We don't have any cars available in this location.");
        }
	}

	private static void getHeader() {
		System.out.println("*******Following are the car options available in your location*******");
		System.out.println("Name          No_of_Seats         location_available");

	}

	private static void bookACar(String response) {

		//System.out.println(":car"+response.split(",")[0]);
		//System.out.println(":kms"+response.split(",")[1]);
		String selectedCar = response.split(",")[0].trim();
		Double selectedKms = Double.parseDouble(response.split(",")[1].trim());
		//Double kms = Double.parseDouble(response.split(",")[1].trim());
		//System.out.println(selectedCar+""+selectedKms);
		Iterator itr=carlist.iterator();  
		//boolean flag = false;
		System.out.println("Receipt:");
		flag3=false;
        while(itr.hasNext()){  
            Car st= (Car)itr.next(); 
           
            //System.out.println("map for baleno"+selectedKms);
	        if(st.getCarName().equalsIgnoreCase(selectedCar)) {
//    	    	System.out.println("map for baleno::::"+st.getMap().keySet());
    	    	  Set set=st.getMap().entrySet();    //Converting to Set so that we can traverse  
    	    	    Iterator setitr=set.iterator();  
    	    	    //System.out.println("set: "+set);
    	    	    while(setitr.hasNext()){  
    	    	    	
    	    	        //Converting to Map.Entry so that we can get key and value separately  
    	    	        Map.Entry entry=(Map.Entry)setitr.next();  
    	    	        //System.out.println("chcek: "+entry.getKey() + " "+ selectedKms);
    	    	        //System.out.println(entry.getKey() == selectedKms);
    	    	        if(entry.getKey().equals(selectedKms) ) {
    	    	        	flag3 = true;
    	    	        	System.out.println("Thank you for booking with us!");
    	    	        	 System.out.println("Payable amount:    Rs."+entry.getValue());  
    	    	        	 System.out.println("Extra Kms to be charged at Rs."+st.getPricePerKM()+"per km");
    	    	        	 System.out.println("Fare include Fuel, Travel and Insurance.");
    	    	        	 
//    	    	        	 long removedValue = st.getMap().remove(selectedKms);
//    	    	        	 System.out.println(":"+removedValue);
//    	    	        	System.out.println("map for baleno::::"+st.getMap());
    	    	        	//st.setMap(st.getMap());
    	    	        	//ObjectMapper oMapper = new ObjectMapper();
    	    	        	
//    	    	        	Map<Long,Long> bookedkmsmap = new HashMap();
//    	    	        	bookedkmsmap.put(kms, removedValue);
//    	    	        	//Map<Long, Long> map = oMapper.convertValue(bookedkmsmap, Map.class);
//    	    	            //Map map= st.setMap(map);
//    	    	        	System.out.println(bookedkmsmap.getClass().getName());
    	    	        	alreadybookedCarList.add(new Car(st.getCarName(),st.getNoOfSeats(),st.getLocationAvailable(),
    	    	        			st.getBasePrice(),st.getPricePerKM(),null));
//    	    	        	System.out.println("another list: "+alreadybookedCarList.size());
    	    	        	//System.out.println(st.getCarName());
    	    	        	carlist.remove(st);
    	    	        	//System.out.println("carlist"+carlist);
    	    	         }
    	    	    }
            	}
    	  	}
	        if(flag3 == false) {
	        	System.out.println("Please enter correct format like baleno,10.0");
	        }
		}
}
