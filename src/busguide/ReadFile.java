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

	//Reads the LTA bus stops into a hashmap
	public HashMap<String, BusStops> readLtaBusStops(String file, String file2) throws IOException {
		//Reads Lta Bus Stop Codes and add it into a hashmap
		HashMap<String, BusStops> busStopMap = new HashMap<>();
		File ltaBusStopCodes = new File(file); //Instantiates the file to read
		File otherFile = new File(file2);
		//Tries to read the file using buffered reader
		try (BufferedReader br = new BufferedReader(new FileReader(ltaBusStopCodes))) {
			br.readLine();//To skip first line
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
					BusStops ltaBsStopCode = new BusStops(busStopCode, roadDesc, busStopDesc);
					busStopMap.put(ltaBsStopCode.getBusStopCode(), ltaBsStopCode);
				}//End while loop for each token
			}//End while loop
		}
		try (BufferedReader br = new BufferedReader(new FileReader(otherFile))) {
			br.readLine();
			StringTokenizer st;
			String line;
			while ((line = br.readLine()) != null) {
				st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) {
					double latitude = Double.parseDouble(st.nextToken());
					double longitude = Double.parseDouble(st.nextToken());
					int zid = Integer.parseInt(st.nextToken());
					BusStops bs = busStopMap.get(st.nextToken());
					if (bs != null) {
						bs.setX(latitude);
						bs.setY(longitude);
						bs.setZid(zid);
					}
				}
			}
		}
		return busStopMap;
	}
	//Reads BOTH the sbs and SMRT file and put them into a single list
	public List<BusRoutes> readBusRoutes(String sbsFile, String smrtFile) throws IOException {
		List<BusRoutes> busRoutes = new ArrayList<>();
		File ltaSbstRoute = new File(sbsFile);
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
					BusRoutes ltaRoutes = new BusRoutes(serviceNum, direction, roadSeq, busStopCode, distance);
					busRoutes.add(ltaRoutes);
				}//End while loop for each token
			}//End while loop
		}
		File smrtBusRoute = new File(smrtFile);
		try (BufferedReader br = new BufferedReader(new FileReader(smrtBusRoute))) {
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
					BusRoutes smrtRoutes = new BusRoutes(serviceNum, direction, roadSeq, busStopCode, distance);
					busRoutes.add(smrtRoutes);
				}//End while loop for each token
			}//End while loop
		}
		return busRoutes;
	}
}
