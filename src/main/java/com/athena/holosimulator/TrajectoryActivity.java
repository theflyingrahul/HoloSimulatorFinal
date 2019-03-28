package com.athena.holosimulator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Space;
import android.widget.TextView;

public class TrajectoryActivity extends AppCompatActivity {
float m;
EditText ux, uy, uz, ox, oy, oz;
int user[], object[], dirVector[];
String eqn1, eqn2;
Space s1, s2, s3, s4;
TextView eqnText1, eqnText2;
Button graph, clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trajectory);
        ux = findViewById(R.id.editTextUserX);
        uy = findViewById(R.id.editTextUserY);
        uz = findViewById(R.id.editTextUserZ);
        ox = findViewById(R.id.editTextObjX);
        oy = findViewById(R.id.editTextObjY);
        oz = findViewById(R.id.editTextObjZ);
        s1 = findViewById(R.id.space1);
        s2 = findViewById(R.id.space2);
        s3 = findViewById(R.id.space3);
        s4 = findViewById(R.id.space4);
        graph = findViewById(R.id.buttonVisualGraph);
        clear = findViewById(R.id.buttonClear);
        eqnText1 = findViewById(R.id.textViewEqn1);
        eqnText2 = findViewById(R.id.textViewEqn2);
        user = new int[3];
        object = new int[3];
        dirVector = new int[3];
    }

    public void trajectoryCompute(View view) {
        user[0] = Integer.parseInt(ux.getText().toString());
        user[1] = Integer.parseInt(uy.getText().toString());
        user[2] = Integer.parseInt(uz.getText().toString());

        object[0] = Integer.parseInt(ox.getText().toString());
        object[1] = Integer.parseInt(oy.getText().toString());
        object[2] = Integer.parseInt(oz.getText().toString());

        for (int i = 0; i < 3; i++) {
            dirVector[i] = object[i] - user[i];
        }

        eqn1 = "Equation 1:\n[x-(" + user[0] + ")]/" + dirVector[0] + "\t=\t" + "[y-(" + user[1] + ")]/" + dirVector[1] + "\t=\t" + "[z-(" + user[2] + ")]/" + dirVector[2];
        eqn2 = "Equation 2:\n[x-(" + object[0] + ")]/" + dirVector[0] + "\t=\t" + "[y-(" + object[1] + ")]/" + dirVector[1] + "\t=\t" + "[z-(" + object[2] + ")]/" + dirVector[2];

        s1.setVisibility(View.VISIBLE);
        s2.setVisibility(View.VISIBLE);
        s3.setVisibility(View.VISIBLE);
        s4.setVisibility(View.VISIBLE);
        eqnText1.setVisibility(View.VISIBLE);
        eqnText2.setVisibility(View.VISIBLE);
        graph.setVisibility(View.VISIBLE);
        clear.setVisibility(View.VISIBLE);

        eqnText1.setText(eqn1);
        eqnText2.setText(eqn2);
    }

    public void graphVisual(View view) {
        Intent toGraph = new Intent(TrajectoryActivity.this, GraphActivity.class);
        toGraph.putExtra("userCoord", user);
        toGraph.putExtra("objectCoord", object);
        startActivity(toGraph);
    }

    public void clear(View view) {
        s1.setVisibility(View.GONE);
        s2.setVisibility(View.GONE);
        s3.setVisibility(View.GONE);
        s4.setVisibility(View.GONE);
        eqnText1.setVisibility(View.GONE);
        eqnText2.setVisibility(View.GONE);
        graph.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);

        eqnText1.setText("");
        eqnText2.setText("");

        ux.setText("");
        uy.setText("");
        uz.setText("");
        ox.setText("");
        oy.setText("");
        oz.setText("");
    }
}
