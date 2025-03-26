import java.util.Arrays;

public class BTree {
    static class Node {
        int[] keys;         // 关键字数组
        Node[] children;    // 孩子节点数组
        boolean isLeaf = true;
        int t;              // 最小度数
        int keyCount = 0;   // 当前 key 的数量

        public Node(int t) {
            this.t = t;
            this.keys = new int[2 * t - 1];         // 最大 key 数量 = 2t-1
            this.children = new Node[2 * t];       // 最大孩子数量 = 2t
        }

        // 多路查找
        Node get(int key) {
            int i = 0;
            while (i < keyCount) {
                if (key == keys[i]) {
                    return this;
                }
                if (key < keys[i]) {
                    break;
                }
                i++;
            }
            return isLeaf ? null : children[i].get(key);
        }
    }

    int t;              // 最小度数
    Node root;
    final int MIN_KEY_NUMBER;
    final int MAX_KEY_NUMBER;

    public BTree(int t) {
        this.t = t;
        root = new Node(t);
        MAX_KEY_NUMBER = 2 * t - 1;
        MIN_KEY_NUMBER = t - 1;
    }

    // 是否存在
    public boolean contains(int key) {
        return root.get(key) != null;
    }

    // 插入 key
    public void put(int key) {
        Node r = root;
        if (r.keyCount == MAX_KEY_NUMBER) {  // 根节点满了，分裂
            Node newRoot = new Node(t);
            newRoot.isLeaf = false;
            newRoot.children[0] = r;
            root = newRoot;
            split(newRoot, 0, r);
        }
        doPut(root, key);
    }

    private void doPut(Node node, int key) {
        int i = node.keyCount - 1;

        if (node.isLeaf) {
            // 插入到合适的位置
            while (i >= 0 && key < node.keys[i]) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }
            node.keys[i + 1] = key;
            node.keyCount++;
        } else {
            while (i >= 0 && key < node.keys[i]) {
                i--;
            }
            i++;

            if (node.children[i].keyCount == MAX_KEY_NUMBER) { // 如果子节点满了，先分裂
                split(node, i, node.children[i]);
                if (key > node.keys[i]) { // 选择合适的孩子
                    i++;
                }
            }
            doPut(node.children[i], key);
        }
    }

    private void split(Node parent, int index, Node left) {
        int midIndex = t - 1;
        int midKey = left.keys[midIndex];

        // 创建新节点
        Node right = new Node(t);
        right.isLeaf = left.isLeaf;

        // 拷贝 keys
        System.arraycopy(left.keys, t, right.keys, 0, t - 1);
        if (!left.isLeaf) {
            // 拷贝 children
            System.arraycopy(left.children, t, right.children, 0, t);
        }

        right.keyCount = t - 1;
        left.keyCount = t - 1;

        // 插入 midKey 到 parent
        for (int j = parent.keyCount; j > index; j--) {
            parent.keys[j] = parent.keys[j - 1];
        }
        parent.keys[index] = midKey;

        for (int j = parent.keyCount + 1; j > index + 1; j--) {
            parent.children[j] = parent.children[j - 1];
        }
        parent.children[index + 1] = right;

        parent.keyCount++;
    }
}

