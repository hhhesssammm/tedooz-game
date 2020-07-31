package tedooz;

import java.awt.*;

public class Mohre {
    private Color color;
    private boolean exist;
    private int i;
    private int j;
    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
    public boolean getExist() {
        return exist;
    }
    public void setExist(boolean exist) {
        this.exist = exist;
    }
    public void setI(int i) {
        this.i = i;
    }
    public int getI() {
        return i;
    }
    public void setJ(int j) {
        this.j = j;
    }
    public int getJ() {
        return j;
    }

    public Mohre(){
        exist=false;
    }
    /**
     * baraye % taiin=0
     * baraye $ taiin=1
     * baraye * taiin=2
     */
    public void tashkhisMohre(int taiin,int i,int j) {
        if (taiin == 0) {
            this.i=i-1;
            this.j=j+1;
        }
        if (taiin == 1) {
            this.i=i;
            this.j=j+1;
        }
        if (taiin == 2) {
            this.i=i+1;
            this.j=j+1;
        }
    }
}
