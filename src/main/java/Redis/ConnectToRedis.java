package Redis;

/**
 * Created by viral on 11/16/15.
 */
import com.lambdaworks.redis.*;

public class ConnectToRedis {

    private static RedisConnection<String, String> connection;

    public ConnectToRedis(){
        if(this.connection  == null){
        RedisClient redisClient = new RedisClient(
                RedisURI.create("redis://127.0.0.1:6379"));
        this.connection = redisClient.connect();
        }

    }

    public boolean isMinusDaysFlagOn(){
        //System.out.println("Connected to Redis");

        String value = connection.get("minusDaysFlag");
        //System.out.println("flag value"+value);

        if(value.equals("1"))
            return true;
        else
            return false;

        //connection.close();
        //redisClient.shutdown();
    }


    public void setMinusDayFlag(boolean value){

        if(value == true)
            connection.set("minusDaysFlag", "1");
        else
            connection.set("minusDaysFlag", "0");

    }
}
