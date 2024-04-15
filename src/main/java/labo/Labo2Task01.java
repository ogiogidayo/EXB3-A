package labo;

import java.util.Scanner;

public class Labo2Task01 {
    public static void main(String[] args) {
        String file = getFileName();
        System.out.println(file);
    }
    public static String getFileName(){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        return str;
    }

}

class SnowRecord{
    public String date;
    public int amount;

    public SnowRecord(String date, int amount){
        this.date   = date;
        this.amount = amount;
    }

    public String toString(){
        return this.date + "," + this.amount;
    }

    public boolean match (int year, int month) {

        if (this.date.startsWith(Integer.toString(year) + "/" + Integer.toString(month))){
            return true;
        }
        return false;
    }
}