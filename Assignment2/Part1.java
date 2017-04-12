import java.util.Random;
import java.util.Scanner;

public class Part1{


	private static void inOrder(Node root) {
	    if(root==null)
	        return;
	    else{
	        inOrder(root.left);
	        System.out.print(root.data +" ");
	        inOrder(root.right);
	    }
	}
	
	private static Node Insert(Node root,int value)
    {
        if(root==null){
            Node temp=new Node();
            temp.data=value;
            temp.right=temp.left=null;
            root=temp;
        }
        else if(value<=root.data){//value to be inserted is less than root value; insert it to the left subtree
        		if(root.left==null){
        			Node t=new Node();
        			t.data=value;
        			t.right=t.left=null;
        			root.left=t;
        		}
        		else
        			Insert(root.left,value);
        }
        else{//value to be inserted is greater than root value
        	if(root.right==null){
        		Node t=new Node();
    			t.data=value;
    			t.right=t.left=null;
    			root.right=t;
        	}
        	else
        		Insert(root.right,value);
        }
            
        return root;
     }
	
	
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
    	
    	Node root = null;
    	int value=0;
    	//random 15 values 
    	root=Insert(root, 39);
    	root=Insert(root, 49);
    	root=Insert(root, 2);
    	root=Insert(root, 41);
    	root=Insert(root, 34);
    	root=Insert(root, 46);
    	root=Insert(root, 14);
    	root=Insert(root, 48);
    	root=Insert(root, 22);
    	root=Insert(root, 20);
    	root=Insert(root, 47);
    	root=Insert(root, 1);
    	root=Insert(root, 7);
    	root=Insert(root, 50);
    	root=Insert(root, 13);
    	
		System.out.println("The tree before deletion:");
        inOrder(root);
        
        
        root=delete(root, 1);
        System.out.println("\n\nThe tree after deletion of 1, a leaf node:");
        inOrder(root);
        
        root=delete(root, 14);
        System.out.println("\n\nThe tree after deletion of 14, a non-leaf node replaced by a PREDECESSOR which is a leaf node:");
        inOrder(root);
        
        root=delete(root, 39);
        System.out.println("\n\nThe tree after deletion of 39, a non-leaf node replaced by a PREDECESSOR which is also non-leaf node:");
        inOrder(root);
        
        
        
    }

	private static Node delete(Node root, int key) {
		// TODO Auto-generated method stub
		if(root==null){
			System.out.println("\nThe element doesn't exist");
			return root;
		}
		if(root.data==key){
			//predecessor/successor
			Node pred=null,succ=null;
			if(root.left==null && root.right==null){
				//only one node in the tree
				root=null;
				return root;
			}
			else if(root.left==null){//find successor if root doesn't have predecessor(left child is null)
				succ=findSucc(root.right);
				if(succ!=null){
					
					findParentSucc(root, succ);
					root.data=succ.data;
					
				}
			}
			else if(root.right==null){//find predecessor if root doesn't have successor(right child is null)
				pred=findPredL(root.left);
				if(pred!=null){
					//findParentPred traverses upto the parent of the predecessor to delete it
					//and link to its children
					findParentPred(root,pred);
					root.data=pred.data;
					
				}
			}
			else{
				pred=findPredL(root.left);
				if(pred!=null){
					
					findParentPred(root,pred);
					root.data=pred.data;
					
				}
			}
				
		}
		else if(root.left!=null && root.left.data==key){
			//find the highest element in the left sub tree/in order predecessor
			Node pred=null,succ=null;
			if(root.left.left==null && root.left.right==null){
				//leaf node
				root.left=null;
			}
			else if(root.left.left==null){//no predecessor,find the successor
				succ=findSucc(root.left.right);
				if(succ!=null){
					
					findParentSucc(root.left, succ);
					root.left.data=succ.data;
					
				}
			}
			else if(root.left.right==null){
				pred=findPredL(root.left.left);
				if(pred!=null){
					
					findParentPred(root.left,pred);
					root.left.data=pred.data;
					
				}
			}
			
			else{
				pred=findPredL(root.left.left);
				if(pred!=null){
					findParentPred(root.left,pred);
					root.left.data=pred.data;
				}
			}
			
		}
		else if(root.right!=null && root.right.data==key){
			//find the highest element in the left sub tree/in order predecessor
			Node pred=null,succ=null;
			if(root.right.left==null && root.right.right==null){
				//leaf node
				root.right=null;
			}
			else if(root.right.left==null){
				succ=findSucc(root.right.right);
				if(succ!=null){
					
					findParentSucc(root.right, succ);
					root.right.data=succ.data;
					
				}
			}
			else if(root.right.right==null){
				pred=findPredL(root.right.left);
				if(pred!=null){
					
					findParentPred(root.right,pred);
					root.right.data=pred.data;
					
				}
			}
			
			else{//both right and left children are not null
				pred=findPredL(root.right.left);
				if(pred!=null){
					
					findParentPred(root.right,pred);
					root.right.data=pred.data;
					
				}
			}
		}
		
		else{//if the element to be deleted is not the root/root's left child/root's right child
			if(key<root.data)
				delete(root.left,key);
			else
				delete(root.right,key);
		}
		return root;
		
	}

	private static void findParentPred(Node root, Node pred) {
		// TODO Auto-generated method stub
		//finds the parent node of the predecessor and detaches(delete) the predecessor node,
		//linking the parent to the children of the deleted node
		if(root==null)
			return;
		if(root.left!=null && root.left.data==pred.data)
			root.left=pred.left;//attach the children of predecessor to the root and delete it
		else if(root.right!=null && root.right.data==pred.data)
			root.right=pred.left;
		else{
			if(pred.data<root.data)
				findParentPred(root.left,pred);
			else
				findParentPred(root.right,pred);
		}
		
	}

	private static void findParentSucc(Node root, Node succ) {
		// TODO Auto-generated method stub
		//finds the parent node of the successor and detaches(delete) the successor node,
		//linking the parent to the children of the deleted node
		if(root==null)
			return;
		if(root.left!=null && root.left.data==succ.data)
			root.left=succ.right;//delete the node
		else if(root.right!=null && root.right.data==succ.data)
			root.right=succ.right;
		else{
			if(succ.data<root.data)
				findParentSucc(root.left,succ);
			else
				findParentSucc(root.right,succ);
		}
		
	}

	private static Node findSucc(Node root) {
		// TODO Auto-generated method stub
		//find successor
		if(root==null)
			return null;
		else if(root.left==null && root.right==null)
			return root;
		else if(root.left==null)
			return root;
		else if(root.right==null)
			root=findSucc(root.left);
		else
			root=findSucc(root.left);
		
		return root;
		
	}

	private static Node findPredL(Node root) {
		// TODO Auto-generated method stub
		//find the highest element in the left subtree of root(predecessor)
		if(root==null)
			return null;
		else if(root.left==null && root.right==null)
			return root;
		else if(root.left==null)
			root=findPredL(root.right);
		else if(root.right==null)
			return root;//root=findPred(root.left);
		else
			root=findPredL(root.right);
		
		return root;
		
	
	}	
	
}