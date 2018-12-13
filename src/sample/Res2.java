package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Res2 {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private javafx.scene.control.TableView<Athlete> TableView;

    @FXML
    private TableColumn<Athlete, Integer> columm1;

    @FXML
    private TableColumn<Athlete, String> columm2;

    @FXML
    private TableColumn<Athlete, String> columm3;

    @FXML
    private TableColumn<Athlete, String> columm4;

    @FXML
    private TableColumn<Athlete, Integer> columm5;

    @FXML
    void initialize() {

        columm1.setCellValueFactory(new PropertyValueFactory<>("AthleteRegistrationNumber"));
        columm2.setCellValueFactory(new PropertyValueFactory<>("nameOfTheAthlete"));
        columm3.setCellValueFactory(new PropertyValueFactory<>("NameOfTheCountry"));
        columm4.setCellValueFactory(new PropertyValueFactory<>("sport"));
        columm5.setCellValueFactory(new PropertyValueFactory<>("Place"));
         System.out.println(Controller.temp.size());
        for (int i = 0; i <Controller.temp.size() ; i++) {
            System.out.println();
        }
        TableView.setItems(Controller.temp);


    }
}
