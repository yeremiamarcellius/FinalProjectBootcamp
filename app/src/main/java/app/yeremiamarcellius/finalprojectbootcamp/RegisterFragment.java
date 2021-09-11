package app.yeremiamarcellius.finalprojectbootcamp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment {

    ImageView backBtn;
    Button register;
    String email, password, name, confirmPassword;
    EditText emailInput, passwordInput, nameInput, confirmPasswordInput;

    SharedPreferences sp;

    View.OnClickListener back = new View.OnClickListener(){
      @Override
      public  void onClick(View view){
          getParentFragmentManager()
                  .popBackStack();
      }
    };

    private FirebaseAuth auth;
    View.OnClickListener signUp = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            email = emailInput.getText().toString();
            password = passwordInput.getText().toString();
            name = nameInput.getText().toString();
            confirmPassword = confirmPasswordInput.getText().toString();

            sp = getActivity().getSharedPreferences(email, Context.MODE_PRIVATE);

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isComplete() && name.length() >= 5 && password.equals(confirmPassword) && email.contains("@") && email.endsWith(".com")) {

                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("name", name);
                                editor.putString("email", email);
                                editor.apply();

                                Toast.makeText(requireActivity(), "Register Success", Toast.LENGTH_SHORT).show();

                                auth.signInWithEmailAndPassword(email, password)
                                        .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    User user = new User(email, password, name);
                                                    Intent toMain = new Intent(requireActivity(), MainActivity.class);
                                                    toMain.putExtra("email", user);
                                                    startActivity(toMain);
                                                }
                                            }
                                        });
                            }
                            else Toast.makeText(requireActivity(), "Register Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        backBtn = view.findViewById(R.id.back_btn);
        backBtn.setOnClickListener(back);

        auth = FirebaseAuth.getInstance();
        register = view.findViewById(R.id.register_btn);

        register.setOnClickListener(signUp);
        emailInput = view.findViewById(R.id.email_et);
        passwordInput = view.findViewById(R.id.password_et);
        nameInput = view.findViewById(R.id.name_et);
        confirmPasswordInput = view.findViewById(R.id.confirm_password_et);
    }
}