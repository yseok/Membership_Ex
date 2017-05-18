package com.yuseok.android.membership_ex;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editId, editPw;
    Button btnLogin, btnJoin;

    Intent intent;

    String chId, chPw;

    // Dialog에 사용되는 위젯명
    Dialog dialog;

    EditText editDId, editDPw;
    Button btnDJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editId = (EditText) findViewById(R.id.editId);
        editPw = (EditText) findViewById(R.id.editPw);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnJoin = (Button) findViewById(R.id.btnJoin);
        btnLogin.setOnClickListener(this);
        btnJoin.setOnClickListener(this);
    }

    // ID와 PASSWORD가 일치하는지 확인
    public void LoginSuccess() {

        chId = editId.getText().toString();
        chPw = editPw.getText().toString();

        // ID가 admin이고 PW가 1234일때
        if (chId.equals("admin") && chPw.equals("1234")) {
            // 다음 Activity로 넘어감
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else  {
            // 아닐시에는 Toast를 띄워준다.
            Toast.makeText(this, "아이디 혹은 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
        }
    }

    public void JoinDialog(View v) {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        final View mView = getLayoutInflater().inflate(R.layout.dialog_join, null);

        btnDJoin = (Button) mView.findViewById(R.id.btnDJoin);

        editDId = (EditText) mView.findViewById(R.id.editDId);
        editDPw = (EditText) mView.findViewById(R.id.editDPw);

        final String joinId = editDId.getText().toString();
        final String joinPw = editDPw.getText().toString();

        btnDJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!joinId.equals(null) && !joinPw.equals(null)) {
                    Toast.makeText(MainActivity.this,"회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,"공백을 채워주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                LoginSuccess();
                break;
            case R.id.btnJoin:
                JoinDialog(v);
                break;
        }
    }
}
