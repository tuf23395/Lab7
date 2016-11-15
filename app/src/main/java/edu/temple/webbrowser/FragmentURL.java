package edu.temple.webbrowser;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentURL extends Fragment {
    urlInterface activity;
    EditText urlText;


    public FragmentURL() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity c) {
        super.onAttach(c);
        activity = (urlInterface) c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_url, container, false);

        urlText = (EditText) v.findViewById(R.id.editText);

        v.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.setURL(urlText.getText().toString(), null);
            }
        });


        return v;
    }

    public void setURL(View v, String text)
    {
        EditText urlText = (EditText) v.findViewById(R.id.editText);
        urlText.setText(text);
    }

    public interface urlInterface {
        void setURL(String url, BrowserFragment browser);
    }


}
