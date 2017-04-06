/**
 * Created by AFDS on 2017/4/6.
 */

class TicketSeller{
    int fiveNumber = 1, tenNumber = 0, twentyNumber = 0;
    public synchronized void sellTicket(int receiveMoney, int buyNumber){
        if(receiveMoney == 5){
            fiveNumber = fiveNumber + 1;
            System.out.printf("\n%s给我5元钱，这是您的一张入场券", Thread.currentThread().getName());
        }
        else if(receiveMoney == 10 && buyNumber == 2){
            tenNumber += 1;
            System.out.printf("\n%s给我10元钱， 这是你的两张入场券",Thread.currentThread().getName());
        }
        else if(receiveMoney == 10&& buyNumber== 1){
            while (fiveNumber<1) {
                try {
                    System.out.printf("\n%30s靠边等",Thread.currentThread().getName());
                    wait();
                    System.out.printf("\n%30s结束等待\n", Thread.currentThread().getName());
                } catch (InterruptedException e) {

                }
            }
            fiveNumber -= 1;
            tenNumber += 1;
            System.out.printf("\n%s给我10元钱，找你5元，这是您的一张入场券",Thread.currentThread().getName());
        }
        else if(receiveMoney == 20 && buyNumber==1){
            while (fiveNumber<1||tenNumber<1){
                try {
                    System.out.printf("\n%30s靠边等", Thread.currentThread().getName());
                    wait();
                    System.out.printf("\n%30s结束等待",Thread.currentThread().getName());
                } catch (InterruptedException e) {
                }
            }
            fiveNumber -= 1;
            tenNumber -= 1;
            twentyNumber += 1;
            System.out.printf("\n%s给我20元，找您一张5元，一张10元， 这是您的一张入场券",Thread.currentThread().getName());
        }
        else if(receiveMoney ==20&&buyNumber ==2){
            while (tenNumber<1){
                try {
                    System.out.printf("\n%30s靠边等\n", Thread.currentThread().getName());
                    wait();
                } catch (InterruptedException e) {
                }
            }
            tenNumber-=1;
            twentyNumber+=1;
            System.out.printf("\n%s给我20元钱，找您一张10元，这是您的二张入场券",Thread.currentThread().getName());
        }

        notifyAll();
    }
}

class Cinema implements Runnable{

    Thread zhao, qian, sun, li, zhou;
    TicketSeller seller;

    Cinema(){
        zhao = new Thread(this);
        qian = new Thread(this);
        sun = new Thread(this);
        li = new Thread(this);
        zhou = new Thread(this);
        zhao.setName("赵");
        qian.setName("钱");
        sun.setName("孙");
        li.setName("李");
        zhou.setName("周");
        seller = new TicketSeller();
    }

    @Override
    public void run() {
        if(Thread.currentThread() == zhao){
            seller.sellTicket(20, 2);
        }else if(Thread.currentThread() == qian){
            seller.sellTicket(20, 1);
        }else if(Thread.currentThread() == sun){
            seller.sellTicket(10,1);
        }else if (Thread.currentThread() == li){
            seller.sellTicket(10,2);
        }else if(Thread.currentThread() == zhou){
            seller.sellTicket(5,1);
        }
    }
}

public class SaleExample1 {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();
        cinema.zhao.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        cinema.qian.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        cinema.sun.start();
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        cinema.li.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        cinema.zhou.start();
    }
}
