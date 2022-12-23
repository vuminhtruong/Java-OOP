package com.efimchick.tasks.figures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Quadrilateral extends Figure {
    private Triangle u,v,k,h;
    private static  final double lim = 0.00000000001;
    public Quadrilateral(Point a,Point b,Point c,Point d){
        if(a==null||b==null||c==null||d==null)
            throw new RuntimeException();
        list = new ArrayList<Point>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        u=new Triangle(list.get(0),list.get(1),list.get(2));
        v=new Triangle(list.get(0),list.get(3),list.get(2));
        k=new Triangle(list.get(1),list.get(2),list.get(3));
        h=new Triangle(list.get(0),list.get(1),list.get(3));
        if(!check(list.get(0),list.get(1),list.get(2),list.get(3)))
            throw new RuntimeException();
        if(!check(list.get(1),list.get(2),list.get(0),list.get(3)))
            throw new RuntimeException();
        if(!check(list.get(2),list.get(3),list.get(0),list.get(1)))
            throw new RuntimeException();
        if(list.size() != 4)
            throw new RuntimeException();
        this.StartPoint = list.get(0);
        this.type = "Quadrilateral";
    }
    boolean check(Point x,Point y,Point u,Point v){
        Point a = new Point(y.getX() - x.getX(),y.getY() - x.getY());
        Point b= new Point(-a.getY(),a.getX());
        double m = b.getX() * (u.getX() - x.getX()) + b.getY()*(u.getY() - x.getY());
        double n = b.getX() * (v.getX() - x.getX()) + b.getY()*(v.getY() - x.getY());
        return  (m*n > 0.0);
    }
    ArrayList <Point> Convex(ArrayList <Point> ds){
        Collections.sort(ds, new Comparator<Point>() {
            @Override
            public int compare(Point x1, Point x2) {
                int a = Double.compare(x1.getX(),x2.getX());
                if(a==0)
                    a = Double.compare(x1.getY(),x2.getY());
                return a;
            }
        });
        ArrayList <Point> ll = new ArrayList<Point>();
        for(int i = 0;i < ds.size();++i){
            while(ll.size() >= 2 && kt(ll.get(ll.size() - 2),ll.get(ll.size() - 1),ds.get(i))){
                ll.remove(ll.size() - 1);
            }
            ll.add(ds.get(i));
        }
        ll.remove(ll.size() - 1);
        int k = ll.size();
        for(int i = ds.size() - 1;i >= 0;--i){
            while(ll.size() - k >= 2 && kt(ll.get(ll.size() - 2),ll.get(ll.size() - 1),ds.get(i))){
                ll.remove(ll.size() - 1);
            }
            ll.add(ds.get(i));
        }
        ll.remove(ll.size() - 1);
        return ll;
    }

    private boolean kt(Point x,Point y,Point z){
        return (y.getX() - x.getX()) * (z.getY() - y.getY()) - (z.getX() - y.getX()) * (y.getY() - x.getY()) >=0;
    }
    @Override
    public double area() {
        return u.area()+v.area();
    }

    @Override
    public Point centroid() {
        Point g1=k.centroid();
        Point g2=v.centroid();
        Point g3=h.centroid();
        Point g4=u.centroid();
        double i = (g1.getY() - g3.getY()) / (g1.getX() - g3.getX());
        double j = (g4.getY() - g2.getY()) / (g4.getX() - g2.getX());
        double m = g1.getY() - i * g1.getX();
        double n = g4.getY() - j * g4.getX();
        double x = (n-m) / (i - j);
        double y = i * x + m;
        if(Math.abs(0 - x) <= lim)
            x = 0;
        if(Math.abs(0 - y) <= lim)
            y = 0;
        return new Point(x,y);

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
            if(k==4)
                return true;
        }
        return false;
    }
    public String toString(){
        String a=Type()+"[";
        for(int i=0;i<4;i++)
            a +=list.get(i).toString();
        a+="]";
        return a;
    }
}
