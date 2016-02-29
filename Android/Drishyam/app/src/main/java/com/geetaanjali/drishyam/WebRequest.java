package com.geetaanjali.drishyam;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Amon on 20/9/2558.
 */
final class WebRequest {
        public static  void callService(AsyncHttpResponseHandler handler,String username,String ajaxMessage,String url) {
        AsyncHttpClient mClient = new AsyncHttpClient();
        mClient.get("https://script.google.com/macros/s/AKfycbzeSMemNvMFz_IKIaimhM4O_1zV0DUNvnlop_PuEBmBmAWMrAI/exec", handler);
        RequestParams params;
        params = new RequestParams();
        params.add("user_name", username);
        params.add("text", ajaxMessage);

            mClient.post(url,params,handler);

    }
}