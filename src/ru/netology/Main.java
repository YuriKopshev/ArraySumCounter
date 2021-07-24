package ru.netology;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static int[] arrayGenerate() {
        int[] arr = new int[2000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10);
        }
        return arr;
    }

    public static int arraySum(int[] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            result = result + array[i];
        }
        return result;
    }

    public static void main(String[] args) {
        long time;


        int[] array = Main.arrayGenerate();
        System.out.println(Arrays.toString(array));

        time = System.currentTimeMillis();
        int sum = Main.arraySum(array);
        System.out.println("Сумма элементов массива равна: " + sum);
        System.out.println("Время однопоточного расчета суммы массива составило: " + (time = System.currentTimeMillis() - time));

        // многпоточный расчет суммы чисел в массиве
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        time = System.currentTimeMillis();
        int result = forkJoinPool.invoke(new Task(array, 0, array.length));
        System.out.println("Сумма элементов массива равна: " + result);
        System.out.println("Время многопоточного расчета суммы массива составило: " + (time = System.currentTimeMillis() - time));


    }

}
