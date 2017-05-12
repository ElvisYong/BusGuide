/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busguide;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elvis
 */
public class LtaBusStops {

	private String busStopCode;
	private String roadDescription;
	private String busStopDescription;

	public LtaBusStops(String busStopCode, String roadDescription, String busStopDescription) {
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
