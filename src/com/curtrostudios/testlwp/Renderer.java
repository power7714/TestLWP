package com.curtrostudios.testlwp;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import rajawali.animation.RotateAnimation3D;
import rajawali.lights.ALight;
import rajawali.lights.DirectionalLight;
import rajawali.materials.DiffuseMaterial;
import rajawali.materials.SimpleMaterial;
import rajawali.math.Number3D;
import rajawali.primitives.Cube;
import rajawali.primitives.Plane;
import rajawali.renderer.RajawaliRenderer;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.view.animation.AccelerateDecelerateInterpolator;

public class Renderer extends RajawaliRenderer implements OnSharedPreferenceChangeListener {
        private RotateAnimation3D mAnim;
        private Plane largeCanvasPlane;
        private String mText = "Hello!";
        private ALight light;
        private int LIGHT_INT = 1;
        private int ROTATION_RATE = 2000;

        public Renderer(Context context) {
                super(context);
        }

        public void initScene() {
                this.light = new DirectionalLight();
                this.light.setPower(this.LIGHT_INT);
                this.light.setPosition(0, 0, -5);
                getCamera().setZ(+10f);
        		getCamera().setLookAt(0, +1, +2);
        		getCamera().setFarPlane(5000);
                
                createCustomPlane();

                Cube cube = new Cube(1);
                DiffuseMaterial material = new DiffuseMaterial();
                cube.setMaterial(material);
                Bitmap texture = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_launcher);
                cube.addTexture(mTextureManager.addTexture(texture));
                cube.addLight(this.light);
                cube.setY(1);
                addChild(cube);

                Number3D localNumber3D = new Number3D(0.0F, 10.0F, 0.0F);
                localNumber3D.normalize();
                this.mAnim = new RotateAnimation3D(localNumber3D, 360.0F);
                this.mAnim.setDuration(this.ROTATION_RATE);
                this.mAnim.setRepeatCount(-1);
                this.mAnim.setInterpolator(new AccelerateDecelerateInterpolator());
                this.mAnim.setTransformable3D(cube);
        }

        protected void createCustomPlane(){
        	if (getChildren().contains(largeCanvasPlane)){
        		mChildren.remove(largeCanvasPlane);
        		largeCanvasPlane = null;
        	}
    		// Create a bitmap to draw to
    		final Bitmap generatedBitmap = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888);
    		
    		final Canvas canvas = new Canvas(generatedBitmap);
    		
    		// Paint for coloring
    		final Paint paint = new Paint();
    		Typeface tanger = Typeface.createFromAsset(mContext.getAssets(),"fonts/Mostwasted.ttf");
    	    paint.setAntiAlias(true);
    	    paint.setSubpixelText(true);
    	    paint.setTypeface(tanger);
    	    paint.setStyle(Paint.Style.FILL);
    	    paint.setColor(Color.BLUE);
    	    //paint.setAlpha(0);
    	    paint.setTextSize(55);
    	    paint.setTextAlign(Align.CENTER);
    	    //canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
    	    canvas.drawText(this.mText, 256, 256, paint);
    	    
    		final SimpleMaterial simpleMaterial = new SimpleMaterial();
    		simpleMaterial.addTexture(mTextureManager.addTexture(generatedBitmap));
    		
    		
    		largeCanvasPlane = new Plane(2, 2, 2, 2);
    		largeCanvasPlane.setTransparent(true);
    		largeCanvasPlane.setPosition(0, -0.70f, +1.5f);
    		largeCanvasPlane.addLight(light);
    		largeCanvasPlane.setMaterial(simpleMaterial);
    		addChild(largeCanvasPlane);
        }

        public void onDrawFrame(GL10 glUnused) {
                super.onDrawFrame(glUnused);
        }
        
        public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
        {
          if (paramString.equals("pref_rotation_rate"))
          {
            this.ROTATION_RATE = this.preferences.getInt("pref_rotation_rate", this.ROTATION_RATE);
            this.mAnim.setDuration(this.ROTATION_RATE);
          }
          if (paramString.equals("pref_light_intensity"))
          {
            this.LIGHT_INT = this.preferences.getInt("pref_light_intensity", this.LIGHT_INT);
            this.light.setPower(this.LIGHT_INT);
          }
          if (paramString.equals("pref_text"))
          {
        	  this.mText = this.preferences.getString("pref_text", "Tag This!");
          }
        }
        
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            super.onSurfaceCreated(gl, config);
            this.preferences.registerOnSharedPreferenceChangeListener(this);
            this.mAnim.start();
    }
        
        public void onSurfaceDestroyed()
        {
          super.onSurfaceDestroyed();
          this.preferences.unregisterOnSharedPreferenceChangeListener(this);
        }
        
}