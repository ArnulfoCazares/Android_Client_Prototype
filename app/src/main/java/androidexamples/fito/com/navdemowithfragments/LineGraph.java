package androidexamples.fito.com.navdemowithfragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

/**
 * Created by Fito on 2/19/2016.
 */
public class LineGraph {

    private GraphicalView view;

    private TimeSeries dataset = new TimeSeries("Rain Fall");
    private XYSeriesRenderer renderer = new XYSeriesRenderer();
    private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
    private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();

    public LineGraph(){

        mDataset.addSeries(dataset);

        renderer.setColor(Color.WHITE);
        renderer.setPointStyle(PointStyle.SQUARE);
        renderer.setFillPoints(true);

        //Zoom in and out
        mRenderer.setZoomButtonsVisible(true);
        mRenderer.setXTitle("Day #");
        mRenderer.setYTitle("CM in Rainfall");

        mRenderer.addSeriesRenderer(renderer);
    }

    public GraphicalView getView(Context context){
        view = ChartFactory.getLineChartView(context, mDataset, mRenderer);
        return view;
    }

    public void addNewPoints(Point p){
        dataset.add(p.getX(), p.getY());
    }

}
