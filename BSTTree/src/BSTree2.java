import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BSTree2<T extends Comparable<T>,V extends Comparable<V>>{


    static class BSTNode<T,V>{
        T key;
        V value;//任意类型的值
        BSTNode<T,V> left;
        BSTNode<T,V> right;
        public BSTNode(T key){
            this.key=key;
        }
        public BSTNode(T key,V value){
            this.key=key;
            this.value=value;
        }
        public BSTNode(T key, V value, BSTNode<T,V> left, BSTNode<T,V> right){
            this.key=key;
            this.value=value;
            this.left=left;
            this.right=right;
        }
    }
    BSTNode<T,V> root;

    //查找关键字key对应的值value
    public V get(T key){
        BSTNode<T,V> p=root;
        while (p!=null){
            if(key.compareTo(p.key) < 0){//key<p.key
                p=p.left;
            }else if(key.compareTo(p.key)>0){//key>p.key
                p=p.right;
            }else{//key==p.key,found!
                return p.value;
            }
        }
        return null;
    }

    public V min(){
        BSTNode<T,V> cur=root;
        while (cur!=null){
            if(cur.left!=null){
                cur=cur.left;
            }else{//no left child
                return cur.value;
            }
        }
        return null;
    }

    public V max(){
        BSTNode<T,V> cur=root;
        while (cur!=null){
            if(cur.right!=null){
                cur=cur.right;
            }else{
                return cur.value;
            }
        }
        return null;
    }


    public void put(T key,V value){
        if(root==null){
            root=new BSTNode<>(key,value);
            return;
        }
        //key existed
        BSTNode<T,V> p=root;
        BSTNode<T,V> parent=null;
        while (p!=null){
            parent=p;
            if(key.compareTo(p.key) < 0){//key<p.key
                p=p.left;
            }else if(key.compareTo(p.key)>0){//key>p.key
                p=p.right;
            }else{//key==p.key,found!
                p.value=value;
                return;
            }
        }
        BSTNode<T,V> NewNode=new BSTNode<>(key,value);
        if (value.compareTo(parent.value) < 0) {
            parent.left = NewNode;
        }
        //key no existed
    }

    public Object successor(T key){
        BSTNode<T,V> p=root;
        BSTNode <T,V> ancestorLeft=null;
        while (p!=null){
            if(key.compareTo(p.key)<0){
                p=p.left;
            }else if(p.key.compareTo(key)<0){
                ancestorLeft=p;
                p=p.right;
            }else{
                break;
            }
        }
        if(p==null){
            return null;
        }
        //如果有左子树,找左子树中的最大值
        if(p.left!=null){
            BSTNode<T,V> cur=p.left;
            while (cur!=null){
                if(cur.right!=null){
                    cur=cur.right;
                }else{
                    return cur.value;
                }
            }
        }
        //如果没有左子树，如果最近的祖先从左侧而来，即为前驱
        return ancestorLeft!=null?ancestorLeft.value:null;
    }

    public Object predecessor(T key){
        BSTNode<T,V> p=root;
        BSTNode<T,V> ancestorRight=null;
        while (p!=null){
            if(p.key.compareTo(key)<0){
                p=p.right;
            }else if(key.compareTo(p.key)<0){
                ancestorRight=p;
                p=p.left;
            }
        }
        //如果有右子树,找右子树中的最小值
        if(p.right!=null) {
            BSTNode<T, V> cur =p.right;
            while (cur!=null){
                if(cur.left!=null){
                    cur=cur.left;
                }else{
                    return cur.value;
                }
            }
        }
        return ancestorRight!=null?ancestorRight.value:null;
    }

    public boolean delete(T key){
        BSTNode<T,V> p=root;
        BSTNode<T,V> parent=null;
        while (p!=null){
            if(key.compareTo(p.key)<0){
                parent=p;
                p=p.left;
            }else if(p.key.compareTo(key)<0){
                parent=p;
                p=p.right;
            }else{
                break;
            }
        }
        if(p==null){
            return false;
        }
        //delete
        if(p.left==null){
                shift(parent,p,p.right);
        }else if(p.right==null){
                shift(parent,p,p.left);
        }else{
            //1.找到被删除节点的后任节点
            //2.处理后任的后事
            //3.让后任取代被删除节点
            BSTNode<T,V> s=p.right;
            BSTNode<T,V> sparent=p;
            while (s.left!=null){
                sparent=s;
                s=s.left;
            }
            if(sparent!=p){//不不相邻
                shift(sparent,s,s.right);//因为s是待删除节点的后任，是待删除节点右子树中的最小值，它不可能有左孩子了
                s.right=p.right;
            }
            shift(parent,p,s);
            s.left=p.left;
        }
        return true;
    }
    private void shift(BSTNode<T,V> parent,BSTNode<T,V> deleted,BSTNode<T,V> child){
        //case1:deleted==root
        if(parent==null){
            root=child;
        }else if(deleted==parent.left){
            parent.left=child;
        }else{
            parent.right=child;
        }

    }
    //<key的所有节点
    public List<V> less(T key){
        ArrayList<V> result=new ArrayList<>();
        BSTNode<T,V> p=root;
        Stack<BSTNode> stack=new Stack<>();
        while (p!=null||!stack.isEmpty()){
            if(p!=null){
                stack.push(p);
                p=p.left;
            }else{
                BSTNode<T,V> poped=stack.pop();
                if(poped.key.compareTo(key)<0){
                    result.add(poped.value);
                }else{
                    break;
                }
                p=poped.right;
            }
        }
        return result;
    }

    public List<V> greater(T key){
        ArrayList<V> result=new ArrayList<>();
        BSTNode<T,V> p=root;
        Stack<BSTNode> stack=new Stack<>();
        while (p!=null||!stack.isEmpty()){
            if(p!=null){
                stack.push(p);
                p=p.right;
            }else{
                BSTNode<T,V> poped=stack.pop();
                if(poped.key.compareTo(key)>0){
                    result.add(poped.value);
                }
                p=poped.left;
            }
        }
        return result;
    }

    public List<V> between(T key1,T key2){
        ArrayList<V> result=new ArrayList<>();
        BSTNode<T,V> p=root;
        Stack<BSTNode<T,V>> stack=new Stack<>();
        while (p!=null||!stack.isEmpty()){
            if(p!=null){
                stack.push(p);
                p=p.left;
            }else{
                BSTNode<T,V> poped=stack.pop();
                if(poped.key.compareTo(key1)>=0&&poped.key.compareTo(key2)<=0){
                    result.add(poped.value);
                }else if(poped.key.compareTo(key2)>0){
                    break;
                }
                p=poped.right;
            }
        }
        return  result;
    }

}
