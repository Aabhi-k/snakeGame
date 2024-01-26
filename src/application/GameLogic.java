package application;

import java.util.Random;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;


public class GameLogic {
	
	public BorderPane gameBoard;
	
	private Board board;
	private GridPane center;
	private Point initPos;
	private Snake snake;
	Direction direction;
	private static int score = 0;
	
	private boolean gameState;
	private Point food;
	private Scene scene;
	
	
	public GameLogic() {
		
		board = new Board();
		gameBoard = board.createMainBoard();
		
		initPos = new Point(board.getRow()/2, board.getRow()/2);
		snake = new Snake(initPos);
		
		center = board.getBPCenter();
		gameState = false;
		generateFood(center);
		

		
		
		gameLoop();
	}
	
	private void generateFood(GridPane grid) {
		
			
		Random rand = new Random();
		int min = 1;
		int maxX = board.getRow();
		int maxY = board.getCol();
		
		int randX = rand.nextInt(maxX - min) + min;
		int randY = rand.nextInt(maxY - min) + min;
		Point foodPosition = new Point(randX, randY);
		while(snake.validFoodPosition(foodPosition)) {
			randX = rand.nextInt(maxX - min) + min;
		    randY = rand.nextInt(maxY - min) + min;
		    foodPosition = new Point(randX, randY);
		}
		
		
			
		food = foodPosition;
		Rectangle foodRec = new Rectangle(board.getSquareSize(), board.getSquareSize());
		foodRec.setFill(Color.RED);

		grid.add(foodRec, food.getX(), food.getY());
			
	}
	
	private void hasEatenFood() {
		Point head = snake.getHead();
		if(head.getX() == food.getX() && head.getY() == food.getY()) {
			generateFood(center);
			snake.grow();
			updateScore();
						
		}
	
	}
	private void userInput() {
		this.scene.setOnKeyPressed(e -> handleInput(e));
		
	}
	
	public int updateScore() {
		score += 1;
		board.genScoreLabel();
		return score;
	}
	
	private boolean hasGameEnded() {
		
		return gameState;
	}
	private AnimationTimer timer = new AnimationTimer() {
	    private long lastTick = System.nanoTime();
	    private static final long GAME_TICK_NANOS = 200_000_000; // 0.5 seconds in nanoseconds

	    @Override
	    public void handle(long now) {
	        long currentTime = System.nanoTime();
	        double deltaTime = (currentTime - lastTick) / GAME_TICK_NANOS;

	        if (deltaTime >= 1.0) {	            
	        	
	        	
	        	hasEatenFood();
	        	snake.drawSnake(center);
	        	
	        	System.out.println(snake.getDirection());

	            lastTick = currentTime; 
	        }
	        userInput();
	        
	        
	    }
	};
	public void initializeGameLogic() {
	    scene = Main.getScene();
	    scene.setOnKeyPressed(event -> handleInput(event));
	}
	
	
	public void gameLoop(){
		if(!hasGameEnded()) {
			timer.start();
		}else {
			stopGame();
		}
	}
	
	
	public void handleInput(KeyEvent event) {
		KeyCode key =  event.getCode();
		switch (key) {
		
        case W:
        	if (direction != Direction.DOWN) {
        		
        		direction = Direction.UP;                       
        	}
        	break;
        case S:
                if (direction != Direction.UP) {
                    direction = Direction.DOWN;
                    
                }
                break;
        case A:
                if (direction != Direction.RIGHT) {
                    direction = Direction.LEFT;
                    
                }
                break;
        case D:
                if (direction != Direction.LEFT) {
                    direction = Direction.RIGHT;
                   
                }
                break;
        default:
				break;
				
			}
			snake.setDirection(direction);
		
	}
	
	public void stopGame() {
        gameState = true;
        
        timer.stop();
        Platform.exit();
    }
	
	
	
}
