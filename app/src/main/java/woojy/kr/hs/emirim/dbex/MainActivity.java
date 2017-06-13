package woojy.kr.hs.emirim.dbex;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    class MyDBHelper extends SQLiteOpenHelper{

        public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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
