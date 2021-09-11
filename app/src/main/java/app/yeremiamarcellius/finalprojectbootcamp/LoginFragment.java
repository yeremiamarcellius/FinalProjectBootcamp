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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {

    TextView createAccountText;
    Button login;
    String email, password;
    EditText emailInput, passwordInput;

    SharedPreferences sp;

    View.OnClickListener toRegister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new RegisterFragment())
                    .addToBackStack(null)
                    .commit();
        }
    };

    private FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createAccountText = view.findViewById(R.id.create_account_text);
        createAccountText.setOnClickListener(toRegister);
        login = view.findViewById(R.id.login_btn);
        emailInput = view.findViewById(R.id.email_et);
        passwordInput = view.findViewById(R.id.password_et);
        auth = FirebaseAuth.getInstance();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();

                sp = getActivity().getSharedPreferences(email, Context.MODE_PRIVATE);

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(requireActivity(), "Login Success", Toast.LENGTH_SHORT).show();

//                                    SharedPreferences.Editor editor = sp.edit();
//                                    editor.putString("email", email);
//                                    editor.apply();

                                    User user = new User(email, password, "");
                                    Intent toMain = new Intent(requireActivity(), MainActivity.class);
                                    toMain.putExtra("email", user);
                                    startActivity(toMain);
                                } else {
                                    Toast.makeText(requireActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}