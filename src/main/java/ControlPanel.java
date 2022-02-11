import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class ControlPanel extends JToolBar {

    private Action newNode = new NewNodeAction("New");
    private Action clearAll = new ClearAction("Clear");
    private Action color = new ColorAction("Color");
    private Action connect = new ConnectAction("Connect");
    private Action delete = new DeleteAction("Delete");
    private JButton defaultButton = new JButton(newNode);
    private ColorIcon hueIcon = new ColorIcon(Color.blue);
    private JPopupMenu popup = new JPopupMenu();

    public ControlPanel() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(Color.lightGray);

        this.add(defaultButton);
        this.add(new JButton(clearAll));
        this.add(new JButton(color));
        this.add(new JLabel(hueIcon));

        popup.add(new JMenuItem(newNode));
        popup.add(new JMenuItem(color));
        popup.add(new JMenuItem(connect));
        popup.add(new JMenuItem(delete));
    }

}
