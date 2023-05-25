package GUI;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BaseMultiResolutionImage;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NewLoginForm extends JDialog {
    public static JFrame frame;
    private JPanel contentPane;
    private JLabel imageLabel;
    private JLabel closeBT;

    private ImageIcon imagine;
    private ImageIcon closeImg;

    public NewLoginForm() {


        closeBT.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.setVisible(false);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("NewLoginForm");
        frame.setUndecorated(true);
        frame.setContentPane(new NewLoginForm().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(720, 480);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        imagine = new ImageIcon(this.getClass().getResource("/b.png"));
        Image imagine2 = imagine.getImage().getScaledInstance(340,94, Image.SCALE_DEFAULT);
        imagine = new ImageIcon(imagine2);
        imageLabel = new JLabel(imagine);



        /*BufferedImage bi = null;
        Graphics2D g2d = null;
        ImageIcon ii = null;
        try{
            ii = new ImageIcon(this.getClass().getResource("/close.png"));
            bi = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            g2d = (Graphics2D) bi.createGraphics();
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
            g2d.drawImage(ii.getImage(), 0, 0, 50, 50, null);
            System.out.println("ciao");
        } catch (Exception e){
            e.printStackTrace();
        }*/


        closeImg = new ImageIcon(this.getClass().getResource("/close.png"));
        Image imagine3 = closeImg.getImage().getScaledInstance(50,50, Image.SCALE_SMOOTH);
        closeImg = new ImageIcon(imagine3);
        closeBT = new JLabel(closeImg);
    }
}
