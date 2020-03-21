package com.eightpuzzle;

import java.util.ArrayList;

public interface Solver {

    public ArrayList<PuzzleState> getSolution();
    public void solve(PuzzleState initialState);
}
