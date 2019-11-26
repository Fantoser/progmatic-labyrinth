package com.progmatic.labyrinthproject.Players;

import java.util.List;

import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.interfaces.Labyrinth;
import com.progmatic.labyrinthproject.interfaces.Player;

public class RandomPlayer implements Player {

    @Override
    public Direction nextMove(Labyrinth l) {
        List<Direction> moveList = l.possibleMoves();

        double randomDouble = Math.random();
	    randomDouble = randomDouble * moveList.size();
        int randomNum = (int) randomDouble;
        
        return moveList.get(randomNum);
    }
    
}