package cn.jarlen.houseinspection;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import cn.jarlen.houseinspection.adapter.ProblemAdapter;
import cn.jarlen.houseinspection.base.BKBaseActivity;
import cn.jarlen.houseinspection.ui.ProblemSubActivity;

public class HomeActivity extends BKBaseActivity {

    @BindView(R.id.xrecyclerview)
    private XRecyclerView mRecyclerView;

    private ProblemAdapter problemAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void preBindView() {

    }

    @Override
    protected void onBKBindView() {
        showRightImg(R.drawable.add, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ProblemSubActivity.class, null);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        problemAdapter = new ProblemAdapter(this);
        mRecyclerView.setAdapter(problemAdapter);
    }
}
