public class BinaryTree{
    public Node root; // Nó possui o level máximo. Conte as arestas

    // Class nó
    public class Node{
        private int data;
        private Node parent;
        private Node right;
        private Node left;

        // Construtor do nó
        Node(int newData){
            data = newData;
            parent = null;
            right = null;
            left = null;
        }

        int getData(){return data;}
        Node getRight(){return right;}
        Node getLeft(){return left;}
        Node getParent(){return parent;}

        void setData(int newData){data = newData;}
        void setRight(Node newRight){right = newRight;}
        void setLeft(Node newLeft){left = newLeft;}
        void setParent(Node newParent){parent = newParent;}

        Boolean isRoot(){
            return parent == null;
        }

        Boolean isLeaf(){
            return right == null && left == null;
        }

        int getDegree(){
            int quant = 0;
            if(right != null) quant++;
            if(left != null) quant++;
            return quant;
        }

        int getLevel(){
            if(isRoot()) return 0;
            Node auxNode = parent;
            int cont = 1;
            while(auxNode.parent != null){
                cont++;
                auxNode = auxNode.parent;
            }
            return cont;
        }
        

        int getHeight(){
            if(isLeaf()) return 0;
            int height = 0;
		    if (hasLeftChild()) height = Math.max(height, left.getHeight());
    		if (hasRightChild()) height = Math.max(height, right.getHeight());
    		return height + 1;
        }
        
        boolean hasLeftChild(){return left != null;}
        boolean hasRightChild(){return right != null;}
        boolean hasParent(){return parent != null;}
        
        // PARA AVL

        int fatorBalanceamento(){
            int esquerda = -1, direita = -1;
            if(this.hasLeftChild()) esquerda = this.getLeft().getHeight();
            if(this.hasRightChild()) direita = this.getRight().getHeight();
            return esquerda - direita;
        }
    }

    // Construtor da Binary tree
    BinaryTree(){
        root = null;
    }

    Node getRoot(){return root;}
    
    void setRoot(int newData){
        if(root==null){
            root = new Node(newData);
            return;
        }
        root.setData(newData);
    }

    Boolean isEmpty(){
        return root == null;
    }

    void preOrderTraversal(){
        if (root == null) return;
        System.out.printf("%d ", root.getData());
        if(root.getLeft() != null)
            preOrderTraversalAux(root.getLeft());
        if(root.getRight() != null)
            preOrderTraversalAux(root.getRight());
        System.out.printf("\n");
    }

    private void preOrderTraversalAux(Node current){
        System.out.printf("%d ", current.getData());
        if(current.getLeft() != null)
            preOrderTraversalAux(current.getLeft());
        if(current.getRight() != null)
            preOrderTraversalAux(current.getRight());
    }

    void postOrderTraversal(){
        if (root == null) return;
        if(root.getLeft() != null)
            postOrderTraversalAux(root.getLeft());
        if(root.getRight() != null)
            postOrderTraversalAux(root.getRight());
        System.out.printf("%d\n", root.getData());
    }

    private void postOrderTraversalAux(Node current){
        if(current.getLeft() != null)
            postOrderTraversalAux(current.getLeft());
        if(current.getRight() != null)
            postOrderTraversalAux(current.getRight());
        System.out.printf("%d ", current.getData());
    }

    void inOrderTraversal(){
        if (root == null) return;
        if(root.getLeft() != null)
            inOrderTraversalAux(root.getLeft());
        System.out.printf("%d ", root.getData());
        if(root.getRight() != null)
            inOrderTraversalAux(root.getRight());
        System.out.printf("\n");
    }

    private void inOrderTraversalAux(Node current){
        if(current.getLeft() != null)
            inOrderTraversalAux(current.getLeft());
        System.out.printf("%d ", current.getData());
        if(current.getRight() != null)
            inOrderTraversalAux(current.getRight());
    }

    void levelOrderTraversal() {
        if (root == null) return;
        Queue<Node> auxQueue = new Queue<>(100);
        auxQueue.enqueue(root);
        if (root.getLeft() != null)
            auxQueue.enqueue(root.getLeft());
        if (root.getRight() != null)
            auxQueue.enqueue(root.getRight());
        if (root.getLeft() != null)
            levelOrderTraversalAux(auxQueue, root.getLeft());
        if (root.getRight() != null)
            levelOrderTraversalAux(auxQueue, root.getRight());
        System.out.printf("%d ", auxQueue.dequeue().getData());
    }
    
    private void levelOrderTraversalAux(Queue<Node> auxQueue, Node currentNode) {
        if (currentNode.getLeft() != null)
            auxQueue.enqueue(currentNode.getLeft());
        if (currentNode.getRight() != null)
            auxQueue.enqueue(currentNode.getRight());
        if (currentNode.getLeft() != null)
            levelOrderTraversalAux(auxQueue, currentNode.getLeft());
        if (currentNode.getRight() != null)
            levelOrderTraversalAux(auxQueue, currentNode.getRight());
        System.out.printf("%d ", auxQueue.dequeue().getData());
    }
    
    int getHeight(){
        if(isEmpty()) return -1;
        return root.getHeight();
    }
    
    private int getDegree(){
        if(root.getDegree() == 0) return 0;
        int degree = 0;
        if(root.getLeft() != null) degree++;
        if(root.getRight() != null) degree++;
        return degree;
    }
    
}