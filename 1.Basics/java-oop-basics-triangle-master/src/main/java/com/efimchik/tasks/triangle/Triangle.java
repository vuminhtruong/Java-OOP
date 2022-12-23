package com.efimchik.tasks.triangle;

public class Triangle {
    private Point a,b,c;
    public Triangle(Point a, Point b, Point c) {
        this.a=a;
        this.b=b;
        this.c=c;
        if(a==null||b==null||c==null)
            throw new RuntimeException();
        if(check(a, b)||check(a,c)||check(b,c))
            throw new RuntimeException();
        if(((lenght(a,b)+lenght(a,c)-(1.0E-15))<=lenght(b,c))||((lenght(a,b)+lenght(b,c)-(1.0E-15))<=lenght(a,c))||((lenght(c,b)+lenght(a,c)-(1.0E-15))<=lenght(b,a)))
            throw new RuntimeException();

    }
    public boolean check(Point a,Point b){
        return ((a.getX()==b.getX())&&(a.getY()==b.getY()));
    }
    public double lenght(Point a,Point b){
        return Math.sqrt(Math.pow((a.getX()-b.getX()),2)+Math.pow((a.getY()-b.getY()),2));
    }

    public double area() {
        double x=lenght(a,b);
        double y=lenght(b,c);
        double z=lenght(a,c);
        double p=(x+y+z)/2;
        return Math.sqrt(p*(p-x)*(p-y)*(p-z));
    }

    public Point centroid(){
        return new Point((a.getX()+b.getX()+c.getX())/3,(a.getY()+b.getY()+c.getY())/3);
    }

    public static void main(String[] args) {

    }

}
