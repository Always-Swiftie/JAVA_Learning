import java.util.Arrays;

public class Binary_basic {
    public static int binary_search_basic(int [] a,int targer){
            int left=0,right= a.length-1;
            while(left<=right){
                int mid=(left+right)/2;
                if(a[mid]<targer){
                    left=mid+1;
                } else if (a[mid]>targer) {
                    right=mid-1;
                } else {//当找到匹配值之后，左右寻找还有没有匹配的
                    int leftmost=mid-1,rightmost=mid+1;//左右新边界
                    while(a[leftmost]==targer) {
                        leftmost--;
                    }
                    while (a[rightmost]==targer){
                        rightmost++;
                    }//现在得到了上界和下界,按需求返回左边界或者右边界即可
                    return leftmost;
                }
            }
        return -1;
    }
    public static int binary_search_basic_1(int [] a,int targer){
        int left=0,right= a.length;//1
        while(left<right){//2
            int mid=(left+right)/2;
            if(a[mid]<targer){
                left=mid+1;
            } else if (a[mid]>targer) {
                right=mid;
            } else {
                return mid;
            }
        }
        return -1;
    }
    public static int binary_search_balanced(int [] a,int target){
        int left=0,right=a.length;
        while (right-left>1){
            int mid=(left+right)>>>1;
            if(target<a[mid]){
                right=mid;
            }else{
                left=mid;
            }
        }
        if(a[left]==target){
            return left;
        }else {
            return  -1;
        }
    }
}
