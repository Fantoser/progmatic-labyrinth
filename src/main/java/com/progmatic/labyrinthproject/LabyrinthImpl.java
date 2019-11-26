package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pappgergely
 */
public class LabyrinthImpl implements Labyrinth {

    ArrayList<ArrayList<CellType>> labyData = new ArrayList<>();
    Coordinate playerPos = new Coordinate(0, 0);
    Coordinate endPos = new Coordinate(0, 0);
    private int width;
    private int height;

    public LabyrinthImpl() {
        
    }

    @Override
    public void loadLabyrinthFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            this.width = Integer.parseInt(sc.nextLine());
            this.height = Integer.parseInt(sc.nextLine());

            for (int hh = 0; hh < height; hh++) {
                String line = sc.nextLine();
                labyData.add(new ArrayList<CellType>());
                for (int ww = 0; ww < width; ww++) {
                    switch (line.charAt(ww)) {
                    case 'W':
                        labyData.get(hh).add(ww, CellType.WALL);
                        break;
                    case 'E':
                        labyData.get(hh).add(ww, CellType.END);
                        endPos = new Coordinate(ww, hh);
                        break;
                    case 'S':
                        labyData.get(hh).add(ww, CellType.START);
                        playerPos = new Coordinate(ww, hh);
                        break;
                    case ' ':
                        labyData.get(hh).add(ww, CellType.EMPTY);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public CellType getCellType(Coordinate c) throws CellException {
        if (c.getRow() < 0 || c.getRow() > this.height-1 || c.getCol() < 0 || c.getCol() > this.width-1) {
            throw new CellException(c, "This coordinate is out of bound!");
        } else if (labyData.isEmpty()) {
            throw new CellException(c, "There is no labyrinth!");
        } else {
            return labyData.get(c.getRow()).get(c.getCol());
        }
    }

    @Override
    public int getHeight() {
        return this.height == 0 ? -1 : this.height;
    }

    @Override
    public int getWidth() {
        return this.width == 0 ? -1 : this.width;
    }

    @Override
    public Coordinate getPlayerPosition() {
        return playerPos;
    }

    @Override
    public boolean hasPlayerFinished() {
        if (playerPos.getRow() == endPos.getRow() && playerPos.getCol() == endPos.getCol()) {
            return true;
        }
        return false;
    }

    @Override
    public void movePlayer(Direction direction) throws InvalidMoveException {
        if (possibleMoves().contains(direction)) {
            if (direction == Direction.NORTH) {
                playerPos = new Coordinate(playerPos.getCol(), playerPos.getRow()-1);
            }
            
            if (direction == Direction.EAST) {
                playerPos = new Coordinate(playerPos.getCol()+1, playerPos.getRow());
            }
    
            if (direction == Direction.SOUTH) {
                playerPos = new Coordinate(playerPos.getCol(), playerPos.getRow()+1);
            }
    
            if (direction == Direction.WEST) {
                playerPos = new Coordinate(playerPos.getCol()-1, playerPos.getRow());
            }
        } else {
            throw new InvalidMoveException();
        }
    }

    @Override
    public List<Direction> possibleMoves() {
        List<Direction> moveList = new ArrayList<>();
        if (playerPos.getRow() > 0){
            if (labyData.get(playerPos.getRow()-1).get(playerPos.getCol()) != CellType.WALL) {
                moveList.add(Direction.NORTH);
            }
        }
        if (playerPos.getRow() < this.height-2){
            if (labyData.get(playerPos.getRow()+1).get(playerPos.getCol()) != CellType.WALL) {
                moveList.add(Direction.SOUTH);
            }
        }
        if (playerPos.getCol() > 0){
            if (labyData.get(playerPos.getRow()).get(playerPos.getCol()-1) != CellType.WALL) {
                moveList.add(Direction.WEST);
            }
        }
        if (playerPos.getCol() < this.width-1){
            if (labyData.get(playerPos.getRow()).get(playerPos.getCol()+1) != CellType.WALL) {
                moveList.add(Direction.EAST);
            }
        }
        return moveList;
    }

    @Override
    public void setCellType(Coordinate c, CellType type) throws CellException {
        if (c.getRow() < 0 || c.getRow() > this.height-1 || c.getCol() < 0 || c.getCol() > this.width-1) {
            throw new CellException(c, "This coordinate is out of bound!");
        } else {
            if (c.getRow() > labyData.size()-1) {
                labyData.add(new ArrayList<CellType>());
            }
            if (c.getCol() > labyData.get(c.getRow()).size()-1) {
                labyData.get(c.getRow()).add(type);
            } else {
                labyData.get(c.getRow()).set(c.getCol(), type);
            }
            if (type == CellType.START) {
                playerPos = c;
            }
            if (type == CellType.END) {
                endPos = c;
            }
        }

    }

    @Override
    public void setSize(int width, int height) {
        
        this.width = width;
        this.height = height;
    }

    

}
