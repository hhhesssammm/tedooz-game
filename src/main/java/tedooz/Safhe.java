package tedooz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;

public class Safhe extends JPanel implements Runnable, MouseListener, KeyListener {

    private static Integer tedadKhooneha=8;

    private static Integer ERTEFA_JPANEL;
    private static Integer ARZ_JPANEL;

    private Color rang1;
    private Color rang2;
    private Khoone khoone[][]=new Khoone[tedadKhooneha][tedadKhooneha];


    private static Integer mouseX=null;
    private static Integer mouseY=null;
    
    private Integer harekat=64;
    /**
     * index 0 baraye % hast
     * index 1 baraye $ hast
     * index 2 baraye * hast
     */
    private boolean mohreVizheSiah[]=new boolean[3];
    private boolean mohreVizheSabz[]=new boolean[3];

    private boolean namadDarsad=false;
    private boolean namadDollar=false;
    private boolean namadXabdar=false;

    private JLabel elamEtiaz1=new JLabel();
    private JLabel elamEtiaz2=new JLabel();
    private JLabel label=new JLabel();
    private JLabel label1=new JLabel();
    public static void setMouseX(Integer mouseX) {
        Safhe.mouseX = mouseX;
    }
    public static Integer getMouseX() {
        return mouseX;
    }
    public static void setMouseY(Integer mouseY) {
        Safhe.mouseY = mouseY;
    }
    public static Integer getMouseY() {
        return mouseY;
    }
    public static void setErtefaJpanel(Integer ertefaJpanel) {
        ERTEFA_JPANEL = ertefaJpanel;
    }
    public static Integer getErtefaJpanel() {
        return ERTEFA_JPANEL;
    }
    public static void setArzJpanel(Integer arzJpanel) {
        ARZ_JPANEL = arzJpanel;
    }
    public static Integer getArzJpanel() {
        return ARZ_JPANEL;
    }
    public void settedadKhooneha(Integer  tedadKhooneha) {
        this.tedadKhooneha = tedadKhooneha;
    }
    public static int gettedadKhooneha() {
        return tedadKhooneha;
    }
    private Thread tedoozThread;
    public void addNotify(){
        super.addNotify();
        tedoozThread=new Thread(this);
        tedoozThread.start();
    }


    public boolean testEmpity(int i,int j){
        if(i>=tedadKhooneha||j>=tedadKhooneha||i<0||j<0){
            return false;
        }
        return true;
}

    public void testVizhe(){
        String text=new String();
        if(mohreVizheSabz[0]){
            text+="sabz: % is ready.  ";
        }
        if(mohreVizheSabz[1]){
            text+="sabz: $ is ready.  ";
        }
        if(mohreVizheSabz[2]){
            text+="sabz: * is ready.  ";
        }
        text+="          ";
        if(mohreVizheSiah[0]){
            text+="siah:  % is ready.  ";
        }
        if(mohreVizheSiah[1]){
            text+="siah:  $ is ready.  ";
        }
        if(mohreVizheSiah[2]){
            text+="siah:  * is ready.  ";
        }
        getShow().setText(text);
    }

    public int testEmtiaz(Integer fullOf[][]){
        int emtiaz=0;
        for(int i=0;i<tedadKhooneha;i++){
            for(int j=0;j<tedadKhooneha;j++){
                if(fullOf[i][j]==1){
                    /**
                     * moraba
                     */
                    if((testEmpity(i+3,j+3)==true)&&(fullOf[i+3][j]==1&&fullOf[i][j+3]==1&fullOf[i+3][j+3]==1)){
                        emtiaz++;
                        fullOf[i+3][j]=-1;
                        fullOf[i][j+3]=-1;
                        fullOf[i+3][j+3]=-1;
                    }
                    /**
                     * movarab 1
                     */
                    if((testEmpity(i-3,j+3)==true)&&fullOf[i-1][j+1]==1&&fullOf[i-2][j+2]==1&fullOf[i-3][j+3]==1){
                        emtiaz++;
                        fullOf[i-1][j+1]=-1;
                        fullOf[i-2][j+2]=-1;
                        fullOf[i-3][j+3]=-1;
                    }
                    /**
                     * movarab 2
                     */
                    if((testEmpity(i+3,j+3)==true)&&fullOf[i+1][j+1]==1&&fullOf[i+2][j+2]==1&fullOf[i+3][j+3]==1){
                        emtiaz++;
                        fullOf[i+1][j+1]=-1;
                        fullOf[i+2][j+2]=-1;
                        fullOf[i+3][j+3]=-1;
                    }
                    /**
                     * amoodi
                     */
                    if((testEmpity(i,j+3)==true)&&fullOf[i][j+1]==1&&fullOf[i][j+2]==1&fullOf[i][j+3]==1){
                        emtiaz++;
                        fullOf[i][j+1]=-1;
                        fullOf[i][j+2]=-1;
                        fullOf[i][j+3]=-1;
                    }
                    /**
                     * ofoghi
                     */
                    if((testEmpity(i+3,i)==true)&&fullOf[i+1][j]==1&&fullOf[i+2][j]==1&fullOf[i+3][j]==1){
                        emtiaz++;
                        fullOf[i+1][j]=-1;
                        fullOf[i+2][j]=-1;
                        fullOf[i+3][j]=-1;
                    }
                }
            }
        }
        return emtiaz;
    }

