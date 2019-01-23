package fun.yuner.serverhostchanger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * modify server host util
 *
 * @author ChenYun
 */
public class ServerHostChangeUtil {
    public static final String SP_NAME = "SERVER_HOST_CHANGE_SP";
    public static final String SP_DEFAULT_SERVER_HOST_KEY = "SP_DEFAULT_SERVER_HOST_KEY";
    public static final String SP_CURRENT_SERVER_HOST_KEY = "SP_CURRENT_SERVER_HOST_KEY";
    public static final String SP_HISTORY_SERVER_HOST_KEY = "SP_HISTORY_SERVER_HOST_KEY";
    private static String currentServerHost;

    /**
     * init ServerHostChangerUtil with context and default server host
     *
     * @param context        context
     * @param defaultBaseUrl default base url
     */
    public static void init(Context context, String defaultBaseUrl) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_DEFAULT_SERVER_HOST_KEY, defaultBaseUrl);
        editor.apply();
    }

    /**
     * get default server host
     *
     * @param context context
     * @return default server host
     */
    public static String getDefaultServerHost(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sp.getString(SP_DEFAULT_SERVER_HOST_KEY, "");
    }

    /**
     * set current server host
     *
     * @param context           context
     * @param currentServerHost current server host
     */
    public static void setCurrentServerHost(Context context, String currentServerHost) {
        //save current server host
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_CURRENT_SERVER_HOST_KEY, currentServerHost);
        editor.commit();
        ServerHostChangeUtil.currentServerHost = null;
        //save history server host
        String historyServerHostJsonArrayStr = sp.getString(SP_HISTORY_SERVER_HOST_KEY, "");
        try {
            JSONArray historyServerHostJsonArray;
            if (historyServerHostJsonArrayStr.isEmpty()) {
                historyServerHostJsonArray = new JSONArray();
                historyServerHostJsonArray.put(currentServerHost);
                editor.putString(SP_HISTORY_SERVER_HOST_KEY, historyServerHostJsonArray.toString());
                editor.commit();
            } else {
                historyServerHostJsonArray = new JSONArray(historyServerHostJsonArrayStr);
                boolean contains = false;
                for (int i = 0; i < historyServerHostJsonArray.length(); i++) {
                    if (historyServerHostJsonArray.get(i).equals(currentServerHost)) {
                        contains = true;
                    }
                }
                if (!contains) {
                    historyServerHostJsonArray.put(currentServerHost);
                    editor.putString(SP_HISTORY_SERVER_HOST_KEY, historyServerHostJsonArray.toString());
                    editor.commit();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * get current server host
     *
     * @param context content
     * @return current server host
     */
    public static String getCurrentServerHost(Context context) {
        if (currentServerHost == null || currentServerHost.isEmpty()) {
            SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
            currentServerHost = sp.getString(SP_CURRENT_SERVER_HOST_KEY, "");
        }
        if (currentServerHost.isEmpty()) {
            currentServerHost = getDefaultServerHost(context);
        }
        return currentServerHost;
    }

    /**
     * get history server host
     *
     * @param context content
     * @return history serve host list
     */
    public static List<String> getHistoryServerHost(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        String historyServerHostJsonStr = sp.getString(SP_HISTORY_SERVER_HOST_KEY, "");
        List<String> historyServerHostList = new ArrayList<>();
        try {
            JSONArray historyServerHostJsonArray = new JSONArray(historyServerHostJsonStr);
            for (int i = 0; i < historyServerHostJsonArray.length(); i++) {
                String historyServerHostStr = historyServerHostJsonArray.getString(i);
                historyServerHostList.add(historyServerHostStr);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return historyServerHostList;
        }
        return historyServerHostList;
    }

    /**
     * start modify server host activity
     *
     * @param context     activity
     * @param requestCode request code
     */
    public static void startChangeServerHostActivity(Activity context, int requestCode) {
        context.startActivityForResult(new Intent(context, ChangeServerHostActivity.class), requestCode);
    }
}
