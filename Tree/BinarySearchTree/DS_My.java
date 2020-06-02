
public class DS_My implements DataStructureADT {

    // define an inner class 
    // for storing key and value as a pair
    // such a class and its members should be "private"
	// User Binary Search Tree to recode paired Key and Value
	public static class BSTnode<Comparable, Object>
	{
		private Comparable key;
		private Object value;
		private BSTnode parent;
		private BSTnode left;
		private BSTnode right;
		 
		public BSTnode(Comparable key, Object value)
		{
			this.key = key;
			this.value = value;
		}
	}
	
    // Private Fields of the class
    // create field(s) here to store data pairs
    private BSTnode root;
    private int size;
    
    public DS_My() {
        // TODO Auto-generated method stub
        this.root = null;  
        this.size = 0;
    }

    // Add the key,value pair to the data structure and increases size.
    // If key is null, throws IllegalArgumentException("null key");
    // If key is already in data structure, throws RuntimeException("duplicate key");
    // can accept and insert null values
    public void insert(Comparable k, Object v) {
    	if (root == null)
            root = new BSTnode(k, v);
        else
        {
        	BSTnode node = root;
        	BSTnode parent = null;
            int result = 0;
            while (node != null)
            {
                result = k.compareTo(node.key);
                parent = node;
                if (result > 0)
                    node = node.right;
                else if (result < 0)
                    node = node.left;
                else
                	throw new RuntimeException("duplicate key");
            }
            BSTnode child = new BSTnode(k, v);
            child.parent = parent;
            if (result > 0)
                parent.right = child;
            else
                parent.left = child;
        }
        size++;
    }

    // If key is found, Removes the key from the data structure and decreases size
    // If key is null, throws IllegalArgumentException("null key") without decreasing size
    // If key is not found, returns false.
    public boolean remove(Comparable k) {
    	BSTnode node = lookup(k);
        if (node == null)
            return false;
        if (node.left == null)
        {
            if (node.right == null)
                replace(node, null);
            else
                replace(node, node.right);
        }
        else if (node.right == null)
        {
            replace(node, node.left);
        }
        else
        {
        	BSTnode tmpNode = node.right;
            while (tmpNode.left != null)
            {
                tmpNode = tmpNode.left;
            }
            
            replace(tmpNode, tmpNode.right);
            tmpNode.left = node.left;
            if (node.left != null)
                node.left.parent = tmpNode;
            
            tmpNode.right = node.right;
            if (node.right != null)
                node.right.parent = tmpNode;
            replace(node, tmpNode);
        }
        size--;
        return true;
    }

    // Returns true if the key is in the data structure
    // Returns false if key is null or not present
    public boolean contains(Comparable k) {
    	if(lookup(k) == null )
    		return false;
    	else 
    		return true;
    }

    // Returns the value associated with the specified key
    // get - does not remove key or decrease size
    // If key is null, throws IllegalArgumentException("null key") 
    public Object get(Comparable k) {
    	BSTnode node = lookup(k);
    	if(node == null)
    		return null;
    	else
    		return node.value;
    }

    // Returns the number of elements in the data structure
    public int size() {
    	return this.size;
    }
    
    private BSTnode lookup(Comparable k)
    {
    	BSTnode node = root;
    	while (node != null)
    	{
    		int result = k.compareTo(node.key);
    		if (result > 0)
    			node = node.right;
    		else if (result < 0)
    			node = node.left;
    		else
    			break;
    	}
    	return node;
    }
    
    private void replace(BSTnode oldNode, BSTnode newNode)
    {
    	BSTnode parent = oldNode.parent;
        if (parent != null)
        {
            if (oldNode == parent.left)
                parent.left = newNode;
            else
                parent.right = newNode;
            oldNode.parent = null;
        }
        else
            root = newNode;
 
        if (newNode != null)
            newNode.parent = parent;
 
        oldNode.left = null;
        oldNode.right = null;
    }
}
