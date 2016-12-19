package com.team.nee.jsnews;


import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.hardware.SensorManager;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.OrientationEventListener;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoginActivity extends BaseActivity {
	
    OrientationEventListener mOrientationListener;
    
    private WebView mWebView;
    private WebChromeClient client;
    private String url = "http://amjsc1111.com";
    private String defaultUrl = "http://amjsc1111.com";
    private String lastUrl = "";
    
    private ProgressDialog dialog = null;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("webview", "on create");
        setContentView(R.layout.activity_broker_login);
        initView();
        
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	 
        if (keyCode == KeyEvent.KEYCODE_BACK
                  && event.getRepeatCount() == 0) {
        		this.mWebView.goBack();
              return true;
          }
          return super.onKeyDown(keyCode, event);
    }
    
    private void initView() { 
    	
    	
    	mOrientationListener = new OrientationEventListener(this,  
                SensorManager.SENSOR_DELAY_NORMAL) {
    			
                @Override  
                public void onOrientationChanged(int orientation) {
                	if(orientation == 180 || orientation == 0){
                		//竖屏
//                		fullscreen(false);
                	}else if(orientation == 90 || orientation == 270){
                		//横屏
//                		fullscreen(true);
                	}
                    Log.v("orientation", 
                        "Orientation changed to " + orientation);  
                }  
            };  
      
           if (mOrientationListener.canDetectOrientation()) {  
               Log.v("orientation", "Can detect orientation");  
               mOrientationListener.enable();  
           } else {  
               Log.v("orientation", "Cannot detect orientation");  
               mOrientationListener.disable();  
           }  
    	
    	
    	
    	
         mWebView = (WebView) this.findViewById(R.id.wb);
        dialog =new  ProgressDialog(this);
        dialog.setTitle("页面加载中，请稍后..");
        client = new WebChromeClient(); 
        
        //设置
        WebSettings webSettings = mWebView.getSettings();
        //设置编码
        webSettings.setDefaultTextEncodingName("utf-8");
        // 让WebView能够执行javaScript
        webSettings.setJavaScriptEnabled(true);
        // 让JavaScript可以自动打开windows
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUserAgentString("Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
        // 设置缓存
        webSettings.setAppCacheEnabled(true);
        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        //不显示webview缩放按钮 
        // 设置缓存模式,一共有四种模式
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置缓存路径
        //        webSettings.setAppCachePath(""); 
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
        // 设置默认字体大小
        webSettings.setDefaultFontSize(12); 
         
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
            	//Log.v("webview",  url);
            	lastUrl =  url;
                mWebView.loadUrl(url);
                return true;
            }

          @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                        dialog.show();
        	  	//Log.v("webview",  url);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
 
            }
            
            //加载https时候，需要加入 下面代码
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();  //接受所有证书
              	Log.v("webview error",  error.getUrl().toString());
            }


        });

        mWebView.setWebChromeClient(client); 
        loadUrl(url);
    }


   public void loadUrl(String url)
    {
        if(mWebView != null)
        {
            mWebView.loadUrl(url);
        }
    }
    
    @Override
    public void onPause() {  
        super.onPause();
//        mWebView.pauseTimers();
    }  
    
    private void fullscreen(boolean enable) {
        if (enable) { //显示状态栏
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
            getWindow().setAttributes(lp);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        } else { //隐藏状态栏
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().setAttributes(lp);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        mWebView.reload();
//        loadUrl(this.lastUrl);
    }
    
    public void onConfigurationChanged(Configuration newConfig) {  
    	
        super.onConfigurationChanged(newConfig);
//        if(mOrientationListener.canDetectOrientation()){ 
//        	return ;
//        }
       // Log.v("change","change way" + this.getResources().getConfiguration().orientation);
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ){
        	//横屏
        	fullscreen(true);
        }else if( this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ){//竖屏
        	fullscreen(false);
        }
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //隐藏状态栏
//        //定义全屏参数
//        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
//        //获得当前窗体对象
//        Window window = this.getWindow();
//        //设置当前窗体为全屏显示
//        window.setFlags(flag, flag);
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) { 
//       	 	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//       	 	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {  
//        	//竖屏
//        	 this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        	 this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        }  
    } 
    
    @Override    
    protected void onResume(){     
        super.onResume();    
        mWebView.resumeTimers();    
    }  
    
    protected void onDestroy() {
		super.onDestroy();
		mOrientationListener.disable();
	}
    
}
