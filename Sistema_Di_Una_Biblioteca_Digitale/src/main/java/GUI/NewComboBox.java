package GUI;

import javax.swing.*;

/**
 * The type New combo box.
 *
 * @param <E> the type parameter
 */
public class NewComboBox<E> extends JComboBox<E> {

    /**
     * Instantiates a new New combo box.
     */
    public NewComboBox() {
        setUI(new NewComboBoxUI());

    }

    /**
     * Create ui components.
     */
    public void createUIComponents(){
        NewComboBox newComboBox = new NewComboBox();
        newComboBox.setModel(new DefaultComboBoxModel());
    }
}
