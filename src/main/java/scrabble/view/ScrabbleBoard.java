package scrabble.view

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ScrabbleBoard extends Application {

    private int numberOfPlayers;
    private int[] scores;

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(0, 20, 20, 70)); 
        gridPane.setHgap(0);
        gridPane.setVgap(0);

        int rows = 15;
        int cols = 15;

        Color[][] colors = new Color[][]{
            {Color.RED, null, null, Color.LIGHTBLUE, null, null, null, Color.RED, null, null, null, Color.LIGHTBLUE, null, null, Color.RED},
            {null, Color.LIGHTPINK, null, null, null, Color.BLUE, null, null, null, Color.BLUE, null, null, null, Color.LIGHTPINK, null},
            {null, null, Color.LIGHTPINK, null, null, null, Color.LIGHTBLUE, null, Color.LIGHTBLUE, null, null, null, Color.LIGHTPINK, null, null},
            {Color.LIGHTBLUE, null, null, Color.LIGHTPINK, null, null, null, Color.LIGHTBLUE, null, null, null, Color.LIGHTPINK, null, null, Color.LIGHTBLUE},
            {null, null, null, null, Color.LIGHTPINK, null, null, null, null, null, Color.LIGHTPINK, null, null, null, null},
            {null, Color.BLUE, null, null, null, Color.BLUE, null, null, null, Color.BLUE, null, null, null, Color.BLUE, null},
            {null, null, Color.LIGHTBLUE, null, null, null, Color.LIGHTBLUE, null, Color.LIGHTBLUE, null, null, null, Color.LIGHTBLUE, null, null},
            {Color.RED, null, null, Color.LIGHTBLUE, null, null, null, Color.LIGHTPINK, null, null, null, Color.LIGHTBLUE, null, null, Color.RED},
            {null, null, Color.LIGHTBLUE, null, null, null, Color.LIGHTBLUE, null, Color.LIGHTBLUE, null, null, null, Color.LIGHTBLUE, null, null},
            {null, Color.BLUE, null, null, null, Color.BLUE, null, null, null, Color.BLUE, null, null, null, Color.BLUE, null},
            {null, null, null, null, Color.LIGHTPINK, null, null, null, null, null, Color.LIGHTPINK, null, null, null, null},
            {Color.LIGHTBLUE, null, null, Color.LIGHTPINK, null, null, null, Color.LIGHTBLUE, null, null, null, Color.LIGHTPINK, null, null, Color.LIGHTBLUE},
            {null, null, Color.LIGHTPINK, null, null, null, Color.LIGHTBLUE, null, Color.LIGHTBLUE, null, null, null, Color.LIGHTPINK, null, null},
            {null, Color.LIGHTPINK, null, null, null, Color.BLUE, null, null, null, Color.BLUE, null, null, null, Color.LIGHTPINK, null},
            {Color.RED, null, null, Color.LIGHTBLUE, null, null, null, Color.RED, null, null, null, Color.LIGHTBLUE, null, null, Color.RED}
        };

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                StackPane cell = new StackPane();
                Rectangle rectangle = new Rectangle(40, 40);
                rectangle.setStroke(Color.BLACK);
                Color color = colors[row][col];
                Label label = new Label();
                if (color != null) {
                    rectangle.setFill(color);
                    if (color == Color.RED || color == Color.BLUE) {
                        label.setText("x3");
                        label.setTextFill(Color.WHITE);
                    } else if (color == Color.LIGHTBLUE || color == Color.LIGHTPINK) {
                        label.setText("x2");
                        label.setTextFill(Color.BLACK);
                    }
                } else {
                    rectangle.setFill(Color.CADETBLUE);
                }
                cell.getChildren().addAll(rectangle, label);
                gridPane.add(cell, col, row);
            }
        }


        Label starLabel = new Label("🌟");
        StackPane centerCell = (StackPane) gridPane.getChildren().get(7 * cols + 7);
        centerCell.getChildren().add(starLabel);

        HBox chevalet = new HBox();
        chevalet.setAlignment(Pos.CENTER);
        chevalet.setSpacing(10);
        chevalet.setPadding(new Insets(10));

        for (int i = 0; i < 7; i++) {
            Rectangle letterTile = new Rectangle(40, 40);
            letterTile.setStroke(Color.BLACK);
            letterTile.setFill(Color.WHITE);
            chevalet.getChildren().add(letterTile);
        }

        VBox root = new VBox();
        root.setPadding(new Insets(40, 20, 20, 20));
        root.setSpacing(0);
        root.setAlignment(Pos.CENTER);

        root.getChildren().add(gridPane);

        root.getChildren().add(chevalet);

        Scene scene = new Scene(root, 800, 900);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scrabble Board");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
