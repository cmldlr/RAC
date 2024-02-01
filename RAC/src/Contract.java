
public class Contract {
	
   private Car car;
   private String start_time;
   private String finish_time;
   private User user;
   
   public Contract(Car car, String start_time, String finish_time, User user) {
		this.car = car;
		this.start_time = start_time;
		this.finish_time = finish_time;
		this.user = user;
   }

public Car getCar() {
	return car;
}

public void setCar(Car car) {
	this.car = car;
}

public String getStart_time() {
	return start_time;
}

public void setStart_time(String start_time) {
	this.start_time = start_time;
}

public String getFinish_time() {
	return finish_time;
}

public void setFinish_time(String finish_time) {
	this.finish_time = finish_time;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}
   
   
   
   
   
}
