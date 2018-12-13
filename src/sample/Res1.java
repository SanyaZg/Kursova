package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Res1 {
    public  static ObservableList<Athlete> dataVictiry = FXCollections.observableArrayList();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Athlete> table;

    @FXML
    private TableColumn<Athlete, String> sportCollum;

    @FXML
    private TableColumn<Athlete, String> victoryCollum;

    @FXML
    private TableColumn<Athlete, Integer> placeCollum;

    @FXML
    void initialize() {
        if(feetback()){
            sportCollum.setCellValueFactory(new PropertyValueFactory<>("sport"));
            victoryCollum.setCellValueFactory(new PropertyValueFactory<>("nameOfTheAthlete"));
            placeCollum.setCellValueFactory(new PropertyValueFactory<>("Place"));
            table.setItems(dataVictiry);
        }

    }
    boolean feetback(){
        ObservableList<Athlete> local_data = FXCollections.observableArrayList();
        local_data=Controller.data.data;
        dataVictiry.clear();

        System.out.println(local_data.size());
        Athlete.sports[] sports = Athlete.sports.values();
        boolean flag = false;

        for (int i = 0; i < sports.length; i++) {

            for (int j = 0; j <local_data.size(); j++) {
                String s1 = local_data.get(j).getSport();
                String s = sports[i].toString();
                if ((local_data.get(j).getPlace() < 4))

                    if (local_data.get(j).getSport().equals(sports[i].toString())) {
                        dataVictiry.add(local_data.get(j));
                        flag = true;

                    }

            }

        }
        if (!flag) {
            return false;
        }
        return true;
    }
}
