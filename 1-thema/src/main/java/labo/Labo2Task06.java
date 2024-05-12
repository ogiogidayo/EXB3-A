package labo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Labo2Task06 {
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
            n.calculate();
        }
    }
}

class Node {
    private Node left;
    private Node right;
    private String func;
    //    private static int[] x=new int[10000];
    private static HashMap<String, MyVal> hm = new HashMap<>();

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
    public MyVal calculate() {
        if (this.left == null || this.right == null){
            return this.calculateForLeaf();
        }
        MyVal v_left = this.left.calculate();
        MyVal v_right = this.right.calculate();
        if (func.equals("plus")) {
            if (v_left instanceof MyDouble || v_right instanceof MyDouble) {
                return new MyDouble(v_left.toDouble() + v_right.toDouble());
            } else {
                return new MyInt(v_left.toInt() + v_right.toInt());
            }
        } else if (func.equals("minus")) {
            if (v_left instanceof MyDouble || v_right instanceof MyDouble){
                return new MyDouble(v_left.toDouble() - v_right.toDouble());
            } else {
                return new MyInt(v_left.toInt() - v_right.toInt());
            }
        } else if (func.equals("mul")) {
            if (v_left instanceof MyDouble || v_right instanceof MyDouble){
                return new MyDouble(v_left.toDouble() * v_right.toDouble());
            } else {
                return new MyInt(v_left.toInt() * v_right.toInt());
            }
        } else if (func.equals("div")) {
            if (v_left instanceof MyDouble || v_right instanceof MyDouble){
                return new MyDouble(v_left.toDouble() / v_right.toDouble());
            } else {
                return new MyInt(v_left.toInt() / v_right.toInt());
            }
        } else if (func.equals("mod")) {
            return new MyInt(v_left.toInt() % v_right.toInt());
        } else if (func.equals("set")) {
            String key = this.left.func;
            MyVal value = this.right.calculate();
            hm.put(key, value);
            return hm.get(key);
        } else if (func.equals("join")) {
            return new MyString(v_left.toString() + v_right.toString());
        } else if (func.equals("print")) {
            System.out.println(v_left);
        }
        return null;
    }

    public MyVal calculateForLeaf() {
        if (this.func.contains("$"))
            return hm.get(func);
        else if (this.func.contains("\"")) {
            String s = this.func.substring(1, this.func.length() - 1);
            return new MyString(s);
        } else if (this.func.contains(".")) {
            double num = Double.parseDouble(this.func);
            return new MyDouble(num);
        } else {
            int num = Integer.parseInt(this.func);
            return new MyInt(num);
        }
    }
}


abstract class MyVal{
    abstract public int toInt();
    abstract public double toDouble();
    abstract public String toString();
}

class MyInt extends MyVal {
    private int v;

    public MyInt(int v){
        this.v = v;
    }

    @Override
    public int toInt() {
        return v;
    }

    @Override
    public double toDouble() {
        return v;
    }

    @Override
    public String toString() {
        return Integer.toString(v);
    }
}

class MyDouble extends MyVal {
    private double v;

    public MyDouble(double v){
        this.v=v;
    }

    @Override
    public int toInt() {
        return (int)v;
    }

    @Override
    public double toDouble() {
        return v;
    }

    @Override
    public String toString() {
        return Double.toString(v);
    }
}

class MyString extends MyVal {
    private String v;

    public MyString(String v){
        this.v=v;
    }

    @Override
    public int toInt() {
        return 0;
    }

    @Override
    public double toDouble() {
        return 0;
    }

    @Override
    public String toString() {
        return v;
    }
}
