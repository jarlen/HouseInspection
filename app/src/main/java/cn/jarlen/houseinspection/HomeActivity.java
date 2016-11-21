package cn.jarlen.houseinspection;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import cn.jarlen.houseinspection.adapter.ProblemAdapter;
import cn.jarlen.houseinspection.base.BKBaseActivity;
import cn.jarlen.houseinspection.data.User;
import cn.jarlen.houseinspection.http.BaseResponse;
import cn.jarlen.houseinspection.http.HttpConstants;
import cn.jarlen.houseinspection.http.OkHttpPatch;
import cn.jarlen.houseinspection.http.ProblemResponse;
import cn.jarlen.houseinspection.ui.LoginActivity;
import cn.jarlen.houseinspection.ui.ProblemSubActivity;
import cn.jarlen.httppatch.okhttp.Callback2;
import cn.jarlen.richcommon.utils.ToastUtil;

public class HomeActivity extends BKBaseActivity implements XRecyclerView.LoadingListener {

    @BindView(R.id.xrecyclerview)
    XRecyclerView mRecyclerView;

    private ProblemAdapter problemAdapter;

    /* 当前页 */
    private int mCurrentPage;
    private boolean isLoading = false;
    private int mLastNums = HttpConstants.PAGE_NUMS_ONE_TIME;

    private Gson gson = new Gson();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void preBindView() {

    }

    @Override
    protected void onBKBindView() {
        showRightImg(R.drawable.submit, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!User.isUserLogin()) {
                    startActivity(LoginActivity.class, null);
                    return;
                }

                startActivity(ProblemSubActivity.class, null);

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        problemAdapter = new ProblemAdapter(this);
        mRecyclerView.setLoadingListener(this);
        mRecyclerView.setAdapter(problemAdapter);
        this.mCurrentPage = 0;
        getData();
    }

    @Override
    public void onRefresh() {
        if (isLoading) {
            return;
        }
        this.mCurrentPage = 0;
        getData();
    }


    @Override
    public void onLoadMore() {
        if (isLoading) {
            return;
        }
        this.mCurrentPage++;
        getData();
    }

    private void getData() {
        isLoading = true;
        OkHttpPatch.getOkHttpPatch().findproblem(mCurrentPage, callback);
    }


    private Callback2 callback = new Callback2() {
        @Override
        public void onResponse(String body) {
            isLoading = false;
            mRecyclerView.refreshComplete();
            mRecyclerView.loadMoreComplete();

            ProblemResponse problemResponse = gson.fromJson(body, ProblemResponse.class);

            if (problemResponse.getStatus() == BaseResponse.RESPONSE_OPT_SUCCESS) {
                if(mCurrentPage == 0){
                    problemAdapter.clearDataList();
                }
                ProblemResponse.ProblemInfo info = problemResponse.getContent();
                problemAdapter.addDataList(info.getInfo());
                mLastNums = info.getNums();
                if (mLastNums < HttpConstants.PAGE_NUMS_ONE_TIME) {
                    mRecyclerView.noMoreLoading();
                    mRecyclerView.setLoadingMoreEnabled(false);
                } else {
                    mRecyclerView.setLoadingMoreEnabled(true);
                }

            } else if (problemResponse.getStatus() == BaseResponse.RESPONSE_ERROR_ACCOUNT) {
                mCurrentPage--;
                User.clearCache();
                ToastUtil.makeToast(HomeActivity.this).setText(problemResponse.getMessage()).show();
                LoginActivity.startLogin(HomeActivity.this);
            } else if (problemResponse.getStatus() == BaseResponse.RESPONSE_ERROR_PARAM) {
                ToastUtil.makeToast(HomeActivity.this).setText(problemResponse.getMessage()).show();
            } else if (problemResponse.getStatus() == BaseResponse.RESPONSE_OPT_FAIL) {
                ToastUtil.makeToast(HomeActivity.this).setText(problemResponse.getMessage()).show();
            }


        }

        @Override
        public void onFailure(Exception e) {
            isLoading = false;
            mRecyclerView.refreshComplete();
            mRecyclerView.loadMoreComplete();
            if (problemAdapter != null) {
                problemAdapter.clearDataList();
            }

        }
    };
}
