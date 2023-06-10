package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class DatePickerColored {
    int month = Calendar.getInstance().get(Calendar.MONTH);
    int year = Calendar.getInstance().get(Calendar.YEAR);
    String day = "";
    JDialog d;
    JComboBox anni = new JComboBox<>();
    JComboBox mesi = new JComboBox<>();
    JButton[] button = new JButton[49];
    JButton previous = new JButton("<<");
    JButton next = new JButton(">>");
    Calendar cal = Calendar.getInstance();

    public DatePickerColored(JFrame parent){
        d = new JDialog();
        d.setUndecorated(false);
        d.setModal(false);

        JPanel p2 = new JPanel(new GridLayout(1,3));
        p2.setBorder(BorderFactory.createEmptyBorder());
        p2.setBackground(Color.decode("#222831"));

        previous.setBackground(Color.decode("#222831"));
        previous.setForeground(Color.decode("#EEEEEE"));
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month--;
                //displayDate();
            }
        });

        p2.add(previous);

        ArrayList<String> mesiAnno = new ArrayList<String>(Arrays.asList("Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno","Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"));
        mesi.setModel(new DefaultComboBoxModel<String>(mesiAnno.toArray(new String[mesiAnno.size()])));
        mesi.setSelectedIndex(java.util.Calendar.getInstance().get(java.util.Calendar.MONTH));
        p2.add(mesi);

        mesi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String s = (String) mesi.getSelectedItem();
                switch (s){
                    case "Gennaio":
                        if (cal.getTime().getMonth()> 0){
                            month = month - (cal.getTime().getMonth() - 0);
                        }
                        break;
                    case "Febbraio":
                        if (cal.getTime().getMonth()> 1){
                            month = month - (cal.getTime().getMonth() - 1);
                        }
                        if (cal.getTime().getMonth()< 1){
                            month = month + (1 - cal.getTime().getMonth());
                        }
                        break;
                    case "Marzo":
                        if (cal.getTime().getMonth()> 2){
                            month = month - (cal.getTime().getMonth() - 2);
                        }
                        if (cal.getTime().getMonth()< 2){
                            month = month + (2 - cal.getTime().getMonth());
                        }
                        break;
                    case "Aprile":
                        if (cal.getTime().getMonth()> 3){
                            month = month - (cal.getTime().getMonth() - 3);
                        }
                        if (cal.getTime().getMonth()< 3){
                            month = month + (3 - cal.getTime().getMonth());
                        }
                        break;
                    case "Maggio":
                        if (cal.getTime().getMonth()> 4){
                            month = month - (cal.getTime().getMonth() - 4);
                        }
                        if (cal.getTime().getMonth()< 4){
                            month = month + (4 - cal.getTime().getMonth());
                        }
                        break;
                    case "Giugno":
                        if (cal.getTime().getMonth()> 5){
                            month = month - (cal.getTime().getMonth() - 5);
                        }
                        if (cal.getTime().getMonth()< 5){
                            month = month + (5 - cal.getTime().getMonth());
                        }
                        break;
                    case "Luglio":
                        if (cal.getTime().getMonth()> 6 && (java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)>=(cal.getTime().getYear()+1900) && java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)<=cal.getTime().getMonth())){
                            month = month - (cal.getTime().getMonth() - 6);
                        }
                        if (cal.getTime().getMonth()< 6 && (java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)>=(cal.getTime().getYear()+1900) && java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)<=cal.getTime().getMonth())){
                            month = month + (6 - cal.getTime().getMonth());
                        }
                        break;
                    case "Agosto":
                        if (cal.getTime().getMonth()> 7){
                            month = month - (cal.getTime().getMonth() - 7);
                        }
                        if (cal.getTime().getMonth()< 7){
                            month = month + (7 - cal.getTime().getMonth());
                        }
                        break;
                    case "Settembre":
                        if (cal.getTime().getMonth()> 8){
                            month = month - (cal.getTime().getMonth() - 8);
                        }
                        if (cal.getTime().getMonth()< 8){
                            month = month + (8 - cal.getTime().getMonth());
                        }
                        break;
                    case "Ottobre":
                        if (cal.getTime().getMonth()> 9){
                            month = month - (cal.getTime().getMonth() - 9);
                        }
                        if (cal.getTime().getMonth()< 9){
                            month = month + (9 - cal.getTime().getMonth());
                        }
                        break;
                    case "Novembre":
                        if (cal.getTime().getMonth()> 10){
                            month = month - (cal.getTime().getMonth() - 10);
                        }
                        if (cal.getTime().getMonth()< 10){
                            month = month + (10 - cal.getTime().getMonth());
                        }
                        break;
                    case "Dicembre":
                        if (cal.getTime().getMonth()< 11){
                            month = month + (11 - cal.getTime().getMonth());
                        }
                        break;
                    default:
                        if (cal.getTime().getMonth()< 12){
                            month = month + (11 - cal.getTime().getMonth());
                        }
                        break;
                }
                //displayDate();
            }
        });

        for(int i = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR); i > 1899; i--) anni.addItem(i);
        p2.add(anni);


        next.setBackground(Color.decode("#222831"));
        next.setForeground(Color.decode("#EEEEEE"));
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month++;
                //displayDate();
            }
        });
        p2.add(next);

        JPanel p1 = new JPanel(new GridLayout(7, 7));
        p1.setBackground(Color.decode("#222831"));

        p1.setPreferredSize(new Dimension(430, 120));
        for (int x = 0; x < button.length; x++){
            button[x] = new JButton();
            p1.add(button[x]);
        }
        d.add(p1, BorderLayout.SOUTH );
        d.add(p2, BorderLayout.NORTH);
        d.pack();
        d.setLocationRelativeTo(parent);
        //displayDate();
        d.setVisible(true);
    }

    public static void main(String[] args) {
        JLabel label = new JLabel("Seleziona Data:");
        final JTextField text = new JTextField(20);
        JButton b = new JButton("Apri");
        JPanel p = new JPanel();
        p.add(label);
        p.add(text);
        p.add(b);
        final JFrame f = new JFrame();
        f.getContentPane().add(p);
        f.pack();
        f.setVisible(true);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DatePickerColored dpc = new DatePickerColored(f);
            }
        });
        /*b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                text.setText(new DatePickerColored(f).setPickedDate());
            }
        });*/
    }

}
