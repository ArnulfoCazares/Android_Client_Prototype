package androidexamples.fito.com.navdemowithfragments;

/**
 * Created by Fito on 2/9/2016.
 */
public class RowObject {

    public String data;
    public int icon;
    private int type;


    public RowObject(){
        super();
    }

    public RowObject(String data, int icon, int type){
        super();
        this.data = data;
        this.icon = icon;
        this.type = type;
    }

    public int getType(){
        return type;
    }

    public void setType(int type){
        this.type = type;
    }


}
