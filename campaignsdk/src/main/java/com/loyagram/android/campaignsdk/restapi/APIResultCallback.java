package com.loyagram.android.campaignsdk.restapi;

import android.content.Context;
import android.widget.Toast;

import com.loyagram.android.campaignsdk.general.enums.ApiErrors;
import com.loyagram.android.campaignsdk.models.BaseResult;
import com.loyagram.android.campaignsdk.models.ErrorModel;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Class which handles the success and error call backs of http request.
 */
public abstract class APIResultCallback<T> {
    public abstract void onSuccess(T result);

    public abstract void onError(List<ErrorModel> errors);

    /**
     * Method which parse the http request result invoke corresponding call backs. if the results is not equal to null
     * success call back will be invoked otherwise error.
     * @param e       Exception
     * @param result  Api result
     * @return true if the results not equal to null.
     */
    public boolean parseResult( Exception e, T result) {

        if (e != null) {
            e.printStackTrace();
            ErrorModel em = new ErrorModel();
            if (e.getClass().equals(SocketTimeoutException.class)) {
                em.setMsg("Network Error. Waited for so long. :-(");
                em.setType(ApiErrors.TIME_OUT.toString());
//                Toast.makeText(context, em.getMsg(),
//                        Toast.LENGTH_SHORT).show();
            } else if (e.getClass().equals(SocketException.class)) {
                em.setMsg("oops... Connectivity issue :-(");
                em.setType(ApiErrors.CONNECTION.toString());
//                Toast.makeText(context, em.getMsg(),
//                        Toast.LENGTH_SHORT).show();
            } else if (e.getClass().equals(ConnectException.class)) {
                em.setMsg("oops... Connectivity issue :-(");
                em.setType(ApiErrors.CONNECTION.toString());
//                Toast.makeText(context, em.getMsg(),
//                        Toast.LENGTH_SHORT).show();
            } else {
                em.setMsg("Something unexpected has happened, please try again later: -(");
                //Toast.makeText(context, em.getMsg(),
                    //    Toast.LENGTH_SHORT).show();
            }
            em.setField("nw");
            List<ErrorModel> list = new ArrayList();
            list.add(em);
            onError(list);
            return false;
        }
        if (result != null) {
            onSuccess(result);
            return true;
        }
        ErrorModel em = new ErrorModel();
        em.setMsg("Some thing has Happened!");
        em.setType(ApiErrors.UNKNOWN.toString());
//        Toast.makeText(context, em.getMsg(),
//                Toast.LENGTH_SHORT).show();
        List<ErrorModel> list = new ArrayList();
        list.add(em);
        onError(list);
        return false;

    }
}
