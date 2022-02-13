import lombok.Data;
import lombok.ToString;

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

    /**
     * Construct a new node.
     */
    public Node(int id, Point p, Color color) {
        this.id = id;
        this.p = p;
        this.r = 20;
        this.color = color;
        setBoundary(b);
    }

    /**
     * Calculate this node's rectangular boundary.
     */
    private void setBoundary(Rectangle b) {
        b.setBounds(p.x - r, p.y - r, 2 * r, 2 * r);
    }

    /**
     * Draw this node.
     */
    public void draw(Graphics g) {

        g.setColor(this.color);
        g.fillOval(b.x, b.y, b.width, b.height);
        g.setColor(Color.black);
        g.drawString(String.valueOf(id),b.x + b.width/2,b.y + b.height/2 );
        if (selected) {
            g.setColor(Color.darkGray);
            g.drawRect(b.x, b.y, b.width, b.height);
        }
    }

    /**
     * Return this node's location.
     */
    public Point getLocation() {
        return p;
    }

    /**
     * Return true if this node contains p.
     */
    public boolean contains(Point p) {
        return b.contains(p);
    }

    /**
     * Return true if this node is selected.
     */
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

    /**
     * Select each node in r.
     */
    public static void selectRect(java.util.List<Node> list, Rectangle r) {
        for (Node n : list) {
            n.setSelected(r.contains(n.p));
        }
    }

    /**
     * Toggle selected state of each node containing p.
     */
    public static void selectToggle(java.util.List<Node> list, Point p) {
        for (Node n : list) {
            if (n.contains(p)) {
                n.setSelected(!n.isSelected());
            }
        }
    }

    /**
     * Update each node's position by d (delta).
     */
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
        //int countSelected = 0;
        Color oldColor = null;
        Node clickedNode = null;
        for (Node n : list) {
            if (n.isSelected()) {
                clickedNode = n;
                oldColor = n.color;
                n.color = color;
            }
        }
        //Serve una funzione che, se esiste un nodo del colore che abbiamo cambiato
        //connesso al nodo cliccato, cambia il colore di quel nodo.
        //Si può fare iterando tra i nodi, escluso quello cliccato, e cercando un path
        //boolean [] visited = new boolean[GraphPanel.getInstance().getNodes().size()+1]
    }

    @Override
    public String toString(){
        return "Node "+this.getId()+": color -> "+this.getColor().toString()+")";
    }
}
