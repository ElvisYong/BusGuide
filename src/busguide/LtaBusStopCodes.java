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

	public String getBusStopCode() {
		return busStopCode;
	}

	public void setBusStopCode(String busStopCode) {
		this.busStopCode = busStopCode;
	}

	public String getRoadDescription() {
		return roadDescription;
	}

	public void setRoadDescription(String roadDescription) {
		this.roadDescription = roadDescription;
	}

	public String getBusStopDescription() {
		return busStopDescription;
	}

	public void setBusStopDescription(String busStopDescription) {
		this.busStopDescription = busStopDescription;
	}
}
