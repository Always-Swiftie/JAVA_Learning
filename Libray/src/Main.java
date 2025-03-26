import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            Scanner scanner=new Scanner(System.in);
            while (scanner.hasNext()){
                String str1=scanner.next();
                String str2=scanner.next();
                BigInteger A=new BigInteger(str1);
                BigInteger B=new BigInteger(str2);
                System.out.println(A.add(B));
            }
    }
}