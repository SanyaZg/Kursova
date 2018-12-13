package sample;

import java.io.Serializable;
import java.util.Random;

public class Athlete implements Serializable {
    int AthleteRegistrationNumber;
    String nameOfTheAthlete;
    String NameOfTheCountry;
    String sport;
    int Place;
    public Athlete(){}

    public Athlete(int athleteRegistrationNumber, String nameOfTheAthlete, String nameOfTheCountry, String sport, int place) {
        this.AthleteRegistrationNumber=(athleteRegistrationNumber);
        this.nameOfTheAthlete=(nameOfTheAthlete);
        this.NameOfTheCountry=(nameOfTheCountry);
        this.sport=(sport);
        this.Place=(place);
    }



    public void setDataTheAthlete(int AthleteRegistrationNumber, String nameOfTheAthlete, String NameOfTheCountry,
                                  String sport, short Place) {
        setAthleteRegistrationNumber(AthleteRegistrationNumber);
        setNameOfTheAthlete(nameOfTheAthlete);
        setNameOfTheCountry(NameOfTheCountry);
        setSport(sport);
        setPlace(Place);
    }

    public void getDataTheAthlete() {
        System.out.print("Спортсмен под номером " + this.getAthleteRegistrationNumber());
        System.out.print(" " + getNameOfTheAthlete());
        System.out.print(",представляющий страну " + this.getNameOfTheCountry());
        System.out.print(" в виде спорта " + this.getSport());
        System.out.print(" занял " + this.getPlace() + " место.");
    }

    public boolean setAthleteRegistrationNumber(int temp) {
        //предположим, номер спотртсена может иметь только 4 знака

        if (temp > 999 && temp < 10000){
            for (int i = 0; i <Controller.data.data.size() ; i++) {
                if(Controller.data.data.get(i).getAthleteRegistrationNumber()==temp)
                    return false;
            }
            this.AthleteRegistrationNumber = temp;
            return true;
        }

        return false;
    }

    public int getAthleteRegistrationNumber() {
        return this.AthleteRegistrationNumber;
    }

    public boolean setNameOfTheAthlete(String temp) {
        if (temp.length() > 3) {
            this.nameOfTheAthlete = temp;
            return true;
        }
        else
            return false;
    }

    public String getNameOfTheAthlete() {
        return this.nameOfTheAthlete;
    }

    public boolean setNameOfTheCountry(String temp) {

        for (Country c : Country.values()) {
            if (c.toString().equals(temp)) {
                this.NameOfTheCountry = temp;
                return true;
            }
        }
       return false;
    }

    public String getNameOfTheCountry() {
        return this.NameOfTheCountry;
    }

    public boolean setSport(String temp) {

        for (sports s : sports.values()) {
            if (s.toString().equals(temp)) {
                this.sport = temp;
                return true;
            }
        }
        return false;
    }

    public String getSport() {
        return this.sport;
    }

    public boolean setPlace(int temp) {
        //если в номере спортсмена лишь четыре знака, то таких спортсменов,как и мест - 9 998
        if (temp > 0 && temp < 9999) {

            this.Place = temp;
            return true;
        }
        return false;
    }

    public int getPlace() {
        return this.Place;
    }

    enum Country {
        USA,
        Ukraine,
        Russia,
        Bulgaria,
        Belarus,
        Germany,
        Britain,
        Japan,
        Monaco
    }

    enum sports {
        boxing,
        freestyle,
        wrestling,
        skiing,
        basketball,
        biathlon,
        swimming,
        fencing
    }
}
