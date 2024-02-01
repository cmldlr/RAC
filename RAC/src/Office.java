import java.util.ArrayList;

public class Office {
	private int officeID;
	private String location;
	ArrayList<Car> cars;
	Office(int officeID, String location){
		this.officeID = officeID;
		this.location = location;
		cars = new ArrayList<Car>();
	}
	public int getOfficeID() {
		return officeID;
	}
	public String getLocation() {
		return location;
	}
	public ArrayList<Car> getCars() {
		return cars;
	}
	public void addCar(Car car) {
		cars.add(car);
	}
	
}
