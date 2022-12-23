package com.efimchick.tasks.figures;

public class ComparatorsCollection {


    public static int compareByArea(Figure lhs, Figure rhs){
        int a=Double.compare(lhs.area(),rhs.area());
        return a;
    }

    //TODO
    public static int compareByHorizontalStartPosition(Figure lhs, Figure rhs){
        Point l = lhs.StartPoint;
        Point r = rhs.StartPoint;
        int Y = Double.compare(l.getY(),r.getY());
        int X = Double.compare(l.getX(),r.getX());
        if(X == 0)
            return -Y;
        else
            return X;
    }

    //TODO
    public static int compareByHorizontalCenterPosition(Figure lhs, Figure rhs){
        Point l = lhs.centroid();
        Point r = rhs.centroid();
        int Y = Double.compare(l.getY(),r.getY());
        int X = Double.compare(l.getX(),r.getX());
        if(X == 0)
            return Y;
        else
            return X;
    }
}


