package com.bw.movie.mvp.model.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;

public class NetworkErrorUtils extends LinearLayout {

    Context context;

    public NetworkErrorUtils(Context context) {
        super(context);
        init(context);
    }

    public NetworkErrorUtils(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NetworkErrorUtils(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        this.context = context;
        View view = View.inflate(context, R.layout.network_error_page, null);
        TextView btnNetworkError = view.findViewById(R.id.btn_network_error);
        addView(view);
        btnNetworkError.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                retryListener.onRetry();
            }
        });
    }

    private RetryListener retryListener;

    public void setRetryListener(RetryListener retryListener) {
        this.retryListener = retryListener;
    }

    public interface RetryListener {
        void onRetry();
    }

}
