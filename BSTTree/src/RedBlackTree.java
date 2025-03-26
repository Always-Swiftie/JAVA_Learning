public class RedBlackTree {
    enum Color{
        RED,BLACK;
    }

    private Node root;

    private static class Node{
        int key;
        Object value;
        Node left;
        Node right;
        Node parent;
        Color color=Color.RED;

        public Node(int key, Object value) {
            this.key=key;
            this.value=value;
        }

        boolean isLeftChild(){
            return parent!=null&&parent.left==this;
        }

        Node uncle(){
            if(parent==null||parent.parent==null){
                return null;
            }
            if(parent.isLeftChild()){
                return parent.parent.right;
            }else{
                return parent.parent.left;
            }
        }

        Node sibling(){
            if(parent==null){
                return null;
            }
            if(this.isLeftChild()){
                return parent.right;
            }else{
                return parent.left;
            }
        }


    }
    boolean isRed(Node node){
        return node!=null&&node.color==Color.RED;
    }
    boolean isBlack(Node node){
        return node==null||node.color==Color.BLACK;
    }

    private void rightRotate(Node pink){
        Node parent=pink.parent;
        Node yellow=pink.left;
        Node green=yellow.right;
        yellow.right=pink;
        yellow.parent=parent;
        pink.parent=yellow;
        pink.left=green;
        if(parent==null){
            root=yellow;
        }else if(parent.left==pink){
            parent.left=yellow;
        }else {
            parent.right=yellow;
        }
    }
    private void leftRotate(Node pink){
        Node parent=pink.parent;
        Node yellow=pink.right;
        Node green=yellow.left;
        yellow.left=pink;
        yellow.parent=parent;
        pink.parent=yellow;
        pink.right=green;
        if(parent==null){
            root=yellow;
        }else if(parent.right==pink){
            parent.right=yellow;
        }else{
            parent.left=yellow;
        }
    }


    public void put(int key,Object value){
        Node p=root;
        Node parent=null;
        while (p!=null){
            parent=p;
            if(key<p.key){
                p=p.left;
            }else if(key>p.key){
                p=p.right;
            }else{
                p.value=value;
                return;
            }
        }
        Node inserted=new Node(key,value);
        if(parent==null){
            root=inserted;
        }else if(key<parent.key){
            parent.left=inserted;
            inserted.parent=parent;
        }else{
            parent.right=inserted;
            inserted.parent=parent;//构建父子关系
        }
    }
    //违反了不红红的规则（插入的新节点默认都是红色）
    void fixRedRed(Node x){
        //case1:插入节点为根->根叶黑，直接变黑即可
        if(x==root){
            x.color=Color.BLACK;
            return;
        }
        //case2：插入节点父亲为黑->无需调整
        if(isBlack(x.parent)){
            return;
        }
        //case3：父亲为红色，违反不红红,叔叔为红色
        Node parent=x.parent;
        Node uncle=x.uncle();
        Node grandparent=parent.parent;
        if(isRed(uncle)){
            parent.color=Color.BLACK;
            uncle.color=Color.BLACK;
            grandparent.color=Color.RED;
            fixRedRed(grandparent);
            return;
        }
        //uncle is black
        //case4:
        if(parent.isLeftChild()&&x.isLeftChild()){//LL型
            parent.color=Color.BLACK;
            grandparent.color=Color.RED;
            rightRotate(grandparent);//祖父右旋
        }else if(parent.isLeftChild()){//LR
            leftRotate(parent);//先左旋父亲
            x.color=Color.BLACK;
            grandparent.color=Color.BLACK;
            rightRotate(grandparent);
        }else if(!x.isLeftChild()){//RR
            parent.color=Color.BLACK;
            grandparent.color=Color.RED;
            leftRotate(grandparent);
        }else{//RL
            rightRotate(parent);
            x.color=Color.BLACK;
            parent.color=Color.RED;
            leftRotate(grandparent);
        }
    }
    public void remove(int key){
        Node deleted=find(key);
        if(deleted==null){
            return;
        }

    }
    private void fixDoubleBlack(Node x){
        if(x==root){
            return;
        }
        Node parent=x.parent;
        Node sibling=x.sibling();
        if(isRed(sibling)){//兄弟为红
            if(x.isLeftChild()){
                leftRotate(parent);
            }else{
                rightRotate(parent);
            }
            sibling.color=Color.BLACK;
            parent.color=Color.RED;
            fixDoubleBlack(x);
            return;
        }//兄弟是黑,俩侄子也为黑
        if(sibling!=null) {
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                sibling.color=Color.RED;
                if(isRed(parent)){
                    parent.color=Color.BLACK;
                }else{
                    fixDoubleBlack(parent);
                }
            }else{//兄弟是黑，侄子中至少一个红色
                //LL
                if(sibling.isLeftChild()&&isRed(sibling.left)){
                    rightRotate(parent);
                    //变色
                    sibling.left.color=Color.BLACK;
                    sibling.color=parent.color;
                    parent.color=Color.RED;
                }
                //LR
                else if(sibling.isLeftChild()&&isRed(sibling.right)){
                    sibling.right.color=parent.color;//因为它旋转到父结点的位置
                    leftRotate(sibling);//->LL
                    rightRotate(parent);
                    parent.color=Color.BLACK;
                }else if(!sibling.isLeftChild()&&isRed(sibling.left)){//RL
                    sibling.left.color=parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                }else{//RR
                    leftRotate(parent);
                    sibling.right.color=Color.BLACK;
                    sibling.color=parent.color;
                }
                parent.color=Color.BLACK;
            }
        }else{
            fixDoubleBlack(parent);
        }
    }
    private void doRemove(Node deleted){
        Node replaced=findReplaced(deleted);
        Node parent=deleted.parent;
        if(replaced==null){//没有孩子
            if(deleted==root){//是根节点，且没有孩子
                root=null;
            }else{//不是根节点，是叶子节点
                if(isBlack(deleted)){//位于叶子的双黑
                    fixDoubleBlack(deleted);
                }else{
                    //红色叶子，无需处理，直接删
                }
                if(deleted.isLeftChild()){
                    parent.left=null;
                }else{
                    parent.right=null;
                }
                deleted.parent=null;
            }//不是根节点，也没有孩子
            return;
        }//有孩子
        if(deleted.left==null||deleted.right==null){//有一个孩子
            if(deleted==root){//是根节点
                root.key=replaced.key;
                root.value=replaced.value;
                root.left=null;
                root.right=null;
            }else{//不是根节点
                if(deleted.isLeftChild()){
                    parent.left=replaced;
                }else{
                    parent.right=replaced;
                }
                replaced.parent=parent;
                deleted.parent=null;
                if(isBlack(deleted)&&isBlack(replaced)){//双黑节点
                    fixDoubleBlack(replaced);
                }else{
                    replaced.color=Color.BLACK;
                }
            }
            return;
        }

        //有两个孩子
        int t=deleted.key;
        deleted.key=replaced.key;
        replaced.key=t;

        Object v=deleted.value;
        deleted.value=replaced.value;
        replaced.value=v;
        doRemove(replaced);
    }
    Node find(int key){
        Node p=root;
        while (p!=null){
            if(key<p.key){
                p=p.left;
            }else if(key>p.key){
                p=p.right;
            }else{
                return p;
            }
        }
        return null;
    }
    //查找剩余节点
    Node findReplaced(Node deleted){
        if(deleted.left==null&&deleted.right==null){//待删除节点是叶子节点->直接删除，所以返回null
            return null;
        }
        if(deleted.left==null){
            return deleted.right;
        }
        if(deleted.right==null){
            return deleted.left;
        }
        Node s=deleted.right;
        while (s.left!=null){
            s=s.left;
        }
        return s;
    }
}
