package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController {
	
private Model model;
	
	public void setModel(Model model) {
		this.model = model;
		List<Nerc> ln = model.getNercList();
		for(Nerc n : ln)
			comboBox.getItems().add(n.toString());
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private TextField txtYear;

    @FXML
    private TextField txthour;

    @FXML
    private Button btnanalysis;

    @FXML
    private TextArea txtResult;

    @FXML
    void doAnalysis(ActionEvent event) {
    	
    	Nerc n = model.getNerc(comboBox.getValue());
    	int year = Integer.parseInt(txtYear.getText());
    	int hour = Integer.parseInt(txthour.getText());
    	ArrayList<PowerOutages> result = new ArrayList<PowerOutages>(model.ricorsione(n, year, hour));
    	txtResult.appendText("Total costomers affected: "+model.calcolaPersone(result)+"\n");
    	for(PowerOutages p : result) {
    		txtResult.appendText(p.toString()+"d\n");
    	}
    }

    @FXML
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtYear != null : "fx:id=\"txtYear\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txthour != null : "fx:id=\"txthour\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert btnanalysis != null : "fx:id=\"btnanalysis\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";

    }
}
