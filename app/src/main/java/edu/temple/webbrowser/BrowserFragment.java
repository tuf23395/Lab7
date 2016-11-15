package edu.temple.webbrowser;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class BrowserFragment extends Fragment {

    browserInterface activity;

    private String CurrentURL = "";

    public BrowserFragment() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Activity c) {
        super.onAttach(c);
        activity = (browserInterface) c;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_browser, container, false);
        Bundle args = getArguments();
        if(args != null){
            CurrentURL = (String) args.get("urlData");
        }
        if(CurrentURL=="")
        {
            CurrentURL="http://www.google.com";
        }
        WebView view = (WebView) v.findViewById(R.id.webView);
        view.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
                newURL(url);
            }
        });
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(CurrentURL);

        return v;
    }
    public void changeURL(View v, String url){
        if(!url.startsWith("www.")&& !url.startsWith("http://")){
            url = "www."+url;
        }
        if(!url.startsWith("http://")){
            url = "http://"+url;
        }
        CurrentURL = url;
        WebView view = (WebView) v.findViewById(R.id.webView);
        view.loadUrl(CurrentURL);
    }
    public String getURL(){
        return CurrentURL;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
    public void newURL(String url){
        CurrentURL = url;
        activity.urlChange(url);
    }

    public interface browserInterface {
        public void urlChange(String newURL);
    }

}
