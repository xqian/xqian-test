package com.example.xinqian.mysunshine;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment
        extends Fragment
{
    ArrayAdapter<String> m_forcastAdaptor;

    public MainFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // Add this line in order for this fragment to handle menu events.
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.frag, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_refresh)
        {
            new FetchWeatherTask().execute(new FetchWeatherTaskParameter(this, "98005"));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = null;
        try
        {
            ArrayList<String> dataHolder = new ArrayList<String>();
            m_forcastAdaptor = new ArrayAdapter<String>(
                    this.getActivity(),
                    R.layout.listitemlayout,
                    R.id.list_item_forcast_text_view,
                    dataHolder);

            rootView = inflater.inflate(R.layout.fragment_main, container, false);

            // Get ListView object from xml
            ListView listView = (ListView) rootView.findViewById(R.id.main_frag_list_view);
            listView.setAdapter(m_forcastAdaptor);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
                {
                    String forecast = m_forcastAdaptor.getItem(position);
                    Toast.makeText(getActivity(), forecast, Toast.LENGTH_SHORT).show();
                }
            });

            String[] values = new String[] {"123","345"};
            addDataIntoForcastAdaptor(values);
        } catch (Exception e)
        {
            android.util.Log.v("exception", "e:" + e.toString());

            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            e.printStackTrace(printWriter);
            printWriter.flush();

            String stackTrace = writer.toString();
            android.util.Log.v("stack", "stack:" + stackTrace);
        }
        return rootView;
    }

    public void addDataIntoForcastAdaptor(String[] p_values)
    {
        m_forcastAdaptor.addAll(p_values);
    }

    public void clearDataInForcastAdaptor()
    {
        m_forcastAdaptor.clear();
    }
}
