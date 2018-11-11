package entities;

import java.sql.Date;

/**
 * 
 * @author Eduard this class is entity for Order from Database. This class has
 *         generated getters and setters for interaction with fields like id ,
 *         starting date, bill etc.
 *
 */
public class Order {
	private int id;
	private int userId;
	private int carId;
	private int bill;
	private String status;
	private String driver;
	private Date start_date;
	private Date finish_date;

	/**
	 * @return id of the order
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id sets id of the order equals id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return UserId who made order
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId who made the order
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return Id of car that was ordered
	 */
	public int getCarId() {
		return carId;
	}

	/**
	 * @param carId set which car is ordered
	 */
	public void setCarId(int carId) {
		this.carId = carId;
	}

	/**
	 * @return bill to pay for order
	 */
	public int getBill() {
		return bill;
	}

	/**
	 * @param bill sets how much user have to pay for order
	 */
	public void setBill(int bill) {
		this.bill = bill;
	}

	/**
	 * @return current status of the order
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status sets current status of the order
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return is including the order a driver
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * @param driver is sets including a driver in order
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * @return date when order starts
	 */
	public Date getstart_date() {
		return start_date;
	}

	/**
	 * @param start_date set date when order starts
	 */
	public void setstart_date(Date start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return last_date of the order
	 */
	public Date getFinish_date() {
		return finish_date;
	}

	/**
	 * @param finish_date sets last day of the order
	 */
	public void setFinish_date(Date finish_date) {
		this.finish_date = finish_date;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", carId=" + carId + ", bill=" + bill + ", status=" + status
				+ ", driver=" + driver + ", start_date=" + start_date + ", finish_date="
				+ finish_date + "]";
	}

}
