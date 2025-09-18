package com.example.trackapp.widgets

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.RemoteViews
import com.example.trackapp.MainActivity
import com.example.trackapp.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.w3c.dom.Text

@AndroidEntryPoint
class widgetList : AppWidgetProvider() {

    @Inject
    lateinit var repository: Repository
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId,repository)
        }
    }
}

fun setImprovisdAdapter(noteList: Any, views: Any, context: Context) {}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
    repository:Repository
) {

    val views = RemoteViews(context.packageName, R.layout.widget_list)

    //--Open App when we click on widget--//

    vol pendingIntent : PendingIntent +PendingIntent.getActivity(
        context,
        requestCode:0,
    Intent(context, MainActivity::class.java),
    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent)

    views.setOnClickPendingIntent(R.id.main_layout, pendingIntent)

    //--get all notes and display in xml--//

    CoroutineScope(Dispatchers.IO).launch {
        val noteList = repository.getAllNotes()
        CoroutineScope(Dispatchers.Main).launch {
            setImprovisdAdapter(noteList, Views, context)
            appWidgetManager.updateAppWidget(appWidgetId, Views)
        }
    }
}

fun setImprovisdAdapter(noteList: Any, views: Any, context: Context) {}

  var viewCounter=1

val listOfViews=listOf(R.id.view1, R.id.view2,R.id.view3,R.id.view4,R.id.view5,R.id.view6,
    R.id.view7,R.id.view8,R.id.view9,R.id.view10)
val listOfContent=listOf(R.id.content1,R.id.content2,R.id.content3,R.id.content4,R.id.content5,
    R.id.content6,R.id.content7,R.id.content8,R.id.content9,R.id.content10)
val listofDates=listOf(R.id.date1,R.id.date2,R.id.date3,R.id.date4,R.id.date5,R.id.date6,
    R.id.date7,R.id.date8,R.id.date9,R.id.date10)

noteList.for.Each{
    views.setTextViewText(listOfContent[viewCounter],it.content)
    views.setTextViewText(listOfContent[viewCounter],it.date.tbx_fromUnixToFormatted())
    views.setTextViewVisibility(listOfContent[viewCounter],View.VISIBLE)
}
