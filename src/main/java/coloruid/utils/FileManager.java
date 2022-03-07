package coloruid.utils;

import coloruid.core.Edge;
import coloruid.core.Node;
import coloruid.gui.GraphPanel;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {

    public static final String FACTS_PATH = "src/main/resources/facts";
    public static final String LEVEL_PATH_FOLDER = "src/main/resources/levels/";

    public static void overwriteFile(String path, String text) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path, false));
        writer.write(text);
        writer.close();
    }

    public static ArrayList<Node> getNodesFromFile(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String regex = "node\\((.*)\\)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String nodeValue = matcher.group(1);
                int id = Integer.parseInt(nodeValue.split(",")[0]);
                String color = nodeValue.split(",")[1];
                Node node = new Node(id, Utils.getRandomPoint(), Utils.getColorFromString(color));
                nodes.add(node);
            }
        }
        for (int i = 0; i < nodes.size(); i++) {
            Node node1 = nodes.get(i);
            //node1.setP(Utils.getPointForId(node1.getId(),(int)Math.sqrt(nodes.size())));
            for (int j = 0; j < nodes.size(); j++) {
                if(i != j){
                    Node node2 = nodes.get(j);
                    if(node1.getId() == node2.getId()){
                        GraphPanel.getInstance().getTextArea().append("Cannot add two nodes with same id: " + node1.getId());
                        return null;
                    }
                }
            }
        }
        return nodes;
    }

    public static ArrayList<Edge> getEdgesFromFile(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String regex = "edge\\((.*)\\)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String edgeValue = matcher.group(1);
                int node1 = Integer.parseInt(edgeValue.split(",")[0]);
                int node2 = Integer.parseInt(edgeValue.split(",")[1]);

                Node realNode1 = Utils.retrieveNodeFromList(node1);
                Node realNode2 = Utils.retrieveNodeFromList(node2);

                if(realNode1 == null || realNode2 == null){
                    GraphPanel.getInstance().getTextArea().append("Cannot add edge(" + node1 + ","+node2 +").");
                    return null;
                }

                Edge edge = new Edge(realNode1, realNode2);
                edges.add(edge);
            }
        }
        return edges;
    }

    public static Integer getMaxRoundsFromFile(String path) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path));
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String regex = "maxRounds\\((.*)\\)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                return Integer.parseInt(matcher.group(1));
            }
        }
        GraphPanel.getInstance().getTextArea().append("MaxRounds was not provided, cannot proceed");
        return null;
    }


}
