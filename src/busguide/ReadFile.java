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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elvis
 */
public class ReadFile {

	public HashMap<String, LtaBusStops> readLtaBusStops(String file) throws IOException {
		//Reads Lta Bus Stop Codes and add it into a hashmap
		HashMap<String, LtaBusStops> busStopMap = new HashMap<>();
		File ltaBusStopCodes = new File(file); //Instantiates the file to read
		//Tries to read the file using buffered reader
		try (BufferedReader br = new BufferedReader(new FileReader(ltaBusStopCodes))) {
			br.readLine();
			StringTokenizer st; //String tokenizer to read the csv token by token
			String line;
			//loops through the csv file and read it
			while ((line = br.readLine()) != null) {
				st = new StringTokenizer(line, ","); //using StringTokenizer's delimiter for ",". Each word as a single token.
				//2nd while loop to loop each and every word after delimiter ","
				while (st.hasMoreTokens()) {
					String busStopCode = st.nextToken();
					String roadDesc = st.nextToken();
					String busStopDesc = st.nextToken();
					//Creates a LtaBusStopCodes object with the data read from csv and insert into the hashmap
					LtaBusStops ltaBsStopCode = new LtaBusStops(busStopCode, roadDesc, busStopDesc);
					busStopMap.put(ltaBsStopCode.getBusStopCode(), ltaBsStopCode);
				}//End while loop for each token
			}//End while loop
			return busStopMap;
		}
	}

	public List<LtaBusStopLocation> readLtaBusStopLocations(String file) throws IOException {
		//Reads lta bus stop LOCATION and put it into a list.
		List<LtaBusStopLocation> busStopLocations = new ArrayList<>();
		File ltaBusStopLocation = new File(file);
		try (BufferedReader br = new BufferedReader(new FileReader(ltaBusStopLocation))) {
			br.readLine();
			StringTokenizer st;
			String line;
			while ((line = br.readLine()) != null) {
				st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) {
					double x = Double.parseDouble(st.nextToken());
					double y = Double.parseDouble(st.nextToken());
					int zid = Integer.parseInt(st.nextToken());
					String busStopCode = st.nextToken();
					LtaBusStopLocation stopLocation = new LtaBusStopLocation(x, y, zid, busStopCode);
					busStopLocations.add(stopLocation);
				}//End while loop for each token
			}//End while loop
			return busStopLocations;
		}
	}

	public List<LtaBusRoutes> readLtaBusRoutes(String file) throws IOException {
		List<LtaBusRoutes> busRoutes = new ArrayList<>();
		File ltaSbstRoute = new File(file);
		try (BufferedReader br = new BufferedReader(new FileReader(ltaSbstRoute))) {
			br.readLine();
			StringTokenizer st;
			String line;
			while ((line = br.readLine()) != null) {
				st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) {
					String serviceNum = st.nextToken();
					int direction = Integer.parseInt(st.nextToken());
					int roadSeq = Integer.parseInt(st.nextToken());
					String busStopCode = st.nextToken();
					Double distance;
					try {
						distance = Double.parseDouble(st.nextToken());
					} catch (NumberFormatException ex) {
						distance = null;
					}
					LtaBusRoutes ltaRoutes = new LtaBusRoutes(serviceNum, direction, roadSeq, busStopCode, distance);
					busRoutes.add(ltaRoutes);
				}//End while loop for each token
			}//End while loop
			return busRoutes;
		}
	}
}
