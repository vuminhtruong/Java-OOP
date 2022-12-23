package com.efimchick.tasks.figures;

import java.util.ArrayList;

public class Circle extends Figure {
    private Point center;
    private double radius;
    private static double pi = 3.1415926535897932384626433;
    public Circle(Point center,double radius){
        this.center=center;
        this.radius=radius;
        if(center==null||radius<=0)
            throw new RuntimeException();
        this.type="Circle";
        this.StartPoint =new Point(center.getX() - radius,center.getY());
        list=new ArrayList<Point>();
        list.add(center);


    }
    @Override
    public double area() {
        return pi * radius * radius;
    }

    @Override
    public Point centroid() {
        return center;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if(list.size()!=figure.list.size())
            return false;
        else
            return center.equa(figure.centroid())&& Math.abs(area()-figure.area()) <=0.00000001;
    }
    public String toString(){
        String a=Type()+"[";
        a +=center.toString();
        a += Double.toString(radius) ;
        a+="]";
        return a;
    }
}
