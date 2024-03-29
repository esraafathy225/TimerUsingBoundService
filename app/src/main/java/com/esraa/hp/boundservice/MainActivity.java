package com.esraa.hp.boundservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button bind,unbind,timer;
TextView time;
MyService myService;
boolean status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind=findViewById(R.id.bind);
        unbind=findViewById(R.id.unbind);
        timer=findViewById(R.id.timer);
        time=findViewById(R.id.time);
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this, MyService.class);
                bindService(i,connection, Service.BIND_AUTO_CREATE);
                status=true;
                Toast.makeText(MainActivity.this,"Binded successfully",Toast.LENGTH_SHORT).show();
            }
        });
        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status){
                    unbindService(connection);
                    status=false;
                    Toast.makeText(MainActivity.this,"unBinded successfully",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(MainActivity.this,"already unbinded",Toast.LENGTH_SHORT).show();

                }
            }
        });
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status){
                    String result=myService.getTimestamp();
                    time.setText(result);
                }
                else{
                    Toast.makeText(MainActivity.this,"bind service first",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    public ServiceConnection connection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.MyBinder myBinder= (MyService.MyBinder) iBinder;
            myService=myBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        myService=null;
        }
    };

}
