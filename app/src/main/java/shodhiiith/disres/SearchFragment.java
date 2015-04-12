package shodhiiith.disres;

/**
 * Created by rajat on 4/4/15.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
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

public class SearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        // Toast.makeText(getActivity().getApplicationContext(), "Response :  ", Toast.LENGTH_SHORT).show();
        final Button search = (Button) rootView.findViewById(R.id.button7);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "searching ", Toast.LENGTH_LONG).show();
                List<NameValuePair> parameters = new ArrayList<NameValuePair>();
                EditText searchText = (EditText) getActivity().findViewById(R.id.editText5);
                String  orgName = searchText.getText().toString();
                parameters.add(new BasicNameValuePair("organisation",orgName));
                new WebServices("searchOrg","http://192.168.137.60:9000/tmp", parameters);
            }
        });
        return rootView;
    }

}