import BinaryTree.Node;
public class Main {

    private static void montarArvoreBST(AVL arvore){
        arvore.insert(3);
        arvore.insert(6);
        arvore.insert(40);
        arvore.insert(9);
        arvore.insert(-4);
        arvore.insert(-20);
        arvore.insert(1);
        arvore.insert(0);
        arvore.insert(50);
        arvore.insert(51);
        arvore.insert(52);
    }

    private static void montarArvoreAVL(AVL arvore){
        arvore.insertAVL(3);
        arvore.insertAVL(6);
        arvore.insertAVL(40);
        arvore.insertAVL(9);
        arvore.insertAVL(-4);
        arvore.insertAVL(-20);
        arvore.insertAVL(1);
        arvore.insertAVL(0);
        arvore.insertAVL(50);
        arvore.insertAVL(51);
        arvore.insertAVL(52);
    }

    public static void main(String[] args) {
        AVL arvore = new AVL();
        // Esta representacao eh soh para NAO AVL
        //              3
        //          -4     6
        //       -20   1      40
        //            0      9   50
        //                          51
        //                             52
        int teste = 3;


        //montarArvoreBST(arvore);
        montarArvoreAVL(arvore);

        arvore.inOrderTraversal();
        //arvore.remove(teste); arvore.inOrderTraversal();
        //System.out.printf("Max: %d\nMin: %d\n", arvore.findMax().getData(), arvore.findMin().getData());
        //System.out.printf("%d", arvore.search(40).getLevel());
        //System.out.printf("%d", arvore.findPredecessor(3).getData());
        //System.out.printf("Antecessor de %d: %d\nSucessor de %d: %d\n",teste, arvore.findAntecessor(teste).getData(),teste, arvore.findSuccessor(teste).getData());

        //Para testar AVL
        System.out.printf("%d\n", arvore.search(3).fatorBalanceamento());
        System.out.printf("%d\n", arvore.getRoot().getData());
        //System.out.prtinf("%d\n", arvore.search(3).getData());
        //System.out.printf("%b", arvore.ehAVL(arvore.search(3)));

    }
}
