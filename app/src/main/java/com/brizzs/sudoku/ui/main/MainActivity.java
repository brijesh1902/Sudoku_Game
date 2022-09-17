package com.brizzs.sudoku.ui.main;

import static com.brizzs.sudoku.utils.Const.CLEAR;
import static com.brizzs.sudoku.utils.Const.SOLVE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.brizzs.sudoku.R;
import com.brizzs.sudoku.databinding.ActivityMainBinding;
import com.brizzs.sudoku.libs.Solver;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Solver gameSolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        gameSolver = binding.sudokuBoard.getSolver();

        binding.btnSolve.setOnClickListener(v -> {
            CLICKANIMATION(v);
            if (binding.btnSolve.getText().toString().equals(SOLVE)){
                binding.btnSolve.setText(CLEAR);
                gameSolver.getEmptyBoxIndexes();
                SolveThread solveThread = new SolveThread();
                new Thread(solveThread).start();
            } else {
                binding.btnSolve.setText(SOLVE);
                gameSolver.resetBoard();
            }
            binding.sudokuBoard.invalidate();
        });

        binding.one.setOnClickListener(v -> {
            CLICKANIMATION(v);
            gameSolver.setNumberPosition(1);
            binding.sudokuBoard.invalidate();
        });

        binding.two.setOnClickListener(v -> {
            CLICKANIMATION(v);
            gameSolver.setNumberPosition(2);
            binding.sudokuBoard.invalidate();
        });

        binding.three.setOnClickListener(v -> {
            CLICKANIMATION(v);
            gameSolver.setNumberPosition(3);
            binding.sudokuBoard.invalidate();
        });

        binding.four.setOnClickListener(v -> {
            CLICKANIMATION(v);
            gameSolver.setNumberPosition(4);
            binding.sudokuBoard.invalidate();
        });

        binding.five.setOnClickListener(v -> {
            CLICKANIMATION(v);
            gameSolver.setNumberPosition(5);
            binding.sudokuBoard.invalidate();
        });

        binding.six.setOnClickListener(v -> {
            CLICKANIMATION(v);
            gameSolver.setNumberPosition(6);
            binding.sudokuBoard.invalidate();
        });

        binding.seven.setOnClickListener(v -> {
            CLICKANIMATION(v);
            gameSolver.setNumberPosition(7);
            binding.sudokuBoard.invalidate();
        });

        binding.eight.setOnClickListener(v -> {
            CLICKANIMATION(v);
            gameSolver.setNumberPosition(8);
            binding.sudokuBoard.invalidate();
        });

        binding.nine.setOnClickListener(v -> {
            CLICKANIMATION(v);
            gameSolver.setNumberPosition(9);
            binding.sudokuBoard.invalidate();
        });

    }

    class SolveThread implements Runnable{
        @Override
        public void run() {
            gameSolver.solve(binding.sudokuBoard);
        }
    }

    private void CLICKANIMATION(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.btn_click));
    }
}