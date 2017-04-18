//LSD Radix sort of 20 random numbers with the largest number having 6 digits

import java.util.ArrayList;
import java.util.Iterator;


public class Bucket {
	private ArrayList<Integer> bucketlist;
	Bucket(){
		bucketlist=new ArrayList<Integer>();
		//System.out.println("Constructor");
	}
	
	
	public static int findMaxNumDigits(int[] numbers){
		int max=numbers[0];
		
		for(int i=1;i<numbers.length;i++){
			if(numbers[i]>=max)
				max=numbers[i];
		}
		return (int) (Math.log10(max)+1);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bucket[] rs=new Bucket[10];
		int[] numbers=new int[]{23,4,900,123456,7,189};
		System.out.println("Before Sorting");
		display(numbers);
		int maxDigits=findMaxNumDigits(numbers);
		//System.out.println(maxDigits);
		radixSort(rs,numbers,0,6,maxDigits);
	}


	private static void display(int[] numbers) {
		// TODO Auto-generated method stub
		for(int i=0;i<numbers.length;i++)
			System.out.print(numbers[i]+" ");
		
		System.out.println();
	}


	private static void radixSort(Bucket[] rs, int[] numbers, int first, int last, int maxDigits) {
		// TODO Auto-generated method stub
		for(int i=0;i<maxDigits;i++){
			//clear all buckets
			for(int b=0;b<10;b++){
				rs[b]=new Bucket();
				//System.out.println(b+" "+rs[b].bucketlist);
			}
			for(int j=first;j<last;j++){
				int digit=getDigit(numbers[j],i);
				rs[digit].bucketlist.add(numbers[j]);
				//System.out.println(digit+" "+rs[digit].bucketlist);
			}
			numbers=copyBucToArray(rs,numbers);
			System.out.print("Pass "+(i+1)+" : ");
			display(numbers);
		}
		//numbers=copyBucToArray(rs,numbers);
		System.out.println("After Radix Sorting");
		display(numbers);
	}


	private static int[] copyBucToArray(Bucket[] rs, int[] numbers) {
		// TODO Auto-generated method stub
		int i=0;
		for(int b=0;b<10;b++){
			Iterator<Integer> itr=rs[b].bucketlist.iterator();
			while(itr.hasNext()){
				int n=itr.next();
				numbers[i++]=n;
			}
		}
		return numbers;
	}


	private static int getDigit(int num, int placeVal) {
		// TODO Auto-generated method stub
		int cnt=-1;
		int digit=0;
		while(num!=0){
			digit=num%10;
			cnt+=1;
			if(cnt==placeVal){
				return digit;
			}
			else{
				num=num/10;
			}
		}
		if(digit!=0)
			return 0;
		else
			return digit;
		//return (digit!=0)?0:digit;
	}
	

}
