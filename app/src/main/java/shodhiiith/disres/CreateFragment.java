package shodhiiith.disres;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import shodhiiith.disres.R;

public class CreateFragment extends Fragment {

	public CreateFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_create, container, false);

		return rootView;
	}

}
