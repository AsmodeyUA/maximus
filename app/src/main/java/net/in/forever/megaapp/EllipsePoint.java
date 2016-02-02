package net.in.forever.megaapp;

public class EllipsePoint {

    public final int x;
    public final int y;
    public final int draw_x;
    public final int draw_y;
    public final boolean stable;
    public final boolean visible;
    public final int radius;
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
}
