import java.util.Map;

public class Car {
	private String carName;
	private int noOfSeats;
	private String locationAvailable;
	private int basePrice;
	private int pricePerKM;
	Map<Long,Long> map;
	
	public Car(String carName, int noOfSeats, String locationAvailable, int basePrice, int pricePerKM,
			Map<Long, Long> map) {
		super();
		this.carName = carName;
		this.noOfSeats = noOfSeats;
		this.locationAvailable = locationAvailable;
		this.basePrice = basePrice;
		this.pricePerKM = pricePerKM;
		this.map = map;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	public String getLocationAvailable() {
		return locationAvailable;
	}
	public void setLocationAvailable(String locationAvailable) {
		this.locationAvailable = locationAvailable;
	}
	public int getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(int basePrice) {
		this.basePrice = basePrice;
	}
	public int getPricePerKM() {
		return pricePerKM;
	}
	public void setPricePerKM(int pricePerKM) {
		this.pricePerKM = pricePerKM;
	}
	public Map<Long, Long> getMap() {
		return map;
	}
	public Map setMap(Map<Long, Long> map) {
		return this.map = map;
	}

	
}
