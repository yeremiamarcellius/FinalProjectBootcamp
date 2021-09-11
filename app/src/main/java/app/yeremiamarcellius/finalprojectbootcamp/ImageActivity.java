package app.yeremiamarcellius.finalprojectbootcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ImageActivity extends AppCompatActivity {

    ImageView image;
    TextView imageName, imageDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        image = findViewById(R.id.image_place);
        imageName = findViewById(R.id.image_name);
        imageDesc = findViewById(R.id.image_desc);

        Image burger = getIntent().getParcelableExtra("image");


        Glide.with(this)
                .load(burger.getImage())
                .into(image);

        imageName.setText(burger.getName());
        imageDesc.setText(burger.getDesc());
    }
}