package com.gyanutsav.gyan.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.gyanutsav.gyan.BuildConfig;
import com.gyanutsav.gyan.R;
import com.gyanutsav.gyan.ui.Utils.PrefrenshesManager;
import com.gyanutsav.gyan.ui.fragments.AboutusFragment;
import com.gyanutsav.gyan.ui.fragments.ContactusFragment;
import com.gyanutsav.gyan.ui.fragments.FAQFragment;
import com.gyanutsav.gyan.ui.fragments.GalleryFragment;
import com.gyanutsav.gyan.ui.fragments.HomeFragment;
import com.gyanutsav.gyan.ui.fragments.NotificationFragment;
import com.gyanutsav.gyan.ui.fragments.ProgramFragment;
import com.gyanutsav.gyan.ui.models.MainModel;
import com.gyanutsav.gyan.ui.models.UserProfile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.gyanutsav.gyan.ui.server.Api;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

import java.text.BreakIterator;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActiviy {
    UserProfile profile;
    private AppBarConfiguration mAppBarConfiguration;
    private AppCompatTextView tv_name;
    private CircleImageView iv_dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        profile = PrefrenshesManager.getUserProfile();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        sendtoken();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        iv_dp = view.findViewById(R.id.iv_dp);
        tv_name = view.findViewById(R.id.tv_name);
        Log.e("TAG", ">>>   " + new Gson().toJson(profile));

        view.findViewById(R.id.fl).setOnClickListener(f -> {
            if (profile != null) {
                startActivity(new Intent(this, SignUpActivity.class));
            }

        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_faq,
                R.id.nav_contactus, R.id.nav_aboutus, R.id.nav_program, R.id.nav_notification)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //  NavigationUI.setupWithNavController(toolbar, navController);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
*/

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //   NavigationUI.setupWithNavController(toolbar, navController, mAppBarConfiguration);

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.nav_home:
                    showFragment(new HomeFragment());
                    setTitle(R.string.menu_home);
                    break;

                case R.id.nav_gallery:
                    showFragment(new GalleryFragment());
                    setTitle(R.string.menu_gallery);
                    break;

                case R.id.nav_faq:
                    showFragment(new FAQFragment());
                    setTitle(R.string.menu_faq);
                    break;

                case R.id.nav_contactus:
                    showFragment(new ContactusFragment());
                    setTitle(R.string.menu_contactus);
                    break;

                case R.id.nav_aboutus:
                    showFragment(new AboutusFragment());
                    setTitle(R.string.menu_aboutus);
                    break;

                case R.id.nav_program:
                    showFragment(new ProgramFragment());
                    setTitle(R.string.menu_program);
                    break;

                case R.id.nav_notification:
                    showFragment(new NotificationFragment());
                    setTitle(R.string.menu_notification);
                    break;

                case R.id.action_share:
                    share();
                    break;

            }
            drawer.closeDrawers();
            return false;
        });
        showFragment(new ProgramFragment());
        setTitle(R.string.menu_program);
    }

    private void showFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.placeholder, mFragment).commit();


    }

    private void share() {
        //  String sharepath = getpath();
        try {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "GyanUtsav");
            String shareMessage = "\nInstall now and became a part of GyanUtsav\n\n";
            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "Share link"));
        } catch (Exception e) {
            //e.toString();
        }
    }

    private String getpath() {
        String uid = "123216";
        String link = "https://mygame.example.com/?invitedby=" + uid;
      /*  FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(Uri.parse(link))
                .setDomainUriPrefix("https://example.page.link")
                .setAndroidParameters(
                        new DynamicLink.AndroidParameters.Builder("com.example.android")
                                .setMinimumVersion(125)
                                .build())
                .setIosParameters(
                        new DynamicLink.IosParameters.Builder("com.example.ios")
                                .setAppStoreId("123456789")
                                .setMinimumVersion("1.0.1")
                                .build())
                .buildShortDynamicLink()
                .addOnSuccessListener(new OnSuccessListener<ShortDynamicLink>() {
                    @Override
                    public void onSuccess(ShortDynamicLink shortDynamicLink) {
                        mInvitationUrl = shortDynamicLink.getShortLink();
                        // ...
                    }
                });
      */


        return null;
    }


    @Override
    protected void onResume() {
        super.onResume();
        profile = PrefrenshesManager.getUserProfile();
        if (profile != null) {
            Glide.with(this).load(profile.getUserPic()).into(iv_dp);
            tv_name.setText(profile.getUserName());

        }


    }

    private void sendtoken() {
        if (profile != null) {
            FirebaseInstanceId.getInstance().getToken();
            Call<MainModel> call = Api.getClient().upadatefcm(profile.getUserId(), FirebaseInstanceId.getInstance().getToken());
            call.enqueue(new Callback<MainModel>() {
                @Override
                public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                    Log.e("FCM", "Token updated");
                }

                @Override
                public void onFailure(Call<MainModel> call, Throwable t) {
                    Log.e("FCM", "Token updated faild");
                }
            });

        }
    }
}
