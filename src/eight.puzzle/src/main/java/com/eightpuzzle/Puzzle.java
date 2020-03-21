package com.eightpuzzle;

import java.util.ArrayList;

public class Puzzle {
    private PuzzleState puzzleState;
    private Solver solver;

    public Puzzle(PuzzleState initialState, Solver solver) {
        this.puzzleState = initialState;
        this.solver = solver;
    }

    public void solve() {
        solver.solve(puzzleState);
    }

    public ArrayList<PuzzleState> getSolution() {
        return solver.getSolution();
    }

}
