package shodhiiith.disres;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.widget.EditText;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import shodhiiith.disres.R;
import com.google.android.gms.maps.MapFragment;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends Activity {


 
    // used to store app title
    private CharSequence mTitle;
    private EditText username,password,usernamereg,passwordreg,emailreg;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String name = "name";
    public static final String pass = "password";
    public static final String cookie = "cookie";
    public static final String namereg = "namereg";
    public static final String passreg = "passwordreg";
    public static final String emailregi = "emailregi";
    public static final String session = "session";
    SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
        username = (EditText)findViewById(R.id.usernametxt);
        password = (EditText)findViewById(R.id.passwordtxt);
        usernamereg = (EditText)findViewById(R.id.usernametxtreg);
        passwordreg = (EditText)findViewById(R.id.passwordtxtreg);
        emailreg = (EditText)findViewById(R.id.emailtxtreg);

	}

    @Override
    protected void onResume() {
        sharedpreferences=getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(cookie))
        {
                Intent i = new Intent(this,shodhiiith.disres.
                        Welcome.class);
                String csrfToken = sharedpreferences.getString(cookie, null);
                String sessionid = sharedpreferences.getString(session, null);
                SharedData.setCookie(csrfToken);
                SharedData.setSessionid(sessionid);
                startActivity(i);
                finish();
        }
        super.onResume();
    }

    public void login(View view){

        String u = username.getText().toString();
        String p = password.getText().toString();
        Editor editor = sharedpreferences.edit();
        editor.putString(name, u);
        editor.putString(pass, p);
        editor.commit();
        String url = SharedData.getAppUrl();
        url = url + "auth/";
        // call AsynTask to perform network operation on separate thread
        if(isConnected()) {
            new HttpAsyncTask()
                    .execute(url);
        }
        else
        {
            Toast.makeText(getBaseContext(), "Something went wrong , Check your network connectivity", Toast.LENGTH_LONG).show();
        }

    }

    private void response(String responsedata){

        JSONObject json;
        JSONArray arr;
        JSONObject jObj = null;
        String status="";
        Log.d("Check Response = ",responsedata);
        try {
           // arr = new JSONArray(responsedata);
            jObj = new JSONObject(responsedata);
            status = jObj.getString("status");
            Log.d("Check status = ",status);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (status.equalsIgnoreCase("invalid credentials")){
            Toast.makeText(getBaseContext(), "Invalid credentials, Try again", Toast.LENGTH_LONG).show();
        }
        else if (status.equalsIgnoreCase("logged in")){
            Toast.makeText(getBaseContext(), "Successful Login", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this,shodhiiith.disres.
                    Welcome.class);
            String csrftoken  = SharedData.getCookie();
            String sessionid = SharedData.getSessionid();
            Editor editor = sharedpreferences.edit();
            editor.putString(cookie, csrftoken);
            editor.putString(session, sessionid);
            editor.commit();
            startActivity(i);
            finish();
        }
        else{
            Toast.makeText(getBaseContext(), "Something went wrong , Check your network connectivity", Toast.LENGTH_LONG).show();
        }

    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
    public static String POST(String url, String u, String p){
        InputStream inputStream = null;
        String result = "";
        try {

            // 1. create HttpClient
            DefaultHttpClient httpclient = new DefaultHttpClient();

            // 2. make POST request to the given URL
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            // 3. build jsonObject
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username", u);
            jsonObject.put("password", p);

            // 4. convert JSONObject to JSON to String
            json = jsonObject.toString();

            // ** Alternative way to convert Person object to JSON string usin Jackson Lib
            // ObjectMapper mapper = new ObjectMapper();
            // json = mapper.writeValueAsString(person);

            // 5. set json to StringEntity
            StringEntity se = new StringEntity(json);
            // 6. set httpPost Entity
            httpPost.setEntity(se);

            // 7. Set some headers to inform server about the type of the content
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

          //  HttpHost proxy = new HttpHost("proxy.iiit.ac.in", 8080);
         //   httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
            // 8. Execute POST request to the given URL
            HttpResponse httpResponse = httpclient.execute(httpPost);

            // 9. receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // 10. convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";



            HttpGet httpGet = new HttpGet(url);

            httpclient.execute(httpGet);

            String csrfToken = new String();
            String sessionid = new String();
            CookieStore cookieStore = httpclient.getCookieStore();
            SharedData.setcookiestore(cookieStore);
            List <Cookie> cookies =  cookieStore.getCookies();
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals("csrftoken")) {
                    csrfToken = cookie.getValue();
                }
                else{
                    sessionid = cookie.getValue();
                }
                Log.d("Domains = " ,cookie.getDomain());
            }
            httpGet.setHeader("Referer", url);
            httpGet.setHeader("X-CSRFToken", csrfToken);
            SharedData.setCookie(csrfToken);
            SharedData.setSessionid(sessionid);


        } catch (Exception e) {
            Log.d("Check InputStream", e.getLocalizedMessage());
        }

        // 11. return result
        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            String u = sharedpreferences.getString(name, null);
            String p = sharedpreferences.getString(pass, null);

            return POST(urls[0],u,p);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            response(result);
        }
    }
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public void register(View view){

        String u = usernamereg.getText().toString();
        String e = emailreg.getText().toString();
        String p = passwordreg.getText().toString();
        Editor editor = sharedpreferences.edit();
        editor.putString(namereg, u);
        editor.putString(passreg, p);
        editor.putString(emailregi, e);
        editor.commit();
        String url = SharedData.getAppUrl();
        url = url + "users/";
        // call AsynTask to perform network operation on separate thread
       // new HttpAsyncTask()
         //       .execute(url);
        Log.v("email =", e);
        Log.v("password =", p);
        Log.v("uswr =", u);


    }



}
