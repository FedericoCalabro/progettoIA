package coloruid.handlers;

import coloruid.gui.GraphPanel;
import coloruid.core.Node;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseMotionHandler extends MouseMotionAdapter {

    Point delta = new Point();

    @Override
    public void mouseDragged(MouseEvent e) {
        Point mousePt = GraphPanel.getInstance().getMousePt();
        if (GraphPanel.getInstance().isSelecting()) {
            GraphPanel.getInstance().getMouseRect().setBounds(
                    Math.min(mousePt.x, e.getX()),
                    Math.min(mousePt.y, e.getY()),
                    Math.abs(mousePt.x - e.getX()),
                    Math.abs(mousePt.y - e.getY()));
            Node.selectRect(GraphPanel.getInstance().getNodes(), GraphPanel.getInstance().getMouseRect());
        } else {
            delta.setLocation(e.getX() - mousePt.x, e.getY() - mousePt.y);
            Node.updatePosition(GraphPanel.getInstance().getNodes(), delta);
            GraphPanel.getInstance().setMousePt(e.getPoint());
        }
        e.getComponent().repaint();
    }
}
