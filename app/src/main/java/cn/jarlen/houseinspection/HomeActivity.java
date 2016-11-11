package cn.jarlen.houseinspection;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jarlen.houseinspection.adapter.ProblemAdapter;
import cn.jarlen.houseinspection.base.BKBaseActivity;
import cn.jarlen.houseinspection.data.Problem;
import cn.jarlen.houseinspection.ui.LoginActivity;

public class HomeActivity extends BKBaseActivity implements XRecyclerView.LoadingListener {

    @BindView(R.id.xrecyclerview)
    XRecyclerView mRecyclerView;

    private ProblemAdapter problemAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void preBindView() {

    }

    @Override
    protected void onBKBindView() {
        showRightImg(R.drawable.add, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(ProblemSubActivity.class, null);
                startActivity(LoginActivity.class,null);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        problemAdapter = new ProblemAdapter(this);
        mRecyclerView.setLoadingListener(this);
        mRecyclerView.setAdapter(problemAdapter);
        initData();
    }

    private void initData(){
        List<Problem> data = new ArrayList<Problem>();

        for(int index = 0;index < 20;index++){
            Problem problem = new Problem();
            problem.setTitle("测试 "+index);
            problem.setContent("测试 测试 测试 测试 测试 测试 测试 测试"+index);
            problem.setCreate_at(1423432345);
            problem.setStatus(index %2);
            data.add(problem);
        }
        problemAdapter.addDataList(data);
        mRecyclerView.refreshComplete();
    }

    @Override
    public void onRefresh() {
        problemAdapter.clearDataList();
        initData();
    }

    @Override
    public void onLoadMore() {

    }
}
