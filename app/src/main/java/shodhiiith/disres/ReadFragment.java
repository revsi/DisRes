package shodhiiith.disres;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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
import com.google.android.gms.maps.MapFragment;
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

import static android.widget.Toast.*;

import shodhiiith.disres.R;

public class ReadFragment extends Fragment {


    public ReadFragment() {
    }



    MapView mapView;
    GoogleMap map;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private List<String> buildingTypes = Arrays.asList("Hospital", "Fire Brigades", "Rescue");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)          {
        View v = inflater.inflate(R.layout.fragment_read, container, false);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this.getActivity());

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.1, -87.9), 10);
        map.animateCamera(cameraUpdate);

        return v;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
      //  setUpMapIfNeeded();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
   /* private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
                mMap.setBuildingsEnabled(true);
                mMap.setTrafficEnabled(true);
                setUpMap();
            }
        }
    }
    private void setUpMap() {
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
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(arg0.getLatitude(), arg0.getLongitude()), 14.0f);
                    mMap.moveCamera(cameraUpdate);
                    //mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("It's Me!"));
                }
            });
        }
    }*/

}