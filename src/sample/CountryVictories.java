package sample;

public class CountryVictories {
    String name;
    public int gold=0;
    public int silver=0;
    public int bronze=0;

    public CountryVictories(String name){
        this.name=name;
    }
    public CountryVictories(){

    }
    public void inrSilver(){
        this.silver++;
    }
    public void inrGold(){
        this.gold++;
    }
    public void inrBronze(){
        this.bronze++;

    }
    public String getName(){
        return this.name;
    }
    public boolean setName(String temp) {
        boolean flag = false;
        for (Athlete.Country c : Athlete.Country.values()) {
            if (c.toString().equals(temp)) {
                this.name = temp;
                return true;
            }
        }
        return false;
    }

    public int getBronze() {
        return bronze;
    }

    public int getGold() {
        return gold;
    }

    public int getSilver() {
        return silver;
    }


}
