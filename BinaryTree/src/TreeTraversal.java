import java.util.*;

public class TreeTraversal {
    //recursive
    static void preOrder1(TreeNode node) {//前序遍历
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        //先左边
        preOrder1(node.left);
        //再右边
        preOrder1(node.right);

    }

    static void inOrder1(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder1(node.left);
        System.out.println(node.val);
        inOrder1(node.right);
    }

    static void postOrder1(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrder1(node.left);
        postOrder1(node.right);
        System.out.println(node.val);
    }

    static void TravelBack(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                System.out.println(cur.val);
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode parent = stack.pop();
                System.out.println(parent.val);
                cur = parent.right;
            }
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode curr = root;
            while (curr != null || !stack.isEmpty()) {
                if (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                } else {
                    TreeNode prev = stack.pop();
                    list.add(prev.val);
                    curr = prev.right;
                }
            }
        }
        return list;
    }

    static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode prev = stack.pop();
                System.out.println(prev.val);
                curr = prev.right;
            }
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                list.add(curr.val);
                curr = curr.left;
            } else {
                TreeNode prev = stack.pop();
                curr = prev.right;
            }
        }
        return list;
    }

    static void preOrder2(TreeNode root) {//非递归
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                System.out.println(curr.val);
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode parent = stack.pop();
                curr = parent.right;
            }
        }
    }

    static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pop = null;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;

            } else {
                TreeNode peek = stack.peek();
                if (peek.right == null || peek.right == pop) {
                    pop = stack.pop();
                    System.out.println(pop.val);
                } else {
                    cur = peek.right;
                }
            }
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root != null) {

            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;
            TreeNode poped = null;
            while (cur != null || !stack.isEmpty()) {
                if (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    TreeNode peek = stack.peek();//此时堆顶的节点是当前节点的前驱节点
                    if (peek.right == null || peek.right == poped) {
                        poped = stack.pop();
                        list.add(poped.val);
                    } else {
                        cur = peek.right;
                    }
                }
            }
        }
        return list;
    }

    public boolean SymmetricCheck(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (right == null || left == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return SymmetricCheck(left.left, right.right) && SymmetricCheck(left.right, right.left);
    }

    public int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;//叶子节点
        }
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);
        int max = leftDepth > rightDepth ? leftDepth : rightDepth;
        return max + 1;
    }

    public int maxDepth1(TreeNode root) {//层顺遍历法
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result=0;
        while (!queue.isEmpty()) {
            int cur_size = queue.size();
            for (int i = 0; i < cur_size; i++) {
                TreeNode polled = queue.poll();
                if (polled.left != null) {
                    queue.offer(polled.left);
                }
                if (polled.right != null) {
                    queue.offer(polled.right);
                }
            }
            result++;
        }
        return result;
    }

    public int minDepth(TreeNode root){
        if(root==null){
            return 0;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        int result=0;
        queue.offer(root);
        while(!queue.isEmpty()){
            int cur_size= queue.size();;
            for(int i=0;i<cur_size;i++){
                TreeNode polled=queue.poll();
                if(polled.left==null&&polled.right==null){
                    return result+1;
                }
                if(polled.left!=null){
                    queue.offer(polled.left);
                }
                if(polled.right!=null){
                    queue.offer(polled.right);
                }
            }
            result++;
        }
        return result+1;
    }

    public TreeNode invertTree(TreeNode root) {//递归
        if(root==null){
            return null;
        }
        fun(root);
        return root;
    }

    private void fun(TreeNode node){
        if(node==null){
            return;
        }
        TreeNode temp=node.left;
        node.left=node.right;
        node.right=temp;//当前节点的左右孩子交换了，左右孩子的孩子也要进行相同的替换
        fun(node.left);
        fun(node.right);
    }

    public TreeNode buildTree(int[] preOrder,int[] inOrder){
        //create root
        int rootIndex=preOrder[0];
        TreeNode root=new TreeNode(rootIndex);
        for(int i=0;i<inOrder.length;i++){
            if(inOrder[i]==rootIndex){
                int[] inLeft=Arrays.copyOfRange(inOrder,0,i);
                int[] inRight=Arrays.copyOfRange(inOrder,i+1,inOrder.length);

                int[] preLeft=Arrays.copyOfRange(preOrder,1,i+1);
                int[] preRight=Arrays.copyOfRange(preOrder,i+1,inOrder.length);

                root.left=buildTree(preLeft,inLeft);
                root.right=buildTree(preRight,inRight);
            }
        }
        return root;
    }
}
