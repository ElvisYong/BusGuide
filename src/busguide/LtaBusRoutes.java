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
public class LtaBusRoutes {

	private String serviceNum;
	private int direction;
	private int routeSeq;
	private String busStopCode;
	private double distance;

	public LtaBusRoutes(String serviceNum, int direction, int routeSeq, String busStopCode, double distance) {
		this.serviceNum = serviceNum;
		this.direction = direction;
		this.routeSeq = routeSeq;
		this.busStopCode = busStopCode;
		this.distance = distance;

	}

	public String getServiceNum() {
		return serviceNum;
	}

	public void setServiceNum(String serviceNum) {
		this.serviceNum = serviceNum;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getRouteSeq() {
		return routeSeq;
	}

	public void setRouteSeq(int routeSeq) {
		this.routeSeq = routeSeq;
	}

	public String getBusStopCode() {
		return busStopCode;
	}

	public void setBusStopCode(String busStopCode) {
		this.busStopCode = busStopCode;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	//For printing to check
	@Override
	public String toString() {
		return getServiceNum() + "\n"
			+ getDirection() + "\n"
			+ getRouteSeq() + "\n"
			+ getBusStopCode() + "\n"
			+ getDistance() + "\n";
	}
}
