import java.util.Random;

public class Part2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numbers=new int[15];
		Random r=new Random();
		int key=0;
		//15 numbers
		System.out.println("Before Sorting:");
		for(int i=0;i<numbers.length;i++){
			key=r.nextInt(50)+1;
			numbers[i]=key;
			System.out.print(numbers[i] +" ");
		}
		System.out.println();
		numbers=bubbleSort(numbers);
		System.out.println("\n\nThe numbers after Bubble Sort:");
		display(numbers);
		
	}
	
	

	private static int[] bubbleSort(int[] numbers) {
		// TODO Auto-generated method stub
		for(int j=0;j<numbers.length;j++){
			for(int i=0;i<numbers.length-1;i++){
				if(numbers[i]>numbers[i+1]){
					int temp=numbers[i];
					numbers[i]=numbers[i+1];
					numbers[i+1]=temp;
				}
			}
			System.out.println("\nPass "+(j+1));
			display(numbers);
		}
			
		return numbers;
	}



	private static void display(int[] numbers) {
		// TODO Auto-generated method stub
		for(int i=0;i<numbers.length;i++)
			System.out.print(numbers[i]+ " ");
	}

}
