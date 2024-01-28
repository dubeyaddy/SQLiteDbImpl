package com.practiceapp.sqlitedb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,contact,dob;
    Button insert,update,view,delete;
    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.et_name);
        contact=findViewById(R.id.et_contact);
        dob=findViewById(R.id.et_dob);

        insert=findViewById(R.id.bt_insert);
        update=findViewById(R.id.bt_update);
        delete=findViewById(R.id.bt_delete);
        view=findViewById(R.id.bt_view);
        DB=new DbHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt=name.getText().toString();
                String contacttxt=contact.getText().toString();
                String dobtxt=dob.getText().toString();

                Boolean checkinsertdata = DB.insertstudentdata(nametxt,contacttxt,dobtxt);
                if (checkinsertdata==true)
                    Toast.makeText(MainActivity.this,"New Entry Inserted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"New Entry cannot be inserted",Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt=name.getText().toString();
                String contacttxt=contact.getText().toString();
                String dobtxt=dob.getText().toString();

                Boolean checkupdatedata = DB.updatestudentdata(nametxt,contacttxt,dobtxt);
                if (checkupdatedata==true)
                    Toast.makeText(MainActivity.this,"Entry updated",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Entry cannot be updated",Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt=name.getText().toString();

                Boolean checkdeletedata = DB.Deletestudentdata(nametxt);
                if (checkdeletedata==true)
                    Toast.makeText(MainActivity.this,"Entry deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Entry cannot be deleted",Toast.LENGTH_SHORT).show();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res= DB.getstudentdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No Entry Exist",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();

                while  (res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Contact :"+res.getString(1)+"\n");
                    buffer.append("DateofBirth :"+res.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}