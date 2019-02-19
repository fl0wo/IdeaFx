package ideafx.control;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXSpinner;
import ideafx.control.helpers.DraggableHelper;
import ideafx.model.prova.Model;
import ideafx.model.prova.jsoninfos.Project;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 *
 * @author flo
 */
public class MainActivityController implements Initializable {

    private Model model;

    private ImageAdder imgAdder = new ImageAdder();

    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        model = new Model();

        Task task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException, UnsupportedEncodingException, ExecutionException, ExecutionException, MalformedURLException {
                model.fireRequest();
                return null;
            }
        };

        task.setOnSucceeded(taskFinish -> {

            ArrayList<Project> projects = model.getProjects();

            final int nProjects = 20;
            spinn.setVisible(true);

            for (int i = 0; i < nProjects; i++) {

                final int j = i;
                
                imgAdder.asyncAdd(projects.get(j), masonry, spinn, 1D / nProjects, (ev) -> {
                    activate(moreDetailPane);
                    
                    try {
                        fill(moreDetailPane,projects.get(j));
                    } catch (IOException ex) {
                        Logger.getLogger(MainActivityController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    disable(mainPane);
                });
            }

            imgAdder.shutdown();

        });

        //Loading thread
        new Thread(task).start();
        
        
        settings_button.setOnMouseClicked((ev)->{
            settings_pane.getChildren().clear();
            settings_pane.getChildren().add(new Label("Settings"));
        });
                
        users_button.setOnMouseClicked((ev)->{
            settings_pane.getChildren().clear();
            settings_pane.getChildren().add(new Label("Users"));
        });
        
        color_theme_button.setOnMouseClicked((ev)->{
            settings_pane.getChildren().clear();
            JFXColorPicker jcp = new JFXColorPicker();
            jcp.setOnAction((ActionEvent ev1) -> {
                Color choosen = jcp.getValue();
                Background needed = new Background(new BackgroundFill(Paint.valueOf(choosen.toString()),CornerRadii.EMPTY,Insets.EMPTY));
                
                draggable.setBackground(needed);
                
                ObservableList<Node> childrens = masonry.getChildren();
                
                for(Node n : childrens){
                    changePostColor(((Pane)((Pane)n).getChildren().get(0)),needed,choosen);
                }

            });
            settings_pane.getChildren().add(jcp);
        });

        Platform.runLater(scrollPane::requestLayout);

        new DraggableHelper(draggable).makeDraggable();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void exit() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void minimize() {
        ((Stage) draggable.getScene().getWindow()).setIconified(true);
    }
    
    private void activate(Pane pane) {
        pane.setVisible(true);
        pane.setDisable(false);
    }

    private void disable(Pane pane) {
        pane.setVisible(false);
        pane.setDisable(true);
    }
    
    private void fill(Pane moreDetailPane, Project p) throws IOException {
        ((ImageView)moreDetailPane.getChildren().get(0)).setImage(p.getLinkedImage());
        ((Label)moreDetailPane.getChildren().get(3)).setText(p.getName());
        ((JFXButton)moreDetailPane.getChildren().get(1)).setOnMouseClicked((ev)->{
            disable(moreDetailPane);
            activate(mainPane);
        });

        FlowPane hbox = ((FlowPane)((Pane)moreDetailPane
                .getChildren().get(2))
                .getChildren().get(0));
             
        OwnerAdder oadd = new OwnerAdder();
        
        oadd.addAll(hbox,p.getOwners());
        
        oadd.shutdown();
    }

    @FXML
    private JFXButton color_theme_button;
    
    @FXML
    private JFXButton settings_button;
    
    @FXML
    private JFXButton users_button;
 
    
    @FXML
    private AnchorPane settings_pane;
    
    @FXML
    private JFXSpinner spinn;

    @FXML
    private AnchorPane draggable;

    @FXML
    private AnchorPane moreDetailPane;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private JFXMasonryPane masonry;

    @FXML
    private ScrollPane scrollPane;

    private void changePostColor(Pane n, Background choosen,Color color) {
        ((Label)n.getChildren().get(0)).setTextFill(Paint.valueOf(color.toString()));
        for (int i = 0; i < 3; i++){
            ((Label)((HBox)((HBox)n.getChildren().get(2)).getChildren().get(i)).getChildren().get(0)).setTextFill(Paint.valueOf(color.toString()));
            ((Label)((HBox)((HBox)n.getChildren().get(2)).getChildren().get(i)).getChildren().get(1)).setStyle("-fx-background-color : "+ toRGBCode(color));

        }
    }
        
    private String toRGBCode(Color color){
        return String.format( "#%02X%02X%02X",
            (int)( color.getRed() * 255 ),
            (int)( color.getGreen() * 255 ),
            (int)( color.getBlue() * 255 ) );
    }


}
