package com.wineguesser.deductive.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.wineguesser.deductive.R
import com.wineguesser.deductive.view.DeductionFormActivity
import com.wineguesser.deductive.view.*

/**
 * Implementation of App Widget functionality.
 */
class StartDeductionWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        private fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.widget_start_deduction)
            val intent = Intent(context, DeductionFormActivity::class.java)

            views.setOnClickPendingIntent(
                R.id.button_white_wine,
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            )

            intent.putExtra(IS_RED_WINE, true)
            views.setOnClickPendingIntent(
                R.id.button_red_wine,
                PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_IMMUTABLE)
            )

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}
