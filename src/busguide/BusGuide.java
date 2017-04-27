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

		List<LtaBusStopCodes> busStopCodes = new ArrayList<>();

		//Reads Lta Bus Stop Codes
		File ltaBusStopCodes = new File("lta-bus_stop_codes.csv");
		try (BufferedReader br = new BufferedReader(new FileReader(ltaBusStopCodes))) {
			br.readLine();
			StringTokenizer st;
			String line;
			while ((line = br.readLine()) != null) {
				st = new StringTokenizer(line, ",");
				while (st.hasMoreTokens()) {
					String busStopCode = st.nextToken();
					String roadDesc = st.nextToken();
					String busStopDesc = st.nextToken();
					LtaBusStopCodes ltaStopCode = new LtaBusStopCodes(busStopCode, roadDesc, busStopDesc);
					busStopCodes.add(ltaStopCode);
					//System.out.Println(ltaStopCode); //For checking
				}
			}
		} catch (IOException ex) {
			Logger.getLogger(BusGuide.class.getName()).log(Level.SEVERE, null, ex);
		}

		//Reads lta sbs route
		List<LtaBusRoutes> busRoutes = new ArrayList<>();

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
					double distance = Double.parseDouble(st.nextToken());
					LtaBusRoutes ltaRoutes = new LtaBusRoutes(serviceNum, direction, roadSeq, busStopCode, distance);
					busRoutes.add(ltaRoutes);
					//System.out.Println(ltaRoutes); //For checking
				}
			}
		} catch (IOException ex) {
			Logger.getLogger(BusGuide.class.getName()).log(Level.SEVERE, null, ex);
		}

		//Reads lta bus stop LOCATION
		List<LtaBusStopLocation> busStopLocations = new ArrayList<>();
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
					//System.out.Println(stopLocation); //For checking
				}
			}
		} catch (IOException ex) {
			Logger.getLogger(BusGuide.class.getName()).log(Level.SEVERE, null, ex);
		}

//		launch(args);
	}

}
