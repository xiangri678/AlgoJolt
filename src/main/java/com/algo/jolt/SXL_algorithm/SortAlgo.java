package com.algo.jolt.SXL_algorithm;

import java.util.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SortAlgo {
    public static void main(String[] args) {
        int[] array = { 64, 31, 1, 6, 19 };
        quickSort(array, 0, array.length - 1);
        log.info("sorted: ", Arrays.toString(array));
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx=j;
                }
            }
            swap(arr, minIdx, i);
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            int target = arr[i], j = i - 1;
            while (j >= 0 && target < arr[j]) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = target;
        }
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l>=r) {
            return;
        }
        int mid = (l+r)/2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid+1, r);
        merge(arr, l, mid, r);
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int[] tmp = new int[r - l + 1];
        int i = l, j = m + 1, k = 0;
        while (i <= m && j <= r) {
            tmp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= m) {
            tmp[k++] = arr[i++];
        }
        while (j <= r) {
            tmp[k++] = arr[j++];
        }
        System.arraycopy(tmp, 0, arr, l, tmp.length);
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = partition(arr, low, high);
        quickSort(arr, low, pivot - 1);
        quickSort(arr, pivot + 1, high);
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low, j = high;
        while (i < j) {
            while (i<j && arr[j] >= pivot) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }
            while (i<j && arr[i] <= pivot) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        arr[i] = pivot;
        return i;
    }

    public static void qs2(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = arr[low];
        int l = low, r = high;
        while (l<r) {
            while (l < r && arr[r] >= pivot) {
                r--;
            }
            arr[l]=arr[r];
            while (l<r&&arr[l]<=pivot) {
                l++;
            }
            arr[r]=arr[l];
        }
        arr[l]=pivot;
        qs2(arr, low, l-1);
        qs2(arr, l+1, high);
    }

    public static void shellSort(int[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int tmp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > tmp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = tmp;
            }
        }
    }
    
    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        for (int i = n-1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }
    
    public static void heapify(int[] arr, int heapSize, int currentRoot) {
        int largest = currentRoot, left = 2 * currentRoot + 1, right = 2 * currentRoot + 2;
        if (left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != currentRoot) {
            swap(arr, currentRoot, largest);
            heapify(arr, heapSize, largest);
        }
    }    

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
