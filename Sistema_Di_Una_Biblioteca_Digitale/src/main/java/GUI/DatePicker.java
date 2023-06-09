package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

class DatePicker {
    int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
    int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
    JLabel l = new JLabel("", JLabel.CENTER);
    String day = "";
    JDialog d;
    JComboBox mesi = new JComboBox<>();
    JComboBox anni = new JComboBox<>();
    JButton[] button = new JButton[49];
    JButton previous = new JButton("<<");
    JButton next = new JButton(">>");
    public DatePicker(JFrame parent) {
        d = new JDialog();
        d.setModal(true);
        String[] header = { "Dom", "Lun", "Mar", "Mer", "Gio", "Ven", "Sab" };

        JPanel p2 = new JPanel(new GridLayout(1, 3));
        p2.setBackground(Color.decode("#222831"));
        previous.setBackground(Color.decode("#222831"));
        previous.setForeground(Color.decode("#EEEEEE"));
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month--;
                displayDate();
            }
        });
        p2.add(previous);

        ArrayList<String> mesiAnno = new ArrayList<String>(Arrays.asList("Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno","Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"));
        mesi.setModel(new DefaultComboBoxModel<String>(mesiAnno.toArray(new String[mesiAnno.size()])));
        mesi.setSelectedIndex(java.util.Calendar.getInstance().get(java.util.Calendar.MONTH));
        p2.add(mesi);

        //Aggiungere ActionPerformed Mesi e Anni
        // Mesi if Esemp gennaio: c

        for(int i = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR); i > 1899; i--) anni.addItem(i);
        p2.add(anni);

        //Aggiungere Combobox Mese -> Anno

        next.setBackground(Color.decode("#222831"));
        next.setForeground(Color.decode("#EEEEEE"));
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month++;
                displayDate();
            }
        });
        p2.add(next);

        JPanel p1 = new JPanel(new GridLayout(7, 7));
        p1.setPreferredSize(new Dimension(430, 120));

        // for (int x = 0; x <button> 6) {
        for (int x = 0; x < button.length; x++) {
            final int selection = x;
            button[x] = new JButton();
            button[x].setFocusPainted(false);
            button[x].setBackground(Color.decode("#222831"));
            button[x].setForeground(Color.decode("#EEEEEE"));
            if (x > 6) {
                button[x].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        day = button[selection].getActionCommand();
                        d.dispose();
                    }
                });
            }
            if (x < 7) {
                button[x].setText(header[x]);
                button[x].setForeground(Color.decode("#222831"));
                button[x].setBackground(Color.decode("#FFD369"));
                button[x].setBorder(BorderFactory.createEmptyBorder());
            }
            p1.add(button[x]);
        }

        d.add(p1, BorderLayout.CENTER);
        d.add(p2, BorderLayout.SOUTH);
        d.pack();
        d.setLocationRelativeTo(parent);
        displayDate();
        d.setVisible(true);
    }

    public void displayDate() {
        for (int x = 7; x < button.length; x++)
            button[x].setText("");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "MMMM yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, 1);
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
            button[x].setText("" + day);
        l.setForeground(Color.decode("#EEEEEE"));
        l.setText(sdf.format(cal.getTime()));


        mesi.setSelectedIndex(cal.getTime().getMonth());
        if((cal.getTime().getYear()+1900) <= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)) anni.setSelectedIndex(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) - (cal.getTime().getYear()+1900));
        else anni.setSelectedItem((cal.getTime().getYear()+1900));
        if ((cal.getTime().getYear()+1900) == 1900 && cal.getTime().getMonth() == 0) previous.setEnabled(false);
        if ((cal.getTime().getYear()+1900) == java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) && cal.getTime().getMonth() == java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)) next.setEnabled(false);
        else{
            previous.setEnabled(true);
            next.setEnabled(true);
        }

        d.setTitle("Date Picker");
    }

    public String setPickedDate() {
        if (day.equals(""))
            return day;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "dd-MM-yyyy");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }
}

class DatePickerExample {
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
            public void actionPerformed(ActionEvent ae) {
                text.setText(new DatePicker(f).setPickedDate());
            }
        });
    }
}