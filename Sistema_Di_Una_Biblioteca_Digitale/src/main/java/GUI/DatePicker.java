package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

class DatePicker extends JPanel{
    int month = Calendar.getInstance().get(java.util.Calendar.MONTH);
    int year = Calendar.getInstance().get(java.util.Calendar.YEAR);
    String day = "";
    JDialog d;
    JComboBox mesi = new JComboBox<>();
    JComboBox anni = new JComboBox<>();
    JButton[] button = new JButton[49];
    JButton previous = new JButton("<<");
    JButton next = new JButton(">>");
    java.util.Calendar cal = java.util.Calendar.getInstance();

    static ImageIcon ico;
    static Image img;
    public DatePicker(JLabel parent) {
        ico = new ImageIcon(this.getClass().getResource("/Calendar.png"));
        img = ico.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ico = new ImageIcon(img);

        d = new JDialog();
        d.setUndecorated(true);
        d.setModal(true);
        UIManager.put("Button.select", Color.TRANSLUCENT);
        UIManager.put("ScrollBar.thumbHighlight", Color.decode("#FFD369"));
        UIManager.put("ScrollBar.thumbDarkShadow", Color.decode("#222831"));
        UIManager.put("ScrollBar.highlight", Color.decode("#FFD369"));
        UIManager.put("ScrollBar.trackHighlight", Color.decode("#222831"));




        JTextField text = new JTextField(20);
        text.setAlignmentY(0.5f);
        text.setAutoscrolls(true);
        text.setBackground(new Color(-14538703));
        text.setCaretColor(new Color(-1118482));
        text.setForeground(new Color(-1118482));
        text.setBorder(BorderFactory.createLineBorder(Color.decode("#222831")));
        text.setMargin(new Insets(2, 6, 2, 6));
        JButton b = new JButton(ico);
        b.setHorizontalTextPosition(SwingConstants.CENTER);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        //b.setPreferredSize(new Dimension(25,25));
        b.setSize(25, 25);

        setLayout(new BorderLayout(0,0));
        add(text);
        add(b);
        setBackground(Color.decode("#FFD369"));
        setBorder(new LineBorder(new Color(0x222831)));

        month = month - 216;

        String[] header = {"Lun", "Mar", "Mer", "Gio", "Ven", "Sab", "Dom"};

        JPanel p2 = new JPanel(new GridLayout(1, 3));
        p2.setBorder(BorderFactory.createEmptyBorder());
        p2.setBackground(Color.decode("#222831"));
        previous.setBackground(Color.decode("#222831"));
        previous.setForeground(Color.decode("#EEEEEE"));
        previous.setBorder(new LineBorder(Color.decode("#FFD369")));
        previous.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent ae) {
                month--;
                if (mesi.getItemCount() < 12) {
                    if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 0) mesi.addItem("Febbraio");
                    if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 1) mesi.addItem("Marzo");
                    if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 2) mesi.addItem("Aprile");
                    if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 3) mesi.addItem("Maggio");
                    if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 4) mesi.addItem("Giugno");
                    if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 5) mesi.addItem("Luglio");
                    if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 6) mesi.addItem("Agosto");
                    if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 7) mesi.addItem("Settembre");
                    if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 8) mesi.addItem("Ottobre");
                    if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 9) mesi.addItem("Novembre");
                    if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 10) mesi.addItem("Dicembre");
                }
                displayDate();
            }
        });
        p2.add(previous);

        ArrayList<String> mesiAnno = new ArrayList<String>(Arrays.asList("Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"));
        mesi.setModel(new DefaultComboBoxModel<String>(mesiAnno.toArray(new String[mesiAnno.size()])));
        mesi.setSelectedIndex(java.util.Calendar.getInstance().get(java.util.Calendar.MONTH));
        mesi.setBackground(Color.decode("#FFD369"));
        mesi.setForeground(Color.decode("#222831"));
        mesi.setBorder(new LineBorder(Color.decode("#222831")));

        Object comp1 = mesi.getUI().getAccessibleChild(mesi, 0);
        if(comp1 instanceof JPopupMenu){
            JPopupMenu popup = (JPopupMenu) comp1;
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
            scrollPane.getVerticalScrollBar().setBackground(new Color(0xFFD369));
            scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors(){
                    this.thumbColor = new Color(0x222831);
                }
                @Override
                protected JButton createDecreaseButton(int orientation) {
                    JButton button = super.createDecreaseButton(orientation);
                    button.setBackground(new Color(0xFFD369));

                    return button;
                }

                @Override
                protected JButton createIncreaseButton(int orientation) {
                    JButton button = super.createIncreaseButton(orientation);
                    button.setBackground(new Color(0xFFD369));

                    return button;
                }
            });
        }

        p2.add(mesi);


        mesi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String s = (String) mesi.getSelectedItem();
                switch (s) {
                    case "Gennaio":
                        if (cal.getTime().getMonth() > 0) {
                            month = month - (cal.getTime().getMonth() - 0);
                        }
                        break;
                    case "Febbraio":
                        if (cal.getTime().getMonth() > 1) {
                            month = month - (cal.getTime().getMonth() - 1);
                        }
                        if (cal.getTime().getMonth() < 1) {
                            month = month + (1 - cal.getTime().getMonth());
                        }
                        break;
                    case "Marzo":
                        if (cal.getTime().getMonth() > 2) {
                            month = month - (cal.getTime().getMonth() - 2);
                        }
                        if (cal.getTime().getMonth() < 2) {
                            month = month + (2 - cal.getTime().getMonth());
                        }
                        break;
                    case "Aprile":
                        if (cal.getTime().getMonth() > 3) {
                            month = month - (cal.getTime().getMonth() - 3);
                        }
                        if (cal.getTime().getMonth() < 3) {
                            month = month + (3 - cal.getTime().getMonth());
                        }
                        break;
                    case "Maggio":
                        if (cal.getTime().getMonth() > 4) {
                            month = month - (cal.getTime().getMonth() - 4);
                        }
                        if (cal.getTime().getMonth() < 4) {
                            month = month + (4 - cal.getTime().getMonth());
                        }
                        break;
                    case "Giugno":
                        if (cal.getTime().getMonth() > 5) {
                            month = month - (cal.getTime().getMonth() - 5);
                        }
                        if (cal.getTime().getMonth() < 5) {
                            month = month + (5 - cal.getTime().getMonth());
                        }
                        break;
                    case "Luglio":
                        if (cal.getTime().getMonth() > 6 && (java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) >= (cal.getTime().getYear() + 1900) && java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) >= cal.getTime().getMonth())) {
                            month = month - (cal.getTime().getMonth() - 6);
                        }
                        if (cal.getTime().getMonth() < 6 && (java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) >= (cal.getTime().getYear() + 1900) && java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) <= cal.getTime().getMonth())) {
                            month = month + (6 - cal.getTime().getMonth());
                        }
                        break;
                    case "Agosto":
                        if (cal.getTime().getMonth() > 7) {
                            month = month - (cal.getTime().getMonth() - 7);
                        }
                        if (cal.getTime().getMonth() < 7) {
                            month = month + (7 - cal.getTime().getMonth());
                        }
                        break;
                    case "Settembre":
                        if (cal.getTime().getMonth() > 8) {
                            month = month - (cal.getTime().getMonth() - 8);
                        }
                        if (cal.getTime().getMonth() < 8) {
                            month = month + (8 - cal.getTime().getMonth());
                        }
                        break;
                    case "Ottobre":
                        if (cal.getTime().getMonth() > 9) {
                            month = month - (cal.getTime().getMonth() - 9);
                        }
                        if (cal.getTime().getMonth() < 9) {
                            month = month + (9 - cal.getTime().getMonth());
                        }
                        break;
                    case "Novembre":
                        if (cal.getTime().getMonth() > 10) {
                            month = month - (cal.getTime().getMonth() - 10);
                        }
                        if (cal.getTime().getMonth() < 10) {
                            month = month + (10 - cal.getTime().getMonth());
                        }
                        break;
                    case "Dicembre":
                        if (cal.getTime().getMonth() < 11) {
                            month = month + (11 - cal.getTime().getMonth());
                        }
                        break;
                    default:
                        if (cal.getTime().getMonth() < 12) {
                            month = month + (11 - cal.getTime().getMonth());
                        }
                        break;
                }
                displayDate();
            }
        });

        //Aggiungere ActionPerformed Mesi e Anni
        // Mesi if Esemp gennaio: c

        for (int i = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR); i > 1899; i--) anni.addItem(i);
        anni.setBackground(Color.decode("#FFD369"));
        anni.setForeground(Color.decode("#222831"));
        anni.setBorder(new LineBorder(Color.decode("#222831")));


        Object comp2 = anni.getUI().getAccessibleChild(anni, 0);
        if(comp2 instanceof JPopupMenu){
            JPopupMenu popup = (JPopupMenu) comp2;
            JScrollPane scrollPane = (JScrollPane) popup.getComponent(0);
            scrollPane.getVerticalScrollBar().setBackground(new Color(0xFFD369));
            scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors(){
                    this.thumbColor = new Color(0x222831);
                }
                @Override
                protected JButton createDecreaseButton(int orientation) {
                    JButton button = super.createDecreaseButton(orientation);
                    button.setBackground(new Color(0xFFD369));
                    return button;
                }

                @Override
                protected JButton createIncreaseButton(int orientation) {
                    JButton button = super.createIncreaseButton(orientation);
                    button.setBackground(new Color(0xFFD369));

                    return button;
                }
            });
        }


        p2.add(anni);

        anni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int y = (int) anni.getSelectedItem();
                if (y > (cal.getTime().getYear() + 1900)) {
                    month = month + (12 * (y - (cal.getTime().getYear() + 1900)));
                }
                if (y < (cal.getTime().getYear() + 1900)) {
                    month = month - (12 * ((cal.getTime().getYear() + 1900) - y));
                }
                if (y == Calendar.getInstance().get(java.util.Calendar.YEAR)) {
                    if (cal.getTime().getMonth() > Calendar.getInstance().get(java.util.Calendar.MONTH))
                        month = Calendar.getInstance().get(java.util.Calendar.MONTH) + 1;
                }
                if (y == Calendar.getInstance().get(java.util.Calendar.YEAR)) {
                    for (int i = mesi.getItemCount(); i > (Calendar.getInstance().get(java.util.Calendar.MONTH) + 1); i--) {
                        mesi.removeItemAt((i - 1));
                    }
                } else {
                    if (mesi.getItemCount() < 12) {
                        if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 0) mesi.addItem("Febbraio");
                        if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 1) mesi.addItem("Marzo");
                        if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 2) mesi.addItem("Aprile");
                        if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 3) mesi.addItem("Maggio");
                        if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 4) mesi.addItem("Giugno");
                        if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 5) mesi.addItem("Luglio");
                        if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 6) mesi.addItem("Agosto");
                        if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 7) mesi.addItem("Settembre");
                        if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 8) mesi.addItem("Ottobre");
                        if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 9) mesi.addItem("Novembre");
                        if (Calendar.getInstance().get(java.util.Calendar.MONTH) <= 10) mesi.addItem("Dicembre");
                    }
                }
                displayDate();
            }
        });

        //Aggiungere Combobox Mese -> Anno

        next.setBackground(Color.decode("#222831"));
        next.setForeground(Color.decode("#EEEEEE"));
        next.setBorder(new LineBorder(Color.decode("#FFD369")));
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                month++;
                displayDate();
            }
        });
        p2.add(next);

        JPanel p1 = new JPanel(new GridLayout(7, 7));
        p1.setBorder(BorderFactory.createEmptyBorder(-1, -1, -1, -1));
        p1.setBackground(Color.decode("#222831"));
        p1.setPreferredSize(new Dimension(430, 120));

        // for (int x = 0; x <button> 6) {
        for (int x = 0; x < button.length; x++) {
            final int selection = x;
            button[x] = new JButton();
            button[x].setBorder(BorderFactory.createEmptyBorder());
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
                button[x].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        day = button[selection].getActionCommand();
                        d.dispose();
                    }
                });
                button[x].addMouseListener(new MouseAdapter() {
                    public void mouseEntered(MouseEvent e) {
                        super.mouseEntered(e);
                        button[selection].setBackground(Color.decode("#393E46"));
                    }

                    public void mouseExited(MouseEvent e) {
                        super.mouseExited(e);
                        button[selection].setBackground(Color.decode("#222831"));
                    }
                });
            }
            if (x < 7) {
                button[x].setText(header[x]);
                button[x].setBorder(BorderFactory.createEmptyBorder());
                button[x].setContentAreaFilled(true);
                button[x].setForeground(Color.decode("#222831"));
                button[x].setBackground(Color.decode("#FFD369"));
            }
            p1.add(button[x]);
        }

        d.add(p1, BorderLayout.NORTH);
        d.add(p2, BorderLayout.SOUTH);
        d.pack();
        d.setLocationRelativeTo(parent);
        displayDate();

    }

    public void displayDate() {
        for (int x = 7; x < button.length; x++)
            button[x].setText("");
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "MMMM yyyy");
        cal.set(year, month, 1);
        int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++) {
            /*button[x].setText("" + day);
            if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) < day && Calendar.getInstance().get(Calendar.MONTH) == cal.getTime().getMonth() && Calendar.getInstance().get(Calendar.YEAR) == (cal.getTime().getYear()+1900)) button[x].setEnabled(false);
            else button[x].setEnabled(true);*/
            if (dayOfWeek == 1) {
                button[x + 6].setText("" + day);
                if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) < day && Calendar.getInstance().get(Calendar.MONTH) == cal.getTime().getMonth() && Calendar.getInstance().get(Calendar.YEAR) == (cal.getTime().getYear() + 1900))
                    button[x + 6].setEnabled(false);
                else button[x + 6].setEnabled(true);
            } else {
                button[x - 1].setText("" + day);
                if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) < day && Calendar.getInstance().get(Calendar.MONTH) == cal.getTime().getMonth() && Calendar.getInstance().get(Calendar.YEAR) == (cal.getTime().getYear() + 1900))
                    button[x - 1].setEnabled(false);
                else button[x - 1].setEnabled(true);
            }

        }
        mesi.setSelectedIndex(cal.getTime().getMonth());
        if ((cal.getTime().getYear() + 1900) <= java.util.Calendar.getInstance().get(java.util.Calendar.YEAR))
            anni.setSelectedIndex(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) - (cal.getTime().getYear() + 1900));
        else anni.setSelectedItem((cal.getTime().getYear() + 1900));
        if (((cal.getTime().getYear() + 1900) == 1900) && (cal.getTime().getMonth() == 0)) {
            previous.setEnabled(false);
        } else if ((cal.getTime().getYear() + 1900) == java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) && cal.getTime().getMonth() == java.util.Calendar.getInstance().get(java.util.Calendar.MONTH))
            next.setEnabled(false);
        else {
            previous.setEnabled(true);
            next.setEnabled(true);
        }

        d.setTitle("Date Picker");
    }

    public String setPickedDate() {
        if (day.equals(""))
            return day;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                "yyyy-MM-dd");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(year, month, Integer.parseInt(day));
        return sdf.format(cal.getTime());
    }
}