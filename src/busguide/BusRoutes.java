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
public class BusRoutes {

	private String serviceNum;
	private int direction;
	private int routeSeq;
	private String busStopCode;
	private Double distance;

	public BusRoutes(String serviceNum, int direction, int routeSeq, String busStopCode, Double distance) {
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

	//For printing to check
	@Override
	public String toString() {
		return getServiceNum() + "\n"
			+ getDirection() + "\n"
			+ getRouteSeq() + "\n"
			+ getBusStopCode() + "\n"
			+ getDistance() + "\n";
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

}
