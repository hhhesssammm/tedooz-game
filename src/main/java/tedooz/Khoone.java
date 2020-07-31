package tedooz;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Khoone implements KeyListener, MouseListener {
    private Safhe safhe;
    /**
     * @param ERTEFA_KHOONE ertefa har khoone ba mohre
     * @param ARZ_KHOONE tool har khoone ba mohre
     * */
    private static int ERTEFA_KHOONE;
    private static int ARZ_KHOONE;

    public void setERTEFA_KHOONE(int ERTEFA_KHOONE) {
        this.ERTEFA_KHOONE = ERTEFA_KHOONE;
    }
    public static int getERTEFA_KHOONE() {
        return ERTEFA_KHOONE;
    }
    public void setARZ_KHOONE(int ARZ_KHOONE) {
        this.ARZ_KHOONE = ARZ_KHOONE;
    }
    public  static int getARZ_KHOONE() {
        return ARZ_KHOONE;
    }
    private  Integer x;
    private  Integer y;
    private Integer mouseX_Khoone=null;
    private Integer mouseY_khoone=null;
    private Mohre mohre;
    private boolean namadDarsadKhoone=false;
    private boolean namadDollarKhoone=false;
    private boolean namadXabdarKhoone=false;
    public void setX(Integer x) {
        this.x = x;
    }
    public  Integer getX() {
        return x;
    }
    public void setY(Integer y) {
        this.y = y;
    }
    public Integer getY() {
        return y;
    }
    public void setMohre(Mohre mohre) {
        this.mohre = mohre;
    }
    public Mohre getMohre() {
        return mohre;
    }
    public void setnamadDarsadKhoone(boolean namadDarsadKhoone) {
        this.namadDarsadKhoone = namadDarsadKhoone;
    }
    public boolean getnamadDarsadKhoone() {
        return namadDarsadKhoone;
    }
    public void setnamadDollarKhoone(boolean namadDollarKhoone) {
        this.namadDollarKhoone = namadDollarKhoone;
    }
    public boolean getnamadDollarKhoone() {
        return namadDollarKhoone;
    }
    public void setnamadXabdarKhoone(boolean namadXabdarKhoone) {
        this.namadXabdarKhoone = namadXabdarKhoone;
    }
    public boolean getnamadXabdarKhoone() {
        return namadXabdarKhoone;
    }

    public Khoone(){
        ERTEFA_KHOONE= (Safhe.getErtefaJpanel()/ Safhe.gettedadKhooneha());
        ARZ_KHOONE= (Safhe.getArzJpanel()/ Safhe.gettedadKhooneha());
        mohre =new Mohre();
    }

    public void paint(Graphics g) {
        Graphics2D g2=(Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.blue);
        g2.drawRect(x*ARZ_KHOONE, y*ERTEFA_KHOONE,ARZ_KHOONE, ERTEFA_KHOONE);
        if(mohre.getExist()==true){
            g2.setColor(mohre.getColor());
            g2.fillOval(mouseX_Khoone*ARZ_KHOONE,mouseY_khoone*ERTEFA_KHOONE,ARZ_KHOONE,ERTEFA_KHOONE);
        }
        g2.setColor(Color.RED);
        Font font = new Font ("", 1, 60);
        if(this.namadDarsadKhoone==true&& mohre.getExist()==true){
            g2.setFont(font);
            g2.drawString("%",mouseX_Khoone*ARZ_KHOONE+(ARZ_KHOONE*1/5),mouseY_khoone*ERTEFA_KHOONE+(ERTEFA_KHOONE*3/4));
        }
        if(this.namadDollarKhoone==true&& mohre.getExist()==true){
            g2.setFont(font);
            g2.drawString("$",mouseX_Khoone*ARZ_KHOONE+(ARZ_KHOONE*1/5),mouseY_khoone*ERTEFA_KHOONE+(ERTEFA_KHOONE*3/4));
        }
        if(this.namadXabdarKhoone==true&& mohre.getExist()==true){
            g2.setFont(font);
            g2.drawString("*",mouseX_Khoone*ARZ_KHOONE+(ARZ_KHOONE*1/5),mouseY_khoone*ERTEFA_KHOONE+(ERTEFA_KHOONE*3/4));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println((e.getX()-30)/(ARZ_KHOONE)+"=X  mouse  Y="+(e.getY()-50)/(ERTEFA_KHOONE));
        for(int i=0;i<5;i++){
            System.out.println("");
        }
        mouseX_Khoone= Safhe.getMouseX();
        mouseY_khoone= Safhe.getMouseY();
        mohre.setExist(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}