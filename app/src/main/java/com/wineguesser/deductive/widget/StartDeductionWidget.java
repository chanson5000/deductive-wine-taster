package com.wineguesser.deductive.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.wineguesser.deductive.R;
import com.wineguesser.deductive.view.DeductionFormActivity;

/**
 * Implementation of App Widget functionality.
 */
public class StartDeductionWidget extends AppWidgetProvider {

    private static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                        int appWidgetId) {
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_start_deduction);

        views.setOnClickPendingIntent(R.id.button_red_wine,
                getPendingSelfIntent(context, true));
        views.setOnClickPendingIntent(R.id.button_white_wine,
                getPendingSelfIntent(context, false));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static PendingIntent getPendingSelfIntent(Context context, boolean isRedWine) {
        Intent intent = new Intent(context, DeductionFormActivity.class);
        if (isRedWine) {
            String IS_RED_WINE = "IS_RED_WINE";
            intent.putExtra(IS_RED_WINE, true);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return PendingIntent.getActivity(context,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

