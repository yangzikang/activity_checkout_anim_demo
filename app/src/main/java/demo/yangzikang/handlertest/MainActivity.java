package demo.yangzikang.handlertest;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.text);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,JumpedActivity.class);
                startActivity(intent,ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, imageView, "change_view").toBundle());
                //overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();

        String path = "https://www.baidu.com";

        /*
        new NetUtils().doGetRequest(path, new NetUtils.IResponse() {
            @Override
            public void onResponse(String jsonString) {
                textView.setText(jsonString);
            }
        });
        */

    }
}
