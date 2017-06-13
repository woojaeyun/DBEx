package woojy.kr.hs.emirim.dbex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button binit,bins,bsel;
    EditText editName,editCount,editrCount,editrName;
    MyDBHelper myHelper;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binit =(Button)findViewById(R.id.but_init);
        bins =(Button)findViewById(R.id.but_insert);
        bsel =(Button)findViewById(R.id.but_select);
        editName = (EditText)findViewById(R.id.edit_group_name);
        editCount = (EditText)findViewById(R.id.edit_count);
        editrCount = (EditText)findViewById(R.id.edit_result_count);
        editrName = (EditText)findViewById(R.id.edit_result_name);

        //DB 생성
        myHelper = new MyDBHelper(this);
        binit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB=myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();
            }
        });
        bins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB=myHelper.getWritableDatabase();
                String sql="insert into playerTalbe values('"+editName.getText()+"',"+editCount.getText()+")";
                sqlDB.execSQL(sql);
                sqlDB.close();
                Toast.makeText(MainActivity.this,"저장완료",Toast.LENGTH_LONG).show();
            }
        });
    }
    class MyDBHelper extends SQLiteOpenHelper{

        public MyDBHelper(Context context) {
            super(context, "playerDB", null, 1);
        } //DB 생성부분
        //player 라는 이름의 테이블 생성
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String sql="create table playerTable(playerName text not null primary key, playerCount integer)";
            sqLiteDatabase.execSQL(sql);
        }
        // 이미 테이블 이 존재하면 기존의 테이블을 삭제하고 새로 테이블 만들 때 호출
        @Override
        public void onUpgrade(SQLiteDatabase sqlLiteDataBase, int i, int i1) {
            String sql="drop table if exist playerTable"; // 만약 플레이어 테이블 존재시 삭제
            sqlLiteDataBase.execSQL(sql);
            onCreate(sqlLiteDataBase);
        }
    }
}
