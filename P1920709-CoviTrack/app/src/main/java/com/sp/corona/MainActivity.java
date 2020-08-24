

package com.sp.corona;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sp.corona.viewmodel.CaseReportModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.container)
    CoordinatorLayout container;
    @BindView(R.id.nav_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;

    static boolean matchDestination(@NonNull NavDestination destination, @IdRes int destId) {
        NavDestination currentDestination = destination;
        while (currentDestination.getId() != destId && currentDestination.getParent() != null) {
            currentDestination = currentDestination.getParent();
        }
        return currentDestination.getId() == destId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        //Avoid Adding same fragment to NavController, if clicked on current BottomNavigation item
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == bottomNavigationView.getSelectedItemId())
                return false;
            if(item.getItemId()==R.id.information){
                swipeLayout.setEnabled(false);
            }else
                swipeLayout.setEnabled(true);
            NavigationUI.onNavDestinationSelected(item, navController);
            return true;
        });

        //Check correct BottomNavigation item, if navigation_main is done programmatically
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            final Menu menu = bottomNavigationView.getMenu();
            final int size = menu.size();
            for (int i = 0; i < size; i++) {
                MenuItem item = menu.getItem(i);
                if (matchDestination(destination, item.getItemId())) {
                    item.setChecked(true);
                }
            }
        });

        final CaseReportModel caseReportModel = new ViewModelProvider(this).get(CaseReportModel.class);
        caseReportModel.getData().observe(this, result -> {
            if (result)
                showSnackBar("Database updated", null);
            swipeLayout.setRefreshing(false);
        });

        caseReportModel.getError().observe(this, s -> {
            showSnackBar("Failed to retrieve new data", v -> caseReportModel.fetchOnlineData());
            swipeLayout.setRefreshing(false);
        });

        swipeLayout.setOnRefreshListener(caseReportModel::fetchOnlineData);
    }

    @Override
    protected void onPause() {
        swipeLayout.setRefreshing(false);
        super.onPause();
    }

    protected void showSnackBar(String message, View.OnClickListener clickListener) {
        Snackbar snackbar = Snackbar.make(container, message, Snackbar.LENGTH_SHORT);
        snackbar.setAnchorView(bottomNavigationView);
        snackbar.setTextColor(getResources().getColor(R.color.colorAccent));
        snackbar.setBackgroundTint(getResources().getColor(R.color.colorBackground));


        if (clickListener != null)
            snackbar.setAction("Retry", clickListener);
        snackbar.show();
    }
}
