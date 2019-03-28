package com.athena.holosimulator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

public class GraphActivity extends AppCompatActivity {

    static GraphView graphView;
    static EditText t1, t2;
    static Button addObject;
    static int userX, userY;
    //int user[], object[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        graphView = findViewById(R.id.graphXYTrajectory);
        t1 = findViewById(R.id.editText4);
        t2 = findViewById(R.id.editText5);
        addObject = findViewById(R.id.button);
        //user = new int[3];
        //object = new int[3];
        int user[] = getIntent().getIntArrayExtra("userCoord");
        int object[] = getIntent().getIntArrayExtra("objectCoord");
        userX = user[0];
        userY = user[1];
        addObjects(user[0], user[1]);
        addObjects(object[0], object[1]);
    }

    public void addObjects(int a, int b) {
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(a, b)
        });
        graphView.addSeries(series);
        addObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addObjects(Integer.parseInt(t1.getText().toString()),Integer.parseInt(t2.getText().toString()));
            }
        });
        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                if((int)dataPoint.getX()==userX && (int)dataPoint.getY()==userY)
                    Toast.makeText(GraphActivity.this, "Visual: On User Position Clicked: "+dataPoint, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(GraphActivity.this, "Visual: On Object Clicked: "+dataPoint, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GraphActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
