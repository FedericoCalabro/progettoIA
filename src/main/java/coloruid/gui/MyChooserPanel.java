package coloruid.gui;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MyChooserPanel extends AbstractColorChooserPanel {

    @Override
    public void buildChooser() {
        setLayout(new GridLayout(1, 5));
        makeAddButton("Red", Color.red);
        makeAddButton("Green", Color.green);
        makeAddButton("Blue", Color.blue);
        makeAddButton("Yellow", Color.yellow);
        makeAddButton("Orange", new Color(255,100,0));
    }

    @Override
    public void updateChooser() {
    }

    @Override
    public String getDisplayName() {
        return "coloruid.gui.MyChooserPanel";
    }

    @Override
    public Icon getSmallDisplayIcon() {
        return null;
    }

    @Override
    public Icon getLargeDisplayIcon() {
        return null;
    }

    private void makeAddButton(String name, Color color) {
        JButton button = new JButton(name);
        button.setPreferredSize(new Dimension(30,30));
        button.setBackground(color);
        button.setAction(setColorAction);
        add(button);
    }

    Action setColorAction = new AbstractAction() {
        public void actionPerformed(ActionEvent evt) {
            JButton button = (JButton) evt.getSource();

            getColorSelectionModel().setSelectedColor(button.getBackground());
        }
    };
}