    /**
     * @param color "0" = siah va "1" = sabz
     * @return
     */
    
    public int emtiaz(Khoone khoone[][], int color){
        Integer fullOfGreen[][]=new Integer[tedadKhooneha][tedadKhooneha];
        Integer fullOfBlack[][]=new Integer[tedadKhooneha][tedadKhooneha];
        for(int i=0;i<tedadKhooneha;i++){
            for(int j=0;j<tedadKhooneha;j++){
                if(khoone[i][j].getMohre().getColor()==Color.black){
                    fullOfBlack[i][j]=1;
                }
                if(khoone[i][j].getMohre().getColor()!=Color.black){
                    fullOfBlack[i][j]=0;
                }
                if(khoone[i][j].getMohre().getColor()==Color.green){
                    fullOfGreen[i][j]=1;
                }
                if(khoone[i][j].getMohre().getColor()!=Color.green){
                    fullOfGreen[i][j]=0;
                }
            }
        }
        if(color==0){
            return testEmtiaz(fullOfBlack);
        }
        if(color==1){
            return testEmtiaz(fullOfGreen);
        }
        return -1;
    }
    private JLabel show=new JLabel();


    public JLabel getShow() {
        return show;
    }
    public Safhe(){
        show.setBounds(5, 5,700 , 20);
        Bazi.getjFrame().add(show);
        testVizhe();
        elamEtiaz2.setBounds(550,40,200,20);
        elamEtiaz2.setBackground(Color.BLACK);
        Bazi.getjFrame().add(elamEtiaz2);
        elamEtiaz1.setBounds(550,90,200,20);
        elamEtiaz1.setBackground(Color.BLACK);
        Bazi.getjFrame().add(elamEtiaz1);
        label.setBounds(550,110,200,30);
        label.setBackground(Color.RED);
        Bazi.getjFrame().add(label);
        label1.setBounds(550,150,200,20);
        label1.setBackground(Color.RED);
        Bazi.getjFrame().add(label1);
        for(int i=0;i<3;i++){
            mohreVizheSiah[i]=true;
            mohreVizheSabz[i]=true;
        }
        ERTEFA_JPANEL = Bazi.getARZ_JFRAME() - Bazi.getARZ_JFRAME() / 3;
        ARZ_JPANEL = Bazi.getERTEFA_JFRAME() - Bazi.getERTEFA_JFRAME() / 3;
        for(int i=0;i<tedadKhooneha;i++){
            for(int j=0;j<tedadKhooneha;j++){
                khoone[i][j]=new Khoone();
                khoone[i][j].setX(i);
                khoone[i][j].setY(j);
            }
        }

        /**
         * taiin shoro ke kodom bazikone 0=siah     1=sabz
         */
        Random random=new Random();
        int rand=random.nextInt(2);
        if(rand==0){
            rang1=Color.black;
            rang2=Color.GREEN;
        }
        if(rand==1){
            rang1=Color.GREEN;
            rang2=Color.black;
        }
        /**taiin size jpanel (safhe bazi)*/
        System.out.println(ARZ_JPANEL+" =arz Jpanel");
        System.out.println(ERTEFA_JPANEL+" =ertefa Jpanel");
        for(int i=0;i<5;i++){
            System.out.println("");
        }

        setBounds(20, 40, ARZ_JPANEL, ARZ_JPANEL);
        /**
         * taiin rang background(board) safhe ke kereme
         */
        setBackground(Color.white);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for(int i=0;i<tedadKhooneha;i++){
            for(int j=0;j<tedadKhooneha;j++){
                khoone[i][j].paint(g2);
            }
        }
    }

    private void move() {
    }

