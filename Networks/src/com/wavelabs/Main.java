package com.wavelabs;

public class Main {

	public static void main(String[] args) {
		        int[][] times = {{2,1,1},{2,3,1},{3,4,1}};
		        int n = 4;
		        int k = 2;

		        Logic solution = new Logic();
		        int minTime = solution.networkDelayTime(times, n, k);

		        System.out.println("Minimum time for all nodes to receive signal: " + minTime);

	}

}
