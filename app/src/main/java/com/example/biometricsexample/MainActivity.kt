package com.example.biometricsexample

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity() {

    private lateinit var btnBiometricLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBiometricLogin = findViewById(R.id.btn_biometric_login)

        btnBiometricLogin.setOnClickListener {
            startBiometricAuthentication()
        }
    }

    private fun startBiometricAuthentication() {
        // Executor to run the authentication on the main thread
        val executor: Executor = ContextCompat.getMainExecutor(this)

        // BiometricPrompt instance to trigger authentication
        val biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                // Handle success (e.g., navigate to a new screen)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                // Handle failure (e.g., show a message to the user)
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                // Handle error (e.g., show error message to the user)
            }
        })

        // Create a biometric prompt dialog
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Login")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Cancel")
            .build()

        // Display the biometric prompt
        biometricPrompt.authenticate(promptInfo)
    }
}
