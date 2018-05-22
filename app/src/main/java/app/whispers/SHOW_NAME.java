package app.whispers;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import static app.whispers.R.id.button10;

public class SHOW_NAME extends AppCompatActivity {
//SUBA S;

    private DatabaseReference mDatabase;
    private static final String TAG = "SHOW_NAME";
    Map<String, Object> map;
    List<Map.Entry<String,Object>> entries;
    Iterator itr;

    Map.Entry<String, Object> entry;
    Iterator curitr;
    boolean found=false;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__name);
        mDatabase= FirebaseDatabase.getInstance().getReference();

        btn= (Button) findViewById(R.id.button10);
        btn.setEnabled(false);

        //Intent i= getIntent();
       //S=(SUBA)i.getSerializableExtra("Sub1");
      //  S.sort();



        Button b1=(Button) findViewById(R.id.button9);
        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Spinner spinnerN = (Spinner) findViewById(R.id.spinner3);
                final String namef=spinnerN.getSelectedItem().toString();



                //S.resetCurI();

                found=false;

                mDatabase.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        TextView Name=(TextView) findViewById(R.id.textView3);
                        TextView Date=(TextView) findViewById(R.id.textView4);
                        EditText Subm=(EditText) findViewById(R.id.editText3);

                        if(dataSnapshot.getValue()==null)
                            return;

                        btn.setEnabled(true);

                        map = (Map<String, Object>) dataSnapshot.getValue();

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


                        curitr=entries.iterator();




                      //  itr = map.entrySet().iterator();

                      //  entry = itr.next();


                        while(curitr.hasNext()) {

                            entry=(Map.Entry<String, Object>)curitr.next();
                            Map singleUser = (Map) entry.getValue();

                            String name = (String) singleUser.get("name");
                            String d = (String) singleUser.get("sdate");
                            String m = (String) singleUser.get("smon");
                            String y = (String) singleUser.get("syear");
                            String Submi = (String) singleUser.get("subm");


                            // subm Sub=S.showD(index);

                            if (name.equals(namef) == true) {
                                Name.setText(name);
                                String s = d + "-" + m + "-" + y;
                                Date.setText(s);
                                Subm.setText(Submi);

                                //curitr.next();
                                found=true;
                              //  Log.d(TAG, "shown in search"+name + " " + Submi);
                                break;
                            }

                           // Log.d(TAG, name + " " + Submi);
                        }
                        if(found==false)
                        {
                            AlertDialog.Builder alert = new AlertDialog.Builder(SHOW_NAME.this);
                            alert.setTitle("Not Found");
                            alert.setMessage("We're extremely very sorry. No records found for this name. ");
                            alert.setPositiveButton("OK", null);
                            alert.show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }

                });



                /*subm Sub=S.showNextN(name);
                if(Sub!=null) {

                    Name.setText(Sub.getName());
                    String s = Sub.getSDate() + "-" + Sub.getSMon() + "-" + Sub.getSYear();
                    Date.setText(s);
                    Subm.setText(Sub.getSubm());
                }
                else if(Sub==null)
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(SHOW_NAME.this);
                    alert.setTitle("Not Found");
                    alert.setMessage("We're extremely very sorry. No records found for this name. ");
                    alert.setPositiveButton("OK", null);
                    alert.show();
                }*/
            }
        });


        Button b2=(Button) findViewById(button10);
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Spinner spinnerN = (Spinner) findViewById(R.id.spinner3);
                final String namef=spinnerN.getSelectedItem().toString();



                //S.resetCurI();


                mDatabase.addValueEventListener(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        TextView Name=(TextView) findViewById(R.id.textView3);
                        TextView Date=(TextView) findViewById(R.id.textView4);
                        EditText Subm=(EditText) findViewById(R.id.editText3);

                        if(dataSnapshot.getValue()==null)
                            return;


                        map = (Map<String, Object>) dataSnapshot.getValue();

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

                        itr = curitr;


                    /*    if(itr.hasNext()) {
                            if (itr.next().equals(x.next())) {
                                itr.next();

                                Log.d(TAG, "back");
                            }
                        }*/


                        //  entry = itr.next();
                        if(itr.hasNext()==false)
                        {
                            itr = entries.iterator();
                            //curitr=itr;

                            //Log.d(TAG,"one");

                        }




                        while (true)
                        {

                            entry=(Map.Entry<String, Object>)itr.next();

                            Map singleUser = (Map) entry.getValue();

                            String name = (String) singleUser.get("name");
                            String d = (String) singleUser.get("sdate");
                            String m = (String) singleUser.get("smon");
                            String y = (String) singleUser.get("syear");
                            String Submi = (String) singleUser.get("subm");


                            // subm Sub=S.showD(index);

                            if (name.equals(namef) == true) {
                                Name.setText(name);
                                String s = d + "-" + m + "-" + y;
                                Date.setText(s);
                                Subm.setText(Submi);
                                curitr=itr;
                              //  Log.d(TAG, "shown" + name + " " + Submi);

                                if(itr.hasNext()==false)
                                {
                                    itr = entries.iterator();
                                    curitr=itr;



                                }
                                break;

                            }
                            if(itr.hasNext()==false)
                            {
                                itr = entries.iterator();
                                curitr=itr;




                            }

                            //Log.d(TAG, "skipped" + name + " " + Submi);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }

                });
               /*subm Sub=S.showNextN(name);
                if(Sub!=null) {

                    Name.setText(Sub.getName());
                    String s = Sub.getSDate() + "-" + Sub.getSMon() + "-" + Sub.getSYear();
                    Date.setText(s);
                    Subm.setText(Sub.getSubm());
                }
                else if(Sub==null)
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(SHOW_NAME.this);
                    alert.setTitle("End of Record");
                    alert.setMessage("You've reached the end of the records for this name. Press 'Next' again to restart from beginning. ");
                    alert.setPositiveButton("OK", null);
                    alert.show();
                }*/

            }
        });



        Button b4=(Button) findViewById(R.id.button12);
        b4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(SHOW_NAME.this,MainActivity.class);
              //  intent.putExtra("Sub",S);
                SHOW_NAME.this.startActivity(intent);
            }
        });

    }




}
