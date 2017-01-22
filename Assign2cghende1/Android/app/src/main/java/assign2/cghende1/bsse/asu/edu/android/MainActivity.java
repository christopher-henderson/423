package assign2.cghende1.bsse.asu.edu.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void alert(View view) {
        Intent intent = new Intent(this, AlertActivity.class);
        startActivity(intent);
    }

    /*
    Clicked the 'home' button to get this to log.
     */
    @Override
    protected void onStop() {
        android.util.Log.w("Override Stop", this.getClass().getSimpleName());
        super.onStop();
    }

    /*
    Clicked the 'home' button to get this to log.
     */
    @Override
    protected void onPause() {
        android.util.Log.w("Override Pause", this.getClass().getSimpleName());
        super.onStop();
    }

    /*
    When first starting the application.

    Also, after going to back to the home screen, I selected the application
    from the "recent applications" activity.
    */
    @Override
    protected void onStart() {
        android.util.Log.w("Override Start", this.getClass().getSimpleName());
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        android.util.Log.w("Override Destroy", this.getClass().getSimpleName());
        super.onDestroy();
    }

    /*
    After going to back to the home screen, I selected the application
    from the "recent applications" activity.
     */
    @Override
    protected void onResume() {
        android.util.Log.w("Override Resume", this.getClass().getSimpleName());
        super.onResume();
    }

    /*
    When first starting the application.

    Also, after going to back to the home screen, I selected the application
    from the "recent applications" activity.
    */
    @Override
    protected void onRestart() {
        android.util.Log.w("Override Restart", this.getClass().getSimpleName());
        super.onRestart();
    }
}
