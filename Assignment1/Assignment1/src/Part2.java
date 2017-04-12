import java.util.ArrayList;

public class Part2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] numbers15=new int[15];
		int num=21;
		//15 numbers
		System.out.println("15 numbers");
		for(int i=0;i<numbers15.length;i++){
			numbers15[i]=num;
			num++;
		}
		display(numbers15);
		int low=0;
		int high=numbers15.length;
		//Number in the list
		System.out.println("The number to be searched is 35");
		int key=35;
		int pos=recBinarySearch(numbers15,low,high,key);
		if(pos>=0 && pos<=14)
			System.out.println("35 was found at position "+pos);
		else
			System.out.println("Key not found");
		//Number not in the list
		System.out.println("The number to be searched is 3");
		key=3;
		pos=recBinarySearch(numbers15,low,high,key);
		if(pos>=0 && pos<=14)
			System.out.println("3 was found at position "+pos);
		else
			System.out.println("3 was not found");
		
		//16 numbers
		int[] numbers16=new int[16];
		num=21;
		
		System.out.println("\n\n16 numbers");
		for(int i=0;i<numbers16.length;i++){
			numbers16[i]=num;
			num++;
		}
		display(numbers16);
		low=0;
		high=numbers16.length;
		//Number in the list
		System.out.println("The number to be searched is 36");
		key=36;
		pos=recBinarySearch(numbers16,low,high,key);
		if(pos>=0 && pos<=15)
			System.out.println("36 was found at position "+pos);
		else
			System.out.println("Key not found");
		//Number not in the list
		System.out.println("The number to be searched is 10");
		key=10;
		pos=recBinarySearch(numbers16,low,high,key);
		if(pos>=0 && pos<=14)
			System.out.println("10 was found at position "+pos);
		else
			System.out.println("10 was not found");
	}

	private static int recBinarySearch(int[] numbers, int low, int high, int key) {
		// TODO Auto-generated method stub
		
		if(low>high)
			return -1;
		int middle=low+(high-low)/2;
		if(key<numbers[middle])
			return recBinarySearch(numbers, low, middle-1, key);
		else if(key>numbers[middle])
			return recBinarySearch(numbers, middle+1, high, key);
		else
			return middle;
		
	}

	private static void display(int[] numbers) {
		// TODO Auto-generated method stub
		for(int i=0;i<numbers.length;i++){
			System.out.print(numbers[i] + " ");
		}
		System.out.println("\n");
	}

	

}
