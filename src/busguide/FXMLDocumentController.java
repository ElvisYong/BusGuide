/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busguide;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MVCArray;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.shapes.Polyline;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author elvis
 */
public class FXMLDocumentController implements Initializable, MapComponentInitializedListener {

	@FXML
	private ComboBox<BusStops> fromComboBox;
	@FXML
	private ComboBox<BusStops> toComboBox;
	@FXML
	private ComboBox<BusStops> svcComboBox;
	@FXML
	private ComboBox<String> routeComboBox;
	@FXML
	private Button btnRoute;
	@FXML
	private ListView routeListView;
	@FXML
	private Button btnSearchRoutes;
	@FXML
	private Button btnSearchSvc;
	@FXML
	private ListView svcListView;
	@FXML
	private GoogleMapView mapView;

	private GoogleMap map;

	private List<BusRoutes> busRoutes;
	private HashMap<String, BusStops> busStopCodes;
	private ListProperty<BusStops> busStopLP;
	private ListProperty<String> busServiceLP;
	private ListProperty<String> busServiceSearchLP;
	private ListProperty<BusStops> busRouteLP;
	private DijkstraAlgorithm dijkstra;
	private Set<BusStops> vertices;
	private Set<Edge> edges;
	private LinkedList<BusStops> mostShortDePath;

	private Graph graph;
	private Polyline theShortestPathYouDrawOut;
	private MVCArray mvcArr;
	private Marker fromMark;
	private Marker toMark;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		//Map listener
		mapView.addMapInializedListener(this);

		//Instantiates readfile object to call in methods to read the csv files
		ReadFile rf = new ReadFile();

		try {
			//HashMap to store bus stop codes
			busStopCodes = rf.readLtaBusStops("lta-bus_stop_codes.csv", "lta-bus_stop_locations.csv");
		} catch (IOException ex) {
			Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
		}

		try {
			//List to store bus routes that will be passed into a hashmap later
			busRoutes = rf.readBusRoutes("lta-sbst_route.csv", "lta-smrt_route.csv");
		} catch (IOException ex) {
			Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
		}

		//Populates the list property to then bind into combobox later
		busStopLP = new SimpleListProperty<>();
		busStopLP.set(FXCollections.observableArrayList(busStopCodes.values()));
		
		busServiceSearchLP = new SimpleListProperty<>();
		busServiceSearchLP.set(FXCollections.observableArrayList(busRoutes.stream().map(svc -> svc.getServiceNum())
									.collect(Collectors.toSet())));
		
		
		fromComboBox.itemsProperty().bind(busStopLP);
		toComboBox.itemsProperty().bind(busStopLP);
		svcComboBox.itemsProperty().bind(busStopLP);
		routeComboBox.itemsProperty().bind(busServiceSearchLP);

		//Autocomplete texts for combobox using controlsFx
		TextFields.bindAutoCompletion(fromComboBox.getEditor(), fromComboBox.getItems());
		TextFields.bindAutoCompletion(toComboBox.getEditor(), toComboBox.getItems());
		TextFields.bindAutoCompletion(svcComboBox.getEditor(), svcComboBox.getItems());
		TextFields.bindAutoCompletion(routeComboBox.getEditor(), routeComboBox.getItems());

		//Creates the list of vertex and edges
		vertices = new HashSet<>();
		edges = new HashSet<>();

		//Map the edges using the bus routes
		for (int i = 0; i < busRoutes.size() - 1; i++) {
			if (busRoutes.get(i).getServiceNum().equals(busRoutes.get(i + 1).getServiceNum())
				&& (busRoutes.get(i).getDirection() == busRoutes.get(i + 1).getDirection())
				&& !(busRoutes.get(i).getDistance() == null) && !(busRoutes.get(i + 1).getDistance() == null)) {
				Edge edge = new Edge(busStopCodes.get(busRoutes.get(i).getBusStopCode()),
					busStopCodes.get(busRoutes.get(i + 1).getBusStopCode()),
					busRoutes.get(i + 1).getDistance() - busRoutes.get(i).getDistance());
				edges.add(edge);
			}
		}

		graph = new Graph(vertices, edges);
		dijkstra = new DijkstraAlgorithm(graph);

		//btnSearchRoutes listener
		btnSearchRoutes.setOnAction((ActionEvent e) -> {
			if (fromComboBox.getEditor().getText().equals(toComboBox.getEditor().getText())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Why would you want to take a bus to the same place? Select another location.");
				alert.showAndWait();
			} else {
				BusStops from = null;
				BusStops to = null;
				for (Map.Entry<String, BusStops> bs : busStopCodes.entrySet()) {
					if (bs.getValue().toString().equals(fromComboBox.getEditor().getText())) {
						from = bs.getValue();
					}
					if (bs.getValue().toString().equals(toComboBox.getEditor().getText())) {
						to = bs.getValue();
					}
				}

				//Executes the algorithm
				dijkstra.execute(from);
				mostShortDePath = dijkstra.getPath(to);

				//Prints the path
				mostShortDePath.stream().forEach(n -> {
					System.out.println(n.getBusStopDescription());
				});

				mostShortDePath.stream().map((busStops) -> new LatLong(busStops.getX(), busStops.getY()))
					.forEach((latLong) -> {
						mvcArr.push(latLong);
					});
				theShortestPathYouDrawOut.setPath(mvcArr);
				map.addMapShape(theShortestPathYouDrawOut);
			}
		});

		//btnSearchSvc listener
		btnSearchSvc.setOnAction((ActionEvent e) -> {
			BusStops bs = busStopLP.stream()
				.filter(b -> b.toString().equals(svcComboBox.getEditor().getText()))
				.findFirst()
				.get();

			busServiceLP = new SimpleListProperty<>();
			busServiceLP.set(FXCollections.observableArrayList(busRoutes.stream()
				.filter(br -> br.getBusStopCode().equals(bs.getBusStopCode()))
				.map(map -> map.getServiceNum())
				.collect(Collectors.toSet())));
			svcListView.itemsProperty().bind(busServiceLP);
		});

		//btnRoute listener
		btnRoute.setOnAction((ActionEvent e)->{
			Set<String> routes = busRoutes.stream().filter(br -> br.getServiceNum().equals(routeComboBox.getEditor().getText()))
				.map(map -> map.getBusStopCode())
				.collect(Collectors.toSet());

			Set<BusStops> busStops = new HashSet<>();
			routes.forEach(r -> {
				busStops.add(busStopCodes.get(r));
			});

			busRouteLP = new SimpleListProperty<>();
			busRouteLP.set(FXCollections.observableArrayList(busStops));
			routeListView.itemsProperty().bind(busRouteLP);
		});
	}

	@Override
	public void mapInitialized() {
		MapOptions options = new MapOptions();

		options.center(new LatLong(1.3521, 103.8198))
			.zoomControl(true)
			.zoom(12)
			.mapType(MapTypeIdEnum.ROADMAP)
			.mapTypeControl(false)
			.streetViewControl(false)
			.mapMaker(true)
			.overviewMapControl(false);

		map = mapView.createMap(options);

		mvcArr = new MVCArray();
	}

}
