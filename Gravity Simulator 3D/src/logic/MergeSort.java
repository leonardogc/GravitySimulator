package logic;

import java.util.Vector;

public class MergeSort {
	private Particle[] numbers;
    private Particle[] helper;
    private double angle;

    private int number;

    public void sort(Vector<Particle> values, double angle) {
    	this.angle = angle;
    	number = values.size();

    	this.numbers = new Particle[number];

    	for(int i = 0; i< values.size();i++) {
    		numbers[i]=values.get(i);
    	}

    	this.helper = new Particle[number];
    	mergesort(0, number - 1);
    	
    	values.clear();
    	
    	for(int i = 0; i< numbers.length;i++) {
    		values.add(numbers[i]);
    	}
    }

    private void mergesort(int low, int high) {
        // check if low is smaller than high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergesort(low, middle);
            // Sort the right side of the array
            mergesort(middle + 1, high);
            // Combine them both
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        double z1;
        double z2;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
        	z1=helper[i].pos[0]*Math.sin(angle)-helper[i].pos[1]*Math.cos(angle);
        	z2=helper[j].pos[0]*Math.sin(angle)-helper[j].pos[1]*Math.cos(angle);
        	
        	/*z1=helper[i].pos[2];
        	z2=helper[j].pos[2];*/
        	
            if (z1 <= z2) {
                numbers[k] = helper[i];
                i++;
            } else {
                numbers[k] = helper[j];
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }
        // Since we are sorting in-place any leftover elements from the right side
        // are already at the right position.

    }
}
