package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewComfirmMessageDialog extends JDialog {
    private JPanel contentPane;
    private JLabel typeLabel;
    private JLabel typeMessageLabel;
    private JButton okButton;
    private JButton noButton;
    private int value = 0;
    private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    public int fontSize = getFontSize();
    public Font baseFontSize = new Font("Segoe UI", Font.PLAIN, fontSize);
    public Font impactFontSize = new Font("Impact", Font.PLAIN, fontSize);
    public Font textFieldFont = new Font("Berlin Sans FB", Font.PLAIN, fontSize-2);

    public NewComfirmMessageDialog() {

    }

    public int comfirmDialog(String message){
        this.setUndecorated(true);  //rimuove la decorazione della finestra
        this.setSize((int) (screenWidth/8.5333),(int) (screenHeight/7.2));  //imposta le dimensioni della finestra
        this.setLocationRelativeTo(null);   //posiziona il frame al centro dello schermo

        Image icona = new ImageIcon(this.getClass().getResource("/icon.png")).getImage();   //carica l'immagine nel percorso /icon.png

        this.setIconImage(icona);   //imposta la l'icona dell'applicazione
        setContentPane(contentPane);
        setModal(true); //imposta la finestra come modal
        getRootPane().setDefaultButton(okButton);   //imposta il JButton 'okButton' come bottone predefinito della finestra

        contentPane.setBorder(BorderFactory.createMatteBorder(2,2,2,2,new Color(0xEEEEEE)));    //imposta il bordo del JPanel 'contentPane'

        ImageIcon attentionIco = new ImageIcon(this.getClass().getResource("/attention.png"));  //carica l'immagine nel percorso /attention.png
        Image attentionImg = attentionIco.getImage().getScaledInstance((int) (screenWidth/51.2),(int) (screenHeight/28.8), Image.SCALE_SMOOTH); //imposta le dimensioni dell'immagine

        attentionIco = new ImageIcon(attentionImg); //reinizializza l'ImageIcon 'attentionIco' con l'Image 'attentionImg'
        typeLabel.setIcon(attentionIco);    //imposta l'icona della JLabel 'typeLabel' con l'immagine

        typeMessageLabel.setFont(baseFontSize); //imposta il font della JLabel 'typeMessageLabel'
        typeMessageLabel.setText(message);  //imposta il testo della JLabel 'typeMessageLabel' con 'message'
        okButton.setText("Si"); //imposta il testo del JButton 'okButton'


        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                okButton.setBackground(Color.decode("#cf9e29"));    //imposta il colore dello sfondo del JButton 'okButton'
            }
        });

        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                okButton.setBackground(Color.decode("#FFD369"));    //imposta il colore dello sfondo del JButton 'okButton'
            }
        });


        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                value = onSI(); //seganala che è stato premuto il JButton 'okButton' e chiude la finestra
            }
        });

        noButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                noButton.setBackground(Color.decode("#cf9e29"));    //imposta il colore dello sfondo del JButton 'noButton'
            }
        });

        noButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                noButton.setBackground(Color.decode("#FFD369"));    //imposta il colore dello sfondo del JButton 'noButton'
            }
        });

        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                value = onNO(); //seganala che è stato premuto il JButton 'noButton' e chiude la finestra
            }
        });

        okButton.setMinimumSize(new Dimension((screenWidth/16), -1));   //imposta la dimensione minima del JButton 'okButton'
        okButton.setFont(baseFontSize); //imposta il font del JButton 'okButton'
        noButton.setMinimumSize(new Dimension((screenWidth/16), -1));   //imposta la dimensione minima del JButton 'noButton'
        noButton.setFont(baseFontSize); //imposta il font del JButton 'noButton'

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);  //imposta nessuna azione predefinita da eseguire quando viene chiusa la finestra
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                value = onCancel(); //chiude la finestra
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                value = onCancel(); //chiude la finestra
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        this.pack();
        this.setVisible(true);  //rende visibile la finestra

        return value;
    }
    private int onSI(){ //seganala che è stato premuto il JButton 'okButton' e chiude la finestra
        dispose();
        return 1;
    }//fine onSI

    private int onNO(){ //seganala che è stato premuto il JButton 'noButton' e chiude la finestra
        dispose();
        return 2;
    }//fine onNo

    private int onCancel() {    //chiude la finestra e lo segnala
        dispose();
        return 0;
    }//fine onCancel

    public int getFontSize(){   //calcola le dimensioni del font in base alle dimensioni dello schermo
        int fontSize = Math.min(screenWidth, screenHeight) / 50;    //dimensione del font
        return fontSize;
    }//fine getFontSize
}