package threads.run;

import threads.services.Auction;

public class Runner {
    public static void main(String[] args) {
        Auction auction = new Auction();
        auction.start();
    }
}
