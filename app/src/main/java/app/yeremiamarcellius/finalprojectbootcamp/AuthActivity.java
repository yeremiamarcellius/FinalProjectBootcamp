package app.yeremiamarcellius.finalprojectbootcamp;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frame_container, new LoginFragment())
                .commit();
    }
}