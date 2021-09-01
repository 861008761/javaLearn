package com.mylovin.algo;

import java.util.Random;

public class LLK {
    // 12x12的表格
    private static int[][] board = new int[][]
            {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
            };

    private void setFlag(int col, int flag) {
        int row = new Random().nextInt(12);
        if (board[row][col] != 0) {
            setFlag(col, flag);
        } else if (board[row][col] == 0) {
            board[row][col] = flag;
            return;
        }
    }

    public void init() { // 初始化为左侧五列 右侧五列的连连看
        int flag = 1;
        for (int col = 0; col < 5; col++) {
            for (int row = 0; row < 6; row++) {
                setFlag(col, flag);
                setFlag(col, flag);
                flag++;
            }
            flag = 1;
        }

        for (int col = 7; col < 12; col++) {
            for (int row = 0; row < 6; row++) {
                setFlag(col, flag);
                setFlag(col, flag);
                flag++;
            }
            flag = 1;
        }
    }

    public void print() {
        for (int row = 0; row < 12; row++) {
            for (int col = 0; col < 12; col++) {
                System.out.print(board[row][col] + ", ");
            }
            System.out.println();
        }
    }

    public void dfs() {

    }

    public static void main(String[] args) {
        LLK llk = new LLK();
        llk.init();
        llk.print();

        llk.dfs();
    }
}
