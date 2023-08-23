public class main {
    public static void main(String[] args) {
        System.out.println("RBTreeeeee start!");
        RBTree tree1 = new RBTree();
        tree1.add(8);
        tree1.add(9);
        tree1.add(100);
        tree1.add(2);
        tree1.add(22);
        tree1.add(0);
        tree1.add(77);

        tree1.pintTree(tree1.root);

    }



}
