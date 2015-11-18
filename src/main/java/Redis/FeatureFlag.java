package Redis;

import org.joda.time.DateTime;

/**
 * Created by viral on 11/17/15.
 */
public class FeatureFlag {

    public static void main(String[] args) {

        ConnectToRedis c = new ConnectToRedis();


        c.setMinusDayFlag(true);
        printDate();

        c.setMinusDayFlag(false);
        printDate();
    }

public static void printDate(){

    ConnectToRedis c = new ConnectToRedis();
    if(c.isMinusDaysFlagOn() == true){

        DateTime date = new DateTime();
        System.out.println(date);
    }else{
        System.out.println("null");
    }
}

}
