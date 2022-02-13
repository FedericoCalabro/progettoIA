import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class GraphPanel extends JComponent {
    public static int id = 0;
    private static GraphPanel instance = null;

    private ControlPanel controlPanel = new ControlPanel();
    private JTextArea textArea = new JTextArea(0,20);
    private int radius = Utils.RADIUS;
    private List<Node> nodes = new ArrayList<Node>();
    private List<Node> selected = new ArrayList<Node>();
    private List<Edge> edges = new ArrayList<Edge>();
    private Point mousePt = new Point(Utils.WIDE / 2, Utils.HIGH / 2);
    private Rectangle mouseRect = new Rectangle();
    private boolean selecting = false;

    private GraphPanel() {
        this.setOpaque(true);
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseMotionHandler());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Utils.WIDE, Utils.HIGH);
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


    public static GraphPanel getInstance(){
        if(instance == null){
            instance = new GraphPanel();
        }
        return instance;
    }

    public void exportCurrentFacts() throws IOException {
        StringBuilder facts = new StringBuilder("");

        facts.append("maxRounds("+ controlPanel.getMaxRoundsSpinner().getValue() +").");
        facts.append(System.lineSeparator());

        for (int i = 0; i < edges.size(); i++){
            facts.append(edges.get(i).toString());
            facts.append(System.lineSeparator());
        }
        for (int i = 0; i < nodes.size(); i++){
            facts.append(nodes.get(i).toString());
            facts.append(System.lineSeparator());
        }

        FileHandler.overwriteFile(FileHandler.FACTS_PATH, facts.toString());
    }
}