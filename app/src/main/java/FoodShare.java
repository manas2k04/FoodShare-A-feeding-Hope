import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.appcompat.app.AppCompatActivity;
import com.manas.mainactivity.Login;
import com.manas.mainactivity.R;

public class FoodShare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_share);

        // Find the root view of the layout
        View rootView = findViewById(android.R.id.content);

        // Create PropertyValuesHolder for translation in the Z-axis
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat(View.TRANSLATION_Z, 100f);

        // Create ObjectAnimator with translation in the Z-axis
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(rootView, pvhZ);

        // Set the duration of the animation
        animator.setDuration(3000); // 3000 milliseconds (3 seconds)

        // Set the interpolator for smooth acceleration and deceleration
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        // Start the animation
        animator.start();

        // Delay the launch of the second activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the second activity
                startActivity(new Intent(FoodShare.this, Login.class));
                // Finish the current activity
                finish();
            }
        }, 3000); // 3000 milliseconds (3 seconds)
    }
}
