package com.mylovin.algo.tree.btree;

public class Tree {
    private Node root;

    public void insert(int data) {
        root = insert0(root, data);
        root = rotate(root);
    }

    private Node insert0(Node node, int data) {
        if (null == node) { // 没有匹配到节点
            return new Node(data);
        }
        if (data < node.getData()) {
            node.setLeft(insert0(node.getLeft(), data));
        } else if (data > node.getData()) {
            node.setRight(insert0(node.getRight(), data));
        }
        node = rotate(node);
        return node;
    }

    public void delete(int data) {
        root = delete0(root, data); // 赋值是为了保证删除的节点是根节点不出现问题
        root = rotate(root);
    }

    private Node delete0(Node node, int data) {
        if (null == node) {
            System.out.println("not found");
            return node;
        }
        if (data == node.getData()) { // 找到待删除节点
            if (null == node.getLeft() && null == node.getRight()) { // 叶子节点直接删除
                return null;
            } else if (null != node.getLeft() && null == node.getRight()) { // 只有左子树，直接删除
                return node.getLeft();
            } else if (null == node.getLeft() && null != node.getRight()) { // 只有右子树，直接删除
                return node.getRight();
            } else if (null != node.getLeft() && null != node.getRight()) { // 两个子树都有
                Node leftChild = node.getLeft();
                Node rightChild = node.getRight();
                Node minRight = rightChild;
                while (null != minRight.getLeft()) { // 找到右子树的最小节点，放置待删除节点的左子树
                    minRight = minRight.getLeft();
                }
                minRight.setLeft(leftChild);
                return rightChild; // 返回右子树的根节点
            }
        }
        if (data < node.getData()) { // 待删除数据比当前小
            node.setLeft(delete0(node.getLeft(), data));
            node = rotate(node);
        }
        if (data > node.getData()) { // 待删除数据比当前大
            node.setRight(delete0(node.getRight(), data));
            node = rotate(node);
        }
        return node;
    }

    public int getHeight(Node node) {
        if (null == node) {
            return -1;
        }
        node.setHeight(Math.max(getHeight(node.getLeft()), getHeight(node.getRight())) + 1);
        return node.getHeight();
    }

    private Node LLRotate(Node node) {
        Node leftChild = node.getLeft();
        Node leftChild_right = leftChild.getRight();
        leftChild.setRight(node);
        node.setLeft(leftChild_right);
        return leftChild;
    }

    private Node RRRotate(Node node) {
        Node rightChild = node.getRight();
        Node rightChild_left = rightChild.getLeft();
        rightChild.setLeft(node);
        node.setRight(rightChild_left);
        return rightChild;
    }

    private Node LRRotate(Node node) {
        node.setLeft(RRRotate(node.getLeft()));
        return RRRotate(node);
    }

    private Node RLRotate(Node node) {
        node.setRight(LLRotate(node.getRight()));
        return LLRotate(node);
    }

    /**
     * 平衡二叉树
     *
     * @param node
     */
    private Node rotate(Node node) {
        if (getHeight(node.getLeft()) - getHeight(node.getRight()) >= 2) { // 左子树高于右子树
            if (getHeight(node.getLeft().getLeft()) > getHeight(node.getLeft().getRight())) { // LL型，进行一次LL旋转
                node = LLRotate(node);
            } else {
                node = LRRotate(node);
            }
        }
        if (getHeight(node.getRight()) - getHeight(node.getLeft()) >= 2) {
            if (getHeight(node.getRight().getRight()) > getHeight(node.getRight().getLeft())) { // RR型，进行一次RR旋转
                node = RRRotate(node);
            } else {
                node = RLRotate(node);
            }
        }
        return node;
    }

    public void printPretty() {
        int height = getHeight(root); // 2
        int width = (int) Math.pow(2, height + 1) - 1; // 8
        int rootx = width / 2; // 3
        int rooty = height; // 2
        root.setPoint(rootx, rooty); // (3, 2)

        int[][] array = new int[width][height + 1]; // 7 2
        array[rootx][rooty] = root.getData();
        printPretty(root, height, array);
        printNow(height, width, array);
        System.out.println();
    }

    private void printPretty(Node node, int height, int[][] array) {
        if (null != node.getLeft()) {
            int x = node.getPoint().getX() - (int) Math.pow(2, height - 1); // 3 - 2*1 = 1
            int y = height - 1; // 1
            node.getLeft().setPoint(x, y);
            array[x][y] = node.getLeft().getData();
            printPretty(node.getLeft(), height - 1, array);
        }
        if (null != node.getRight()) {
            int x = node.getPoint().getX() + (int) Math.pow(2, height - 1); // 3 - 2*1 = 1
            int y = height - 1; // 1
            node.getRight().setPoint(x, y);
            array[x][y] = node.getRight().getData();
            printPretty(node.getRight(), height - 1, array);
        }
    }

    private void printNow(int height, int width, int[][] array) {
        for (int i = height; i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                if (0 == array[j][i]) {
                    System.out.print("--");
                } else {
                    System.out.print(String.format("%02d", array[j][i]));
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert(50);
        tree.printPretty();
        tree.insert(30);
        tree.printPretty();
        tree.insert(20);
        tree.printPretty();
        tree.insert(40);
        tree.printPretty();
        tree.insert(35);
        tree.printPretty();
        tree.insert(25);
        tree.printPretty();
        tree.insert(10);
        tree.printPretty();
        tree.insert(45);
        tree.printPretty();
        tree.insert(80);
        tree.printPretty();
        tree.insert(60);
        tree.printPretty();
        tree.insert(55);
        tree.printPretty();
        tree.insert(75);
        tree.printPretty();
        tree.insert(90);
        tree.printPretty();
        tree.insert(85);
        tree.printPretty();
        tree.insert(95);
        tree.printPretty();
        tree.insert(1);
        tree.printPretty();
        tree.insert(39);
        tree.printPretty();
        tree.insert(67);
        tree.printPretty();
        tree.delete(60);
        tree.printPretty();
    }
}
