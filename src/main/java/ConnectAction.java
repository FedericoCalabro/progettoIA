import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ConnectAction extends AbstractAction {

    public ConnectAction(String name) {
        super(name);
    }

    public void actionPerformed(ActionEvent e) {
        List<Node> selected = GraphPanel.getInstance().getSelected();
        Node.getSelected(GraphPanel.getInstance().getNodes(), selected);
        if (selected.size() > 1) {
            for (int i = 0; i < selected.size() - 1; ++i) {
                Node n1 = selected.get(i);
                Node n2 = selected.get(i + 1);
                GraphPanel.getInstance().getEdges().add(new Edge(n1, n2));
            }
        }
        GraphPanel.getInstance().repaint();
    }
}
