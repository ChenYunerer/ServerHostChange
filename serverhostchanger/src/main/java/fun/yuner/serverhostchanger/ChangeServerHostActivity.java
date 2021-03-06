package fun.yuner.serverhostchanger;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * modify server host activity
 * @author ChenYun
 */
public class ChangeServerHostActivity extends Activity {
    private String defaultServerHost;
    private String currentServerHost;
    private TextView defaultServerHostTv, currentServerHostTv;
    private ListView historyLv;
    private EditText ip1Edt, ip2Edt, ip3Edt, ip4Edt, portEdt, serverNameEdt;
    private Button setToDefaultBtn, saveChangesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_server_host);
        defaultServerHost = ServerHostChangeUtil.getDefaultServerHost(this);
        currentServerHost = ServerHostChangeUtil.getCurrentServerHost(this);
        initView();
    }

    private void initView() {
        defaultServerHostTv = findViewById(R.id.default_server_host_tv);
        currentServerHostTv = findViewById(R.id.current_server_host_tv);
        ip1Edt = findViewById(R.id.ip_1_edt);
        ip2Edt = findViewById(R.id.ip_2_edt);
        ip3Edt = findViewById(R.id.ip_3_edt);
        ip4Edt = findViewById(R.id.ip_4_edt);
        portEdt = findViewById(R.id.port_edt);
        serverNameEdt = findViewById(R.id.server_name_edt);
        setToDefaultBtn = findViewById(R.id.set_to_default_btn);
        saveChangesBtn = findViewById(R.id.save_changes_btn);
        historyLv = findViewById(R.id.history_lv);

        defaultServerHostTv.setText(defaultServerHost);
        currentServerHostTv.setText(currentServerHost);

        setToDefaultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToDefault();
            }
        });

        saveChangesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });

        final List<String> historyServerHostList = ServerHostChangeUtil.getHistoryServerHost(this);
        HistoryServerHostListAdapter adapter = new HistoryServerHostListAdapter(this, historyServerHostList);
        historyLv.setAdapter(adapter);
        historyLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ServerHostChangeUtil.setCurrentServerHost(ChangeServerHostActivity.this, historyServerHostList.get(position));
                Toast.makeText(ChangeServerHostActivity.this, "success", Toast.LENGTH_LONG).show();
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }

    /**
     * set current server host to default host
     */
    private void setToDefault() {
        String defaultServerHostStr = ServerHostChangeUtil.getDefaultServerHost(ChangeServerHostActivity.this);
        ServerHostChangeUtil.setCurrentServerHost(ChangeServerHostActivity.this, defaultServerHostStr);
        Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
        setResult(Activity.RESULT_OK);
        finish();
    }

    /**
     * save changes
     */
    private void saveChanges() {
        String schemaStr = "http://";
        String ip1Str = ip1Edt.getText().toString().trim();
        String ip2Str = ip2Edt.getText().toString().trim();
        String ip3Str = ip3Edt.getText().toString().trim();
        String ip4Str = ip4Edt.getText().toString().trim();
        String portStr = portEdt.getText().toString().trim();
        String serverNameStr = serverNameEdt.getText().toString().trim();
        if (ip1Str.equals("") || ip2Str.equals("") || ip3Str.equals("") || ip4Str.equals("")) {
            Toast.makeText(this, "server host error", Toast.LENGTH_LONG).show();
            return;
        }
        String serverHostStr = schemaStr + ip1Str + "." + ip2Str + "." + ip3Str + "." + ip4Str + ":" + portStr + "/" + serverNameStr;
        if (!serverHostStr.endsWith("/")) {
            serverHostStr = serverHostStr + "/";
        }
        Toast.makeText(this, serverHostStr, Toast.LENGTH_LONG).show();
        ServerHostChangeUtil.setCurrentServerHost(this, serverHostStr);
        Toast.makeText(this, "success", Toast.LENGTH_LONG).show();
        setResult(Activity.RESULT_OK);
        finish();
    }
}
