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
        Intent intent = new Intent(context, DeductionFormActivity.class);

        views.setOnClickPendingIntent(R.id.button_white_wine,
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE));

        intent.putExtra("IS_RED_WINE", true);
        views.setOnClickPendingIntent(R.id.button_red_wine,
                PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_IMMUTABLE));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
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

