package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Data implements Serializable {

    public  static ObservableList<Athlete> data = FXCollections.observableArrayList();
    public int size(){
        return data.size();
    }
    public void add(Athlete athlete) {
        this.data.add(athlete);
    }
    public Athlete newRandomAthlete(int counter) {
        Random random=new Random();
        int temp;
        String tempS="";
        int AthleteRegistrationNumber=random.nextInt(10000-9001)+9000;
        temp=100-counter;
        String nameOfTheAthlete="AthleteName"+temp;

        Athlete.Country country[]=Athlete.Country.values();
        temp=random.nextInt(country.length);
        tempS=country[temp].toString();
        String NameOfTheCountry=tempS;

        Athlete.sports sports[]=Athlete.sports.values();
        temp=random.nextInt(sports.length);
        String sport=sports[temp].toString();
        int Place=random.nextInt(10)+1;

        Athlete athlete=new Athlete( AthleteRegistrationNumber,  nameOfTheAthlete,  NameOfTheCountry, sport,  Place);
        return athlete;

    }

    public void newAthlete() {
        Athlete athlete = new Athlete();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input Name Of The Athlete:");
        String tempString = scanner.nextLine();
        athlete.setNameOfTheAthlete(tempString);
        System.out.println("Input Athlete Registration Number(4 sumbol):");
        int tempInt = scanner.nextInt();
        athlete.setAthleteRegistrationNumber(tempInt);
        System.out.println("Input Name Of The Country:");
        tempString=scanner.next();
        athlete.setNameOfTheCountry(tempString);
        System.out.println("Input Sport:");
        tempString=scanner.next();
        athlete.setSport(tempString);
        System.out.println("Input Place:");
        tempInt=scanner.nextInt();
        athlete.setPlace((short) tempInt);

        data.add(athlete);

        //return athlete;
    }

    public void outData() {
        for (int i = 0; i < data.size(); i++) {
            System.out.println("Athlete Registration Number:\n" + data.get(i).AthleteRegistrationNumber +
                    "\nname Of The Athlete:\n" + data.get(i).nameOfTheAthlete +
                    "\nName Of The Country:\n" + data.get(i).NameOfTheCountry +
                    "\nsport:\n" + data.get(i).sport + "\nPlace:\n" + data.get(i).Place);
        }
    }
    //region lab8

    public void taskB() {

        Athlete.Country[] countries = Athlete.Country.values();
        ArrayList<CountryVictories> countryVictories = new ArrayList<CountryVictories>();
        for (int i = 0; i < countries.length; i++) {

            countryVictories.add(new CountryVictories(countries[i].toString()));
            //System.out.println(countryVictories.get(i).name);
        }

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).Place == 1) {
                for (int j = 0; j < countryVictories.size(); j++) {
                    if (data.get(i).getNameOfTheCountry().equals(countryVictories.get(j).getName()))
                        countryVictories.get(j).inrGold();
                }
            } else if (data.get(i).Place == 2) {
                for (int j = 0; j < countryVictories.size(); j++) {
                    if (data.get(i).getNameOfTheCountry().equals(countryVictories.get(j).getName()))
                        countryVictories.get(j).inrSilver();
                }
            } else if (data.get(i).Place == 3) {
                for (int j = 0; j < countryVictories.size(); j++) {
                    if (data.get(i).getNameOfTheCountry().equals(countryVictories.get(j).getName()))
                        countryVictories.get(j).inrBronze();
                }
            }
        }


        for (int i = 0; i < countryVictories.size(); i++) {
            if (countryVictories.get(i).gold > 0)
                System.out.println(countryVictories.get(i).name + ". gold medals:" + countryVictories.get(i).gold);
            if (countryVictories.get(i).silver > 0)
                System.out.println(countryVictories.get(i).name + ". silver medals:" + countryVictories.get(i).silver);

            if (countryVictories.get(i).bronze > 0)
                System.out.println(countryVictories.get(i).name + ". bronze medals:" + countryVictories.get(i).bronze);


        }
    }

    public void taskA() {
        Athlete.sports[] sports = Athlete.sports.values();
        boolean flag = false;
        for (int i = 0; i < sports.length; i++) {
            System.out.println(sports[i] + "\n--------------------------------------");
            for (int j = 0; j < data.size(); j++) {
                String s1 = data.get(j).getSport();
                String s = sports[i].toString();

                //System.out.println(sports[i]);
                if ((data.get(j).getPlace() < 4))

                    if (data.get(j).getSport().equals(sports[i].toString())) {
                        System.out.println("Name " + data.get(j).getNameOfTheAthlete() + ". Place:" + data.get(j).getPlace());
                        flag = true;
                    }
                System.out.println("\n----------------------------------");
            }
            if (!flag) {
                System.out.println("not winner.");
            }
        }
    }

    public void taskC() {
        System.out.println("Input country:");
        String c;
        Scanner scanner = new Scanner(System.in);
        c = scanner.next();
        Athlete.Country[] countries = Athlete.Country.values();
        boolean flag = false;
        for (int i = 0; i < countries.length; i++) {
            if (countries[i].toString().equals(c)) {
                flag = true;
                break;
            }
        }
        if (flag) {
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getNameOfTheCountry().equals(c)) {
                    data.get(i).getDataTheAthlete();
                    System.out.println();

                }
            }
            System.out.println();
        } else
            System.out.println("not country");


    }

    public void taskD() {
        System.out.println("Input name of the Athlete:");
        Scanner scanner = new Scanner(System.in);
        String deleteName = scanner.nextLine();
        boolean flag = false;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getNameOfTheAthlete().equals(deleteName)) {
                System.out.println("Athlete detected!");
                data.remove(i);
                break;
            }

        }

    }

    //endregion


}
