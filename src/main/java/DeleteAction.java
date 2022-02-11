import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ListIterator;

public class DeleteAction extends AbstractAction {

    public DeleteAction(String name) {
        super(name);
    }

    public void actionPerformed(ActionEvent e) {
        ListIterator<Node> iter = GraphPanel.getInstance().getNodes().listIterator();
        while (iter.hasNext()) {
            Node n = iter.next();
            if (n.isSelected()) {
                deleteEdges(n);
                iter.remove();
            }
        }
        GraphPanel.getInstance().repaint();
    }

    private void deleteEdges(Node n) {
        ListIterator<Edge> iter = GraphPanel.getInstance().getEdges().listIterator();
        while (iter.hasNext()) {
            Edge e = iter.next();
            if (e.getN1() == n || e.getN2() == n) {
                iter.remove();
            }
        }
    }
}
