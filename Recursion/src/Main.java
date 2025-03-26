import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        PascalTriangle(5);
    }

    public static long Factorial(int n){
        if(n==1){
            return 1;
        }
        return n*Factorial(n-1);
    }

    public static void reversePrint(int index,String str){
        if(index==str.length()){
            return;                        //结束递
        }
        reversePrint(index+1,str);
        System.out.print(str.charAt(index));
    }

    public static int Binary_Search(int left,int right,int [] arr,int target){
        int mid=(left+right)>>1;
        if(left>right){
            return -1;
        }
        if(target<arr[mid]){
            return Binary_Search(left,mid-1,arr,target);
        } else if (target > arr[mid]) {
            return Binary_Search(mid+1,right,arr,target);
        }else {
            return mid;
        }
    }
    //j是未排序的右边界
    public static void bubble(int[]a,int j){
        if(j==0){
            return;
        }
        for(int i=0;i<j;i++){
            if(a[i]>a[i+1]){
                int t=a[i];
                a[i]=a[i+1];
                a[i+1]=t;
            }
        }
        bubble(a,j-1);
    }
    public static void insertion(int[] a,int low){//low代表未排序区间的左边界，一开始数组内的low=0，随着排序进行，low会递增
        if(low==a.length){
            return;
        }
        int temp=a[low];
        int i=low-1;//已排序区域右边界
        while (i>=0&&a[i]>temp){ //没有找到就不断循环
              a[i+1]=a[i]; //空出插入位置
              i--;
        }
        if(i+1!=low) {
            a[i + 1] = temp;
        }
        insertion(a,low+1);
    }

    public static int fib(int n,int[]cache){
        if(cache[n]!=-1){
            return cache[n];
        }
        int a=fib(n-1,cache);
        int b=fib(n-2,cache);
        int i=a+b;
        cache[n]=i;
        return cache[n];
    }

    public static int fib_memorization(int n){
        int[] cache=new int[n+1];
        Arrays.fill(cache,-1);
        cache[0]=0;
        cache[1]=1;
        return fib(n,cache);
    }

    public static int element(int i,int j,int[][] arr)
    {
        if(arr[i][j]!=0){
            return arr[i][j];
        }
        if(j==0||i==j){
            arr[i][j]=1;
            return 1;
        }
        arr[i][j]= element(i-1,j-1,arr)+element(i-1,j,arr);
        return arr[i][j];
    }

    public static void PascalTriangle(int n){
        int [] row=new int[n];
        for(int i=0;i<n;i++){
                createRow(row,i);//通过上一行的数组就可以得到下一行的数组
                for(int j=0;j<n;j++){
                System.out.printf("%-4d",row[j]);
            }
            System.out.print('\n');
        }
    }

    private static void createRow(int[] row,int i){
        if(i==0){
            row[0]=1;
            return;
        }
        for(int j=i;j>0;j--){
            row[j]=row[j]+row[j-1];
        }
    }
}