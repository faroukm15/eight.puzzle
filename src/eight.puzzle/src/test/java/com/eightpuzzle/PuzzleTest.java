package com.eightpuzzle;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class PuzzleTest {
    private Puzzle puzzle;
    private Solver solver;
    private PuzzleState initialState;

    @Before
    public void setup() {
        initialState = new PuzzleState();

        ArrayList<ArrayList<Integer>> state = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            state.add(new ArrayList<>());
            for (int j = 0; j < 3; j++) {
                state.get(i).add(0);
            }
        }


        state.get(0).set(0, 1);
        state.get(0).set(1, 2);
        state.get(0).set(2, 3);
        state.get(1).set(0, 7);
        state.get(1).set(1, 0);
        state.get(1).set(2, 6);
        state.get(2).set(0, 5);
        state.get(2).set(1, 4);
        state.get(2).set(2, 8);

        initialState.setPuzzle(state);

        solver = new BFSSolver();
        puzzle = new Puzzle(initialState , solver);
    }

    @Test
    public void testBfs() {
        puzzle.solve();
        ArrayList<PuzzleState> solution = puzzle.getSolution();

        for (PuzzleState ps: solution) {
            System.out.println("--------------------");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(ps.getPuzzle().get(i).get(j));
                    System.out.print(" ");
                }
                System.out.println("");
            }
        }

        assert(solution.get(solution.size() - 1).isSolvedState());
    }
}
