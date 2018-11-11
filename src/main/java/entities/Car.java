package entities;

/**
 * 
 * @author Eduard this class is entity for Car from Database. This class has
 *         generated getters and setters for interaction with fields like id ,
 *         model etc.
 *
 */
public class Car {
	private int id;
	private String model;
	private String mark;
	private String carClass;
	private int cost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getCarClass() {
		return carClass;
	}

	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", model=" + model + ", mark=" + mark + ", carClass=" + carClass + ", cost=" + cost
				+ "]";
	}

}
