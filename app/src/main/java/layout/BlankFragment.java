package layout;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.farameo.loginfacebook.MainActivity;
import com.farameo.loginfacebook.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {
    LoginButton loginButton;

    TextView tvEmail;
    TextView tvUsuario;
    TextView tvFechaNacimiento;

    CallbackManager callbackManager;
    Context context;
    Profile profile;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        context = getContext();

        tvEmail = (TextView) view.findViewById(R.id.tvEmail);
        tvUsuario = (TextView) view.findViewById(R.id.tvUsuario);
        tvFechaNacimiento = (TextView) view.findViewById(R.id.tvFechaNacimiento);

        loginButton = (LoginButton) view.findViewById(R.id.btnLoginFacebook);
        loginButton.setReadPermissions("email");
        loginButton.setFragment(this);

        callbackManager  = CallbackManager.Factory.create();
        
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(context, "OK logueado", Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        profile   = Profile.getCurrentProfile();
        tvUsuario.setText(profile.getFirstName());
        tvEmail.setText(profile.getId());
        tvFechaNacimiento.setText(profile.getName());
    }
}

