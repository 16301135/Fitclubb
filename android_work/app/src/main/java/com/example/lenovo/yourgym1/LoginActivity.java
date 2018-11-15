package com.example.lenovo.yourgym1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button mLoginButton;
    private TextView mRegisterTV;
    private EditText mUsername;
    private EditText mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //无title
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏
        //getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLoginButton = (Button)findViewById(R.id.login_button);
        mUsername = findViewById(R.id.username_login);
        mPassword = findViewById(R.id.password_login);

        //登录进入主界面
        mLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //获取用户名和密码，判断格式是否正确
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

//                判断输入的内容是否为phone
//                boolean b = isPhoneNumber(mobile);

                if (username.isEmpty() || password.isEmpty()) {
                    Toast toast = Toast.makeText(LoginActivity.this, "用户名/密码不能为空", Toast.LENGTH_SHORT);
                    LinearLayout layout = (LinearLayout) toast.getView();
                    layout.setBackgroundColor(Color.parseColor("#EEEEEE"));  //设置toast的背景颜色
                    toast.setGravity(Gravity.CENTER, 0, 0);//设置toast位置
                    toast.show();
                } else if (password.length() < 6) {
                    Toast toast = Toast.makeText(LoginActivity.this, "密码不能少于六位数", Toast.LENGTH_SHORT);
                    LinearLayout layout = (LinearLayout) toast.getView();
                    layout.setBackgroundColor(Color.parseColor("#EEEEEE"));  //设置toast的背景颜色
                    toast.setGravity(Gravity.CENTER, 0, 0);//设置toast位置
                    toast.show();
                } else {
                    //login(username, password);
                    //把数据转化成一个json对象传给服务器的数据库，服务器返回成功或者失败的消息
                    GetRequest_Interface request = Client.retrofit.create(GetRequest_Interface.class);
                    Call<Reception> call = request.login(username,password);
                    call.enqueue(new Callback<Reception>(){
                                     @Override
                                     public void onResponse(Call<Reception> call, Response<Reception> response) {
                                         //这里的response.body()返回的就是定义的Reception
                                         if (response.body().getStatus() == 1) {

                                                        Client.thisname = response.body().toString();

                                                        Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                                        startActivity(intent);
                                            // toast.show();
                                             // Toast.makeText(RegisterActivity.this, response.body().toString() + " registed successfully!", Toast.LENGTH_SHORT).show();
                                         } else if (response.body().getStatus() == 2){
                                             Toast toast = Toast.makeText(LoginActivity.this, "该用户名不存在，请先注册！", Toast.LENGTH_SHORT);
                                             LinearLayout layout = (LinearLayout) toast.getView();
                                             layout.setBackgroundColor(Color.parseColor("#EEEEEE"));  //设置toast的背景颜色
                                             toast.setGravity(Gravity.CENTER, 0, -50);

                                             toast.show();
                                             //Toast.makeText(RegisterActivity.this, response.body().toString() + " already taken", Toast.LENGTH_SHORT).show();
                                         }else{  //此时错误码为0
                                             Toast toast = Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT);
                                             LinearLayout layout = (LinearLayout) toast.getView();
                                             layout.setBackgroundColor(Color.parseColor("#EEEEEE"));  //设置toast的背景颜色
                                             toast.setGravity(Gravity.CENTER, 0, -50);
//                TextView view1 = (TextView) toast.getView().findViewById(android.R.id.toast_message); //toast显示的文本内容
//                view1.setTextColor(Color.GREEN);   //设置toast的字体颜色
//                view1.setTextSize(20);
                                             toast.show();
                                             //Toast.makeText(RegisterActivity.this, response.body().toString() + " already taken", Toast.LENGTH_SHORT).show();
                                         }
                                     }
                                     @Override
                                     public void onFailure(Call<Reception> call, Throwable t){
                                         Log.d("Error", t.getMessage());
                                         Toast.makeText(LoginActivity.this, "Error Registering!", Toast.LENGTH_SHORT).show();
                                     }
                                 }
                    );



                }



            }
        });

        //跳转注册界面
        mRegisterTV = (TextView) findViewById(R.id.register_tv);
        mRegisterTV.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//设置下划线

        mRegisterTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
            }
        });


    }
}
