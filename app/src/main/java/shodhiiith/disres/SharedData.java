package shodhiiith.disres;

import android.app.Application;
import java.net.CookieStore;
import java.net.HttpCookie;
/**
 * Created by rajat on 4/13/15.
 */
public class SharedData extends Application {

        private static String app_url;
        private static String cookie;

        @Override
        public void onCreate() {
            super.onCreate();
            app_url="http://disres.pythonanywhere.com/";
            cookie="";
        }

        public static String getCookie() {
            return cookie;
        }

        public static void setCookie(String cookie) {
            SharedData.cookie = cookie;
        }

        public static String getAppUrl() {

            app_url="http://disres.pythonanywhere.com/";
            return app_url;
        }


    }