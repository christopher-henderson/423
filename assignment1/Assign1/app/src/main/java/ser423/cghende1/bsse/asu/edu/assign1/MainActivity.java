package ser423.cghende1.bsse.asu.edu.assign1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlaceDescription pd = new PlaceDescription(
                "home", "This is my house", "Residential", "this is the address title",
                "6934 East Ventana Ave, Mesa AZ 85212", 2000.5, -20, 20
        );
        TextView pdText = (TextView) findViewById(R.id.placeDescription);
        pdText.setText(pd.toJSON());
    }
}
