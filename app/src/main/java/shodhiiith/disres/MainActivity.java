package shodhiiith.disres;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
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

import shodhiiith.disres.R;
import com.google.android.gms.maps.MapFragment;

public class MainActivity extends Activity {


 
    // used to store app title
    private CharSequence mTitle;
    private EditText username,password;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String name = "";
    public static final String pass = "";
    public String cookie = "";
    SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
        username = (EditText)findViewById(R.id.usernametxt);
        password = (EditText)findViewById(R.id.passwordtxt);

	}

    @Override
    protected void onResume() {
        sharedpreferences=getSharedPreferences(MyPREFERENCES,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(name))
        {
            if(sharedpreferences.contains(pass)){
                Intent i = new Intent(this,shodhiiith.disres.
                        Welcome.class);
                startActivity(i);
            }
        }
        super.onResume();
    }

    public void login(View view){
        Editor editor = sharedpreferences.edit();
        String u = username.getText().toString();
        String p = password.getText().toString();
        editor.putString(name, u);
        editor.putString(pass, p);
        editor.commit();
        Intent i = new Intent(this,shodhiiith.disres.
                Welcome.class);
        startActivity(i);
    }
}
