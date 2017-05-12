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
		//Instantiates readfile object to call in methods to read the csv files
		ReadFile rf = new ReadFile();

		//HashMap to store bus stop codes
		HashMap<String, LtaBusStops> busStopCodes = new HashMap<>();
		busStopCodes = rf.readLtaBusStops("lta-bus_stop_codes.csv");

		//List to store the x, y, and ZID of the bus stop locations
		List<LtaBusStopLocation> busStopLocations = new ArrayList<>();
		busStopLocations = rf.readLtaBusStopLocations("lta-bus_stop_locations.csv");

		//List to store bus routes that will be passed into a hashmap later
		List<LtaBusRoutes> busRoutes = new ArrayList<>();
		busRoutes = rf.readLtaBusRoutes("lta-sbst_route.csv");
		
		//HashMap to store the list of bus routes
		HashMap<String, List<LtaBusRoutes>> busServiceMap = new HashMap<>();
		//To transfer the list of bus routes into a bus service hashmap
		for(LtaBusRoutes busInfo: busRoutes){
			if(busServiceMap.get(busInfo.getServiceNum()) == null){
				busServiceMap.put(busInfo.getServiceNum(), new ArrayList<LtaBusRoutes>());
			}
			LtaBusRoutes lbr = new LtaBusRoutes(busInfo.getServiceNum(), busInfo.getDirection(), busInfo.getRouteSeq(),
			busInfo.getBusStopCode(), busInfo.getDistance());
			busServiceMap.get(busInfo.getServiceNum()).add(lbr);
		}
		
		//Creates the list of vertex and edges
		List<Vertex> vertices = new ArrayList<>();
		List<Edge> edges = new ArrayList<>();

		//Transfer the list of bus stop location code into the vertices
		for(LtaBusStopLocation bsLocation: busStopLocations){
			Vertex vertex = new Vertex(bsLocation.getBusStopCode());
			vertices.add(vertex);
		}

		
		//TODO get the edges
			
		launch(args);
	}

}
