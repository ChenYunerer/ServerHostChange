package fun.yuner.serverhostchange;

import fun.yuner.serverhostchanger.ServerHostChangeUtil;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ServerHostChangeUtil.init(this, "BASE_URL");
    }
}
