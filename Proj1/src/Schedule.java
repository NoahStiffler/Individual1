import java.util.*;

public class Schedule {
	
	// Storing weighted tasks in a class as objects. Could not get it to work using my 2D array idea.
	static class Wtask {
		int start, finish, profit;
		Wtask(int start, int finish, int profit) {
			this.start = start;
			this.finish = finish;
			this.profit = profit;
		}
	}
	
	// This method will find the next task that does not have a time conflict with the current tasks
	static int nextTask(Wtask arr[], int i) {
		
		for (int j = i-1; j>=0; j--) {
			if (arr[j].finish <= arr[i-1].start){
				return j;
			}

		}
		return -1;
	}
	
	// sorted tasks in
	static int maxProfit(Wtask arr[], int n) {
		
		int[] profits = new int[n];
		// earliest finishing is stored first
		profits[0] = arr[0].profit;
		
		// for every task (except for the first one) we compare the profit (weight) of the task with the profit of the next task which does not have a time conflict.
		for (int i = 1; i < n; i++ ) {
			int curr = arr[i].profit;
			int next = nextTask(arr, i);
			
			// once the next possible task is found, add the profit it to the total profits
			if (next != -1) {
				curr += profits[next];
			}
			profits[i] = Math.max(curr, profits[i-1]);
		}
		int result = profits[n-1];
		return result;
	}
	
	// default test n = 4
	static int findMaxProfit(Wtask arr[], int n) {
		
		// Sort tasks from earliest finishing to last finishing
		Arrays.sort(arr, new Comparator<Wtask>() {
			public int compare(Wtask t1, Wtask t2) {
				return t1.finish - t2.finish;
			}
		} );
		//send sorted tasks to method which determines the maximum profit
		//again, the earliest finishing task will become the first task
		return maxProfit(arr, n);
	}
	
	
	
	static int[][] arrSort(int[][] arr, int col) {
		
		//Sort the array from earliest finishing task to last finishing
			Arrays.sort(arr, new Comparator<int[]>() {
				public int compare(int[] first, int[] second) {
					if(first[col-1] > second[col-1]) {
						return 1;
					}
					else return -1;
				}
			});

			// The task with earliest finish time is automatically scheduled, 
			// from here we find the next tasks which can fit into the schedule and print the task number for those jobs.
			System.out.println("Tasks to schedule: " + arr[0][0]);
			int j = 0;
			for (int i = 1; i < arr.length; i++) {
				if(arr[i][1] >= arr[j][2]) {

					j = i;
					System.out.println(arr[j][0]);
				}
				else {
				//	System.out.println("no");

				}
				
			}

		return arr;
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);

		System.out.println("Weighted (w) or unweighted (u)?");
		char d = in.next().charAt(0);
		
		System.out.println(d);


		// Task #, start position, end position
		int [][] tasks = {{1, 0, 6},
						{2, 1, 4},
						{3, 3, 5},
						{4, 3, 8},
						{5, 4, 7},
						{6, 5, 9},
						{7, 6, 10},
						{8, 8, 11}};
		
		// no more task #, start position, end position, weight
		Wtask arr[] = new Wtask[4];
		arr[0] = new Wtask(3, 10, 20);
		arr[1] = new Wtask(1, 2, 50);
		arr[2] = new Wtask(6, 19, 100);
		arr[3] = new Wtask(2, 100, 200);
		
		
		if (d == 'u') {
	
		System.out.println(Arrays.deepToString(arrSort(tasks, 3)));
		}
		else {
			System.out.println("The maximum profit from the given tasks is: " + findMaxProfit(arr, arr.length));
		}

	}
	

}
