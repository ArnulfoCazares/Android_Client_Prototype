package androidexamples.fito.com.navdemowithfragments;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;

import java.text.DecimalFormat;

public class MyYAxisValueFormatter implements YAxisValueFormatter {

    private DecimalFormat mFormat;

    public MyYAxisValueFormatter () {
        mFormat = new DecimalFormat("###,###,##0.0"); // use one decimal
    }

    @Override
    public String getFormattedValue(float value, YAxis yAxis) {
        // write your logic here
        // access the YAxis object to get more information
        return mFormat.format(value) + " C"; // e.g. append a dollar-sign
    }
}