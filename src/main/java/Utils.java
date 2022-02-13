import java.awt.*;
import java.util.List;
import java.util.Random;

public class Utils {
    public static final int WIDE = 640;
    public static final int HIGH = 480;
    public static final int RADIUS = 20;

    public static Point getRandomPoint() {
        int width = GraphPanel.getInstance().getWidth();
        int height = GraphPanel.getInstance().getHeight();

        System.out.println("w="+width);
        System.out.println("h="+height);

        Random random = new Random();
        int x = random.nextInt(20, width-20);
        int y = random.nextInt(20, height-20);

        return new Point(x, y);
    }

    public static String getStringFromColor(Color color){
        int r,g,b;
        r = color.getRed();
        g = color.getGreen();
        b = color.getBlue();
        String colorString;
        if(r == 0 && g == 0 && b == 255)
            colorString = "blue";
        else if(r == 255 && g == 0 && b == 0)
            colorString = "red";
        else if(r == 0 && g == 255 && b == 0)
            colorString = "green";
        else if(r == 255 && g == 255 && b == 0)
            colorString = "yellow";
        else
            colorString = "orange";
        return colorString;
    }

    public static Color getColorFromString(String color) {
        if(color.equals("red")) return Color.RED;
        else if(color.equals("blue")) return Color.BLUE;
        else if(color.equals("green")) return Color.GREEN;
        else if(color.equals("yellow")) return Color.YELLOW;
        else return Color.ORANGE;
    }

    public static Node retrieveNodeFromList(int id){
        List<Node> nodeList = GraphPanel.getInstance().getNodes();
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.get(i);
            if (node.getId() == id)
                return node;
        }
        return null;
    }
}
