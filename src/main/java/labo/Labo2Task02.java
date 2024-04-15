package labo;

public class Labo2Task02 {
}

class Complex {
    public double re;
    public double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double abs(){
        return Math.sqrt(Math.pow(re, 2) + Math.pow(im, 2));
    }

    public String toString() {
        return "(" + this.re + "," + this.im + ")";
    }

    public Complex plus(Complex other){
        double new_re = this.re + other.re;
        double new_im = this.im + other.im;
        Complex new_complex = new Complex(new_re, new_im);
        return new_complex;
    }

    public Complex minus(Complex other){
        double new_re = this.re - other.re;
        double new_im = this.im - other.im;
        Complex new_complex = new Complex(new_re, new_im);
        return new_complex;
    }

    public Complex mul(Complex other){
        double new_re = (this.re * other.re) - (this.im * this.im);
        double new_im = (this.re * other.im) - (this.im * this.re);
        Complex new_complex = new Complex(new_re, new_im);
        return new_complex;
    }

    public Complex div(Complex other){
        double new_re = ((this.re * other.re) - (this.im * this.im))/Math.pow(other.abs(), 2);
        double new_im = ((this.im * other.re) - (this.re * this.im))/Math.pow(other.abs(), 2);
        Complex new_complex = new Complex(new_re, new_im);
        return new_complex;
    }
}