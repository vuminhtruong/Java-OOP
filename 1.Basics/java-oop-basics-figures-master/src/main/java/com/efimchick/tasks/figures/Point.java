package com.efimchick.tasks.figures;

class Point {
    private Double x;
    private Double y;
    private static double q = 0.00000001;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString(){
            String ret = "";
            ret += "(";
            ret += Double.toString(x);
            ret += ",";
            ret += Double.toString(y);
            ret += ")";
            return ret;
        }
    public boolean equa(Point ano){
        return (Math.abs(x - ano.getX()) <= q && Math.abs(y - ano.getY()) <= q);
    }


}

