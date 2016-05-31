package bit_operation;

/**
 * Created by xinz on 2016/5/31.
 */
public class BitOperation {
    public static void main(String[] args) {
        unsignMove(-2324);
    }

    //两数交换
    public static void exchange(int a,int b){
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("a="+a+" b="+b);
    }

    public static void getBit(){

    }
    public static void unsignMove(int a){
        System.out.println(a >>> 1);
    }
}
