package labo;

public class Labo2Task04 {
    public static void main(String[] args) {
        String test = "plus(mul(4,5),minus(2,div(3,2)))";
        Node t = new Node();
        t.makeTree(test);
        System.out.println(t.calculate());
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