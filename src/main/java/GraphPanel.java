import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


@Data
public class GraphPanel extends JComponent {
    public static int id = 0;
    private static GraphPanel instance = null;
    private static final int WIDE = 640;
    private static final int HIGH = 480;
    private static final int RADIUS = 20;
    private ControlPanel control = new ControlPanel();
    private JTextArea textArea = new JTextArea(0,20);
    private int radius = RADIUS;
    private List<Node> nodes = new ArrayList<Node>();
    private List<Node> selected = new ArrayList<Node>();
    private List<Edge> edges = new ArrayList<Edge>();
    private Point mousePt = new Point(WIDE / 2, HIGH / 2);
    private Rectangle mouseRect = new Rectangle();
    private boolean selecting = false;

    private GraphPanel() {
        this.setOpaque(true);
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseMotionHandler());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDE, HIGH);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(0x00f0f0f0));
        g.fillRect(0, 0, getWidth(), getHeight());
        for (Edge e : edges) {
            e.draw(g);
        }
        for (Node n : nodes) {
            n.draw(g);
        }
        if (selecting) {
            g.setColor(Color.darkGray);
            g.drawRect(mouseRect.x, mouseRect.y,
                    mouseRect.width, mouseRect.height);
        }
    }

    public ControlPanel getControlPanel() {
        return control;
    }

    public JTextArea getTextArea(){ return textArea;}

    public static GraphPanel getInstance(){
        if(instance == null){
            instance = new GraphPanel();
        }
        return instance;
    }

    public boolean getSelecting(){return selecting;}
}