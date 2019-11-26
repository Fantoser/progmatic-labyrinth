package com.progmatic.labyrinthproject;

import com.progmatic.labyrinthproject.Players.WallFollowerPlayer;
import com.progmatic.labyrinthproject.enums.CellType;
import com.progmatic.labyrinthproject.enums.Direction;
import com.progmatic.labyrinthproject.exceptions.CellException;
import com.progmatic.labyrinthproject.exceptions.InvalidMoveException;
import com.progmatic.labyrinthproject.interfaces.Player;

public class Main {
    public static void main(String[] args) throws InvalidMoveException {
        LabyrinthImpl laby = new LabyrinthImpl();

        laby.loadLabyrinthFile("labyrinth1.txt");

        Player wp = new WallFollowerPlayer();

        for (int i = 0; i < 10; i++) {
            Direction d = wp.nextMove(laby);
            System.out.println(d);
            laby.movePlayer( d );
        }

    }
}