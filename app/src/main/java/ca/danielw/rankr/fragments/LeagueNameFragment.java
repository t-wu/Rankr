package ca.danielw.rankr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ca.danielw.rankr.R;
import ca.danielw.rankr.activities.CreateLeagueActivity;

public class LeagueNameFragment extends Fragment {
    private static final String TAG = "LeagueNameFragment";

    private Button nextBtn;
    private EditText etLeagueName;
    private String leagueName;

    private TextView tvWarning;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		/* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.league_name_fragment, container, false);

        nextBtn = (Button) view.findViewById(R.id.btnNext);
        etLeagueName = (EditText) view.findViewById(R.id.etLeagueName);
        tvWarning = (TextView) view.findViewById(R.id.tvWarning);

        etLeagueName.requestFocus();

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateLeagueActivity.mLeagueName = leagueName;

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                transaction.replace(R.id.root_frame, new UsernameFragment());
                transaction.addToBackStack("LeagueName");
                transaction.commit();
            }
        });

        etLeagueName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                leagueName = etLeagueName.getText().toString();

                if (leagueName.isEmpty()){
                    nextBtn.setEnabled(false);
                } else {
                    nextBtn.setEnabled(true);
                }
            }
        });

        return view;
    }
}
