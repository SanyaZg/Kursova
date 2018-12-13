package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class Res4 {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField14;

    @FXML
    private TextField textField3;

    @FXML
    private TextField textField2;

    @FXML
    private TextField textField5;

    @FXML
    void initialize() {

    }

    public void addClick(ActionEvent actionEvent) {
        Athlete athlete = new Athlete();
        boolean flag = false;
        int temp = 0;
        try {
            temp = Integer.parseInt(textField1.getText());
        } catch (Exception exeption) {
            exeption.getMessage();
        }

        if (athlete.setAthleteRegistrationNumber(temp))
            if (athlete.setNameOfTheAthlete(textField2.getText()))
                if (athlete.setNameOfTheCountry(textField3.getText()))
                    if (athlete.setSport(textField14.getText())) {
                        try {
                            temp = Integer.parseInt(textField5.getText());
                        } catch (Exception e) {
                        }

                        if (athlete.setPlace(temp))
                            flag = true;
                    }
                    if(flag){
                        Controller.data.data.add(athlete);
                        new Controller().write(Controller.data.data);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Операция успешна");
                        alert.setHeaderText("Спортсмен добавлен");
                        alert.getButtonTypes().setAll(ButtonType.CANCEL);
                        alert.showAndWait();

                        Controller.primaryStageAdd.close();
                    }
                    else{
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Ошибка");
                        alert.setHeaderText("Спортсмен не добавлен\nПроверьте правильность ввода данных!");
                        alert.getButtonTypes().setAll(ButtonType.CANCEL);
                        alert.showAndWait();
                    }
    }
}
