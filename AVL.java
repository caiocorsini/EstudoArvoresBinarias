public class AVL extends BST{
    //AVISO!!! OS METODOS ABAIXOS NAO MANIPULAM OS PARENTS EM NADA!!!

    AVL(){
        super.root = null;
    }

    boolean ehAVL(Node noAtual){
        int fatorAtual = noAtual.fatorBalanceamento();
        if(fatorAtual<-1 || fatorAtual>1) return false;
        if(noAtual.getLeft() != null)
            if(!ehAVL(noAtual.getLeft())) return false;
        if(noAtual.getRight() != null)
            if(!ehAVL(noAtual.getRight())) return false;
        return true;
    }

    void insertAVL(int newData){
        root = insertAVLAUX(root, newData); // Tem que fazer isso pois em AVL a raiz da arvore muda constantemente
    }

    private Node insertAVLAUX(Node rootNode, int newData) {
        if (rootNode == null) return new Node(newData); // Se arvore esta vazia insere direto na raiz
    
        if (newData <= rootNode.getData()) {

            rootNode.setLeft(insertAVLAUX(rootNode.getLeft(), newData));
            if (rootNode.fatorBalanceamento() >= 2) {
                if (newData <= rootNode.getLeft().getData()) // Caso left left (externo)
                    rootNode = rightRotate(rootNode);
                else { // Caso left right (interno)
                    rootNode = leftRotate(rootNode);
                    rootNode = rightRotate(rootNode);
                }
            }
        } else {
            rootNode.setRight(insertAVLAUX(rootNode.getRight(), newData));
            if (rootNode.fatorBalanceamento() <= -2) {
                if (newData > rootNode.getRight().getData()) // Caso right right (externo)
                    rootNode = leftRotate(rootNode);
                else { // Caso right left (interno)
                    rootNode = rightRotate(rootNode);
                    rootNode = leftRotate(rootNode);
                }
            }
        }
        return rootNode; // Retorna a nova raiz (caso tenha sido alterada)
    }
    

    // Filho da esquerda vira a nova raiz da SUBARVORE
    private Node rightRotate(Node rootSubArvore){
        Node aux;
        aux = rootSubArvore.getLeft();
        rootSubArvore.setLeft(aux.getRight());
        aux.setRight(rootSubArvore);
        return aux;
    }

    // Filho da direita vira a nova raiz da SUBARVORE
    private Node leftRotate(Node rootSubArvore){
        Node aux;
        aux = rootSubArvore.getRight();
        rootSubArvore.setRight(aux.getLeft());
        aux.setLeft(rootSubArvore);
        return aux;
    }
}