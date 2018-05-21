package app.whispers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.content.Intent;
import android.widget.Button;
import java.io.Serializable;
import android.widget.Spinner;
import android.widget.EditText;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Adding extends AppCompatActivity {


    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
        mDatabase= FirebaseDatabase.getInstance().getReference();

    }


    //Intent i = getIntent();
//SUBA S=new SUBA();

    public void inputData(View v)
    {
        Spinner spinner0 = (Spinner) findViewById(R.id.spinner0);
        Spinner spinnerM = (Spinner) findViewById(R.id.spinner2);
        Spinner spinnerD = (Spinner) findViewById(R.id.spinner);
        Spinner spinnerY = (Spinner) findViewById(R.id.spinner4);
        EditText submission=(EditText) findViewById(R.id.editText);
        String name=spinner0.getSelectedItem().toString();
        String date=spinnerD.getSelectedItem().toString();
        int m=spinnerM.getSelectedItemPosition()+1;
        String year=spinnerY.getSelectedItem().toString();
        int y=Integer.parseInt(year);

        int d=Integer.parseInt(date);
        String sub=submission.getText().toString();
        submission.setText("");

        subm new_sub=new subm(name,sub,d,m,y);

        mDatabase.push().setValue(new_sub);



        //S.inputSub(name,sub,d,m,y);









    }
    public void Back(View v)
    {
        Intent intent = new Intent(Adding.this,MainActivity.class);
        //intent.putExtra("Sub",S);
        Adding.this.startActivity(intent);
    }
}
