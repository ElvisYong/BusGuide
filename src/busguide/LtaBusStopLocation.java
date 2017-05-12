/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busguide;

import java.util.List;

/**
 *
 * @author elvis
 */
public class LtaBusStopLocation {

	private double x;
	private double y;
	private int zid;
	private String busStopCode;

	public LtaBusStopLocation(double x, double y, int zid, String busStopCode) {
		this.x = x;
		this.y = y;
		this.zid = zid;
		this.busStopCode = busStopCode;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return the zid
	 */
	public int getZid() {
		return zid;
	}

	/**
	 * @param zid the zid to set
	 */
	public void setZid(int zid) {
		this.zid = zid;
	}

	/**
	 * @return the busStopCode
	 */
	public String getBusStopCode() {
		return busStopCode;
	}

	/**
	 * @param busStopCode the busStopCode to set
	 */
	public void setBusStopCode(String busStopCode) {
		this.busStopCode = busStopCode;
	}

	//For printing to check
	@Override
	public String toString() {
		return getX() + "\n"
			+ getY() + "\n"
			+ getZid() + "\n"
			+ getBusStopCode() + "\n";
	}

}
