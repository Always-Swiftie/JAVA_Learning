public class AVLTree {
    static private class AVLNode {
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height = 1;

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
        private int height(AVLNode node){
            if(node==null){
                return 0;
            }
            return node.height;
        }
        //update
        private void updateHeight(AVLNode node){
            node.height=Integer.max(height(node.left),height(node.right))+1;
        }
        //平衡因子=leftheight-rightheight<=1->balanced
        private int balanceFactor(AVLNode node){
            return height(node.left)-height(node.right);
        }//return 0,-1,1->node is balanced else->not balanced

        private AVLNode rightRotate(AVLNode red){
            AVLNode yellow=red.left;
            AVLNode green=yellow.right;
            yellow.right=red;//上位
            red.left=green;
            updateHeight(red);
            updateHeight(yellow);
            return yellow;//新的根节点
        }
        private AVLNode leftRotate(AVLNode red){
            AVLNode yellow=red.right;
            AVLNode green=yellow.left;
            yellow.left=red;
            red.right=green;
            updateHeight(red);
            updateHeight(yellow);
            return yellow;
        }
        //先左旋左子树，再右旋根节点
        private AVLNode leftRightRotate(AVLNode node){
            node.left=leftRotate(node.left);
            return rightRotate(node);
        }
        //先右旋右子树，再左旋根节点
        private AVLNode rightLeftRotate(AVLNode node){
            node.right=rightRotate(node.right);
            return leftRotate(node);
        }

        private AVLNode balanced(AVLNode node){
            if(node==null){
                return null;
            }
            int bf=balanceFactor(node);
            if(bf>1&&balanceFactor(node.left)>=0){//左子树左边更高LL
                    return rightRotate(node);
            }else if(bf>1&&balanceFactor(node.left)<0){//LR
                    return leftRightRotate(node);
            }else if(bf<-1&&balanceFactor(node.right)>0){//RL
                    return rightLeftRotate(node);
            }else if(bf<-1&&balanceFactor(node.right)<=0){//RR
                    return leftRotate(node);
            }
            return node;
        }

        public void put(int key,Object value){
                root=doPut(root,key,value);
        }

        AVLNode root;
        private AVLNode doPut(AVLNode node,int key,Object value){
            //1.找到空位，创建新节点
            //2.当前key已存在，更新
            //3.继续查找
            if(node==null){
                return new AVLNode(key, value);
            }
            if(key==node.key){
                node.value=value;
                return node;
            }
            if(key<node.key){
                node.left=doPut(node.left,key,value);
            }else {
                node.right=doPut(node.right,key,value);
            }
            updateHeight(node);
            return balanced(node);
        }

        public void remove(int key){
            root=  doRemove(root,key);
        }
        private AVLNode doRemove(AVLNode node,int key){//返回删剩下的
            //1.node==null->return
            if(node==null){
                return null;
            }
            //2.not found yet->continue
            if(key<node.key){
                node.left=doRemove(node.left,key);
            }else if(key>node.key){
                node.right=doRemove(node.right,key);
            }else{
                if(node.left==null&&node.right==null){
                    return null;
                }else if(node.left==null){
                    return node.right;
                }else if(node.right==null){
                    return node.left;
                }else{//左右孩子都存在，那么要找到后任节点,还要处理好它的后事
                    AVLNode s=node.right;
                    while (s.left!=null){
                        s=s.left;
                    }
                    node.key=s.key;
                    node.value=s.value;
                    node.right=doRemove(node.right,s.key);
                }
            }
            //3.found->(a.no child  b.only one child  c.two child)
            //4.update height
            //5.balance
            updateHeight(node);
            return balanced(node);
        }

        public Object get(int key){
            AVLNode cur=root;
            while (cur!=null){
                if(key>cur.key){
                    cur=cur.right;
                }else if(key<cur.key){
                    cur=cur.left;
                }else{
                    return cur.value;
                }
            }
            return null;
        }

}
