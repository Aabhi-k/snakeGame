package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Board {
	private static final int ROWS = 25;
	private static final int COLS = 25;
	private static final int SQUARE_SIZE = 35;
	private int score = 0;
	public BorderPane board;
	private Label scoreLabel;
	
	
	
	public BorderPane createMainBoard() {
		// TODO Auto-generated method stub
		board = new BorderPane();
		GridPane grid = gridBoard();
		StackPane topPane = stackBoard();
		
		board.setBackground(new Background(new BackgroundFill(Color.valueOf("#010A0B"), CornerRadii.EMPTY, Insets.EMPTY)));
		
		board.setTop(topPane);		
		board.setCenter(grid);
		
		return board;
	}

	private StackPane stackBoard() {
		
		StackPane stack = new StackPane();
		Label scoreLabel = genScoreLabel();
		stack.getChildren().add(scoreLabel);
	

		StackPane.setAlignment(scoreLabel, Pos.CENTER);
		stack.setAlignment(Pos.BOTTOM_LEFT);
		
		
		
		return stack;
	}

	public Label genScoreLabel() {
		// TODO Auto-generated method stub
		score = gm.updateScore();
		
		scoreLabel = new Label("SCORE\n    " + score);
		
		scoreLabel.setFont(new Font(16));
		scoreLabel.setTextFill(Color.WHITE);

		return scoreLabel;
	}
	

	private GridPane gridBoard() {
		GridPane board = new GridPane();
		
		board.setHgap(2);
		board.setVgap(2);
		
		for (int i = 1; i <= ROWS; i++) {
			for (int j = 1; j <= COLS; j++) {
				Rectangle cell = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);
				cell.setFill(Color.valueOf("#504D47"));
                board.add(cell, j, i);
			}
		}

		return board;
	}

	
	public int getSquareSize() {
		return SQUARE_SIZE;
	}
	public int getRow() {
		return ROWS;
	}
	public int getCol() {
        return COLS;
    }
	public GridPane getBPCenter() {
		return (GridPane) board.getCenter();
	}
	

}
