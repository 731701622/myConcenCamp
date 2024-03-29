package com.edu.wkx.myConcencamp.view.activity

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.KeyEvent
import android.view.WindowManager
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.webkit.WebViewClient
import com.edu.wkx.myConcencamp.R
import com.edu.wkx.myConcencamp.core.sonic.HostSonicRuntime
import com.edu.wkx.myConcencamp.core.sonic.SonicJavaScriptInterface
import com.edu.wkx.myConcencamp.core.sonic.SonicSessionClientImpl
import com.tencent.sonic.sdk.SonicConfig
import com.tencent.sonic.sdk.SonicEngine
import com.tencent.sonic.sdk.SonicSession
import kotlinx.android.synthetic.main.activity_webview.*


class WebviewActivity : AppCompatActivity(), LifecycleRegistryOwner {

    private var lifeRegister = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry {
        return lifeRegister
    }

    companion object {
        val PARAM_URL = "param_url"
    }

    private var sonicSession: SonicSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.getStringExtra(PARAM_URL)
        if (TextUtils.isEmpty(url)) {
            finish()
            return
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED)

        if (!SonicEngine.isGetInstanceAllowed()) {
            SonicEngine.createInstance(HostSonicRuntime(applicationContext),
                    SonicConfig.Builder().build())
        }

        val sonicSessionClient: SonicSessionClientImpl? = null
        setContentView(R.layout.activity_webview)
        webView.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (sonicSession != null) {
                    sonicSession?.sessionClient?.pageFinish(url)
                }
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldInterceptRequest(view: WebView?, request: WebResourceRequest?): WebResourceResponse? {
                return shouldInterceptRequest(view, request?.url.toString())
            }

            override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
                if (sonicSession != null) {
                    return sonicSession?.sessionClient?.requestResource(url) as WebResourceResponse
                }
                return null
            }
        }

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.removeJavascriptInterface("searchBoxJavaBridge_")
        intent.putExtra(SonicJavaScriptInterface.PARAM_LOAD_URL_TIME, System.currentTimeMillis())
        webView.addJavascriptInterface(SonicJavaScriptInterface(sonicSessionClient, intent), "sonic")

        webSettings.allowContentAccess = true
        webSettings.databaseEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.setAppCacheEnabled(true)
        webSettings.savePassword = false
        webSettings.saveFormData = false
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true

        if (sonicSessionClient != null) {
            sonicSessionClient.bindWebView(webView)
            sonicSessionClient.clientReady()
        } else {
            webView.loadUrl(url)
        }
    }

    override fun onDestroy() {
        if (null != sonicSession) {
            sonicSession?.destroy()
            sonicSession = null
        }
        super.onDestroy()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}