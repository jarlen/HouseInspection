package cn.jarlen.houseinspection.base;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jarlen.richcommon.ui.BaseFragment;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/21.
 */

public abstract class BKBaseFragment extends BaseFragment {

    private Unbinder unbinder;

    @Override
    protected void preBindView() {
        unbinder = ButterKnife.bind(this, getRootView());
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
