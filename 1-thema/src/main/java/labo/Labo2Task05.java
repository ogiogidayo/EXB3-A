package labo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Labo2Task05 {
    public static void main(String[] args) {
        List<String> fileLines = new ArrayList<>();
        Node n = new Node();
        String filename = args[0];
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            fileLines.addAll(lines);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        for (String line : fileLines) {
            n.makeTree(line);
            System.out.println(n.calculate());
        }
    }
}

class Node {
    private Node left;
    private Node right;
    private String func;
//    private static int[] x=new int[10000];
    private static HashMap<String, Integer> hm = new HashMap<>();

    public void makeTree(String statement){
        int sindex=statement.indexOf("(");
        int eindex=statement.lastIndexOf(")");
        if (sindex == -1 && eindex == -1){
            this.left = null;
            this.right = null;
            this.func = statement;
            return;
        } else {
            int count = 0;
            int mindex = 0;
            for (int i = 0; i < statement.length(); i++) {
                if (statement.charAt(i) == '('){
                    count++;
                } else if (statement.charAt(i) == ')') {
                    count--;
                } else if (statement.charAt(i) == ',' && count == 1) {
                    mindex = i;
                    break;
                }
            }
            String func = statement.substring(0, sindex).trim();
            this.func = func;
            left = new Node();
            left.makeTree(statement.substring(sindex+1,mindex).trim());
            right = new Node();
            right.makeTree(statement.substring(mindex+1,eindex).trim());
        }
    }
    public int calculate() {
        if (this.left == null || this.right == null){
            if (this.func.startsWith("$")){
                String key = this.func;
                return hm.get(key);
            }
            return Integer.parseInt(this.func);
        } else if (func.equals("plus")) {
            return this.left.calculate() + this.right.calculate();
        } else if (func.equals("minus")) {
            return this.left.calculate() - this.right.calculate();
        } else if (func.equals("mul")) {
            return this.left.calculate() * this.right.calculate();
        } else if (func.equals("div")) {
            return this.left.calculate() / this.right.calculate();
        } else if (func.equals("mod")) {
            return this.left.calculate() % this.right.calculate();
        } else if (func.equals("set")) {
            String key = this.left.func;
            int value = this.right.calculate();
            hm.put(key, value);
            return hm.get(key);
        }
        return 0;

    }
}