package net.developia.webconnectiontest;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editName, editId;
    Button btnSend;
    String name, id;




    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        editName=(EditText) findViewById(R.id.editName);
        editId=(EditText) findViewById(R.id.editId);

        btnSend= (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                name=editName.getText().toString();
                id=editId.getText().toString();
                String requestURL=
                        getBaseContext().getText(R.string.site_url)+
                        "test/insert_action.jsp";
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(requestURL);


                List<NameValuePair> paramList = new ArrayList<NameValuePair>();

                paramList.add(new BasicNameValuePair("name",name));
                paramList.add(new BasicNameValuePair("id", id));

                try {
                    post.setEntity(new UrlEncodedFormEntity(paramList, HTTP.UTF_8));
                    HttpResponse response = client.execute(post);
                }catch (Exception e){
                    Log.d("sendPost", e.toString());
                }
            }
        });








    }
}
