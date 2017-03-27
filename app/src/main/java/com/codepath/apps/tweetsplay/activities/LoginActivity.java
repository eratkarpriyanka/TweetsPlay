package com.codepath.apps.tweetsplay.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.codepath.apps.tweetsplay.R;
import com.codepath.apps.tweetsplay.models.SampleModel;
import com.codepath.apps.tweetsplay.network.TwitterClient;
import com.codepath.oauth.OAuthLoginActionBarActivity;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class LoginActivity extends OAuthLoginActionBarActivity<TwitterClient> {

	private static final String TAG = LoginActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

	}


	// Inflate the menu; this adds items to the action bar if it is present.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	// OAuth authenticated successfully, launch primary authenticated activity
	// i.e Display application "homepage"
	@Override
	public void onLoginSuccess() {

		Log.d(TAG,"LOgin Success");
		Intent intent = new Intent(this, TimelineActivity.class);
		startActivity(intent);

	}

	// OAuth authentication flow failed, handle the error
	// i.e Display an error dialog or toast
	@Override
	public void onLoginFailure(Exception e) {

		Log.d(TAG,"LOgin Failure");
		e.printStackTrace();
	}

	// Click handler method for the button used to start OAuth flow
	// Uses the client to initiate OAuth authorization
	// This should be tied to a button used to login
	public void loginToRest(View view) {
		getClient().connect();
	}

}
