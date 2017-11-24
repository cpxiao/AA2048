package com.cpxiao.aa2048.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.cpxiao.R;
import com.cpxiao.aa2048.mode.extra.Extra;
import com.cpxiao.androidutils.library.utils.PreferencesUtils;
import com.cpxiao.androidutils.library.utils.RateAppUtils;
import com.cpxiao.androidutils.library.utils.ShareAppUtils;
import com.cpxiao.gamelib.fragment.BaseZAdsFragment;

/**
 * @author cpxiao on 2017/9/27.
 */

public class HomeFragment extends BaseZAdsFragment implements View.OnClickListener {

    public static HomeFragment newInstance(Bundle bundle) {
        HomeFragment fragment = new HomeFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Button easy = (Button) view.findViewById(R.id.easy);
        Button normal = (Button) view.findViewById(R.id.normal);
        Button hard = (Button) view.findViewById(R.id.hard);
        Button insane = (Button) view.findViewById(R.id.insane);
        Button impossible = (Button) view.findViewById(R.id.impossible);
        ImageButton rateApp = (ImageButton) view.findViewById(R.id.rate_app);
        ImageButton share = (ImageButton) view.findViewById(R.id.share);
        ImageButton bestScore = (ImageButton) view.findViewById(R.id.best_score);
        ImageButton settings = (ImageButton) view.findViewById(R.id.settings);

        easy.setOnClickListener(this);
        normal.setOnClickListener(this);
        hard.setOnClickListener(this);
        insane.setOnClickListener(this);
        impossible.setOnClickListener(this);
        rateApp.setOnClickListener(this);
        share.setOnClickListener(this);
        bestScore.setOnClickListener(this);
        settings.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Context context = getHoldingActivity();
        Bundle bundle = new Bundle();
        if (id == R.id.easy) {
            bundle.putInt(Extra.Key.SQUARE_COUNT_X, 4);
            bundle.putInt(Extra.Key.SQUARE_COUNT_Y, 4);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.normal) {
            bundle.putInt(Extra.Key.SQUARE_COUNT_X, 5);
            bundle.putInt(Extra.Key.SQUARE_COUNT_Y, 5);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.hard) {
            bundle.putInt(Extra.Key.SQUARE_COUNT_X, 6);
            bundle.putInt(Extra.Key.SQUARE_COUNT_Y, 6);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.insane) {
            bundle.putInt(Extra.Key.SQUARE_COUNT_X, 7);
            bundle.putInt(Extra.Key.SQUARE_COUNT_Y, 7);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.impossible) {
            bundle.putInt(Extra.Key.SQUARE_COUNT_X, 8);
            bundle.putInt(Extra.Key.SQUARE_COUNT_Y, 8);
            addFragment(GameFragment.newInstance(bundle));
        } else if (id == R.id.rate_app) {
            RateAppUtils.rate(context);
        } else if (id == R.id.share) {
            String msg = getString(R.string.share_msg) + "\n" +
                    getString(R.string.app_name) + "\n" +
                    "https://play.google.com/store/apps/details?id=" + context.getPackageName();
            ShareAppUtils.share(context, getString(R.string.share), msg);
        } else if (id == R.id.best_score) {
            showBestScoreDialog(context);
        } else if (id == R.id.settings) {
            Toast.makeText(context, "Coming soon...", Toast.LENGTH_SHORT).show();
        }
    }

    private void showBestScoreDialog(Context context) {
        long bestScoreEasy = PreferencesUtils.getLong(context, Extra.Key.getKey(4, 4, Extra.Key.BEST_SCORE), 0);
        long bestScoreNormal = PreferencesUtils.getLong(context, Extra.Key.getKey(5, 5, Extra.Key.BEST_SCORE), 0);
        long bestScoreHard = PreferencesUtils.getLong(context, Extra.Key.getKey(6, 6, Extra.Key.BEST_SCORE), 0);
        long bestScoreInsane = PreferencesUtils.getLong(context, Extra.Key.getKey(7, 7, Extra.Key.BEST_SCORE), 0);
        long bestScoreImpossible = PreferencesUtils.getLong(context, Extra.Key.getKey(8, 8, Extra.Key.BEST_SCORE), 0);

        String msg = getString(R.string.easy) + ": " + bestScoreEasy + "\n"
                + getString(R.string.normal) + ": " + bestScoreNormal + "\n"
                + getString(R.string.hard) + ": " + bestScoreHard + "\n"
                + getString(R.string.insane) + ": " + bestScoreInsane + "\n"
                + getString(R.string.impossible) + ": " + bestScoreImpossible + "\n";

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(R.string.best_score)
                .setMessage(msg)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}
