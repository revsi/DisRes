package shodhiiith.disres;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.MarkerOptions;
import shodhiiith.disres.R;

public class Report extends Fragment {

    int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    Button btnSelect, btnsubmit;
    ImageView ivImage;
    Bitmap bm;
    Spinner spinner;
    File f;
    String tempPath="";

    public Report() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.report, container, false);

        super.onCreate(savedInstanceState);

        spinner = (Spinner) rootView.findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                R.array.disasters, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        btnSelect = (Button) rootView.findViewById(R.id.btnSelectPhoto);
        btnSelect.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        btnsubmit = (Button) rootView.findViewById(R.id.btnreport);
        btnsubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                submitReport();
            }
        });
        ivImage = (ImageView) rootView.findViewById(R.id.ivImage);
		return rootView;
	}

    public void submitReport(){
        String selected = spinner.getSelectedItem().toString();
        EditText obsdata = (EditText) getActivity().findViewById(R.id.obs);
        String observation = obsdata.getText().toString();
        String imgurl = "";
        if(!tempPath.equals("")){
            imgurl = tempPath;
            if(isConnected()){
                Toast.makeText(getActivity(), "Sending Report . . . " , Toast.LENGTH_LONG).show();
                ///async to send
            }
            else{
                Toast.makeText(getActivity(), "Check your Network connectivity and Try Again" , Toast.LENGTH_SHORT).show();

            }
        }
        else{
            Toast.makeText(getActivity(), "Please select Photo" , Toast.LENGTH_SHORT).show();
        }
        Log.v("check selected = ", selected);
        Log.v("check observation = ", observation);
        Log.v("check imgurl = ", imgurl);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Gallery",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment
                            .getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(
                            Intent.createChooser(intent, "Select File"),
                            SELECT_FILE);
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                f = new File(Environment.getExternalStorageDirectory()
                        .toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {

                    BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                    tempPath = f.getAbsolutePath();
                    bm = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            btmapOptions);
                    Bitmap d = new BitmapDrawable(getActivity().getResources() , f.getAbsolutePath()).getBitmap();
                   // int nh = (int) ( d.getHeight() * (512.0 / d.getWidth()) );
                    double xFactor = 0;
                    double width = Double.valueOf(d.getWidth());
                    Log.v("check WIDTH", String.valueOf(width));
                    double height = Double.valueOf(d.getHeight());
                    Log.v("check height", String.valueOf(height));
                    if(width>height){
                        xFactor = 841/width;
                    }
                    else{
                        xFactor = 595/width;
                    }
                    int Nheight = (int) ((xFactor*height));
                    int NWidth =(int) (xFactor * width) ;
                    bm = Bitmap.createScaledBitmap( bm,NWidth, Nheight, true);
                    ivImage.setImageBitmap(bm);

                   /* String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream fOut = null;
                    File file = new File(path, String.valueOf(System
                            .currentTimeMillis()) + ".jpg");
                    try {
                        fOut = new FileOutputStream(file);
                        bm.compress(Bitmap.CompressFormat.JPEG, 70, fOut);
                        fOut.flush();
                        fOut.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();

                tempPath = getPath(selectedImageUri, this.getActivity());
                Bitmap bm;
                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
                Bitmap d = new BitmapDrawable(getActivity().getResources() , tempPath).getBitmap();
                // int nh = (int) ( d.getHeight() * (512.0 / d.getWidth()) );
                double xFactor = 0;
                double width = Double.valueOf(d.getWidth());
                Log.v("check WIDTH", String.valueOf(width));
                double height = Double.valueOf(d.getHeight());
                Log.v("check height", String.valueOf(height));
                if(width>height){
                    xFactor = 841/width;
                }
                else{
                    xFactor = 595/width;
                }
                int Nheight = (int) ((xFactor*height));
                int NWidth =(int) (xFactor * width) ;
                bm = Bitmap.createScaledBitmap( bm,NWidth, Nheight, true);
                ivImage.setImageBitmap(bm);
            }
        }
    }

    public String getPath(Uri uri, Activity activity) {
        String[] projection = { MediaStore.MediaColumns.DATA };
        Cursor cursor = activity
                .managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    private LatLng getCurrentPosition(){
        // location details
        LocationManager locationManager     = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria   = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, false);

        Location location   = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        LatLng posLatLon    = new LatLng(location.getLatitude(), location.getLongitude());
        Toast.makeText(getActivity(), "position: " + location.getLatitude() + "," + location.getLongitude(), Toast.LENGTH_SHORT).show();
        return posLatLon;
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
}
