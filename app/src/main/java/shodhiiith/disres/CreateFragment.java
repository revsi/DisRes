package shodhiiith.disres;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import shodhiiith.disres.R;

public class CreateFragment extends Fragment {

	public CreateFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_create, container, false);
        Button btn = (Button) rootView.findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                earthquake_sos(v);
            }
        });

        Button btn2 = (Button) rootView.findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                flood_sos(v);
            }
        });

        Button btn3 = (Button) rootView.findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                landslide_sos(v);
            }
        });

        Button btn4 = (Button) rootView.findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                fire_sos(v);
            }
        });

        Button btn5 = (Button) rootView.findViewById(R.id.button5);
        btn5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tsunami_sos(v);
            }
        });

        Button btn6 = (Button) rootView.findViewById(R.id.button6);
        btn6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                cyclone_sos(v);
            }
        });




		return rootView;
	}

    public void earthquake_sos(View v)
    {
       /* List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("disaster","Earthquake"));
        parameters.add(new BasicNameValuePair("latitude",    ));
        parameters.add(new BasicNameValuePair("longitude",    ));
        new WebServices("searchOrg", parameters);
        */
        Toast.makeText(getActivity(), "Earthquake SOS sent ", Toast.LENGTH_SHORT).show();
    }
    public void fire_sos(View v)
    {
        /* List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("disaster","Fire"));
        parameters.add(new BasicNameValuePair("latitude",    ));
        parameters.add(new BasicNameValuePair("longitude",    ));
        new WebServices("searchOrg", parameters);
        */
        Toast.makeText(getActivity(), "Fire SOS sent ", Toast.LENGTH_SHORT).show();
    }
    public void landslide_sos(View v)
    {
        /* List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("disaster","Landslide"));
        parameters.add(new BasicNameValuePair("latitude",    ));
        parameters.add(new BasicNameValuePair("longitude",    ));
        new WebServices("searchOrg", parameters);
        */
        Toast.makeText(getActivity(), "Landslide SOS sent ", Toast.LENGTH_SHORT).show();
    }
    public void cyclone_sos(View v)
    {
        /* List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("disaster","Cyclone"));
        parameters.add(new BasicNameValuePair("latitude",    ));
        parameters.add(new BasicNameValuePair("longitude",    ));
        new WebServices("searchOrg", parameters);
        */
        Toast.makeText(getActivity(), "Cyclone SOS sent ", Toast.LENGTH_SHORT).show();
    }
    public void tsunami_sos(View v)
    {
        /* List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("disaster","Tsunami"));
        parameters.add(new BasicNameValuePair("latitude",    ));
        parameters.add(new BasicNameValuePair("longitude",    ));
        new WebServices("searchOrg", parameters);
        */
        Toast.makeText(getActivity(), "Tsunami SOS sent ", Toast.LENGTH_SHORT).show();
    }
    public void flood_sos(View v)
    {
        /* List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("disaster","Flood"));
        parameters.add(new BasicNameValuePair("latitude",    ));
        parameters.add(new BasicNameValuePair("longitude",    ));
        new WebServices("searchOrg" parameters);
        */
        Toast.makeText(getActivity(), "Flood SOS sent ", Toast.LENGTH_SHORT).show();
    }
}
