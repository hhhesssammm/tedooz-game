package tedooz;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bazi {
    private static int ERTEFA_JFRAME=780;
    private static int ARZ_JFRAME=780;

    public void setERTEFA_JFRAME(int ERTEFA_JFRAME) {
        this.ERTEFA_JFRAME = ERTEFA_JFRAME;
    }
    public static int getERTEFA_JFRAME() {
        return ERTEFA_JFRAME;
    }
    public void setARZ_JFRAME(int ARZ_JFRAME) {
        this.ARZ_JFRAME = ARZ_JFRAME;
    }
    public static int getARZ_JFRAME() {
        return ARZ_JFRAME;
    }

    private static JFrame jFrame;

    public static void setjFrame(JFrame jFrame) {
        Bazi.jFrame = jFrame;
    }
    public static JFrame getjFrame() {
        return jFrame;
    }
    public Bazi(){
        new File("C:\\Tedooz_Game").mkdir();
        jFrame=new JFrame();
        Safhe safhe =new Safhe();
        jFrame.addKeyListener(safhe);
        jFrame.addMouseListener(safhe);
        jFrame.add(safhe);
        jFrame.setTitle("Bazi Tedooz");
        jFrame.setSize(ARZ_JFRAME,ERTEFA_JFRAME);
        jFrame.setLayout(null);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
    /**
     * @param component = jframe
     * @param fileName = jaii ke zakhire mishavad
     * @throws IOException
     * baraye har marhale ke click mishe az safhe bazi  ye aks migire
     */
    public static void saveScreen(Component component,String fileName) throws IOException {
        BufferedImage img=getScreenshot(component);
        ImageIO.write(img,"png",new File(fileName));
    }
    /**
     * baraye zakhire har marhale bazi
     * @athur stackoverflow.com
     */
    public static BufferedImage getScreenshot(Component component){
        BufferedImage image=new BufferedImage(component.getWidth(),component.getHeight() , BufferedImage.TYPE_INT_RGB);
        component.paint(image.getGraphics());
        return image;
    }


    public static void main(String[] args) {
        new Bazi();
    }
}
