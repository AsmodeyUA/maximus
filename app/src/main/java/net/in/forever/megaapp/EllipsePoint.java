package net.in.forever.megaapp;

public class EllipsePoint {

    public final int x;
    public final int y;
    public int draw_x;
    public int draw_y;
    public final boolean stable;
    public final boolean visible;
    public int radius;
    public EllipsePoint prev;
    public EllipsePoint next;

    EllipsePoint(int init_x,int init_y,boolean init_stable,int init_radius,boolean init_visible){
        x = init_x;
        y = init_y;
        stable = init_stable;
        visible = init_visible;
        radius = init_radius;
        draw_x = x- radius /2;
        draw_y = y- radius /2;
        next=null;
        prev=null;
    }

    double sqrDistanceTo(EllipsePoint toPoint){
        return (this.x-toPoint.x)*(this.x-toPoint.x)+(this.y-toPoint.y)*(this.y-toPoint.y);
    }

    EllipsePoint setNewRadius(int init_radius){
        this.radius =init_radius;
        this.draw_x = x- this.radius /2;
        this.draw_y = y- this.radius /2;
        return this;
    }


}
