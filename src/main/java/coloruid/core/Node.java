package coloruid.core;

import coloruid.gui.GraphPanel;
import coloruid.utils.Utils;
import lombok.Data;

import java.awt.*;
import java.util.List;

@Data
public class Node {

    private int id;
    private Point p;
    private int r;
    private Color color;
    private boolean selected = false;
    private Rectangle b = new Rectangle();

    public Node(int id, Point p, Color color) {
        this.id = id;
        this.p = p;
        this.r = 20;
        this.color = color;
        setBoundary(b);
    }

    private void setBoundary(Rectangle b) {
        b.setBounds(p.x - r, p.y - r, 2 * r, 2 * r);
    }

    public void draw(Graphics g) {

        g.setColor(this.color);
        g.fillOval(b.x, b.y, b.width, b.height);
        g.setColor(Color.black);
        g.drawString(String.valueOf(id),b.x + (int)(b.width/2.25),b.y + (int)(b.height/1.6) );
        if (selected) {
            g.setColor(Color.darkGray);
            g.drawRect(b.x, b.y, b.width, b.height);
        }
    }

    public Point getLocation() {
        return p;
    }

    public boolean contains(Point p) {
        return b.contains(p);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public static void getSelected(java.util.List<Node> list, java.util.List<Node> selected) {
        selected.clear();
        for (Node n : list) {
            if (n.isSelected()) {
                selected.add(n);
            }
        }
    }

    public static void selectNone(java.util.List<Node> list) {
        for (Node n : list) {
            n.setSelected(false);
        }
    }

    public static boolean selectOne(java.util.List<Node> list, Point p) {
        for (Node n : list) {
            if (n.contains(p)) {
                if (!n.isSelected()) {
                    Node.selectNone(list);
                    n.setSelected(true);
                }
                return true;
            }
        }
        return false;
    }

    public static void selectRect(java.util.List<Node> list, Rectangle r) {
        for (Node n : list) {
            n.setSelected(r.contains(n.p));
        }
    }

    public static void selectToggle(java.util.List<Node> list, Point p) {
        for (Node n : list) {
            if (n.contains(p)) {
                n.setSelected(!n.isSelected());
            }
        }
    }

    public static void updatePosition(java.util.List<Node> list, Point d) {
        for (Node n : list) {
            if (n.isSelected()) {
                n.p.x += d.x;
                n.p.y += d.y;
                n.setBoundary(n.b);
            }
        }
    }


    public static void updateColor(List<Node> list, Color color) {

        Node clickedNode = returnSelectedNode(list);

        if(clickedNode != null){
            String currentStatus = Utils.checkGameStatus();
            if(currentStatus.equals("LOSE") || currentStatus.equals("WIN")){
                GraphPanel.getInstance().getTextArea().append("Cannot color the node, game is " + currentStatus + System.lineSeparator());
                return;
            }

        Color oldColor = clickedNode.getColor();

        for (Node n : list) {
            if (n.isSelected()) {
                n.color = color;
            }
        }
        if(GraphPanel.getInstance().getControlPanel().getMode().getSelectedIndex() == 1)
            expandColor(clickedNode, oldColor, color);
        Utils.updateStepLabel(Utils.getStepFromLabel()+1);

        //check vittora / sconfitta
        String status = Utils.checkGameStatus();
        if(!status.equals("IN-PROGRESS")){
            GraphPanel.getInstance().getTextArea().append(status + System.lineSeparator());
        }else{
            GraphPanel.getInstance().getTextArea().append("Node color changed to " + Utils.getStringFromColor(color) + System.lineSeparator());
        }

        }
    }
    private static void expandColor(Node currentNode, Color oldColor, Color newColor){
        for(Node n: GraphPanel.getInstance().getNodes()){
            if(currentNode.getId()!=n.getId() && isNeighbor(n,currentNode)){
                if(n.color.equals(oldColor)){
                    n.setColor(newColor);
                    expandColor(n,oldColor, newColor);
                }
            }
        }
    }
    private static Node returnSelectedNode(List<Node> list){
        for (Node n : list) {
            if (n.isSelected()) {
                return n;
            }
        }
        return null;
    }
    public static boolean isNeighbor(Node n1, Node n2){
        List<Edge> edgeList = GraphPanel.getInstance().getEdges();
        for(Edge e:edgeList){
            if(e.getN1().getId()==n1.getId() && e.getN2().getId()==n2.getId())
                return true;
        }
        return false;
    }
    @Override
    public String toString(){
        return "node("+this.getId()+","+ Utils.getStringFromColor(color)+","+"0).";
    }

}
