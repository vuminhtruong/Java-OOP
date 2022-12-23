package com.efimchick.tasks.segments;

public class Segment {
    private Point start,end;
    public Segment(Point start, Point end) { 
         this.start= start;
         this.end= end;
        if(start==null||end==null)
            throw new java.lang.RuntimeException();
        if((start.getX()==end.getX())&&(start.getY()==end.getY()))
            throw new java.lang.RuntimeException();
    }



    double length() {
        return Math.sqrt(Math.pow(start.getX()-end.getX(),2)+Math.pow(start.getY()-end.getY(),2));
    }

    Point middle() {
        return new Point((start.getX()+end.getX())/2,(start.getY()+end.getY())/2);
    }
    public Point getStart(){ 
        return start;
    }
    Point getEnd(){
        return end;
    }
    public boolean check(Point x,Point y){
        return ((x.getX()==y.getX())&&(x.getY()==y.getY()));
    }
    public double getLength(Point x,Point y){
        return Math.sqrt(Math.pow(x.getX()-y.getX(),2)+Math.pow(x.getY()-y.getY(),2));
    }
    public double area(Point a,Point b,Point c){
        Point ac = new Point(c.getX() - a.getX(),c.getY() - a.getY());
        Point ab = new Point(b.getX() - a.getX(),b.getY() - a.getY());
        return Math.abs(ac.getX() * ab.getY() - ac.getY() * ab.getX());
    }
    Point intersection(Segment another) {
        Point m=another.getStart();
        Point n=another.getEnd();
        double a=(start.getX()*end.getY()-start.getY()*end.getX());
        double b=(m.getX()*n.getY()-m.getY()*n.getX());
        double c=((start.getX()-end.getX())*(m.getY()-n.getY())-(start.getY()-end.getY())*(m.getX()-n.getX()));
        if(area(start,m,end) + area(start,n,end) > 0){
            if(area(start,m,end) + area(start,n,end)-area(start,n,m) -  area(end,n,m) !=0)
                return null;
        }
        if(c!=0){
            double x1=(a*(m.getX()-n.getX())-(start.getX()-end.getX())*b)/c;
            double y1=(a*(m.getY()-n.getY())-(start.getY()-end.getY())*b)/c;
            return new Point(x1,y1);
        }
        else{
            double k = length() + another.length();
            if(check(start,m)){
                if(k == getLength(end,another.end)){
                    return start;
                }
            }
            if(check(start,n)){
                if(k == getLength(end,another.start)){
                    return start;
                }
            }
            if(check(end,m)){
                if(k == getLength(start,another.end)){
                    return end;
                }
            }
            if(check(end,n)){
                if(k == getLength(start,another.start)){
                    return end;
                }
            }
            return null;
        }
    }
}

