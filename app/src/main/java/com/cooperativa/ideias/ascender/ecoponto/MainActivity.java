package com.cooperativa.ideias.ascender.ecoponto;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.cooperativa.ideias.ascender.ecoponto.Utils.ConstantsUtils;
import com.cooperativa.ideias.ascender.ecoponto.Utils.EventMessage;
import com.cooperativa.ideias.ascender.ecoponto.Utils.FragmentUtils;
import com.cooperativa.ideias.ascender.ecoponto.Utils.GetDataFrom;
import com.cooperativa.ideias.ascender.ecoponto.viewpagercards.CardFragmentPagerAdapter;
import com.cooperativa.ideias.ascender.ecoponto.viewpagercards.ShadowTransformer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;

import static android.app.PendingIntent.getActivity;
import static com.cooperativa.ideias.ascender.ecoponto.Utils.FragmentUtils.circleReveal;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Variaveis...

    Toolbar toolbar = null;

    Toolbar  toolbarSearch;
    //WebCustomTabs
    CustomTabsIntent customTabsIntent;
    CustomTabsIntent.Builder intentBuilder;

    //Firebase
    private FirebaseAuth mAuth;

    private Menu search_menu;
    private MenuItem item_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

        customTabs();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //SearchView
        toolbarSearch = findViewById(R.id.searchtoolbar);
        setSearchToolbar();



        //Metodo para AdMob anuncios...
        // mInterstitialAd = new InterstitialAd(this);
        // mInterstitialAd.setAdUnitId("ca-app-pub-4036318734376935/2022261408");
        //Chamando fragmento inicial...
        navigationView.setCheckedItem(R.id.nav_coleta);
        FragmentUtils.replace(this, new MapsColetasFragment());



        // Parametros para utilização dos Cards como ViewPager...
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        CardFragmentPagerAdapter pagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, this));
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    circleReveal(MainActivity.this, R.id.searchtoolbar, 1, true, true);
                else
                    toolbarSearch.setVisibility(View.VISIBLE);

                item_search.expandActionView();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void setSearchToolbar() {

        if (toolbarSearch != null) {
            toolbarSearch.inflateMenu(R.menu.menu_search);
            search_menu = toolbarSearch.getMenu();

            toolbarSearch.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                        circleReveal(MainActivity.this, R.id.searchtoolbar, 1, true, false);
                    else
                        toolbarSearch.setVisibility(View.GONE);
                }
            });


            item_search = search_menu.findItem(R.id.action_filter_search);
            MenuItemCompat.setOnActionExpandListener(item_search, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        circleReveal(MainActivity.this, R.id.searchtoolbar, 1, true, false);
                    } else
                        toolbarSearch.setVisibility(View.GONE);
                    return true;
                }

                @Override
                public boolean onMenuItemActionExpand(MenuItem item) {

                    return true;
                }
            });

            initSearchView();
        } else {

        }

    }

    private void initSearchView() {

        final SearchView searchView =
                (SearchView) search_menu.findItem(R.id.action_filter_search).getActionView();



        searchView.setSubmitButtonEnabled(false);



        ImageView closeButton = searchView.findViewById(R.id.search_close_btn);
        closeButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_close));




        EditText txtSearch = searchView.findViewById(R.id.search_src_text);
        txtSearch.setHint("Buscar...");
        txtSearch.setHintTextColor(Color.DKGRAY);
        txtSearch.setTextColor(getResources().getColor(R.color.colorPrimary));


        //setando Cursor

        AutoCompleteTextView searchTextView = searchView.findViewById(R.id.search_src_text);
        try {
            Field mCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            mCursorDrawableRes.setAccessible(true);
            mCursorDrawableRes.set(searchTextView, R.drawable.search_cursor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                callSearch(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                callSearch(newText);
                return true;
            }

            public void callSearch(String query) {

                Log.i("ENVIANDO_QUERY", query);
                EventBus.getDefault().post(new EventMessage(query));
            }

        });

    }

    // Metodp onBackPressed Menu DrawerNavigation...
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        new GetDataFrom().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //Menu Drawer OnNavigationitensSelected...
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displayView(item.getItemId());
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
  }


    //Utilização do menu usando Switch e case...
    private void displayView(int itemId) {
        switch (itemId) {
            case R.id.nav_coleta:
                FragmentUtils.replace(this, new MapsColetasFragment());
                break;
            case R.id.nav_oleo:
                FragmentUtils.replace(this, new MapsActivityNsr());
                break;
                case R.id.nav_horario:
                FragmentUtils.replace(this, new ColetasFragment());
                break;
                case R.id.nav_protejapontos:
                    startActivity(new Intent(this, ProtejaOsEcoPontos.class));
                    break;case R.id.nav_sobre:
                        FragmentUtils.replace(this, new SobreFragment());
                break;
                case R.id.nav_catadores:
//                startActivity(new Intent(this, ASCItaperuna.class));
                   customTabsIntent.launchUrl(this, Uri.parse(ConstantsUtils.URL_CATADORES));
                break;

            case R.id.nav_ascender:

//                startActivity(new Intent(this, AscenderIdeias.class));
                customTabsIntent.launchUrl(this, Uri.parse(ConstantsUtils.URL_AI));
                break;

            case R.id.nav_sair:
                //dialog
                dialogSair();

                break;
            case R.id.nav_compartilar:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                share.putExtra(Intent.EXTRA_SUBJECT, "Compartilhe nosso aplicativo...");
                share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.cooperativa.ideias.ascender.ecoponto");

                startActivity(Intent.createChooser(share, "Compartilhar link!"));
                //startActivity ( intent );

                break;

        }
    }

    //instanciando custom tabs para carregamento de paginas web
    private void customTabs(){
        intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        intentBuilder.setExitAnimations(this, R.anim.right_to_left_end, R.anim.left_to_right_end);
        intentBuilder.setStartAnimations(this, R.anim.left_to_right_start, R.anim.right_to_left_start);
        intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        customTabsIntent = intentBuilder.build();
    }



    //AlertDialog de confimação de signOut
    private void dialogSair() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Desconectar?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //desconectando e passando null como parametro para redirecionamento a activityLogin
                FirebaseAuth.getInstance().signOut();
                updateUI(null);

            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        //verificando se há algum usuario logado
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    //caso usuario == null redireciona para a activity de login
    private void updateUI(FirebaseUser currentUser){
        if(currentUser!= null){

        }else {
//            startActivity(new Intent( this, LoginActivity.class));
//            finish();
        }
    }

    //
    /**
     * ViewPager com Cards Código de teste...
     * Change value in dp to pixels
     * @param dp
     * @param context
     */
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}

















