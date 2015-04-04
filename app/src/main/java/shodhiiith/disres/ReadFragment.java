package shodhiiith.disres;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shodhiiith.disres.R;

public class ReadFragment extends Fragment {

    private MapView mMapView;
    private GoogleMap mMap;
    private Bundle mBundle;

    private List<String> buildingTypes = Arrays.asList("Hospital", "Fire Brigades", "Rescue");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_read, container, false);

        MapsInitializer.initialize(getActivity());

        mMapView = (MapView) inflatedView.findViewById(R.id.mapview);
        mMapView.onCreate(mBundle);
        setUpMapIfNeeded(inflatedView);

        return inflatedView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBundle = savedInstanceState;
    }

    private void setUpMapIfNeeded(View inflatedView) {
        if (mMap == null) {
            mMap = ((MapView) inflatedView.findViewById(R.id.mapview)).getMap();
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                mMap.setBuildingsEnabled(true);
                mMap.setTrafficEnabled(true);
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        /*start temp locations*/
        Address spot1 = new Address(null);
        spot1.setLatitude(17.4456);
        spot1.setLongitude(78.3497);
        Address spot2 = new Address(null);
        spot2.setLatitude(17.4456);
        spot2.setLongitude(78.3427);
        Address spot3 = new Address(null);
        spot3.setLatitude(17.44219);
        spot3.setLongitude(78.3587);
        Address spot4 = new Address(null);
        spot4.setLatitude(17.4502415);
        spot4.setLongitude(78.364239);
        List<Address> affectedAreas = new ArrayList<Address>();
        affectedAreas.add(spot1);
        affectedAreas.add(spot2);
        List<Address> healthCare = new ArrayList<Address>();
        healthCare.add(spot3);
        healthCare.add(spot4);
        Map<String, List<Address>> hotSpots = new HashMap<String, List<Address>>();
        hotSpots.put("Affected", affectedAreas);
        hotSpots.put("Hospital", healthCare);
        /*end tmp locations*/
        //Toast.makeText(getApplicationContext(), hotSpots.values().toString(), Toast.LENGTH_LONG).show();
        for (Map.Entry<String, List<Address>> entry : hotSpots.entrySet()) {
            String key = entry.getKey();
            List<Address> value = entry.getValue();
            for (Address location : value) {
                Marker place = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(location.getLatitude(), location.getLongitude()))
                        .title(key)
                        .alpha(0.7f));
                if(key == "Affected") {
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 14.0f);
                    mMap.moveCamera(cameraUpdate);
                }
                if (!buildingTypes.contains(key)) {
                    CircleOptions circleOptions = new CircleOptions()
                            .center(new LatLng(location.getLatitude(), location.getLongitude()));
                    circleOptions.fillColor(Color.argb(80, 255, 0, 0));
                    circleOptions.strokeColor(Color.argb(80, 255, 0, 0));
                    circleOptions.strokeWidth(3);
                    circleOptions.radius(500);
                    mMap.addCircle(circleOptions);
                } else {
                    //color hospitals and others
                    place.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                }
            }
        }
        if(mMap.isMyLocationEnabled()){
            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                //Toast.makeText(getApplicationContext(), .toString(), Toast.LENGTH_LONG).show();
                @Override
                public void onMyLocationChange(Location arg0) {
                    // TODO Auto-generated method stub
                    //CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(arg0.getLatitude(), arg0.getLongitude()), 14.0f);
                    //mMap.moveCamera(cameraUpdate);
                    //mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("It's Me!"));
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }
}