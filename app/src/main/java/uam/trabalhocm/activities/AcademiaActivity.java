package uam.trabalhocm.activities;

        import android.support.v4.app.FragmentActivity;
        import android.os.Bundle;

        import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;
        import com.google.android.gms.maps.model.CameraPosition;
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.MarkerOptions;

        import uam.trabalhocm.R;

public class AcademiaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academia);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng anhembiMorumbi = new LatLng(-23.6001012, -46.6769765);
        mMap.addMarker(new MarkerOptions().position(anhembiMorumbi).title("Anhembi Morumbi"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(anhembiMorumbi));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(anhembiMorumbi, 15));


        mMap.animateCamera(CameraUpdateFactory.zoomIn());


        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 6000, null);


        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(anhembiMorumbi)
                .zoom(15)
                .bearing(45)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        mMap.getUiSettings().setZoomControlsEnabled(true);



        LatLng teamNogueira = new LatLng(-23.5999339,-46.6763702);
        mMap.addMarker(new MarkerOptions().position(teamNogueira).title("Team Nogueira"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(teamNogueira));

        LatLng vertical = new LatLng(-23.601695,-46.675762);
        mMap.addMarker(new MarkerOptions().position(vertical).title("Vertical"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vertical));

        LatLng bodyTech = new LatLng(-23.5948884,-46.679117);
        mMap.addMarker(new MarkerOptions().position(bodyTech).title("Bodytech"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bodyTech));



    }
}
