package com.eightpuzzle;

import lombok.Getter;

import java.util.*;

public class BFSSolver implements Solver {
    @Getter
    private ArrayList<PuzzleState> solution = null;
    @Getter
    private boolean solved = false;

    @Override
    public void solve(PuzzleState initialState) {
        solved = false;

        LinkedList<PuzzleState> nextStates = new LinkedList<>();
        Set<PuzzleState> visited = new TreeSet<>();

        nextStates.add(initialState);

        while (!nextStates.isEmpty()) {
            PuzzleState currentState = nextStates.remove();

            if (visited.contains(currentState)) {
                continue;
            }

            visited.add(currentState);

            if (currentState.isSolvedState()) {
                buildSolution(currentState);
                break;
            }

            ArrayList<PuzzleState> nexts = currentState.getNextStates();
            for (PuzzleState ps: nexts) {
                nextStates.add(ps);
            }
        }

        solved = true;
    }

    private void buildSolution(PuzzleState finalState) {
        solution = new ArrayList<>();

        PuzzleState transState = finalState;
        solution.add(transState);

        while (transState.getPrevState() != null) {
            solution.add(transState.getPrevState());
            transState = transState.getPrevState();
        }

        Collections.reverse(solution);
    }
}
