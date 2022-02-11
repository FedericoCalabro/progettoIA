import lombok.Data;

import java.awt.*;

@Data
public class Edge {

    private Node n1;
    private Node n2;

    public Edge(Node n1, Node n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    public void draw(Graphics g) {
        Point p1 = n1.getLocation();
        Point p2 = n2.getLocation();
        g.setColor(Color.darkGray);
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
}