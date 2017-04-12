//HEAP SORT

import java.util.Random;

public class HeapSort {
	private void heapify(int[] arr,int i){
		
		int heap_size=arr[0];//arr[0] stores the size of heap(number of elements in the heap)
		int largest=i;
		if(2*i<=heap_size && arr[2*i]>=arr[i])//if the left child is greater, assign it as the largest
			largest=2*i;
		
		if((2*i+1)<=heap_size && arr[(2*i)+1]>=arr[largest])//if the right child is greater than the element at largest, assign right child as largest
			largest=2*i+1;
		
		if(largest!=i){//swap the parent element with the largest if indices are different
			swap(arr,i,largest);
			heapify(arr,largest);//recursively call heapify on the swapped element
		}
		
	}

	private void swap(int[] arr, int index1, int index2) {
		// TODO Auto-generated method stub
		int temp=arr[index1];
		arr[index1]=arr[index2];
		arr[index2]=temp;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HeapSort hs=new HeapSort();
		int[] arr=new int[16];
		Random r=new Random();	
		int element=0;
		System.out.println("Array of 15 random numbers before construction of heap(heapify):");
		arr[0]=arr.length-1;//heap size
		for(int i=1;i<arr.length;i++){
			element=r.nextInt(50)+1;
			arr[i]=element;
			System.out.print(arr[i]+ " ");
		}
		//build max heap				
		int heap_size=arr.length-1;
		for(int i=(heap_size)/2;i>=1;i--)
			hs.heapify(arr,i);
		
		System.out.println("\n\nAfter construction of Max heap(heapify) and before Heap Sort: ");
		hs.display(arr);
		//heapsort
		hs.heapSort(arr);
		System.out.println("\nAfter Heap Sort(sorted in non-decreasing order): ");
		
		hs.display(arr);
	}

	private void heapSort(int[] arr) {
		// TODO Auto-generated method stub
		int heap_size=arr[0];
		for(int i=heap_size;i>=2;i--){
			swap(arr, 1,i);//swap the root element of the heap and the last element 
			arr[0]-=1;//reduce the heap size by 1
			heapify(arr,1);//heapify to maintain heap-order property
		}
		
	}

	private void display(int[] arr) {
		// TODO Auto-generated method stub
		for(int i=1;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println("\n");
	}

}
