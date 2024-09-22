public class BST extends BinaryTree{

    BST(){
        super.root = null;
    }
    
    void insert(int newData){
        Node newNode = new Node(newData);
        if(root == null) root = newNode;
        if(newData > root.getData()){
            if(!root.hasRightChild()){
                root.setRight(newNode);
                newNode.setParent(root);
                return;
            } else {
                insertAux(root.getRight(), newNode);
            }
        }
        else if(newData < root.getData()){
            if(!root.hasLeftChild()){
                root.setLeft(newNode);
                newNode.setParent(root);
                return;
            } else {
                insertAux(root.getLeft(), newNode);
            }
        }
    }
    
    private void insertAux(Node currentNode, Node newNode){
        int newData = newNode.getData();
        if(newData > currentNode.getData()){
            if(!currentNode.hasRightChild()){
                currentNode.setRight(newNode);
                newNode.setParent(currentNode);
                return;
            } else {
                insertAux(currentNode.getRight(), newNode);
            }
        }
        else if(newData < currentNode.getData()){
            if(!currentNode.hasLeftChild()){
                currentNode.setLeft(newNode);
                newNode.setParent(currentNode);
                return;
            } else {
                insertAux(currentNode.getLeft(), newNode);
            }
        }
    }

    Node findMax(){
        if(isEmpty()) return null;
        else return findMaxAux(root.getRight());
    }

    private Node findMaxAux(Node currentNode){
        if(currentNode.getRight() == null) return currentNode;
        else return findMaxAux(currentNode.getRight());
    }


    Node findMin(){
        if(isEmpty()) return null;
        else return findMinAux(root.getLeft());
    }
    
    private Node findMinAux(Node currentNode){
        if(currentNode.getLeft() == null) return currentNode;
        else return findMinAux(currentNode.getLeft());
    }

    Node search(int dataToSearch){
        if(root == null) return null;
        if(root.getData() == dataToSearch) return root;
        if(root.hasRightChild() && dataToSearch > root.getData())
            return searchAux(root.getRight(),dataToSearch);
        if(root.hasLeftChild() && dataToSearch < root.getData())
            return searchAux(root.getLeft(),dataToSearch);
        return null;
    }

    private Node searchAux(Node currentNode, int dataToSearch){
        if(currentNode.getData() == dataToSearch) return currentNode;
        if(currentNode.hasRightChild() && dataToSearch > currentNode.getData())
            return searchAux(currentNode.getRight(), dataToSearch);
        if(currentNode.hasLeftChild() && dataToSearch < currentNode.getData())
            return searchAux(currentNode.getLeft(), dataToSearch);
        return null;
    }

    Node findAntecessor(int data){
        if(isEmpty()) return null;
        Node inicio = search(data);
        if(inicio == null) return null;
        if(!inicio.hasLeftChild()) return findAntecessorUp(inicio);
        else return findMaxAux(inicio.getLeft());
    }

    private Node findAntecessorUp(Node currentNode){
        Node parentNode = currentNode.getParent();
        if(parentNode.getRight() == currentNode) return parentNode;
        else{
            if(parentNode.hasParent()) return findAntecessorUp(parentNode);
            else return null; // No caso de não haver antecessor at all.
        }
    }

    Node findSuccessor(int data){
        if(isEmpty()) return null;
        Node inicio = search(data);
        if(inicio == null) return null;
        if(!inicio.hasRightChild()) return findSuccessorUp(inicio);
        else return findMinAux(inicio.getRight());
    }

    private Node findSuccessorUp(Node noAtual){
        Node currentParent = noAtual.getParent();
        if(currentParent.getLeft() == noAtual) return currentParent;
        else{
            if(parentNode.hasParent()) return findAntecessorUp(parentNode);
            else return null;
        }
    }

    void remove(int data){
        if(isEmpty()) return;

        Node inicio = search(data);
        if (inicio == null) return;
        if(inicio.isRoot()) {
            removeRoot();
            return;
        }

        // Caso um: sem filhos (folha)
        if(inicio.isLeaf()){
            Node anterior = inicio.getParent();
            if(anterior.getRight() == inicio){
                anterior.setRight(null);
            } else if (anterior.getLeft() == inicio){
                anterior.setLeft(null);
            }
            inicio.setParent(null);
            return;
        }

        // Caso dois: um filho a esquerda
        if(inicio.hasLeftChild() && !inicio.hasRightChild()){
            Node proximo = inicio.getLeft();
            Node anterior = inicio.getParent();
            if(anterior.getRight() == inicio){
                anterior.setRight(proximo);
            } else if (anterior.getLeft() == inicio){
                anterior.setLeft(proximo);
            }
            proximo.setParent(anterior);
            inicio.setParent(null);
            inicio.setLeft(null);
            return;
        }

        // Caso tres: um filho a direita
        if(inicio.hasRightChild() && !inicio.hasLeftChild()){
            Node proximo = inicio.getRight();
            Node anterior = inicio.getParent();
            if(anterior.getRight() == inicio){
                anterior.setRight(proximo);
            } else if (anterior.getLeft() == inicio){
                anterior.setLeft(proximo);
            }
            proximo.setParent(anterior);
            inicio.setParent(null);
            inicio.setRight(null);
            return;
        }

        // Caso quatro: dois filhos
        if(inicio.getDegree() == 2){
            Node successor = findSuccessor(inicio.getData());
            int successorData = successor.getData();
            remove(successorData);  // Remove the successor node, not the original node
            inicio.setData(successorData);  // Update the current node with the successor's data
            return;
        }
    }

    private void removeRoot(){
        if(!root.hasLeftChild() && !root.hasRightChild()){
            root = null;
            return;
        }
        if(root.hasLeftChild() && !root.hasRightChild()){
            Node aux = root.getLeft();
            root.setLeft(null);
            root = aux;
            return;
        }
        if(!root.hasLeftChild() && root.hasRightChild()){
            Node aux = root.getRight();
            root.setRight(null);
            root = aux;
            return;
        }
        if(root.hasLeftChild() && root.hasRightChild()){
            Node successor = findSuccessor(root.getData());
            int successorData = successor.getData();
            remove(successorData);
            root.setData(successorData);
            return;
        }
    }
}