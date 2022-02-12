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
//        for(Edge edge : GraphPanel.getInstance().getEdges()){
//            System.out.println(edge.toString());
//        }
        GraphPanel.getInstance().repaint();
    }
    private boolean itsNotADuplicate(Node n1, Node n2){//controllo se ci sono archi duplicati
        for(Edge edge : GraphPanel.getInstance().getEdges()){
            if(edge.getN1()==n1 && edge.getN2()==n2){
                return false;
            }
        }
        return true;
    }
}
