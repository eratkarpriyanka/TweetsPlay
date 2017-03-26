package com.codepath.apps.tweetsplay.network;

/**
 * This interface declares Webservice callback methods
 * Components that register for these methods, can implement these methods to
 * carry out "post webservice call" functionalities
 */
public interface IWSResponseListener {

    public void onSuccess(Object response);
    public void onFailure(Object response);
}

