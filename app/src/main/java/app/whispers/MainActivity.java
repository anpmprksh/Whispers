package app.whispers;

import android.app.Activity;
import android.os.Bundle;
import android.net.Uri;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import java.io.Serializable;
public class MainActivity extends Activity {
SUBA S;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     // Intent i = getIntent();
      // S=(SUBA)i.getSerializableExtra("Sub");
        Button b1=(Button) findViewById(R.id.add);
        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Adding.class);

                MainActivity.this.startActivity(intent);
            }
        });

        Button b2=(Button) findViewById(R.id.button3);
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SHOW.class);
                //intent.putExtra("Sub1",S);

                MainActivity.this.startActivity(intent);

            }
        });

        Button b3=(Button) findViewById(R.id.button8);
        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SHOW_NAME.class);
               // intent.putExtra("Sub1",S);

                MainActivity.this.startActivity(intent);

            }
        });
    }










}
class subm
{
    String name;
    String sub;
    int d,m,y;
    public subm(String name,String sub, int d, int m, int y)
    {
        this.name=name;
        this.sub=sub;
        this.d=d;
        this.m=m;
        this.y=y;
    }
    public String getName()
    {
        return name;
    }
    public String getSubm()
    {
        return sub;
    }
    public String getSDate()
    {
        return Integer.toString(d);
    }
    public String getSMon()
    {
        return Integer.toString(m);
    }
    public String getSYear()
    {
        return Integer.toString(y);
    }
    public int getDate()
    {
        return 10000*y+100*m+d;
    }






}

class SUBA implements Serializable
{
    int index;
    int curI;

    subm S[];
    public SUBA()
    {
        S=new subm[1000];
        index=0;
        curI=0;


    }
    public void inputSub(String name,String sub, int d, int m, int y)
    {
        S[index++]=new subm(name,sub,d,m,y);
    }
    public void sort()
    {
        for(int i=0;i<index-1;i++)
        {
            for(int j=0;j<index-i-1;j++)
            {
                if(S[j].getDate()>S[j+1].getDate())
                {
                    subm temp=S[j+1];
                    S[j+1]=S[j];
                    S[j]=temp;
                }
            }
        }
    }
    public subm showNextN(String n)
    {
        int st=0;

        for(int i=curI;i<index;i++)
        {
            if(S[i].getName().equals(n)==true)
            {
                if(i!=index-1)
                {curI=i+1;}
                else
                {
                    curI=0;
                }

                st=1;
                return S[i];


            }
        }
        if(st==0)
        {
            curI=0;
        }
        return null;
    }

    public subm showD(int i)
    {
        return S[i];
    }
    public void Delete(int i)
    {
        for(int j=i;j<index-1;j++)
        {
            S[j]=S[j+1];
        }
        index--;
    }
    public int getCurI()
    {
        return index;
    }
    public void resetCurI()
    {
        curI=0;
    }

}