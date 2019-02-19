package ideafx.control;

import animatefx.animation.BounceIn;
import ideafx.model.prova.jsoninfos.Owner;
import ideafx.model.prova.jsoninfos.ProfileImages;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

/**
 *
 * @author flo
 */
public class OwnerAdder {

    private ExecutorService executor;

    private LineChart<Number, Number> lineChart;
    private ObservableList<Event> events;
    private Pane layout = new HBox();

    public OwnerAdder() {
        executor = Executors.newFixedThreadPool(4);
    }

    public void addAll(Pane hbox, ArrayList<Owner> owners) throws IOException {

        hbox.getChildren().clear();

        /*        HashSet<String> set = new HashSet<>();
        ArrayList<String> all_set = new ArrayList<>();*/
        for (int i = 0; i < owners.size(); i++) {
            asyncAdd(owners.get(i), hbox, (ev) -> {
                System.out.println("Tell me more about the owner of this project!");
                // TODO
            });

            /* set.addAll(owners.get(i).getFields());
            all_set.addAll(owners.get(i).getFields());*/
        }

    }

    public void shutdown(){
        executor.shutdown();
    }
    
    public void asyncAdd(final Owner owner, Pane root, EventHandler onclick) {
        if (owner != null) {
            executor.submit(() -> {

                // load an image in the background.
                final Image image = new Image(owner.getImages().getErr_100(), true);

                //owner.setLinkedImage(image);
                image.progressProperty().addListener(
                        (ObservableValue<? extends Number> observable, Number oldValue, Number progress) -> {
                            if ((Double) progress == 1.0 && !image.isError()) {

                                //final Color theme = Color.BLACK;
                                try {
                                    Pane root_ = FXMLLoader.load(getClass().getResource("owner.fxml"));

                                    root_.setOnMouseClicked(onclick);

                                    ((Label) root_.getChildren().get(0)).setText(owner.getDisplayName());

                                    Label city = ((Label) ((HBox) ((VBox) root_.getChildren().get(2)).getChildren().get(0)).getChildren().get(0));
                                    Label state = ((Label) ((HBox) ((VBox) root_.getChildren().get(2)).getChildren().get(0)).getChildren().get(1));
                                    Label location = ((Label) ((HBox) ((VBox) root_.getChildren().get(2)).getChildren().get(0)).getChildren().get(2));

                                    Label company = ((Label) ((HBox) ((VBox) root_.getChildren().get(2)).getChildren().get(1)).getChildren().get(0));
                                    Label createdOn = ((Label) ((HBox) ((VBox) root_.getChildren().get(2)).getChildren().get(1)).getChildren().get(1));

                                    Label occupation = ((Label) ((HBox) ((VBox) root_.getChildren().get(2)).getChildren().get(2)).getChildren().get(0));

                                    fillLabel(city, owner.getCity());
                                    fillLabel(state, owner.getState());
                                    fillLabel(location, owner.getLocation());

                                    fillLabel(company, owner.getCompany());
                                    fillLabel(createdOn, owner.getCreatedOn() + "");

                                    fillLabel(createdOn, owner.getOccupation());

                                    ImageView iv = ((ImageView) root_.getChildren().get(1));

                                    iv.setImage(image);

                                    iv.setPreserveRatio(true);

                                    iv.setFitHeight(ProfileImages.DIMENSIONE_100);
                                    iv.setFitWidth(ProfileImages.DIMENSIONE_100);

                                    Pane container = new Pane(root_);

                                    root.getChildren().addAll(container);

                                    new BounceIn(container)
                                            .play();

                                } catch (IOException ex) {
                                    Logger.getLogger(ImageAdder.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        });

            });
        }

    }

    private void fillLabel(Label label, String fill) {
        if (fill == null || fill.isEmpty()) {
            label.setVisible(false);
            label.setDisable(true);

            label.setScaleX(0);
            label.setScaleY(0);

        } else {
            label.setText("" + fill);
        }
    }

    void addChart(AnchorPane anchorPane, ArrayList<Owner> owners) {

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        xAxis.setMinorTickVisible(false);
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(1);
        xAxis.setUpperBound(3);
        xAxis.setTickUnit(1);

        //creating the chart
        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setAnimated(false);

        events = FXCollections.observableArrayList(
                new Event("Javelin", "6 6", FXCollections.observableArrayList(
                        createSeries("Javelin - Tokyo", FXCollections.observableArrayList(18, 20, 22)),
                        createSeries("Javelin - Kyoto", FXCollections.observableArrayList(23, 14, 15))
                ))
        );
        populateData(events,lineChart);

        anchorPane.getChildren().add(lineChart);
    }

    private void populateData(final ObservableList<Event> events, final LineChart<Number, Number> lineChart) {
        lineChart.getData().clear();
        for (Event event : events) {
            if (event.isActive()) {
                lineChart.getData().addAll(event.getSeries());
            }
        }
    }

    private void styleSeries(ObservableList<Event> events, final LineChart<Number, Number> lineChart) {
        // force a css layout pass to ensure that subsequent lookup calls work.
        lineChart.applyCss();

        // mark different series with different depending on whether they are above or below average.
        int nSeries = 0;
        for (Event event : events) {
            if (!event.isActive()) {
                continue;
            }
            for (int j = 0; j < event.getSeries().size(); j++) {
                XYChart.Series<Number, Number> series = event.getSeries().get(j);
                Set<Node> nodes = lineChart.lookupAll(".series" + nSeries);
                for (Node n : nodes) {
                    StringBuilder style = new StringBuilder();
                    if (event.isBelowAverage(series)) {
                        style.append("-fx-stroke: red; -fx-background-color: red, white; ");
                    } else {
                        style.append("-fx-stroke: blue; -fx-background-color: blue, white; ");
                    }
                    if (event.getStrokeDashArray() != null && !event.getStrokeDashArray().isEmpty()) {
                        style.append("-fx-stroke-dash-array: ").append(event.getStrokeDashArray()).append(";");
                    }

                    n.setStyle(style.toString());
                }
                nSeries++;
            }
        }
    }

    private XYChart.Series<Number, Number> createSeries(String name, List<Number> data) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName(name);
        ObservableList<XYChart.Data<Number, Number>> seriesData = FXCollections.observableArrayList();
        for (int i = 0; i < data.size(); i++) {
            seriesData.add(new XYChart.Data<>(i + 1, data.get(i)));
        }
        series.setData(seriesData);

        return series;
    }

    private class Event {

        private String name;
        private ObservableList<XYChart.Series<Number, Number>> series;
        private String strokeDashArray;
        private boolean isActive = true;

        public String getName() {
            return name;
        }

        public String getStrokeDashArray() {
            return strokeDashArray;
        }

        public Event(String name, String strokeDashArray, ObservableList<XYChart.Series<Number, Number>> series) {
            this.name = name;
            this.strokeDashArray = strokeDashArray;
            this.series = series;
        }

        public boolean isBelowAverage(XYChart.Series<Number, Number> checkedSeries) {
            double checkedSeriesAvg = calcSeriesAverage(checkedSeries);
            double allSeriesAvgTot = 0;
            double seriesCount = series.size();
            for (XYChart.Series<Number, Number> curSeries : series) {
                allSeriesAvgTot += calcSeriesAverage(curSeries);
            }
            double allSeriesAvg = seriesCount != 0 ? allSeriesAvgTot / seriesCount : 0;

            return checkedSeriesAvg < allSeriesAvg;
        }

        public ObservableList<XYChart.Series<Number, Number>> getSeries() {
            return series;
        }

        private double calcSeriesAverage(XYChart.Series<Number, Number> series) {
            double sum = 0;
            int count = series.getData().size();
            for (XYChart.Data<Number, Number> data : series.getData()) {
                sum += data.YValueProperty().get().doubleValue();
            }
            return count != 0 ? sum / count : 0;
        }

        private boolean isActive() {
            return isActive;
        }

        private void setActive(boolean isActive) {
            this.isActive = isActive;
        }
    }

}
