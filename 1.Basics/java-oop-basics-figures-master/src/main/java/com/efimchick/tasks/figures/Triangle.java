package com.efimchick.tasks.figures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Triangle extends Figure {
    private Point a,b,c;
    public Triangle(Point a,Point b,Point c){
        this.a=a;
        this.b=b;
        this.c=c;
        if(a==null||b==null||c==null)
            throw new RuntimeException();
        if(checkTri())
            throw new RuntimeException();
    list = new ArrayList<Point>();
    list.add(a);
    list.add(b);
    list.add(c);
        Collections.sort(list, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return Double.compare(p1.getX(), p2.getX());
            }
        });
    this.type = "Triangle";
    this.StartPoint = list.get(0);
    }
    boolean checkTri(){
        return (((lenght(a,b)+lenght(a,c)-(1.0E-15))<=lenght(b,c))||((lenght(a,b)+lenght(b,c)-(1.0E-15))<=lenght(a,c))||((lenght(c,b)+lenght(a,c)-(1.0E-15))<=lenght(b,a)));

    }
    public double lenght(Point a,Point b){
        return Math.sqrt(Math.pow((b.getX()-a.getX()),2)+Math.pow((b.getY()-a.getY()),2));
    }

    @Override
    public double area() {
        Point ab=new Point(b.getX()-a.getX(),b.getY()-a.getY());
        Point ac=new Point(c.getX()-a.getX(),c.getY()-a.getY());
        return 0.5*Math.abs(ab.getX()*ac.getY()-ac.getX()*ab.getY());
    }

    @Override
    public Point centroid() {
        return new Point((a.getX()+b.getX()+c.getX())/3,(a.getY()+b.getY()+c.getY())/3);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if(list.size()!=figure.list.size())
            return false;
        else{
            int k=0;
        for(int i=0;i<list.size();i++){
            for(int j=0;j<figure.list.size();j++){
                if(list.get(i).equa(figure.list.get(j))){
                    k++;
                    break;
                }
            }
        }
        if(k==3)
            return true;
    }
        return false;
    }
    public String toString(){
        String a=Type()+"[";
        for(int i=0;i<3;i++)
             a +=list.get(i).toString();
        a+="]";
        return a;
    }
}

