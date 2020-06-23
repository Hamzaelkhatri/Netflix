/*
 * Create by Hamza elkhatri
 */

/*
 * Create by Hamza elkhatri
 */

package com.example.netflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.netflix.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class avatar extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);
        gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(new ListResources(this));
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Toast.makeText(this,"Value is "+ mydata.get(position).Drawble ,Toast.LENGTH_LONG).show();
        setAvatar(mydata.get(position).Drawble,constants.UserName);
    }
    private void setAvatar(final int id, final String email)
    {
        StringRequest request = new StringRequest(StringRequest.Method.POST,constants.URL_AVATAR,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jso =new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jso.getString("message"),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("Email",email);
                params.put("avatar",""+id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    class data
    {
        int Drawble;
        data(int drawable)
        {
            this.Drawble=drawable;
        }
    }
    ArrayList<data>mydata;
    public class ListResources extends BaseAdapter{


        Context context;
        ListResources(Context context)
        {
            this.context=context;
            mydata=new ArrayList<data>();
            mydata.add(new data(R.drawable.avatar_1));
            mydata.add(new data(R.drawable.avatar_2));
            mydata.add(new data(R.drawable.avatar_3));
            mydata.add(new data(R.drawable.avatar_4));
            mydata.add(new data(R.drawable.avatar_5));
            mydata.add(new data(R.drawable.avatar_6));
            mydata.add(new data(R.drawable.avatar_7));
            mydata.add(new data(R.drawable.avatar_8));
            mydata.add(new data(R.drawable.avatar_9));


        }
        @Override
        public int getCount() {
            return mydata.size();
        }

        @Override
        public Object getItem(int position) {
            return mydata.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.lisview,parent,false);
            ImageView image=(ImageView)row.findViewById(R.id.imge);
            final data inge = mydata.get(position);

            image.setImageResource(inge.Drawble);

            return row;
        }
    }
}
