package interfacesDAO;

import java.util.ArrayList;

import entities.Car;

public interface CarDAO {

	final static String Id = "id";
	final static String Cost = "cost";
	final static String CarClass = "class";
	final static String Mark = "mark";
	final static String Model = "model";

	Car findCarById(int id);

	/**
	 * this method adds new car into a DataBase
	 * 
	 * @param model    is the model of incoming car
	 * @param mark     is the mark of incoming car
	 * @param carClass is a prescription to which type this car belong
	 * @param cost     is the cost for renting a car for a minimal unit of time
	 */
	void addCar(String model, String mark, String carClass, int cost);

	/**
	 * this method changes model of car with defined id
	 * 
	 * @param id    is id of incoming car for changing a model
	 * @param model defines new model to set
	 */
	void changeModel(int id, String model);

	/**
	 * this method changes a mark of defined car
	 * 
	 * @param id   is id of incoming car that has to be changed
	 * @param mark defines new mark of a car
	 */
	void changeMark(int id, String mark);

	/**
	 * this method changes class of a car
	 * 
	 * @param id       is id of incoming car that has to be changed
	 * @param carClass defines new car class
	 */
	void changeClass(int id, String carClass);

	void changeCar(int id, String model, String mark, String carClass, int cost);

	/**
	 * this method deletes car from database
	 * 
	 * @param id of car which should be deleted
	 */
	void deleteCar(int id);

	/**
	 * this method returns cars with certain mark from database
	 */
	ArrayList<Car> selectCarsByMark(String mark);

	/**
	 * this method returns list of cars with defined model
	 * 
	 * @param model is cars model to get list of cars from database
	 */
	ArrayList<Car> selecCarsByClass(String model);
	/**
	 * this method makes search by incoming string parameter in database table cars. As the result returns list of Car entities which match prescribed string .  
	 * @param param incoming string for checking matches with DB  
	 * @return list of Car entities 
	 */
	ArrayList<Car> searchCars(String param);

	/**
	 * this method return list of cars sorted by cost
	 */
	ArrayList<Car> selectCarsGroupByCost();

	/**
	 * this method returns list of cars sorted by name
	 */
	ArrayList<Car> selectCarsGroupByName();

}
