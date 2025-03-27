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
        int removeKey(int index){
            int t=keys[index];//待删除位置的key
            System.arraycopy(keys,index+1,keys,index,--keyCount-index);
            return t;
        }
        Node removeChild(int index){
            Node t=children[index];
            int count=children.length;
            System.arraycopy(children,index+1,children,index,count-1-index);
            return t;
        }
        void insertKey(int key,int index){
            //先把待删除位置后的所有元素后移一位
            System.arraycopy(keys,index,keys,index+1,keyCount);
            keys[index]=key;
        }
        void insertChild(Node child,int index){
            System.arraycopy(children,index,children,index+1,children.length);
            children[index]=child;
        }
        int removeLeftmostKey(){
            return removeKey(0);
        }
        int removeRightmostKey(){
            return removeKey(keyCount-1);
        }
        Node removeLeftmostChild(){
            return removeChild(0);
        }
        Node removeRightmostChild(){
            return removeChild(children.length-1);
        }
        Node childLeftSibling(int index){
            return index>0?children[index-1]:null;
        }
        Node childRightSibling(int index){
            return index==keyCount?null:children[index+1];
        }
        void moveToTarget(Node target){
            int start=target.keyCount;
            if(isLeaf){
                for(int i=0;i<=keyCount;i++){
                    target.children[start+i]=children[i];
                }
            }
            for (int i=0;i<keyCount;i++){
                target.keys[target.keyCount++]=keys[i];
            }
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
    public int[] find(int key){
        Node target=root.get(key);
        if(target==null){//说明没有找到这个key
            return null;
        }
        //如果target!=null,说明要找的key就在这个target节点里面
        return target.keys;
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

    public void remove(int key){
        doRemove(null,root,0,key);
    }

    private void doRemove(Node parent,Node node,int index,int key){
        int i=0;
        while (i<node.keyCount){
            if(node.keys[i]>=key){
                //found or go to child
                break;
            }
            i++;
        }
        //如果当前节点没找到，那要去退出时的i下标的的孩子节点
        //如果找到，i就是待删除key的索引
        if(node.isLeaf) {
            if (i < node.keyCount && node.keys[i] == key) {//found&&isLeaf case2
                node.removeKey(i);
            } else {//not found&&isLeaf case1
                return;
            }
        }else{
            if (i < node.keyCount && node.keys[i] == key) {//found&&NotLeaf  case4
                //先找后继，再替换
                Node s=node.children[i+1];
                while (!s.isLeaf){
                    s=s.children[0];//向左走
                }
                int skey=s.keys[0];//找到后继key
                node.keys[i]=skey;
                doRemove(node,node.children[i+1],i+1,skey);
            } else {//not found&&NotLeaf case3
                doRemove(node,node.children[i],i,key);
            }
        }
        if(node.keyCount<MIN_KEY_NUMBER){
            //下溢出了，调整平衡
            balance(parent,node,index);
        }
    }
    private void balance(Node parent,Node x,int i){
        //case6 根节点
        if(x==root){
            if(root.keyCount==0&&root.children[0]!=null){
                root=root.children[0];
            }
            return;
        }
        Node leftSibling=parent.childLeftSibling(i);
        Node rightSibling=parent.childRightSibling(i);
        //case5-1:左边兄弟可以借，右旋
         if(leftSibling!=null&&leftSibling.keyCount>MIN_KEY_NUMBER){
             x.insertKey(parent.keys[i-1],0);//父亲下来
             if(!leftSibling.isLeaf){//如果兄弟不是叶子，有孩子，在下一步之后兄弟会少一个孩子
                 x.insertChild(leftSibling.removeRightmostChild(),0);//把兄弟最右边的孩子给被调整节点
             }
             parent.keys[i-1]=leftSibling.removeRightmostKey();//兄弟上去
             return;
         }
        //case5-2:右边兄弟可以借，左旋
        if(rightSibling!=null&&rightSibling.keyCount>MIN_KEY_NUMBER){
            x.insertKey(parent.keys[i],x.keyCount);//父亲下来
            if(!rightSibling.isLeaf){
                x.insertChild(rightSibling.removeLeftmostChild(),x.keyCount+1);
            }
            parent.keys[i]=x.removeLeftmostKey();//兄弟上去
        }
        //case5-3 两边都不够借，向左合并
        //a.x有左兄弟-合并到左兄弟
        if(leftSibling!=null){
            parent.removeChild(i);
            leftSibling.insertKey(parent.removeKey(i-1),leftSibling.keyCount);
            x.moveToTarget(leftSibling);//赋值当前节点所有的key,child到目标节点
        }else{//b.x没有左兄弟-合并到x自己，移除右边兄弟
            parent.removeChild(i+1);
            x.insertKey(parent.removeKey(i),x.keyCount);
            rightSibling.moveToTarget(x);
        }
    }

}

