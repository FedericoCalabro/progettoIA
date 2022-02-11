import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    @Override
    public void mouseReleased(MouseEvent e) {
        GraphPanel.getInstance().setSelecting(false);
        GraphPanel.getInstance().getMouseRect().setBounds(0, 0, 0, 0);
        if (e.isPopupTrigger()) {
            showPopup(e);
        }
        e.getComponent().repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point mousePt = GraphPanel.getInstance().getMousePt();
        java.util.List<Node> nodes = GraphPanel.getInstance().getNodes();

        mousePt.setLocation(e.getPoint());
        if (e.isShiftDown()) {
            Node.selectToggle(nodes, mousePt);
        } else if (e.isPopupTrigger()) {
            Node.selectOne(nodes, mousePt);
            showPopup(e);
        } else if (Node.selectOne(nodes, mousePt)) {
            GraphPanel.getInstance().setSelecting(false);
        } else {
            Node.selectNone(nodes);
            GraphPanel.getInstance().setSelecting(true);
        }
        e.getComponent().repaint();
    }

    private void showPopup(MouseEvent e) {
        GraphPanel.getInstance().getControlPanel().getPopup().show(e.getComponent(), e.getX(), e.getY());
    }
}
