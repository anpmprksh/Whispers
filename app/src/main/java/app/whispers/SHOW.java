package app.whispers;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SHOW extends AppCompatActivity {
//SUBA S;
    //int index=0;
    private DatabaseReference mDatabase;
    private static final String TAG = "SHOW";
    Map<String, Object> map;
    List<Map.Entry<String,Object>> entries;

    Iterator itr;

    Map.Entry<String, Object> entry;
    Button btn2;
    Button btn3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        btn2= (Button) findViewById(R.id.button7);
        btn2.setEnabled(false);
        btn3= (Button) findViewById(R.id.button4);
        btn3.setEnabled(false);
       // mDatabase.child("whispers-53db6").orderByChild("date");

        //Intent i= getIntent();
       // S=(SUBA)i.getSerializableExtra("Sub1");
        //S.sort();
        mDatabase.orderByChild("date").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue()==null)
                    return;

                btn2.setEnabled(true);
                btn3.setEnabled(true);

                map = (Map<String, Object>) dataSnapshot.getValue();
                /*Map<String, subm> resultSet = map.entrySet()
                        .stream()
                        .sorted(Comparator.comparingInt(e -> e.getValue().getId()))
                        .collect(Collectors.toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (left, right) -> left,
                                LinkedHashMap::new));*/

                entries = new ArrayList<Map.Entry<String,Object>>(map.size());

                entries.addAll(map.entrySet());

                for(int i = 0; i <entries.size()-1; i++) {
                    for(int j = 0; j < entries.size()-i-1; j++) {

                        Map s1 = (Map) entries.get(j).getValue();
                        Map s2 = (Map) entries.get(j+1).getValue();
                        Long d1= (Long)s1.get("date");
                        Long d2= (Long)s2.get("date");


                        if(d1>d2 ) {
                            Map.Entry<String,Object> temp = entries.get(j);
                            entries.set(j, entries.get(j + 1));
                            entries.set(j + 1, temp);
                            //Log.d(TAG,"sorting");
                        }
                    }
                }


                itr=entries.iterator();

                entry = (Map.Entry<String, Object>) itr.next();


                Map singleUser = (Map) entry.getValue();



                String name=(String) singleUser.get("name");
                String d=(String) singleUser.get("sdate");
                String m=(String) singleUser.get("smon");
                String y=(String) singleUser.get("syear");
                String Submi=(String) singleUser.get("subm");







                TextView Name=(TextView) findViewById(R.id.textView);
                TextView Date=(TextView) findViewById(R.id.textView2);
                EditText Subm=(EditText) findViewById(R.id.editText2);
                // subm Sub=S.showD(index);

                    Name.setText(name);
                    String s = d + "-" + m + "-" + y;
                    Date.setText(s);
                    Subm.setText(Submi);


                //Log.d(TAG,name+" "+Submi);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

        });


     /*  TextView Name=(TextView) findViewById(R.id.textView);
        TextView Date=(TextView) findViewById(R.id.textView2);
        EditText Subm=(EditText) findViewById(R.id.editText2);
       // subm Sub=S.showD(index);
        if(Sub!=null) {
            Name.setText(Sub.getName());
            String s = Sub.getSDate() + "-" + Sub.getSMon() + "-" + Sub.getSYear();
            Date.setText(s);
            Subm.setText(Sub.getSubm());
        }
*/
     Button b1=(Button) findViewById(R.id.button4);
        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
               /* index++;
                if(S.getCurI()<=index)
                    index=0;

                TextView Name=(TextView) findViewById(R.id.textView);
                TextView Date=(TextView) findViewById(R.id.textView2);
                EditText Subm=(EditText) findViewById(R.id.editText2);
                subm Sub=S.showD(index);
                if(Sub!=null) {
                    Name.setText(Sub.getName());
                    String s = Sub.getSDate() + "-" + Sub.getSMon() + "-" + Sub.getSYear();
                    Date.setText(s);
                    Subm.setText(Sub.getSubm());
                }*/



                        if(itr.hasNext()==false)
                        {
                            itr = entries.iterator();

                        }
                       // prev=entry;
                        entry = (Map.Entry<String, Object>) itr.next();



                        Map singleUser = (Map) entry.getValue();

                        String name=(String) singleUser.get("name");
                        String d=(String) singleUser.get("sdate");
                        String m=(String) singleUser.get("smon");
                        String y=(String) singleUser.get("syear");
                        String Submi=(String) singleUser.get("subm");






                        TextView Name=(TextView) findViewById(R.id.textView);
                        TextView Date=(TextView) findViewById(R.id.textView2);
                        EditText Subm=(EditText) findViewById(R.id.editText2);
                        // subm Sub=S.showD(index);

                        Name.setText(name);
                        String s = d + "-" + m + "-" + y;
                        Date.setText(s);
                        Subm.setText(Submi);


                        //Log.d(TAG,name+" "+Submi);



            }
        });



        Button b3=(Button) findViewById(R.id.button6);
        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SHOW.this,MainActivity.class);
             //   intent.putExtra("Sub",S);
                SHOW.this.startActivity(intent);
            }
        });

        Button b4=(Button) findViewById(R.id.button7);
        b4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(SHOW.this);

                builder.setTitle("Delete This Recod");
                builder.setMessage("Are you sure you want to delete this record ?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        String key=entry.getKey();
                        mDatabase.child(key).setValue(null);
                        if(itr.hasNext()==false)
                        {
                            itr = entries.iterator();

                        }
                        // prev=entry;
                        entry= (Map.Entry<String, Object>) itr.next();



                        Map singleUser = (Map) entry.getValue();

                        String name=(String) singleUser.get("name");
                        String d=(String) singleUser.get("sdate");
                        String m=(String) singleUser.get("smon");
                        String y=(String) singleUser.get("syear");
                        String Submi=(String) singleUser.get("subm");






                        TextView Name=(TextView) findViewById(R.id.textView);
                        TextView Date=(TextView) findViewById(R.id.textView2);
                        EditText Subm=(EditText) findViewById(R.id.editText2);
                        // subm Sub=S.showD(index);

                        Name.setText(name);
                        String s = d + "-" + m + "-" + y;
                        Date.setText(s);
                        Subm.setText(Submi);




                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();


            }
        });

  }


}
