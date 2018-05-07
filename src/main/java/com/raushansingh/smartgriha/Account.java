package com.raushansingh.smartgriha;


import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.TextView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Account extends AppCompatActivity {


    Drawer drawer ;
    ActionBar actionbar;
    PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.connect).withIcon(R.drawable.first);
    PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.add_device).withIcon(R.drawable.second);
    PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.customize).withIcon(R.drawable.third);
    PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName(R.string.share).withIcon(R.drawable.fourth);
    PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.about).withIcon(R.drawable.fifth);
    PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName(R.string.help).withIcon(R.drawable.sixth);
    PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName(R.string.service).withIcon(R.drawable.seventh);

    PrimaryDrawerItem item8 = new PrimaryDrawerItem().withIdentifier(8).withName(R.string.developer).withIcon(R.drawable.eight);




//    private Button sign_Out;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        final ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();
//ls

        setContentView(R.layout.activity_account);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();

        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);



//        sign_Out =(Button)findViewById(R.id.sign_out);
        firebaseAuth =FirebaseAuth.getInstance();

        Bundle account_data =getIntent().getExtras();
        if(account_data==null)
            return;
        String email_id =account_data.getString("email_id");
        String name =account_data.getString("name");


        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.material_drawer_dark_background)
                .addProfiles(
                        new ProfileDrawerItem().withName(name).withEmail(email_id))
        .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        drawer = new DrawerBuilder().withActivity(this).withAccountHeader(headerResult)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false).addDrawerItems(item1,item2,item3,item4,item5,item6,item7,item8)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener(){
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
//to do


                        return false;

                    }
                }).build();




//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
//        drawer.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
//        if(drawer.isDrawerOpen()==true){
//            actionBar.hide();
//        } else actionBar.show();




//        sign_Out.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                firebaseAuth.signOut();
//                /*FirebaseAuth.AuthStateListener authStateListener =new FirebaseAuth.AuthStateListener() {
//                @Override
//                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                        FirebaseUser user = firebaseAuth.getCurrentUser();
//                if(user ==null)
//                        {*/
//                            startActivity(new Intent(Account.this,Register.class));
//                            finish();
//                      /*}
//                   }
//                };*/
//            }
//        });
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               drawer.openDrawer();
                return true;
            case R.id.action_favorite:

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.smartgriha.com"));
                startActivity(browserIntent);

                return true;
            case R.id.logout:
                firebaseAuth.signOut();
                startActivity(new Intent(Account.this,Register.class));
                          finish();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {

            super.onBackPressed();

        } else {

            final android.support.v7.app.AlertDialog ad = new android.support.v7.app.AlertDialog.Builder(this)
                    .create();
            ad.setCancelable(false);
            ad.setTitle("Exit ?");
            ad.setMessage("Do you really want to exit ? ");
            ad.setButton(DialogInterface.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    finish();
                }
            });
            ad.setButton(DialogInterface.BUTTON_NEGATIVE, "no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();

                }
            });
            ad.show();
        }
    }

    public void setFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contentContainer, fragment);
            fragmentTransaction.commit();
        }
    }

    public void addFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.contentContainer, fragment);
            fragmentTransaction.commit();
        }
    }




}
