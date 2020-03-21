package com.eightpuzzle;

import lombok.*;

import java.util.ArrayList;
import java.util.Collections;

@Data
public class PuzzleState implements Comparable {

    @Setter(AccessLevel.PRIVATE)
    @EqualsAndHashCode.Exclude
    private PuzzleState prevState;
    private ArrayList<ArrayList<Integer>> puzzle;

    private PuzzleState move(int locationX, int locationY, Direction direction) {
        PuzzleState newState = new PuzzleState();

        ArrayList<ArrayList<Integer>> newPuzzle =  new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            newPuzzle.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                newPuzzle.get(i).add(puzzle.get(i).get(j));
            }
        }

        if (direction.equals(Direction.LEFT) && (locationY - 1) >= 0) {
            int oldVal = newPuzzle.get(locationX).get(locationY);
            newPuzzle.get(locationX).set(locationY, puzzle.get(locationX).get(locationY - 1));
            newPuzzle.get(locationX).set(locationY - 1, oldVal);
        } else if (direction.equals(Direction.RIGHT) && (locationY + 1) <= 2) {
            int oldVal = newPuzzle.get(locationX).get(locationY);
            newPuzzle.get(locationX).set(locationY, puzzle.get(locationX).get(locationY + 1));
            newPuzzle.get(locationX).set(locationY + 1, oldVal);
        } else if (direction.equals(Direction.UP) && (locationX - 1) >= 0) {
            int oldVal = newPuzzle.get(locationX).get(locationY);
            newPuzzle.get(locationX).set(locationY, puzzle.get(locationX - 1).get(locationY));
            newPuzzle.get(locationX - 1).set(locationY, oldVal);
        } else if (direction.equals(Direction.DOWN) && (locationX + 1) <= 2) {
            int oldVal = newPuzzle.get(locationX).get(locationY);
            newPuzzle.get(locationX).set(locationY, puzzle.get(locationX + 1).get(locationY));
            newPuzzle.get(locationX + 1).set(locationY, oldVal);
        } else {
            return null;
        }

        newState.setPuzzle(newPuzzle);
        newState.prevState = this;

        return newState;
    }

    public ArrayList<PuzzleState> getNextStates() {
        ArrayList<PuzzleState> next = new ArrayList<>();

        int zeroX = -1, zeroY = -1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (puzzle.get(i).get(j).equals(0)) {
                    zeroX = i;
                    zeroY = j;
                    break;
                }
            }
            if (zeroX != -1) break;
        }

        PuzzleState leftState = move(zeroX, zeroY, Direction.LEFT);
        if (leftState != null)
            next.add(leftState);

        PuzzleState rightState = move(zeroX, zeroY, Direction.RIGHT);
        if (rightState != null)
            next.add(rightState);

        PuzzleState upState = move(zeroX, zeroY, Direction.UP);
        if (upState != null)
            next.add(upState);

        PuzzleState downState = move(zeroX, zeroY, Direction.DOWN);
        if (downState != null)
            next.add(downState);

        return next;
    }

    public boolean isSolvedState() {
        boolean solved = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 2 && j ==2) {
                    if (!puzzle.get(i).get(j).equals(0)) {
                        solved = false;
                    }
                } else if (!puzzle.get(i).get(j).equals((i * 3 + j) + 1)) {
                    solved = false;
                }
            }
        }

        return solved;
    }

    @Override
    public int compareTo(Object o) {
        if (hashCode() < o.hashCode())
            return -1;
        else if (hashCode() > o.hashCode())
            return 1;
        else
            return 0;
    }
}
