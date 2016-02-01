package com.example.aravind.graphapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    GraphView graph;
    LineGraphSeries<DataPoint> series;
    int x=1,y=19;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void buildUI() {

      /*  this.graph = (GraphView) findViewById(R.id.graph);
       // ArrayList<DataPoint> graph_points = new ArrayList();
       // graph_points.add(new DataPoint(x,y));
        this.series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(x-1,y-1),
                new DataPoint(x,y),
                new DataPoint(x+1,y+1)

        });
        graph.addSeries(series);*/
       /* StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"old", "middle", "new"});
        staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        */
    }
    public void OnRun(View view){

      /*  x = x+1;
        y =y-1; */
        Log.d("VERBOSE", "button press");
        this.graph = (GraphView) findViewById(R.id.graph);
        graph.removeAllSeries();

        LineGraphSeries<DataPoint> new_series = new LineGraphSeries<DataPoint>(generateData());
        /*        new DataPoint[] {
                new DataPoint(x-1,y-1),
                new DataPoint(x,y),
                new DataPoint(x+1,y+1)
        });

       /* this.series.resetData(new DataPoint[]{
                new DataPoint(x - 1, y - 1),
                new DataPoint(x, y),
                new DataPoint(x + 1, y + 1)
        });
        */graph.addSeries(new_series);
    }

    public void OnStop (View view){
        graph.removeAllSeries();
        EditText name = (EditText)findViewById(R.id.nameText);
        EditText id = (EditText)findViewById(R.id.idText);
        EditText age = (EditText)findViewById(R.id.ageText);

        name.setText("");
        age.setText("");
        id.setText("");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private DataPoint[] generateData() {
        Random rand = new Random();
        int count = 30;
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double x = i;
            double f = rand.nextDouble()*0.15+0.3;
            double y = Math.sin(i*f+2) + rand.nextDouble()*0.3;
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;
    }
}
