package coloruid.actions;

import coloruid.gui.GraphPanel;
import coloruid.core.Edge;
import coloruid.core.Node;

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
                if(itsNotADuplicate(n1,n2)) {//se è vero che non ci sono archi duplicati allora aggiungo i due nuovi archi
                    GraphPanel.getInstance().getEdges().add(new Edge(n1, n2));
                    GraphPanel.getInstance().getEdges().add(new Edge(n2, n1));
                }
            }
        }
        GraphPanel.getInstance().repaint();
    }
    public static boolean itsNotADuplicate(Node n1, Node n2){//controllo se ci sono archi duplicati
        for(Edge edge : GraphPanel.getInstance().getEdges()){
            if(edge.getN1().getId()==n1.getId() && edge.getN2().getId()==n2.getId()){
                return false;
            }
        }
        return true;
    }
}
