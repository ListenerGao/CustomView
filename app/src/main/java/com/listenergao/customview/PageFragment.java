package com.listenergao.customview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * create on 2019/3/15
 *
 * @author ListenerGao
 */
public class PageFragment extends Fragment {

    private static final String TAG = "sampleLayoutRes";
    private int mSampleLayoutRes;


    public static PageFragment newInstance(int sampleLayoutRes) {
        PageFragment fragment = new PageFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(TAG, sampleLayoutRes);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        ViewStub sampleStub = view.findViewById(R.id.sample_stub);
        sampleStub.setLayoutResource(mSampleLayoutRes);
        sampleStub.inflate();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            mSampleLayoutRes = arguments.getInt(TAG);
        }


    }
}