    private int testRadif(int x){
        int j=0;
        for(j=0;j<tedadKhooneha;j++){
            if(khoone[x][j].getMohre().getColor()!=null){
                break;
            }
        }
        return j-1;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int checkingColor=0;
        mouseX=(e.getX()-30)/(Khoone.getARZ_KHOONE());
        mouseY=testRadif(mouseX);
        if(khoone[mouseX][mouseY].getMohre().getColor()==null&&mouseY>=0) {
            if (harekat % 2 == 0) {
                khoone[mouseX][mouseY].getMohre().setColor(rang1);
                checkingColor=1;
            }
            if (harekat % 2 == 1) {
                khoone[mouseX][mouseY].getMohre().setColor(rang2);
                checkingColor=-1;
            }
            harekat--;
        }
        if(mouseY>=0){
            khoone[mouseX][mouseY].mouseClicked(e);
        }else {
            label.setText("your position is wrong");
        }
        if(checkingColor==1) {
            /**
             * VK_0 baraye % hast
             */
            if (this.namadDarsad&&rang1==Color.black&&mohreVizheSiah[0]) {
                khoone[mouseX][mouseY].setnamadDarsadKhoone(true);
                khoone[mouseX][mouseY].getMohre().tashkhisMohre(0,mouseX,mouseY);
                if(testEmpity(khoone[mouseX][mouseY].getMohre().getI(), khoone[mouseX][mouseY].getMohre().getJ()) && khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().getExist()){
                    khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().setColor(rang1);
                }
                mohreVizheSiah[0]=false;
            }
            if (this.namadDarsad &&rang1==Color.green&&mohreVizheSabz[0]) {
                khoone[mouseX][mouseY].setnamadDarsadKhoone(true);
                khoone[mouseX][mouseY].getMohre().tashkhisMohre(0,mouseX,mouseY);
                if(testEmpity(khoone[mouseX][mouseY].getMohre().getI(), khoone[mouseX][mouseY].getMohre().getJ()) && khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().getExist()){
                    khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().setColor(rang1);
                }
                mohreVizheSabz[0]=false;
            }
            this.namadDarsad = false;
            /**
             * VK_1 baraye $ hast
             */
            if (this.namadDollar&&rang1==Color.black&&mohreVizheSiah[1]) {
                khoone[mouseX][mouseY].setnamadDollarKhoone(true);
                khoone[mouseX][mouseY].getMohre().tashkhisMohre(1,mouseX,mouseY);
                if(testEmpity(khoone[mouseX][mouseY].getMohre().getI(), khoone[mouseX][mouseY].getMohre().getJ())==true
                        && khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().getExist()==true){
                    khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().setColor(rang1);
                }
                mohreVizheSiah[1]=false;
            }
            if (this.namadDollar &&rang1==Color.green&&mohreVizheSabz[1]) {
                khoone[mouseX][mouseY].setnamadDollarKhoone(true);
                khoone[mouseX][mouseY].getMohre().tashkhisMohre(1,mouseX,mouseY);
                if(testEmpity(khoone[mouseX][mouseY].getMohre().getI(), khoone[mouseX][mouseY].getMohre().getJ())==true
                        && khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().getExist()==true){
                    khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().setColor(rang1);
                }
                mohreVizheSabz[1]=false;
            }
            this.namadDollar = false;
            /**
             * VK_2 baraye * hast
             */
            if (this.namadXabdar &&rang1==Color.black&&mohreVizheSiah[2]) {
                khoone[mouseX][mouseY].setnamadXabdarKhoone(true);
                khoone[mouseX][mouseY].getMohre().tashkhisMohre(2,mouseX,mouseY);
                if(testEmpity(khoone[mouseX][mouseY].getMohre().getI(), khoone[mouseX][mouseY].getMohre().getJ()) && khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().getExist()){
                    khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().setColor(rang1);
                }
                mohreVizheSiah[2]=false;
            }
            if (this.namadXabdar&&rang1==Color.green&&mohreVizheSabz[2]) {
                khoone[mouseX][mouseY].setnamadXabdarKhoone(true);
                khoone[mouseX][mouseY].getMohre().tashkhisMohre(2,mouseX,mouseY);
                if(testEmpity(khoone[mouseX][mouseY].getMohre().getI(), khoone[mouseX][mouseY].getMohre().getJ()) && khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().getExist()){
                    khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().setColor(rang1);
                }
                mohreVizheSabz[2]=false;
            }
            this.namadXabdar = false;
        }

        if(checkingColor==-1) {
            /**
             * VK_0 baraye % hast
             */
            if (this.namadDarsad &&rang2==Color.black&&mohreVizheSiah[0]) {
                khoone[mouseX][mouseY].setnamadDarsadKhoone(true);
                khoone[mouseX][mouseY].getMohre().tashkhisMohre(0,mouseX,mouseY);
                if(testEmpity(khoone[mouseX][mouseY].getMohre().getI(), khoone[mouseX][mouseY].getMohre().getJ()) && khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().getExist()){
                    khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().setColor(rang2);
                }
                mohreVizheSiah[0]=false;
            }
            if (this.namadDarsad &&rang2==Color.green&&mohreVizheSabz[0]) {
                khoone[mouseX][mouseY].setnamadDarsadKhoone(true);
                khoone[mouseX][mouseY].getMohre().tashkhisMohre(0,mouseX,mouseY);
                if(testEmpity(khoone[mouseX][mouseY].getMohre().getI(), khoone[mouseX][mouseY].getMohre().getJ()) && khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().getExist()){
                    khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().setColor(rang2);
                }
                mohreVizheSabz[0]=false;
            }
            this.namadDarsad = false;
            /**
             * VK_1 baraye $ hast
             */
            if (this.namadDollar &&rang2==Color.black&&mohreVizheSiah[1]) {
                khoone[mouseX][mouseY].setnamadDollarKhoone(true);
                khoone[mouseX][mouseY].getMohre().tashkhisMohre(1,mouseX,mouseY);
                if(testEmpity(khoone[mouseX][mouseY].getMohre().getI(), khoone[mouseX][mouseY].getMohre().getJ()) && khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().getExist()){
                    khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().setColor(rang2);
                }
                mohreVizheSiah[1]=false;
            }
            if (this.namadDollar &&rang2==Color.green&&mohreVizheSabz[1]) {
                khoone[mouseX][mouseY].setnamadDollarKhoone(true);
                khoone[mouseX][mouseY].getMohre().tashkhisMohre(1,mouseX,mouseY);
                if(testEmpity(khoone[mouseX][mouseY].getMohre().getI(), khoone[mouseX][mouseY].getMohre().getJ()) && khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().getExist()){
                    khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().setColor(rang2);
                }
                mohreVizheSabz[1]=false;
            }
            this.namadDollar = false;
            /**
             * VK_2 baraye * hast
             */
            if (this.namadXabdar &&rang2==Color.black&&mohreVizheSiah[2]) {
                khoone[mouseX][mouseY].setnamadXabdarKhoone(true);
                khoone[mouseX][mouseY].getMohre().tashkhisMohre(2,mouseX,mouseY);
                if(testEmpity(khoone[mouseX][mouseY].getMohre().getI(), khoone[mouseX][mouseY].getMohre().getJ()) && khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().getExist()){
                    khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().setColor(rang2);
                }
                mohreVizheSiah[2]=false;
            }
            if (this.namadXabdar &&rang2==Color.green&&mohreVizheSabz[2]) {
                khoone[mouseX][mouseY].setnamadXabdarKhoone(true);
                khoone[mouseX][mouseY].getMohre().tashkhisMohre(2,mouseX,mouseY);
                if(testEmpity(khoone[mouseX][mouseY].getMohre().getI(), khoone[mouseX][mouseY].getMohre().getJ()) && khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().getExist()){
                    khoone[khoone[mouseX][mouseY].getMohre().getI()][khoone[mouseX][mouseY].getMohre().getJ()].getMohre().setColor(rang2);
                }
                mohreVizheSabz[2]=false;
            }
            this.namadXabdar = false;
        }
        testVizhe();
        try {
            Bazi.saveScreen(Bazi.getjFrame(),"C:\\Tedooz_Game\\"+harekat.toString()+".png");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        System.out.println(mouseX+"=X  mouse  Y="+mouseY);
        System.out.println(khoone[mouseX][mouseY].getX()+"=X  house  Y="+ khoone[mouseX][mouseY].getY()+" harekat is "+harekat);
        for(int i=0;i<5;i++){
            System.out.println("");
        }
        elamEtiaz2.setText("emtiaz siah: "+emtiaz(khoone,0));
        elamEtiaz1.setText("emtiaz sabz: "+emtiaz(khoone,1));
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
    public void run(){
        while (true){
            move();
            repaint();
            if(harekat==0){
                String tamama=new String();
                if(emtiaz(khoone,0)>emtiaz(khoone,1)){
                    tamama="black win "+emtiaz(khoone,0);
                }
                if(emtiaz(khoone,0)<emtiaz(khoone,1)){
                    tamama="green win"+emtiaz(khoone,1);
                }
                if(emtiaz(khoone,0)==emtiaz(khoone,1)){
                    tamama="equal"+emtiaz(khoone,0);
                }
                int a = JOptionPane.showConfirmDialog(Bazi.getjFrame(), tamama);
                break;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        /**
         * VK_0 baraye % hast
         */
        if(e.getKeyCode()==KeyEvent.VK_0){
            namadDarsad=true;
        }
        /**
         * VK_1 baraye $ hast
         */
        if(e.getKeyCode()==KeyEvent.VK_1){
            namadDollar=true;
        }
        /**
         * VK_2 baraye $ hast
         */
        if(e.getKeyCode()==KeyEvent.VK_2){
            namadXabdar=true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
