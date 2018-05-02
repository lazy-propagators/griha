package com.raushansingh.smartgriha;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
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

public class Account extends AppCompatActivity {

    private TextView textView;
    PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.connect);
    PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.add_device);
    PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.customize);
    PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName(R.string.share);
    PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.about);
    PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName(R.string.help);
    PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName(R.string.service);
//    private Toolbar toolbar = findViewById(R.id.toolbar);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
ls
        
        setContentView(R.layout.activity_account);
        Bundle account_data =getIntent().getExtras();
        if(account_data==null)
            return;
        String email_id =account_data.getString("email_id");

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.background)
                .addProfiles(
                        new ProfileDrawerItem().withName("Shubham raj").withEmail(email_id).withIcon(R.drawable.background))
        .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
        Drawer drawer =  new DrawerBuilder().withActivity(this).withAccountHeader(headerResult)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false).addDrawerItems(item1,item2,item3,item4,item5,item6,item7)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener(){
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
//to do

                        return false;

                    }
                }).build();
//        if(drawer.isDrawerOpen()==true){
//            actionBar.hide();
//        } else actionBar.show();


        textView =(TextView)findViewById(R.id.emailfield);
               textView.setText(email_id);
    }
}
