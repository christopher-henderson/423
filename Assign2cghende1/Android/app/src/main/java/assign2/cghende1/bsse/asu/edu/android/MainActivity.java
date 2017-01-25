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

    public void finishApp(View view) {
        this.finish();
    }

    /*
    Clicked the button to go to the alert activity.
     */
    @Override
    protected void onStop() {
        android.util.Log.w("Override Stop from:", this.getClass().getSimpleName());
        super.onStop();
    }

    /*
    Clicked the button to go to the alert activity.
     */
    @Override
    protected void onPause() {
        android.util.Log.w("Override Pause from:", this.getClass().getSimpleName());
        super.onStop();
    }

    /*
    When first starting the application.

    Also, after going to back to the home screen, I selected the application
    from the "recent applications" activity.
    */
    @Override
    protected void onStart() {
        android.util.Log.w("Override Start from:", this.getClass().getSimpleName());
        super.onStart();
    }

    /*
    After clicking the "Exit" button which calls this.finish().
     */
    @Override
    protected void onDestroy() {
        android.util.Log.w("Override Destroy from:", this.getClass().getSimpleName());
        super.onDestroy();
    }

    /*
    After going to back to the home screen, I selected the application
    from the "recent applications" activity. Also, when first starting the app.
     */
    @Override
    protected void onResume() {
        android.util.Log.w("Override Resume from:", this.getClass().getSimpleName());
        super.onResume();
    }

    /*
    When first starting the application.

    Also, after going to back to the home screen, I selected the application
    from the "recent applications" activity.
    */
    @Override
    protected void onRestart() {
        android.util.Log.w("Override Restart from:", this.getClass().getSimpleName());
        super.onRestart();
    }
}
