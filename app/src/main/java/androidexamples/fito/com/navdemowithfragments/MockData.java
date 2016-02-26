package androidexamples.fito.com.navdemowithfragments;

import java.util.Random;

/**
 * Created by Fito on 2/19/2016.
 */
public class MockData {


    //X is the day number,
    public static Point getDataFromReciever(int x){
        return new Point(x, generateRandomData());
    }

    private static int generateRandomData(){
        Random random = new Random();
        return random.nextInt(40);
    }
}
