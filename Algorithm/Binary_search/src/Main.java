import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] arr = {7, 13, 21, 30, 32, 32, 38, 38, 44, 52, 53};
        Arrays.sort(arr);
        System.out.println(binary_search_basic1(arr, 38));
        System.out.println(binary_search_basic2(arr, 38));
        System.out.println(binary_search_basic3(arr,38));
        System.out.println(binary_search_basic4(arr,38));
    }

    public static int binary_search_basic1(int[] a, int targer) {
        int left = 0, right = a.length - 1;
        int candidate = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] < targer) {
                left = mid + 1;
            } else if (a[mid] > targer) {
                right = mid - 1;
            } else {//当找到匹配值之后，左右寻找还有没有匹配的
                candidate = mid;//记录候选位置
                right = mid - 1;
            }
        }
        return candidate;
    }

    public static int binary_search_basic2(int[] a, int targer) {
        int left = 0, right = a.length - 1;
        int candidate = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (a[mid] < targer) {
                left = mid + 1;
            } else if (a[mid] > targer) {
                right = mid - 1;
            } else {//当找到匹配值之后，左右寻找还有没有匹配的
                candidate = mid;//记录候选位置
                left = mid + 1;
            }
        }
        return candidate;
    }

    public static int binary_search_basic3(int[] a, int targer) {
        int left = 0, right = a.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (targer <= a[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    public static int binary_search_basic4(int[] a, int targer) {
        int left = 0, right = a.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (targer < a[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left-1;
    }

}