package app.yeremiamarcellius.finalprojectbootcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.name_place);
        email = findViewById(R.id.email_place);

        User user = getIntent().getParcelableExtra("email");

        SharedPreferences sp = getSharedPreferences(user.getEmail(), MODE_PRIVATE);

        String valueName = sp.getString("name", "");
        String valueEmail = sp.getString("email", "");

        name.setText(valueName);
        email.setText(valueEmail);

    }
}