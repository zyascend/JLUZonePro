package jluzonepro.zyascend.com.score;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jluzonepro.zyascend.com.common.base.BaseFragment;
import jluzonepro.zyascend.com.common.base.BaseReAdapter;
import jluzonepro.zyascend.com.common.entity.AvgScore;
import jluzonepro.zyascend.com.common.entity.ConstValue;
import jluzonepro.zyascend.com.common.entity.Score;
import jluzonepro.zyascend.com.common.entity.ScoreDetail;
import jluzonepro.zyascend.com.common.entity.ScoreStatics;
import jluzonepro.zyascend.com.common.utils.ActivityUtils;


/**
 *
 * Created by zyascend on 2016/8/3.
 */
public class ScoreFragment extends BaseFragment<ScoreContract.View, ScorePresenter>
        implements ScoreContract.View, SwipeRefreshLayout.OnRefreshListener, BaseReAdapter.OnItemClickListener {

    private static final String TAG = "TAG_ScoreFragment";

    @BindView(R2.id.recyclerLayout)
    LinearLayout recyclerLayout;
    @BindView(R2.id.layout_fail)
    LinearLayout failLayout;
    @BindView(R2.id.sp_type)
    AppCompatSpinner spType;
    @BindView(R2.id.sp_params)
    AppCompatSpinner spParams;
    @BindView(R2.id.tv_sort)
    TextView tvSort;
    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;


    private ScoreAdapter adapter = new ScoreAdapter();
    private List<Score> mList;
    private List<String> params = new ArrayList<>();
    private ArrayAdapter<String> paramsAdapter;
    private int refreshType;
    private int refreshParams;

    private Context mContext;
    private int currentDialogPosition;
    private int mCurrentPosition;

    private Handler mHandler;
    private ProgressDialog progressDialog;


    private static class MHandler extends Handler {
        WeakReference<ScoreFragment> weakReference;
        public MHandler(ScoreFragment fragment) {
            super();
            weakReference = new WeakReference<ScoreFragment>(fragment);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            assert weakReference != null;
            weakReference.get().fetchAvScore();
        }
    }

    public void fetchAvScore() {
        progressDialog.show();
        mPresenter.getAvgScore();
    }

    /**
     * onAttach()先于onCreate()执行
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ScoreActivity activity = (ScoreActivity) context;
        mHandler = new MHandler(this);
        activity.setHandler(mHandler);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_score;
    }


    @Override
    protected void initViews() {

        mContext = getActivity();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        swipeRefreshLayout.setOnRefreshListener(this);
        tvSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (refreshType == ConstValue.SCORE_TYPE_NEW) {
                    Toast.makeText(mContext, "当前不可排序，请切换查询类型", Toast.LENGTH_SHORT).show();
                } else {
                    showSortDialog();
                }
            }
        });

        String[] types = getResources().getStringArray(R.array.score_types);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(mContext, R.layout.spinner_item, types);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(typeAdapter);
        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemSelected: type");
                switch (position) {
                    case 0:
                        refreshType = ConstValue.SCORE_TYPE_NEW;
                        refreshParams = 15;
                        spParams.setVisibility(View.INVISIBLE);
                        onRefresh();
                        Log.d(TAG, "onItemSelected: 0");
                        break;
                    case 1:
                        refreshType = ConstValue.SCORE_TYPE_YEAR;
                        showYearSpinner();
                        break;
                    case 2:
                        refreshType = ConstValue.SCORE_TYPE_ALL;
                        spParams.setVisibility(View.INVISIBLE);
                        onRefresh();
                        Log.d(TAG, "onItemSelected: 2");
                        break;
                    default:
                        refreshType = ConstValue.SCORE_TYPE_NEW;
                        refreshParams = 15;
                        spParams.setVisibility(View.INVISIBLE);
                        onRefresh();
                        Log.d(TAG, "onItemSelected: 0");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spType.setSelection(0);

        paramsAdapter = new ArrayAdapter<String>(mContext, R.layout.spinner_item, params);
        paramsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spParams.setAdapter(paramsAdapter);
        spParams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                refreshParams = Integer.parseInt(params.get(position).split("~")[0]);
                Log.d(TAG, "onItemSelected: year = " + refreshParams);
                onRefresh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spParams.setSelection(0);
        spParams.setVisibility(View.INVISIBLE);
        //首次加载时的参数
        refreshParams = 15;
        refreshType = ConstValue.SCORE_TYPE_NEW;

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.show();
        onRefresh();
    }

    @Override
    protected ScorePresenter getPresenter() {
        Log.d(TAG, "getPresenter: fragment");
        return new ScorePresenter(getActivity());
    }


    @Override
    public void loadScore(List<Score> scoreList) {
        progressDialog.dismiss();
        swipeRefreshLayout.setRefreshing(false);
        if (!ActivityUtils.NotNullOrEmpty(scoreList)) {
            showFailure();
            return;
        }
        failLayout.setVisibility(View.GONE);
        recyclerLayout.setVisibility(View.VISIBLE);

        mList = scoreList;
        adapter.setList(mList);
    }

    @Override
    public void showFailure() {

        failLayout.setVisibility(View.VISIBLE);
        recyclerLayout.setVisibility(View.GONE);
    }


    @Override
    public void loadScoreDetail(ScoreDetail detail) {

    }

    @Override
    public void onSorted(List<Score> scores) {
        if (ActivityUtils.NotNullOrEmpty(scores)) {
            mList = scores;
            adapter.setList(mList);
        } else {
            Toast.makeText(mContext, "排序失败，请重试", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void loadAvgScore(AvgScore score) {
        progressDialog.dismiss();
        assert score != null;
        ScoreStatics statics = score.getValue().get(0);
        Intent intent = new Intent(getActivity(), ScoreStaticsActivity.class);
        intent.putExtra(ScoreStaticsActivity.INTENT_SCORE, statics);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        mPresenter.getScore(refreshParams, refreshType);
        Log.d(TAG, "onRefresh: " + refreshType + " " + refreshParams);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(mContext, ScoreDetailActivity.class);
        intent.putExtra(ScoreDetailActivity.EXTRA_SCORE, mList.get(position));
        mContext.startActivity(intent);
    }

    private void showYearSpinner() {

        //获取到YearList
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        params.clear();
        for (int i = 0; i < 5; i++) {
            String year = String.valueOf(currentYear - i) + "~" + String.valueOf(currentYear - i + 1) + "年";
            params.add(year);
        }
        paramsAdapter.notifyDataSetChanged();
        spParams.setVisibility(View.VISIBLE);
    }

    private void showSortDialog() {
        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle("选择排序类型：")
                .setSingleChoiceItems(R.array.score_sort_list, currentDialogPosition, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentDialogPosition = which;
                        sortScore(which);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    private void sortScore(int type) {
        if (type != 4) {
            mPresenter.sort(type, mList);
        } else {
            //选择了默认排序，仅刷新
            onRefresh();
        }
    }


}
