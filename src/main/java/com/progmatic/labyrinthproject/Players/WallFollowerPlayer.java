package com.progmatic.labyrinthproject.Players;

import java.util.List;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

public class WallFollowerPlayer implements Player {

    Direction direction = Direction.EAST;

    @Override
    public Direction nextMove(Labyrinth l) {
        List<Direction> moveList = l.possibleMoves();

        if (moveList.contains(turnRight())) {
            direction = turnRight();
            System.out.println("turn right");
            return direction;
        } else if (moveList.contains(direction)) {
            System.out.println("forward");
            return direction;
        } else if (moveList.contains(turnLeft())) {
            System.out.println("turn left");
            direction = turnLeft();
            return direction;
        }
        System.out.println("turn back");
        direction = turnBack();
        return direction;
    }

    private Direction turnRight() {
        switch(direction) {
            case NORTH:
                return Direction.EAST;
            case EAST:
                return Direction.SOUTH;
            case SOUTH:
                return Direction.WEST;
            default:
                return Direction.NORTH;
        }
    }

    private Direction turnLeft() {
        switch(direction) {
            case NORTH:
                return Direction.WEST;
            case EAST:
                return Direction.NORTH;
            case SOUTH:
                return Direction.EAST;
            default:
                return Direction.SOUTH;
        }
    }

    private Direction turnBack() {
        switch(direction) {
            case NORTH:
                return Direction.SOUTH;
            case EAST:
                return Direction.WEST;
            case SOUTH:
                return Direction.NORTH;
            default:
                return Direction.EAST;
        }
    }
    
}