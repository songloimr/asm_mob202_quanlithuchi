

package com.quanlithuchi;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.core.view.GravityCompat;
        import androidx.drawerlayout.widget.DrawerLayout;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentManager;

        import android.os.Bundle;
        import android.view.MenuItem;

        import com.google.android.material.navigation.NavigationView;
        import com.quanlithuchi.fragments.ChiFragment;
        import com.quanlithuchi.fragments.GioiThieuFragment;
        import com.quanlithuchi.fragments.ThongKeFragment;
        import com.quanlithuchi.fragments.ThuFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle navToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(navToggle);
        navToggle.syncState();

        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setCheckedItem(R.id.nav_item_thu);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_item_thu:
                fragment  = new ThuFragment();
                break;
            case R.id.nav_item_chi:
                fragment  = new ChiFragment();
                break;
            case R.id.nav_item_gioithieu:
                fragment = new GioiThieuFragment();
                break;
            case R.id.nav_item_thongke:
                fragment = new ThongKeFragment();
                break;
            case R.id.nav_item_thoat:
                finishAndRemoveTask();
                break;
        }

        if (fragment != null) {
            FragmentManager ft = getSupportFragmentManager();
            ft.beginTransaction().replace(R.id.content_frame, fragment).commit();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}