package cn.jarlen.houseinspection;

import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jarlen.houseinspection.base.BKBaseActivity;
import cn.jarlen.houseinspection.base.BKBaseFragment;
import cn.jarlen.houseinspection.ui.AdSubActivity;
import cn.jarlen.houseinspection.ui.MeFragment;
import cn.jarlen.houseinspection.ui.ProblemFragment;
import cn.jarlen.houseinspection.ui.ProblemSubActivity;
import cn.jarlen.richcommon.ui.FragmentStack;

public class HomeActivity extends BKBaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener, View.OnLongClickListener {

    List<BKBaseFragment> fragments;

    @BindView(R.id.menu_rg)
    RadioGroup menuGroup;

    @BindView(R.id.menu_submit)
    ImageButton menuSubmit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void preBindView() {

    }

    @Override
    protected void onBKBindView() {
        FragmentStack.setCustomAnimations(R.anim.anim_fade_in,R.anim.anim_fade_out);
        menuGroup.setOnCheckedChangeListener(this);
        menuSubmit.setOnClickListener(this);
        menuSubmit.setOnLongClickListener(this);

        if (fragments == null) {
            fragments = new ArrayList<BKBaseFragment>();
        }
        fragments.clear();

        ProblemFragment problem = (ProblemFragment) FragmentStack.findFragment(this, ProblemFragment.class);
        if (problem == null) {
            problem = new ProblemFragment();
        }
        fragments.add(problem);

        MeFragment me = (MeFragment) FragmentStack.findFragment(this, MeFragment.class);
        if (me == null) {
            me = new MeFragment();
        }
        fragments.add(me);

        ((RadioButton) menuGroup.getChildAt(0)).setChecked(true);
    }


    private void showFragment(BKBaseFragment fragment) {

        for (BKBaseFragment item : fragments) {
            if (item.isAdded()) {
                FragmentStack.hideFragment(this, item);
            }
        }

        if (fragment.isAdded()) {
            FragmentStack.showFragment(this, fragment);
        } else {
            FragmentStack.addFrament(this, R.id.fragment_container, fragment);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.menu_home:
                showFragment(fragments.get(0));
                break;
            case R.id.menu_me:
                showFragment(fragments.get(1));
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.menu_submit:
                startActivity(ProblemSubActivity.class,null);
                break;
            default:

                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.menu_submit:
                startActivity(AdSubActivity.class,null);
                break;
        }

        return false;
    }
}
