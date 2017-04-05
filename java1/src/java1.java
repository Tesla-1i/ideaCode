/**
 * Created by AFDS on 2017/4/4.
 */

class ThreadCos extends Thread{
    private int jd;
    public ThreadCos(){
        jd = 0;
    }
    public ThreadCos(int x){
        jd = x;
    }
    public int getJd(){
        return jd;
    }
    public void setJd(int a){
        jd = a;
    }
    public void run(){
        try{
            for(int i = jd; i <= 90; i++){
                System.out.println("cos("+i+")="+Math.cos(i*3.14159/180.0)+"我在上网聊天");
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            System.out.println("线程休眠打断");
        }
    }
}

class ThreaSqrt implements Runnable{
    private int a, b;
    public ThreaSqrt(){
        a = 0; b = 100;
    }
    public ThreaSqrt(int x, int y){
        a = x; b =y;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public void run() {
        for(int i = a; i < b ; i++){
            System.out.println("sqrt("+i+")="+Math.sqrt(i)+"我在打印");
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("ok!");
            }
        }
    }

}

public class java1{
    public static void main(String[] args) {
        Thread t1 = new ThreadCos(60);
        Thread t2 = new Thread(new ThreaSqrt(20, 50));
        System.out.println("线程开始执行");
        t1.start();
        t2.start();
    }
}