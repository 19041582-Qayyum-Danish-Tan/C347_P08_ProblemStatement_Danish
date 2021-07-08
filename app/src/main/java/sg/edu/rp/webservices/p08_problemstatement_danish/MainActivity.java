package sg.edu.rp.webservices.p08_problemstatement_danish;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    Button btnNorth, btnCentral, btnEast;
    Spinner spinner;
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.location_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }

                LatLng North = new LatLng(1.4299234813852086, 103.78000711086116);
                Marker north = map.addMarker(new
                        MarkerOptions()
                        .position(North)
                        .title("North - HQ:")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng Central = new LatLng(1.3050457461687082, 103.83215548202445);;
                Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(Central)
                        .title("Central:")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng East = new LatLng(1.3490860904004773, 103.93578603969773);
                Marker east = map.addMarker(new
                        MarkerOptions()
                        .position(East)
                        .title("East:")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\"\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String markerToast = marker.getTitle();
                        Toast.makeText(MainActivity.this, markerToast, Toast.LENGTH_LONG).show();
                        return false;
                    }
                });


            }
        });


        btnNorth = findViewById(R.id.btnNorth);
        btnCentral = findViewById(R.id.btnCentral);
        btnEast = findViewById(R.id.btnEast);

        btnNorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    LatLng North = new LatLng(1.4299234813852086, 103.78000711086116);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(North,
                            15));

                }
            }
        });

        btnCentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    LatLng Central = new LatLng(1.3050457461687082, 103.83215548202445);;
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(Central,
                            15));
                }
            }
        });

        btnEast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    LatLng East = new LatLng(1.3490860904004773, 103.93578603969773);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(East,
                            15));
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i){
                    case 1:
                        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        LatLng North = new LatLng(1.4299234813852086, 103.78000711086116);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(North,15));

                        break;

                    case 2:
                        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        LatLng Central = new LatLng(1.3050457461687082, 103.83215548202445);;
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(Central,15));

                        break;

                    case 3:
                        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        LatLng East = new LatLng(1.3490860904004773, 103.93578603969773);
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(East, 15));

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}