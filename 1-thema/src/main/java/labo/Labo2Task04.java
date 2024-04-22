package labo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Labo2Task04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> fileLines = new ArrayList<>();

        String filename = sc.nextLine();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            fileLines.addAll(lines);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        for (String line : fileLines) {
            Node n = new Node();
            n.makeTree(line);
            System.out.println(n.calculate());
        }
    }
}

class Node {
    public Node left;
    public Node right;
    public String func;

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
        } else return 0;
    }
}