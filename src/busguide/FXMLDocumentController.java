/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busguide;

import com.lynden.gmapsfx.GoogleMapView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 *
 * @author elvis
 */
public class FXMLDocumentController implements Initializable {

	@FXML
	private Button button;

	@FXML
	private GoogleMapView mapView;

	@FXML
	private void handleButtonAction(ActionEvent event) {
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

}
