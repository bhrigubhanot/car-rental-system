import java.util.ArrayList;
import java.util.Map;

public class CarRepository {
	private ArrayList<Car> carlist;
	private Map<String,Integer> availableCars;
	
	public CarRepository(String location) {
		initializeListOfCars();
	}
	
	public ArrayList<Car> initializeListOfCars(){
		carlist = new ArrayList<Car>();
		return carlist;
	}
	public void addCar() {
		
	}
	public void removeCar() {
		
	}
	public void availableCars() {
		
	}
}
