package com.example.fileselection;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.show_uri);
        button = (Button)findViewById(R.id.getUri);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");  //MIME格式
                startActivityForResult(intent, 0);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Uri uri = data.getData();
            String[] uriPath = uri.getPath().split("raw:"); //分割字串
            if (uri != null){       //判斷是否為空值
                try {               //檢查路徑是否需修改
                    textView.setText(uriPath[1]);
                }
                catch (Exception message){
                    textView.setText(uri.getPath());
                }
            }else{
                textView.setText("無效檔案路徑");
            }
        }else{
            textView.setText("取消選取");
        }
    }
}