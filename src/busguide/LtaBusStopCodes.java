/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busguide;

/**
 *
 * @author elvis
 */
public class LtaBusStopCodes {

	private String busStopCode;
	private String roadDescription;
	private String busStopDescription;

	public LtaBusStopCodes(String busStopCode, String roadDescription, String busStopDescription) {
		this.busStopCode = busStopCode;
		this.roadDescription = roadDescription;
		this.busStopDescription = busStopDescription;
	}

	@Override
	public String toString() {
		return getBusCode() + "\n"
			+ getRoadDescription() + "\n"
			+ getBusStopDescription();
	}

	/**
	 * @return the busCode
	 */
	public String getBusCode() {
		return busStopCode;
	}

	/**
	 * @param busCode the busCode to set
	 */
	public void setBusCode(String busCode) {
		this.busStopCode = busCode;
	}

	/**
	 * @return the roadDescription
	 */
	public String getRoadDescription() {
		return roadDescription;
	}

	/**
	 * @param roadDescription the roadDescription to set
	 */
	public void setRoadDescription(String roadDescription) {
		this.roadDescription = roadDescription;
	}

	/**
	 * @return the busStopDescription
	 */
	public String getBusStopDescription() {
		return busStopDescription;
	}

	/**
	 * @param busStopDescription the busStopDescription to set
	 */
	public void setBusStopDescription(String busStopDescription) {
		this.busStopDescription = busStopDescription;
	}
}
