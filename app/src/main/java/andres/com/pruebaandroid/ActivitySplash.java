package andres.com.pruebaandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ActivitySplash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        Animation animRight = AnimationUtils.loadAnimation(this, R.anim.animation_translate_right);
        Animation animLeft = AnimationUtils.loadAnimation(this, R.anim.animation_translate_left);
        Animation animZoom = AnimationUtils.loadAnimation(this, R.anim.animation_zoomx2);


        ImageView ivSkullLeft = (ImageView) findViewById(R.id.ivSplash1);
        ImageView ivLove= (ImageView) findViewById(R.id.ivSplash2);
        ImageView ivSkullRight = (ImageView) findViewById(R.id.ivSplash3);

        ivSkullLeft.startAnimation(animRight);
        ivLove.startAnimation(animZoom);
        ivSkullRight.startAnimation(animLeft);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =  new Intent(ActivitySplash.this, ActivityListadoCategoria.class);
                startActivity(intent);
                finish();

            }
        }, 1000);

    }

}
