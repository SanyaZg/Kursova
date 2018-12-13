package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Controller implements Serializable {
    public static Data data = new Data();
    public static ObservableList<Athlete> temp = FXCollections.observableArrayList();
    public static ObservableList<CountryVictories> countries = FXCollections.observableArrayList();
    public  static  Stage primaryStageAdd = new Stage();


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
    private MenuItem button1;

    @FXML
    private MenuItem button2;



    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    void initialize() {
        System.out.println("start");
        data.data=read();
        for (int i = 0; i < 10; i++) {
           // data.add(data.newRandomAthlete(i));
        }

        // data.data=data.fileInput();
        System.out.println("считал" + data.data.size());


        columm1.setCellValueFactory(new PropertyValueFactory<>("AthleteRegistrationNumber"));
        columm2.setCellValueFactory(new PropertyValueFactory<>("nameOfTheAthlete"));
        columm3.setCellValueFactory(new PropertyValueFactory<>("NameOfTheCountry"));
        columm4.setCellValueFactory(new PropertyValueFactory<>("sport"));
        columm5.setCellValueFactory(new PropertyValueFactory<>("Place"));

        TableView.setItems(data.data);
        write(data.data);

    }

    @FXML
    //призьоры в спорте
    void button1Click() throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/sample/res1.fxml"));
        primaryStage.setTitle("Призёры в спорте");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(new Scene(root, 400, 200));
        primaryStage.show();
    }

//метод оччьота по стране
    public void button2Click(ActionEvent actionEvent) throws IOException {

        Dialog<Pair<String, String>> pairDialog = new Dialog<>();

        pairDialog.setTitle("Укажите страну");
        TextField textField = new TextField();


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.setPadding(new Insets(20, 10, 10, 10));
        grid.add(new Label("Страна:"), 0, 0);
        grid.add(textField, 1, 0);

        ButtonType showButtonType = new ButtonType("Отобразить", ButtonBar.ButtonData.OK_DONE);
        ButtonType closeButtonType = new ButtonType("Отменить", ButtonBar.ButtonData.CANCEL_CLOSE);
        pairDialog.getDialogPane().getButtonTypes().addAll(showButtonType, closeButtonType);

        pairDialog.getDialogPane().setContent(grid);

        pairDialog.setResultConverter(btt -> {
            if (btt == showButtonType) {
                temp.clear();
                String searchCountry = textField.getText();
                for (int i = 0; i < data.data.size(); i++) {
                    if (data.data.get(i).getNameOfTheCountry().toLowerCase().equals(searchCountry.toLowerCase()))
                        if (data.data.get(i).getPlace() < 4)
                            temp.add(data.data.get(i));

                }
                if (temp.size() < 1) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Поиск не дал результатов =(");
                    alert.getButtonTypes().setAll(ButtonType.CANCEL);
                    alert.showAndWait();
                } else {
                    Stage primaryStage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/sample/res2.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    primaryStage.setTitle("Призёры страны " + searchCountry);
                    primaryStage.initModality(Modality.APPLICATION_MODAL);
                    primaryStage.setScene(new Scene(root, 600, 200));
                    primaryStage.show();
                }

            }

            return null;
        });


        pairDialog.showAndWait();
    }
//призьоры в спорте
    public void button3Click(ActionEvent actionEvent) throws IOException {
        temp.clear();
        countriesInizialise();
        Stage primaryStage = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("/sample/res3.fxml"));
        primaryStage.setTitle("Призёры в спорте");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(new Scene(root, 400, 200));
        primaryStage.show();

    }
//удаления по фамилии
    public void button4Click(ActionEvent actionEvent) {
        Dialog<Pair<String, String>> pairDialog = new Dialog<>();

        pairDialog.setTitle("Допинг-контроль");
        pairDialog.setHeaderText("Введите фамилию нарушителя:");
        TextField textField = new TextField();


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.setPadding(new Insets(20, 10, 10, 10));

        grid.add(textField, 1, 0);

        ButtonType deleteButtonType = new ButtonType("Дисквалифицировать", ButtonBar.ButtonData.OK_DONE);
        ButtonType closeButtonType = new ButtonType("Отменить", ButtonBar.ButtonData.CANCEL_CLOSE);
        pairDialog.getDialogPane().getButtonTypes().addAll(deleteButtonType, closeButtonType);
        pairDialog.getDialogPane().setMinWidth(350);
        pairDialog.getDialogPane().setContent(grid);

        pairDialog.setResultConverter(btt -> {
            boolean deleteFlag=false;
            if (btt == deleteButtonType) {
                for (int i = 0; i <data.data.size() ; i++) {
                    String searhAthlete=textField.getText();
                    if(data.data.get(i).getNameOfTheAthlete().toLowerCase().equals(searhAthlete.toLowerCase()))
                    {
                        data.data.remove(i);
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Операция успешна");
                        alert.setHeaderText("Нарушитель удален!");
                        alert.getButtonTypes().setAll(ButtonType.CANCEL);
                        alert.showAndWait();
                        deleteFlag=true;
                        write(data.data);
                        break;
                    }

                }
                if(!deleteFlag){
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Нарушитель не найден!");
                    alert.getButtonTypes().setAll(ButtonType.CANCEL);
                    alert.showAndWait();
                }


            }

            return null;
        });


        pairDialog.showAndWait();


    }
//считает количество стран
    public void countriesInizialise() {
        countries.clear();
        boolean detected = false;
        for (int i = 0; i < data.data.size(); i++) {
            detected = false;
            for (int j = 0; j < countries.size(); j++) {
                if (data.data.get(i).getNameOfTheCountry().toLowerCase().equals(countries.get(j).getName().toLowerCase()))
                    detected = true;
            }

            if (!detected) {
                CountryVictories country = new CountryVictories();
                country.setName(data.data.get(i).getNameOfTheCountry());

                countries.add(country);
            }

        }
       // countries.clear();
        counter();

    }
//считает мидали для стран
    public void counter() {

        for (int i = 0; i < Controller.data.data.size(); i++) {
            for (int j = 0; j < Controller.countries.size(); j++) {

                if (Controller.data.data.get(i).getNameOfTheCountry().toLowerCase().equals(Controller.countries.get(j).getName().toLowerCase())) {
                    if (Controller.data.data.get(i).getPlace() == 1)
                        Controller.countries.get(j).inrGold();
                    if (Controller.data.data.get(i).getPlace() == 2)
                        Controller.countries.get(j).inrSilver();
                    if (Controller.data.data.get(i).getPlace() == 3)
                        Controller.countries.get(j).inrBronze();
                }
            }

        }
    }
//добавления
    public void button5Click(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/sample/res4.fxml"));
        primaryStageAdd.setTitle("Добавление:");

        if(!primaryStageAdd.getModality().equals(Modality.APPLICATION_MODAL))

        primaryStageAdd.initModality(Modality.APPLICATION_MODAL);
        primaryStageAdd.setScene(new Scene(root, 500, 500));
        primaryStageAdd.show();
    }
//запись в файла
    public boolean write (ObservableList<Athlete> database) {
        System.out.println("write");
        try {

            FileOutputStream fos = new FileOutputStream("base.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new ArrayList<Athlete>(database));
            oos.close();
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public ObservableList<Athlete> read(){

        ArrayList<Athlete> database=null;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("base.bin")))
        {
            database=(ArrayList) ois.readObject();
            ObservableList<Athlete> databaseObservable =FXCollections.observableList(database);


            return databaseObservable;

        }
        catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        return null;
    }
}

