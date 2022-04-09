package com.example.amazingstocks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession
import com.amplifyframework.auth.cognito.AWSCognitoUserPoolTokens
import com.amplifyframework.auth.result.AuthSessionResult
import com.amplifyframework.core.Amplify

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Amplify.Auth.signIn("AmazingStocks@2022", "AS@Bengaluru2022",
            { result ->
                if (result.isSignInComplete) {
                    Log.i("AuthQuickstart", "Sign in succeeded")
                } else {
                    Log.i("AuthQuickstart", "Sign in not complete")
                }
            },
            { Log.e("AuthQuickstart", "Failed to sign in", it) }
        )
        Amplify.Auth.fetchAuthSession(
            {
                val session = it as AWSCognitoAuthSession
                val token = it.userPoolTokens.value as AWSCognitoUserPoolTokens
                Log.i("AmplifyQuickstart", "token : "+ token.accessToken)
            },
            { error -> Log.e("AmplifyQuickstart", "Failed to fetch auth session", error) }
        )


    }
}