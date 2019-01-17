package fun.yuner.serverhostchanger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * modify server host util
 * @author ChenYun
 */
public class ServerHostChangeUtil {
    public static final String SP_NAME = "SERVER_HOST_CHANGE_SP";
    public static final String SP_DEFAULT_SERVER_HOST_KEY = "SP_DEFAULT_SERVER_HOST_KEY";
    public static final String SP_CURRENT_SERVER_HOST_KEY = "SP_CURRENT_SERVER_HOST_KEY";
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
        SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(SP_CURRENT_SERVER_HOST_KEY, currentServerHost);
        editor.commit();
        ServerHostChangeUtil.currentServerHost = null;
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
     * start modify server host activity
     *
     * @param context     activity
     * @param requestCode request code
     */
    public static void startChangeServerHostActivity(Activity context, int requestCode) {
        context.startActivityForResult(new Intent(context, ChangeServerHostActivity.class), requestCode);
    }
}
