package com.example.aravind.graphapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.aravind.graphapplication.databasehelper.DatabaseMethods;
import com.example.aravind.graphapplication.sensoractivities.sensorHelper;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.sql.SQLException;
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
        sensorHelper obj = new sensorHelper(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void OnRun(View view){

      /*  x = x+1;
        y =y-1; */
        Log.d("VERBOSE", "button press");
        this.graph = (GraphView) findViewById(R.id.graph);
        graph.removeAllSeries();

        LineGraphSeries<DataPoint> new_series = new LineGraphSeries<DataPoint>(generateAcceleratorData());

        graph.addSeries(new_series);

    }

    private DataPoint[] generateAcceleratorData() {
        DatabaseMethods obj = new DatabaseMethods(this);
        try {
            obj.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<String> values = obj.GetAccelerometerValues();
        //ArrayList<Float[]> list = obj.values;
        //int count = 30;
        DataPoint[] value = new DataPoint[10];
        //int i=0;
        //for (Float[] val: list) {
        //    double x = val[0];
        //    double y = val[1];
        //    double z = val[2];
//
        //    DataPoint v = new DataPoint(x,y);
        //    values[i++] = v;
        //}
        return value;
    }

    public void OnStop (View view){
        graph.removeAllSeries();
        EditText name = (EditText)findViewById(R.id.nameText);
        EditText id = (EditText)findViewById(R.id.idText);
        EditText age = (EditText)findViewById(R.id.ageText);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.sexGroup);

        name.setText("");
        age.setText("");
        id.setText("");
        radioGroup.clearCheck();
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
