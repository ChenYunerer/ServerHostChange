package fun.yuner.serverhostchange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fun.yuner.serverhostchanger.ServerHostChangeUtil;

public class MainActivity extends AppCompatActivity {
    private static final int CHANGE_SERVER_HOST_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServerHostChangeUtil.init(this, "baseUrl");
        Button changeServerHostBtn = findViewById(R.id.change_server_host_btn);
        changeServerHostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start modify activity
                ServerHostChangeUtil.startChangeServerHostActivity(MainActivity.this, CHANGE_SERVER_HOST_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHANGE_SERVER_HOST_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            //get current url
            String currentBaseUrl = ServerHostChangeUtil.getCurrentServerHost(MainActivity.this);
            //In order for the changes to take effect sometimes you should
            //restart app like : System.exit(0);
        }
    }
}
