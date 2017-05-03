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
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author elvis
 */
public class BusGuide extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param args the command line arguments
	 * @throws java.io.IOException
	 */
	public static void main(String[] args) throws IOException {

		//List to store bus routes that will be passed into a hashmap later
		ArrayList<LtaBusRoutes> busRoutes = new ArrayList<>();
		//List to store the x, y, and ZID of the bus stop locations
		ArrayList<LtaBusStopLocation> busStopLocations = new ArrayList<>();

		//List to store bus stop codes
		HashMap<String, LtaBusStopCodes> busStopCodes = new HashMap<>();
		HashMap<String, ArrayList<LtaBusRoutes>> busServiceMap = new HashMap<>();

		//Reads Lta Bus Stop Codes and add it into a hashmap
		File ltaBusStopCodes = new File("lta-bus_stop_codes.csv"); //Instantiates the file to read
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
					LtaBusStopCodes ltaBsStopCode = new LtaBusStopCodes(busStopCode, roadDesc, busStopDesc);
					busStopCodes.put(ltaBsStopCode.getBusStopCode(), ltaBsStopCode);
//					System.out.println(ltaStopCode); //For checking
				}//End while loop for each token
			}//End while loop
		} catch (IOException ex) {
			Logger.getLogger(BusGuide.class.getName()).log(Level.SEVERE, null, ex);
		}

		//Reads lta bus stop LOCATION and put it into a list.
		File ltaBusStopLocation = new File("lta-bus_stop_locations.csv");
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
//					System.out.println(stopLocation); //For checking
				}
			}
		} catch (IOException ex) {
			Logger.getLogger(BusGuide.class.getName()).log(Level.SEVERE, null, ex);
		}

		//Reads lta sbs route
		File ltaSbstRoute = new File("lta-sbst_route.csv");
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
					} 
					catch (NumberFormatException ex) {
						distance = null;
					}
					LtaBusRoutes ltaRoutes = new LtaBusRoutes(serviceNum, direction, roadSeq, busStopCode, distance);
					busRoutes.add(ltaRoutes);
//					System.out.println(ltaRoutes); //For checking
				}
			}
		} catch (IOException ex) {
			Logger.getLogger(BusGuide.class.getName()).log(Level.SEVERE, null, ex);
		}
		//To transfer the list of bus routes into a bus service hashmap
		for(LtaBusRoutes busInfo: busRoutes){
			if(busServiceMap.get(busInfo.getServiceNum()) == null){
				busServiceMap.put(busInfo.getServiceNum(), new ArrayList<LtaBusRoutes>());
			}
			LtaBusRoutes lbr = new LtaBusRoutes(busInfo.getServiceNum(), busInfo.getDirection(), busInfo.getRouteSeq(),
			busInfo.getBusStopCode(), busInfo.getDistance());
			busServiceMap.get(busInfo.getServiceNum()).add(lbr);
		}
		
		launch(args);
	}

}
