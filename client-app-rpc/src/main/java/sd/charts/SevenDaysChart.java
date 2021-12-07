package sd.charts;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;

public class SevenDaysChart extends ApplicationFrame {

    public SevenDaysChart(String applicationTitle, String chartTitle, List<Float> values) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Day and hour","KWh",
                createDataset(values),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 400 ) );
        setContentPane(chartPanel);
    }

    private DefaultCategoryDataset createDataset(List<Float> values) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int counter = 1;
        int day = 1;
        for(Float f: values){
            dataset.addValue(f, "Consumption", counter + " " + day);
            counter++;
            if(counter == 24) {
                counter = 0;
                day++;
            }
        }
        return dataset;
    }

}