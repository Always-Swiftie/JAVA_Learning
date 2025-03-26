public class Main {

    public static void main(String[] args) {
        test tes=new test() {
            @Override
            public void tes() {
                System.out.println("i am abstract class!");
            }
        };
        tes.tes();
    }
}