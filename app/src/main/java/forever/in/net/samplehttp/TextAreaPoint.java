package forever.in.net.samplehttp;

import android.widget.RelativeLayout;
import android.widget.TextView;

public class TextAreaPoint {
    public TextView textView;
    public String name;
    EllipsePoint point;

    TextAreaPoint(TextView init_textView, String init_name, EllipsePoint init_point){
        textView =init_textView;
        name = init_name;
        point = init_point;
    }

    void move_next(int steps){
        for(int i=0;i<steps;i++){
            point = point.next;
        }
        update_textarea();
    }
    void move_prev(int steps){
        for(int i=0;i<steps;i++){
            point = point.prev;
        }
        update_textarea();
    }
    void update_textarea(){
        RelativeLayout.LayoutParams linnear_lay = new RelativeLayout.LayoutParams(
                point.radius, point.radius);
        linnear_lay.leftMargin = point.draw_x; // отступ слева
        linnear_lay.topMargin = point.draw_y; // отступ сверху
        textView.setLayoutParams(linnear_lay);
        if (point.visible){
            textView.setText(name);
        } else {
            textView.setText("_");
        }
        //textView.setText(point.draw_x+"\n"+point.draw_y);
    }
    void is_clicked(){
        RelativeLayout.LayoutParams linnear_lay = new RelativeLayout.LayoutParams(
                2*point.radius, 2*point.radius);
        linnear_lay.leftMargin = point.draw_x; // отступ слева
        linnear_lay.topMargin = point.draw_y; // отступ сверху
        textView.setLayoutParams(linnear_lay);
        if (point.visible){
            textView.setText(name);
        } else {
            textView.setText("_");
        }
        //textView.setText(point.draw_x+"\n"+point.draw_y);
    }

}
