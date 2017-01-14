package cn.jarlen.houseinspection.ui;

import android.support.v7.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jarlen.houseinspection.R;
import cn.jarlen.houseinspection.adapter.ProblemAdapter;
import cn.jarlen.houseinspection.base.BKBaseFragment;
import cn.jarlen.houseinspection.data.User;
import cn.jarlen.houseinspection.http.BaseResponse;
import cn.jarlen.houseinspection.http.HttpConstants;
import cn.jarlen.houseinspection.http.OkHttpPatch;
import cn.jarlen.houseinspection.http.ProblemResponse;
import cn.jarlen.httppatch.okhttp.Callback2;
import cn.jarlen.richcommon.utils.ToastUtil;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/21.
 */

public class ProblemFragment extends BKBaseFragment implements XRecyclerView.LoadingListener {
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
        return R.layout.fragment_home;
    }

    @Override
    protected void onBindView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallPulse);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.SquareSpin);
        problemAdapter = new ProblemAdapter(getActivity());
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

            try {
                JSONObject jsonObject = new JSONObject(body);
                int status = jsonObject.getInt("status");
                String msg = jsonObject.getString("message");

                if(status == BaseResponse.RESPONSE_OPT_SUCCESS){
                    if(mCurrentPage == 0){
                        problemAdapter.clearDataList();
                    }
                    JSONObject content = jsonObject.getJSONObject("content");
                    JSONArray info = content.getJSONArray("info");

                    problemAdapter.addDataList(toJSONObject(info));
                    mLastNums = content.getInt("nums");
                    if (mLastNums < HttpConstants.PAGE_NUMS_ONE_TIME) {
                        mRecyclerView.noMoreLoading();
                        mRecyclerView.setLoadingMoreEnabled(false);
                    } else {
                        mRecyclerView.setLoadingMoreEnabled(true);
                    }

                }
                else if(status == BaseResponse.RESPONSE_ERROR_ACCOUNT){
                    mCurrentPage--;
                    User.clearCache();
                    ToastUtil.makeToast(getActivity()).setText(msg).show();
                    LoginActivity.startLogin(getActivity());
                }
                else if (status == BaseResponse.RESPONSE_ERROR_PARAM) {
                    ToastUtil.makeToast(getActivity()).setText(msg).show();
                } else if (status == BaseResponse.RESPONSE_OPT_FAIL) {
                    ToastUtil.makeToast(getActivity()).setText(msg).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
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


    private List<JSONObject> toJSONObject(JSONArray array){
        List<JSONObject> list = new ArrayList<JSONObject>();
        try {
            int length = array.length();
            for(int index = 0;index < length;index++){
                JSONObject object = array.getJSONObject(index);
                list.add(object);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
