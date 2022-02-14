package coloruid.actions;

import coloruid.utils.FileManager;
import coloruid.gui.GraphPanel;
import coloruid.utils.Utils;
import coloruid.core.Edge;
import coloruid.core.Node;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;

public class LoadLevelAction extends AbstractAction {

    public LoadLevelAction(String name) {
        super(name);
    }

    @SneakyThrows
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(FileManager.LEVEL_PATH_FOLDER);

        int returnVal = fileChooser.showOpenDialog(GraphPanel.getInstance());

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String path = file.getAbsolutePath();
            Integer maxRounds = FileManager.getMaxRoundsFromFile(path);
            if(maxRounds != null){
                GraphPanel.getInstance().getControlPanel().getMaxRoundsSpinner().setValue(maxRounds);
                ArrayList<Node> nodes = FileManager.getNodesFromFile(path);
                if(nodes != null){
                    GraphPanel.getInstance().setNodes(nodes);
                    ArrayList<Edge> edges = FileManager.getEdgesFromFile(path);
                    GraphPanel.getInstance().setEdges(edges);
                    if(edges != null){
                        GraphPanel.getInstance().setEdges(edges);
                        JTextArea textArea = GraphPanel.getInstance().getTextArea();
                        textArea.append("successfully loaded: " + file.getName() + System.lineSeparator());
                        for(Node node : GraphPanel.getInstance().getNodes()){
                            textArea.append(node.toString());
                            textArea.append(System.lineSeparator());
                        }
                        for(Edge edge : GraphPanel.getInstance().getEdges()){
                            textArea.append(edge.toString());
                            textArea.append(System.lineSeparator());
                        }
                    }
                }
            }
        }
        Utils.updateStepLabel(0);
        GraphPanel.getInstance().repaint();
        GraphPanel.getInstance().getTextArea().repaint();
    }
}
