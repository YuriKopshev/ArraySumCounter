package ru.netology;

import java.util.concurrent.RecursiveTask;

public class Task extends RecursiveTask<Integer> {
    int[] array;
    int low;
    int high;

    public Task(int[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected Integer compute() {

        final int diff = high - low;
        switch (diff) {
            case 0:
                return 0;
            case 1:
                return array[low];
            case 2:
                return array[low] + array[low + 1];
            default:
                return forkTasksAndGetResult();
        }
    }

    private int forkTasksAndGetResult() {
        int mid = low + (high - low) / 2;
        Task left = new Task(array, low, mid);
        Task right = new Task(array, mid, high);
        left.fork();
        right.fork();
        int rightResult = right.join();
        int leftResult = left.join();
        return leftResult + rightResult;
    }
}