package GUI;

import javax.swing.*;

public class NewComboBox<E> extends JComboBox<E> {

    public NewComboBox() {
        setUI(new NewComboBoxUI());

    }

    public void createUIComponents(){
        NewComboBox newComboBox = new NewComboBox();
        newComboBox.setModel(new DefaultComboBoxModel());
    }
}
