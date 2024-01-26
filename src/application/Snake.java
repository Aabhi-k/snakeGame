package application;

import java.util.*;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Snake {
	private Direction direction;
	private Point head;
	private ArrayList<Point> snakeList = new ArrayList<Point>();
	private Board board = new Board();
	private boolean isValid = false;
	


	public Snake(Point initPos) {
		this.direction = Direction.RIGHT;
		head = initPos;
		snakeList.add(head);
		
		grow();
		grow();
	}
	
	public void grow() {
		head = snakeList.get(getSize()-1);
		
		switch(direction) {
		case UP:
			head = new Point(head.getX(), head.getY()+1);
            break;
		case DOWN:
			head = new Point(head.getX(), head.getY()-1);
            break;
		case LEFT:
			head = new Point(head.getX()+1, head.getY());
            break;
		case RIGHT:
			head = new Point(head.getX()-1, head.getY());
            break;
		}
		
		snakeList.add(head);
		
		
	}
	
	public void removeTail(GridPane grid) {
		int size = getSize();
		
		Point snakeTail = snakeList.get(size-1);
			
			
		Rectangle gridRec = new Rectangle(board.getSquareSize(),board.getSquareSize());
		gridRec.setFill(Color.valueOf("#504D47"));
		grid.add(gridRec, snakeTail.getX(), snakeTail.getY());
			
		snakeList.remove(size-1);
			
			
			
		
	}
	private Point forwardHead() {
		Point newFHead;
		newFHead = snakeList.get(0);
		
		switch(direction) {
		case UP:
			newFHead = new Point(newFHead.getX(), newFHead.getY()-1);
            break;
		case DOWN:
			newFHead = new Point(newFHead.getX(), newFHead.getY()+1);
            break;
		case LEFT:
			newFHead = new Point(newFHead.getX()-1, newFHead.getY());
            break;
		case RIGHT:
			newFHead = new Point(newFHead.getX()+1, newFHead.getY());
            break;
		}
		
		
		return newFHead;
	}
	
	
	public void drawSnake(GridPane grid) {
		
		
		Point snakeHead = forwardHead();
		snakeList.add(0, snakeHead);
		
		removeTail(grid);
		System.out.println("drawSnake direction: " + direction);
		
		for (Point snakeSegment : snakeList) {
	        Rectangle snakeRect = new Rectangle(board.getSquareSize(), board.getSquareSize());
	        snakeRect.setFill(Color.GREEN);
	        grid.add(snakeRect, snakeSegment.getX(), snakeSegment.getY());
	    }
        
    }
	
	
	
	public boolean validFoodPosition(Point foodPosition) {
		// TODO Auto-generated method stub
		if(snakeList.contains(foodPosition)) {
			isValid = true;
		}
		else {
			isValid = false;
		}
		return isValid;
	}
	
	
	public Point getHead() {
		head = snakeList.get(0);
		return head;
	}
	
	public int getSize() {
		return snakeList.size();
	}
	public Direction getDirection() {
		System.out.println("getter: " + direction);
		return direction;	
	}
	
	public void setDirection(Direction direction) {
		System.out.println("setter: " + direction);
        this.direction = direction;
    }

}
