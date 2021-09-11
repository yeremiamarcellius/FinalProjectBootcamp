package app.yeremiamarcellius.finalprojectbootcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("picture");
    ImageView imagePlace;
    Image burger;
    Button profileBtn;
    User user;

    View.OnClickListener toImageDesc = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent toImageActivity = new Intent(MainActivity.this, ImageActivity.class);
            toImageActivity.putExtra("image", burger);
            startActivity(toImageActivity);
        }
    };

    View.OnClickListener toProfile = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent Profile = new Intent( MainActivity.this, ProfileActivity.class);
            Profile.putExtra("email", user);
            startActivity(Profile);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = getIntent().getParcelableExtra("email");

        imagePlace = findViewById(R.id.burger);
        profileBtn = findViewById(R.id.profile_btn);

        imagePlace.setOnClickListener(toImageDesc);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                burger = dataSnapshot.getValue(Image.class);

                Glide.with(MainActivity.this)
                        .load(burger.getImage())
                        .into(imagePlace);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });



        profileBtn.setOnClickListener(toProfile);

    }
}