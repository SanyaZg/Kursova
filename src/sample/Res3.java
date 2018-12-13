package sample;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Res3 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
@FXML
    TableView<CountryVictories> table;
    @FXML
    private TableColumn<CountryVictories, String> c1;
    @FXML
    private TableColumn<CountryVictories, Integer> c2;
    @FXML
    private TableColumn<CountryVictories, Integer> c3;
    @FXML
    private TableColumn<CountryVictories, Integer> c4;



    @FXML
    void initialize() {
        c1.setCellValueFactory(new PropertyValueFactory<>("name"));
        c2.setCellValueFactory(new PropertyValueFactory<>("gold"));
        c3.setCellValueFactory(new PropertyValueFactory<>("silver"));
        c4.setCellValueFactory(new PropertyValueFactory<>("bronze"));
        table.setItems(Controller.countries);

    }
}
