package com.example.mahesh.stackoverflowapplication;

import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.view.Window;

import com.example.mahesh.stackoverflowapplication.adapters.ViewPagerAdapter;
import com.example.mahesh.stackoverflowapplication.model.ListOfQuestions;
import com.example.mahesh.stackoverflowapplication.model.Questions;
import com.example.mahesh.stackoverflowapplication.model.Tags;
import com.example.mahesh.stackoverflowapplication.support.Common;
import com.example.mahesh.stackoverflowapplication.support.StackOverFlowAPI;
import com.example.mahesh.stackoverflowapplication.viewModels.TagsViewModel;

import static com.example.mahesh.stackoverflowapplication.support.Common.TAB_TITLE_HOT;
import static com.example.mahesh.stackoverflowapplication.support.Common.TAB_TITLE_TRENDING;
import static com.example.mahesh.stackoverflowapplication.support.Common.TAB_TITLE_WEEK;
import static com.example.mahesh.stackoverflowapplication.support.Common.questionsAdapter;
import static com.example.mahesh.stackoverflowapplication.support.Common.sort;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionListActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private static final String TAG = "gdfgddfg";
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    public static final String TAG_NAME = "tagName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_question_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        initViewPagerAndTabs();


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentByTag("navigation");
        mTitle = getTitle();

        initializingTags();


    }

    private void initializingTags() {
        TagsViewModel viewModel = ViewModelProviders.of(this).get(TagsViewModel.class);

        final Bundle bundle = new Bundle();

        viewModel.getListLiveData().observe(this, new Observer<List<Tags>>() {
            @Override
            public void onChanged(@Nullable List<Tags> tags) {
                Log.d(TAG, "onChanged: "+tags);
                String[] allTags = new String[tags.size()];
                for (int i = 0; i < tags.size(); i++)
                    allTags[i] = tags.get(i).getName();
                bundle.putStringArray(TAG_NAME, allTags);

                mNavigationDrawerFragment.setUp(
                        R.id.navigation_drawer,
                        (DrawerLayout) findViewById(R.id.drawer_layout),
                        bundle);

            }
        });
    }


    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }



    public void restoreActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_question_list, container, false);
            return rootView;
        }

    }

    private void initViewPagerAndTabs() {
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.textColor)));
        tabLayout.addTab(tabLayout.newTab().setText(TAB_TITLE_TRENDING));
        tabLayout.addTab(tabLayout.newTab().setText(TAB_TITLE_HOT));
        tabLayout.addTab(tabLayout.newTab().setText(TAB_TITLE_WEEK));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() != 0) {
                    questionsAdapter.clear();

                    final ProgressDialog progressDialog = new ProgressDialog(QuestionListActivity.this);
                    progressDialog.show();

                    viewPager.setCurrentItem(tab.getPosition());
                    StackOverFlowAPI stackOverFlowAPI = Common.getStackOverFlowAPI(Common.STACKEXCHANGEURL);
                    sort = tab.getText().toString();
                    if (sort.equals(TAB_TITLE_TRENDING))
                        sort = "votes";
                    stackOverFlowAPI.getListOfQuestions(sort, Common.TAGGED, Common.SITE).enqueue(
                            new Callback<ListOfQuestions>() {
                                @Override
                                public void onResponse(Call<ListOfQuestions> call, Response<ListOfQuestions> response) {

                                    Common.questionsAdapter.setQuestionsList(response.body().getItems());
                                    Common.questionsAdapter.notifyDataSetChanged();
                                    progressDialog.dismiss();
                                }

                                @Override
                                public void onFailure(Call<ListOfQuestions> call, Throwable throwable) {

                                }
                            }
                    );
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabReselected: "+tab.getPosition());
            }
        });
    }


}
