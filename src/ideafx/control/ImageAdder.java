package ideafx.control;

import animatefx.animation.BounceIn;
import com.jfoenix.controls.JFXSpinner;
import ideafx.model.prova.jsoninfos.Project;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 *
 * @author flo
 */
public class ImageAdder {

    private Random r = new Random();

    private ExecutorService executor;

    public ImageAdder() {
        executor = Executors.newFixedThreadPool(4);
    }

    public void asyncAdd(final Project project, Pane root, JFXSpinner spinn, double gap,EventHandler onclick) {
        if (project != null) {
            executor.submit(() -> {

                // load an image in the background.
                final Image image = new Image(project.getCovers().getOriginal(), true);

                project.setLinkedImage(image);
                
                image.progressProperty().addListener(
                        (ObservableValue<? extends Number> observable, Number oldValue, Number progress) -> {
                            if ((Double) progress == 1.0 && !image.isError()) {

                                try {
                                    Pane root_ = FXMLLoader.load(getClass().getResource("post.fxml"));

                                    root_.setOnMouseClicked(onclick);
                                    
                                    ((Label) root_.getChildren().get(0)).setText(project.getName());

                                    ((Label) ((HBox) ((HBox) root_.getChildren().get(2)).getChildren().get(0)).getChildren().get(1)).setText("" + project.getStats().getViews());
                                    ((Label) ((HBox) ((HBox) root_.getChildren().get(2)).getChildren().get(1)).getChildren().get(1)).setText("" + project.getStats().getAppreciations());
                                    ((Label) ((HBox) ((HBox) root_.getChildren().get(2)).getChildren().get(2)).getChildren().get(1)).setText("" + project.getStats().getComments());

                                    ImageView iv = ((ImageView) root_.getChildren().get(1));

                                    int futureWidth = r.nextInt(400) + 200;

                                    iv.setImage(image);

                                    iv.setPreserveRatio(true);
                                    iv.setFitWidth(futureWidth);
                                    iv.setFitHeight(futureWidth);

                                    Pane container = new Pane(root_);
                                    
                                    root.getChildren().addAll(container);
                                    
                                    new BounceIn(container)
                                            .play();

                                } catch (IOException ex) {
                                    Logger.getLogger(ImageAdder.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                spinn.setProgress(spinn.getProgress() + gap);
                            }
                        });

            });
        }
    }

    public void shutdown() {
        executor.shutdown();
    }

}
