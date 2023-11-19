package GUI;

import javax.swing.*;

public class ComboBoxSuggestion<E> extends JComboBox<E> {

    public ComboBoxSuggestion() {
        setUI(new ComboSuggestionUI());

    }

    public void createUIComponents(){
        ComboBoxSuggestion comboBoxSuggestion = new ComboBoxSuggestion();
        comboBoxSuggestion.setModel(new DefaultComboBoxModel());
    }
}
