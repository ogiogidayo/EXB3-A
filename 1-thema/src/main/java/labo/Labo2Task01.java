package labo;

import java.nio.file.Path;
import java.util.Scanner;

public class Labo2Task01 {
    public static void main(String[] args) {
        String file = getFileName();
        int i = 0;
        SnowRecord[] records=new SnowRecord[10000];
        try (Scanner sc_f = new Scanner(Path.of(file), "utf8")) {
            if (sc_f.hasNextLine()) {
                sc_f.nextLine();  // 最初の行を読み飛ばす
            }
            while (sc_f.hasNext()) {
                String date = sc_f.next();
                int amount = sc_f.nextInt();
                records[i] = new SnowRecord(date, amount);
                i += 1;
                if (sc_f.hasNextLine()) {
                    sc_f.nextLine();
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        for (SnowRecord record: records){
            if (record == null){
                break;
            }
            if (!record.match(2018, 1)){
                continue;
            }
            System.out.println(record.toString());
        }
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