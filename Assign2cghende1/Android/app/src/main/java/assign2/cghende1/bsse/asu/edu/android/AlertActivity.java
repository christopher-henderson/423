package assign2.cghende1.bsse.asu.edu.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

public class AlertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert2);

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.x = -10;
        params.height = 1000;
        params.width = 1000;
        params.y = -10;

        this.getWindow().setAttributes(params);
    }

    public void goHome(View view) {
        finish();
    }

    /*
    Clicked the "OK" button which triggered finish(), and thus onStop.
     */
    @Override
    protected void onStop() {
        android.util.Log.w("Override Stop from:", this.getClass().getSimpleName());
        super.onStop();
    }

    /*
    Clicked the 'home' button to get this to log.
     */
    @Override
    protected void onPause() {
        android.util.Log.w("Override Pause from:", this.getClass().getSimpleName());
        super.onStop();
    }

    /*
    When coming to the activity from the MainActivity.
    */
    @Override
    protected void onStart() {
        android.util.Log.w("Override Start from:", this.getClass().getSimpleName());
        super.onStart();
    }

    /*
    Clicked the "OK" button which triggered finish(), and thus onDestroy.
     */
    @Override
    protected void onDestroy() {
        android.util.Log.w("Override Destroy from:", this.getClass().getSimpleName());
        super.onDestroy();
    }

    /*
    When coming to the activity from the MainActivity.
    */
    @Override
    protected void onResume() {
        android.util.Log.w("Override Resume from:", this.getClass().getSimpleName());
        super.onResume();
    }

    /*
    After going to back to the home screen, I selected the application
    from the "recent applications" activity.
    */
    @Override
    protected void onRestart() {
        android.util.Log.w("Override Restart from:", this.getClass().getSimpleName());
        super.onRestart();
    }
}
