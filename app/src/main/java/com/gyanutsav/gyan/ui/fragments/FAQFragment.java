package com.gyanutsav.gyan.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.viewmodels.FAQViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FAQFragment extends Fragment {
    @BindView(R.id.webview)
    WebView webView;
    private FAQViewModel FAQViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       /* FAQViewModel =
                ViewModelProviders.of(this).get(FAQViewModel.class);
       */ View root = inflater.inflate(R.layout.fragment_faq, container, false);
        ButterKnife.bind(this,root);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://www.gyanutsav.com/Gyanutsav/faq");

        /*webView.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int progress)
            {
                if(progress>50)
                    setTitle(R.string.app_name);
            }
        });*/
        return root;
    }
}