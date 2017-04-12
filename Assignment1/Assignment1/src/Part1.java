import java.util.Random;

public class Part1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//single linked list of 10 random numbers
		Node head=null;
		head=createSingleLL(head);
		//traverse the list and show the node values
		System.out.println("Single Linked List\n");
		//display the elements before sorting
		traverseSingleLL(head);
		head=selectionSort(head);
		System.out.println("\n\nSorted Linked List\n");
		//display the elements after sorting
		traverseSingleLL(head);
	}

	private static Node selectionSort(Node head) {
		// TODO Auto-generated method stub
		
		Node sorted=null;
		//sorted points to the node that was last sorted to its correct position
		Node prev_p1,min,min_prev;
		//prev_p1: pointer to the node before current node
		//min_prev: pointer to the node before min node
		//min: the minimum node in every iteration
		for(Node p1=head;p1!=null;p1=sorted.next){
			
			min=null;
			prev_p1=null;
			min_prev=null;
			
			for(Node p2=p1;p2!=null;p2=p2.next){
				//if a new minimum is found, assign it to min
				if(min==null || p2.val<=min.val){
					min=p2;
					min_prev=prev_p1;
				}
				prev_p1=p2;
			}
					
			//if p1(current node) and min are not the same, swap the two nodes
			if(p1!=min){
				min_prev.next=min.next;
				
				min.next=p1;
				if(sorted!=null)
					sorted.next=min;
				else
					head=min;//head will be fixed after the first swapping is done and the position 
					//of the least element(stored in min) is fixed
			}
			sorted=min;
			
		}
		return head;
		
	}

	private static void traverseSingleLL(Node head) {
		// TODO Auto-generated method stub
		//traverse through the linked list and display the elements
		Node temp=head;
		if(temp==null)
			System.out.println("The list is empty!");
		else{
			while(temp!=null){
				System.out.print(temp.val+ " ");
				temp=temp.next;
			}
		}
		
		
	}

	private static Node createSingleLL(Node head) {
		// TODO Auto-generated method stub
		Random r=new Random();
		int key=0;
		//randomly generate 10 numbers between 1 and 50 and create a linked list 
		if(head==null){//first element
			key=r.nextInt(50)+1;
			//head of the linked list
			head=new Node(key);
		}
		for(int i=1;i<=9;i++){//remaining 9 elements
			key=r.nextInt(50)+1;
			Node temp=head;
			while(temp.next!=null)
				temp=temp.next;
			
			Node n=new Node(key);
			temp.next=n;
		}
		return head;
	}

}
