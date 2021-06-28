package com.youkemm.kcommonproject.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Process
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.youkemm.kcommon.base.BaseActivity
import com.youkemm.kcommonproject.R
import com.youkemm.kcommonproject.common.http.ApiService
import com.youkemm.kcommonproject.common.http.CacheService
import com.youkemm.kcommonproject.mvp.contract.WebContract
import com.youkemm.kcommonproject.mvp.presenter.WebPresenter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.layout_toolbar.*


class WebActivity : BaseActivity<ApiService, CacheService, WebPresenter, Any?>(),
        WebContract.IWebView {
    private lateinit var mUrl: String
    private lateinit var mTitle: String

    override val swipeRefreshView: androidx.swiperefreshlayout.widget.SwipeRefreshLayout?
        get() = null

    override val multiStateView: MultiStateView?
        get() = null

    override val layoutResId: Int
        get() = R.layout.activity_web

    override val presenter: WebPresenter
        get() = WebPresenter(this)

    override fun onExtraBundleReceived(bundle: Bundle) {
        super.onExtraBundleReceived(bundle)
        mUrl = bundle.getString("url")?:""
        mTitle = bundle.getString("title")?:""

    }

    override fun initView() {
        super.initView()
        rl_left.setOnClickListener {
            finish()
        }
        tv_middle.text = mTitle

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.loadUrl(mUrl)
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    if (progress_bar != null) {
                        progress_bar.visibility = View.GONE
                    }
                } else {
                    if (progress_bar != null) {
                        progress_bar.visibility = View.VISIBLE
                        progress_bar.progress = newProgress //设置加载进度
                    }
                }
            }

        }

        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                mUrl = url
                return try {
                    if (url.startsWith("http:") || url.startsWith("https:")) {
                        view.loadUrl(url)
                        true
                    } else {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                        true
                    }
                } catch (e: Exception) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    false
                }

            }

        }
    }

    override fun initData() {
    }

    override fun showContentView(data: Any?) {
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        //将WebActivity置于一个单独的进程，在结束的时候杀死进程来避免内存泄漏
        Process.killProcess(Process.myPid())
    }

}
