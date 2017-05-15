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
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
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
		HashMap<String, BusStops> busStopCodes = new HashMap<>();
		busStopCodes = rf.readLtaBusStops("lta-bus_stop_codes.csv", "lta-bus_stop_locations.csv");

		//List to store bus routes that will be passed into a hashmap later
		List<BusRoutes> busRoutes = new ArrayList<>();
		busRoutes = rf.readBusRoutes("lta-sbst_route.csv", "lta-smrt_route.csv");

		//HashMap to store the list of bus routes
		HashMap<String, List<BusRoutes>> busServiceMap = new HashMap<>();
		//To transfer the list of bus routes into a bus service hashmap
		for (BusRoutes busInfo : busRoutes) {
			if (busServiceMap.get(busInfo.getServiceNum()) == null) {
				busServiceMap.put(busInfo.getServiceNum(), new ArrayList<BusRoutes>());
			}
			BusRoutes lbr = new BusRoutes(busInfo.getServiceNum(), busInfo.getDirection(), busInfo.getRouteSeq(),
				busInfo.getBusStopCode(), busInfo.getDistance());
			busServiceMap.get(busInfo.getServiceNum()).add(lbr);
		}

		//Creates the list of vertex and edges
		List<Vertex> vertices = new ArrayList<>();
		List<Edge> edges = new ArrayList<>();

		for (Map.Entry<String, BusStops> busStops : busStopCodes.entrySet()) {
			Vertex vertex = new Vertex(busStops.getKey());
			vertices.add(vertex);
		}
		
		//Map the edges using the bus routes data.
		for (int i = 0; i < busRoutes.size() - 1; i++) {
			if (busRoutes.get(i).getServiceNum().equals(busRoutes.get(i + 1).getServiceNum())
				&& busRoutes.get(i).getDirection() == busRoutes.get(i + 1).getDirection()
				&& !(busRoutes.get(i).getDistance() == null) && !(busRoutes.get(i + 1).getDistance() == null)) {
				Edge edge = new Edge(new Vertex(busRoutes.get(i).getBusStopCode()),
					new Vertex(busRoutes.get(i + 1).getBusStopCode()),
					(busRoutes.get(i + 1).getDistance() - busRoutes.get(i).getDistance()));
				edges.add(edge);
			}
		}

		Graph graph = new Graph(vertices, edges);
		DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
		//Execute dijkstra from the location you want to start
		dijkstra.execute(vertices.get(1));
		//Put the paths into a linked list from dijkstra
		LinkedList<Vertex> path = dijkstra.getPath(vertices.get(5));

		if (path == null) {
			System.out.println("No paths available");
		}
		else{
			System.out.println(path.size());
		}

		launch(args);
	}

}
