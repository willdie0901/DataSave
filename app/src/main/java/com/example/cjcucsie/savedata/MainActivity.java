package com.example.cjcucsie.savedata;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNew, btnDel, btnAlt;
    private EditText edtID, edtRow1, edtRow2;
    private TextView txt;

    private MyHelper helper;
    private SQLiteDatabase db;
    private Cursor c;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNew = (Button) this.findViewById(R.id.btn_new);
        btnDel = (Button) this.findViewById(R.id.btn_delete);
        btnAlt = (Button) this.findViewById(R.id.btn_alter);
        edtID = (EditText) this.findViewById(R.id.edt1);
        edtRow1 = (EditText) this.findViewById(R.id.edt2);
        edtRow2 = (EditText) this.findViewById(R.id.edt3);
        txt = (TextView) this.findViewById(R.id.txt);
        txt.setMovementMethod(ScrollingMovementMethod.getInstance());

        btnNew.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnAlt.setOnClickListener(this);

        helper = new MyHelper(this, "dbname", null, 1);
        db = helper.getWritableDatabase();

        queryDB();
    }

    private void queryDB() {
        c = db.query("tablename", null, null, null, null, null, null);
        result="_id, row1, row2\n";
        while (c.moveToNext()) {
            result += c.getString(c.getColumnIndex("_id")) + ", "+
                    c.getString(c.getColumnIndex("row1")) + ", "+
                    c.getString(c.getColumnIndex("row2")) +"\n";
        }
        txt.setText(result);
    }


    @Override
    public void onClick(View view) {
        String id = edtID.getText().toString();
        ContentValues values = new ContentValues();
        switch (view.getId()){
            case R.id.btn_new:
                values.put("row1", "Row1");
                values.put("row2", "Row2");
                db.insert("tablename", null, values);
                queryDB();
                break;
            case R.id.btn_delete:
                db.delete("tablename", "_id="+id,null);
                queryDB();
                break;
            case R.id.btn_alter:
                String row1 = edtRow1.getText().toString();
                String row2 = edtRow2.getText().toString();
                values.put("row1", row1);
                values.put("row2", row2);
                db.update("tablename", values, "_id=" + id, null);
                queryDB();
                break;
        }
    }

    @Override
    public void finish() {
        c.close();
        db.close();
        super.finish();
    }
}