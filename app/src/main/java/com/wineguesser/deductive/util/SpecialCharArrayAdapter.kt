package com.wineguesser.deductive.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import timber.log.Timber
import java.util.ArrayList
import java.util.Arrays
import java.util.Collections
import java.util.Comparator

class SpecialCharArrayAdapter<T> : BaseAdapter, Filterable {

    private lateinit var mObjects: MutableList<T>
    private val mLock = Any()
    private var mResource: Int = 0
    private var mDropDownResource: Int = 0
    private var mFieldId = 0
    private var mNotifyOnChange = true
    private lateinit var mContext: Context
    private var mOriginalValues: ArrayList<T>? = null
    private var mFilter: HRArrayFilter? = null
    private var mInflater: LayoutInflater? = null

    constructor(context: Context, textViewResourceId: Int) {
        init(context, textViewResourceId, 0, ArrayList())
    }

    constructor(context: Context, resource: Int, textViewResourceId: Int) {
        init(context, resource, textViewResourceId, ArrayList())
    }

    constructor(context: Context, textViewResourceId: Int, objects: Array<T>) {
        init(context, textViewResourceId, 0, Arrays.asList(*objects))
    }

    constructor(context: Context, resource: Int, textViewResourceId: Int, objects: Array<T>) {
        init(context, resource, textViewResourceId, Arrays.asList(*objects))
    }

    constructor(context: Context, textViewResourceId: Int, objects: MutableList<T>) {
        init(context, textViewResourceId, 0, objects)
    }

    constructor(context: Context, resource: Int, textViewResourceId: Int, objects: MutableList<T>) {
        init(context, resource, textViewResourceId, objects)
    }

    private fun init(context: Context, resource: Int, textViewResourceId: Int, objects: MutableList<T>) {
        mContext = context
        mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mDropDownResource = resource
        mResource = resource
        mObjects = objects
        mFieldId = textViewResourceId
    }

    fun add(`object`: T) {
        if (mOriginalValues != null) {
            synchronized(mLock) {
                mOriginalValues!!.add(`object`)
                if (mNotifyOnChange) notifyDataSetChanged()
            }
        } else {
            mObjects.add(`object`)
            if (mNotifyOnChange) notifyDataSetChanged()
        }
    }

    fun insert(`object`: T, index: Int) {
        if (mOriginalValues != null) {
            synchronized(mLock) {
                mOriginalValues!!.add(index, `object`)
                if (mNotifyOnChange) notifyDataSetChanged()
            }
        } else {
            mObjects.add(index, `object`)
            if (mNotifyOnChange) notifyDataSetChanged()
        }
    }

    fun remove(`object`: T) {
        if (mOriginalValues != null) {
            synchronized(mLock) {
                mOriginalValues!!.remove(`object`)
            }
        } else {
            mObjects.remove(`object`)
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    fun clear() {
        if (mOriginalValues != null) {
            synchronized(mLock) {
                mOriginalValues!!.clear()
            }
        } else {
            mObjects.clear()
        }
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    fun sort(comparator: Comparator<in T>) {
        Collections.sort(mObjects, comparator)
        if (mNotifyOnChange) notifyDataSetChanged()
    }

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
        mNotifyOnChange = true
    }

    fun setNotifyOnChange(notifyOnChange: Boolean) {
        mNotifyOnChange = notifyOnChange
    }

    fun getContext(): Context {
        return mContext
    }

    override fun getCount(): Int {
        return mObjects.size
    }

    override fun getItem(position: Int): T {
        return mObjects[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent, mResource)
    }

    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup, resource: Int): View {
        val view: View
        val text: TextView

        if (convertView == null) {
            view = mInflater!!.inflate(resource, parent, false)
        } else {
            view = convertView
        }

        try {
            if (mFieldId == 0) {
                //  If no custom field is assigned, assume the whole resource is a TextView
                text = view as TextView
            } else {
                //  Otherwise, find the TextView field within the layout
                text = view.findViewById(mFieldId)
            }
        } catch (e: ClassCastException) {
            Timber.tag("ArrayAdapter").e("You must supply a resource ID for a TextView")
            throw IllegalStateException("ArrayAdapter requires the resource ID to be a TextView", e)
        }

        val item = getItem(position)
        if (item is CharSequence) {
            text.text = item
        } else {
            text.text = item.toString()
        }

        return view
    }

    fun setDropDownViewResource(resource: Int) {
        this.mDropDownResource = resource
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createViewFromResource(position, convertView, parent, mDropDownResource)
    }

    override fun getFilter(): Filter {
        if (mFilter == null) {
            mFilter = HRArrayFilter()
        }
        return mFilter!!
    }

    private inner class HRArrayFilter : Filter() {
        override fun performFiltering(prefix: CharSequence?): FilterResults {
            val results = FilterResults()

            if (mOriginalValues == null) {
                synchronized(mLock) {
                    mOriginalValues = ArrayList(mObjects)
                }
            }

            if (prefix == null || prefix.length == 0) {
                synchronized(mLock) {
                    val list = ArrayList(mOriginalValues!!)
                    results.values = list
                    results.count = list.size
                }
            } else {
                val prefixString = prefix.toString().lowercase()

                val values = mOriginalValues!!
                val count = values.size

                val newValues = ArrayList<T>()

                for (i in 0 until count) {
                    val value = values[i]
                    val valueText = value.toString().lowercase()
                    val valueTextNoPalatals = toNoPalatals(valueText)
                    val prefixStringNoPalatals = toNoPalatals(prefixString)

                    if (valueText.startsWith(prefixString) || valueTextNoPalatals.startsWith(prefixStringNoPalatals)) {
                        newValues.add(value)
                    } else {
                        val words = valueText.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

                        for (word in words) {
                            if (word.startsWith(prefixString)) {
                                newValues.add(value)
                                break
                            }
                        }
                    }
                }

                results.values = newValues
                results.count = newValues.size
            }

            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            //noinspection unchecked
            mObjects = results.values as MutableList<T>
            if (results.count > 0) {
                notifyDataSetChanged()
            } else {
                notifyDataSetInvalidated()
            }
        }

        private fun toNoPalatals(original: String): String {
             var result = original
            result = result.replace("ñ", "n")
            result = result.replace("ü", "u")
            result = result.replace("é", "e")
            result = result.replace("ô", "o")
            result = result.replace("è", "e")
            result = result.replace("ä", "a")
            result = result.replace("â", "a")
            result = result.replace("É", "E")
            result = result.replace("ô", "o")
            result = result.replace("í", "i")
            return result
        }
    }

    companion object {
        fun createFromResource(context: Context, textArrayResId: Int, textViewResId: Int): SpecialCharArrayAdapter<CharSequence> {
            val strings = context.resources.getTextArray(textArrayResId)
            return SpecialCharArrayAdapter(context, textViewResId, strings)
        }
    }
}
