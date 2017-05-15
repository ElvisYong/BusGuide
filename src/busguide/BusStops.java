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
public class BusStops {

	private String busStopCode;
	private String roadDescription;
	private String busStopDescription;
	private double x;
	private double y;
	private int zid;

	public BusStops(String busStopCode, String roadDescription, String busStopDescription) {
		this.busStopCode = busStopCode;
		this.roadDescription = roadDescription;
		this.busStopDescription = busStopDescription;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setZid(int zid) {
		this.zid = zid;
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

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getZid() {
		return zid;
	}

	@Override
	public String toString(){
		return busStopCode;
	}

}
