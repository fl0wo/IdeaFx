package ideafx.control.helpers;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author flo
 */
public class DraggableHelper {

    private Pane target;

    private double xOffset = 0;
    private double yOffset = 0;

    public DraggableHelper(Pane target) {
        this.target = target;
    }

    public void makeDraggable() {
        target.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        target.setOnMouseDragged((MouseEvent event) -> {
            target.getScene().getWindow().setX(event.getScreenX() - xOffset);
            target.getScene().getWindow().setY(event.getScreenY() - yOffset);
        });
    }

}
