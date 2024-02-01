import java.util.Date;

public class Car {
	private String brand;
	private String model;
	private String plate;
	private int km;
	private int price;
	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}
	private String type;
	private String office;
	private boolean avaliable;
	private Date avaliableOn;
	
	public Car(String brand, String model, String plate, int km, int price,String type,String office, boolean avaliable,Date avaliableOn ) {
		this.brand = brand;
		this.model = model;
		this.plate = plate;
		this.km = km;
		this.price = price;
		this.type=type;
		this.office=office;
		this.avaliable = avaliable;
		this.avaliableOn = avaliableOn;
	}

	public String getBrand() {
		return brand;
	}
	public String getModel() {
		return model;
	}
	public int getPrice() {
		return price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public boolean isAvaliable() {
		return avaliable;
	}

	public void setAvaliable(boolean avaliable) {
		this.avaliable = avaliable;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	public void setAvaliableOn(Date date) {
		avaliableOn = date;
	}
	public Date getAvaliableOn() {
		return avaliableOn;
	}
}
