import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Main {

    public static void main (String[] args)throws IOException  {
        System.out.println("Please enter numbers:");
        Scanner scanner=new Scanner(System.in);
        double sum=0;
        int m=0;

        while (scanner.hasNextDouble()){
            double x=scanner.nextDouble();
            m+=1;
            sum+=x;
        }
        System.out.println(sum/m);
    }
}




