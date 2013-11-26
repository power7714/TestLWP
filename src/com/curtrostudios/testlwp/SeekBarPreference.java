package com.curtrostudios.testlwp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarPreference extends DialogPreference
  implements SeekBar.OnSeekBarChangeListener
{
  private Context mContext;
  private int mDefault;
  private int mMax;
  private int mMin;
  private SeekBar mSeekBar;
  private String mSuffix;
  private int mValue = 0;
  private TextView mValueText;
  
  

  public SeekBarPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    //setThumb(mContext.getResources().getDrawable(R.drawable.thumb));
    this.mContext = paramContext;
    this.mSuffix = paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "text");
    this.mDefault = paramAttributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "defaultValue", 50);
    this.mMax = paramAttributeSet.getAttributeIntValue("http://schemas.android.com/apk/res/android", "max", 400);
    this.mMin = paramAttributeSet.getAttributeIntValue(null, "min", 0);
  }
  
  @Override
	protected View onCreateView(ViewGroup parent) {
		View newView = super.onCreateView(parent);

      ((TextView)newView.findViewById(android.R.id.title)).setTextColor(Color.RED);
      ((TextView)newView.findViewById(android.R.id.summary)).setTextColor(Color.YELLOW);
      
   	return newView;
	}

  public int getMax()
  {
    return this.mMax;
  }

  public int getProgress()
  {
    return this.mValue;
  }

  @Override
protected void onBindDialogView(View paramView)
  {
    super.onBindDialogView(paramView);
    this.mSeekBar.setMax(this.mMax);
    this.mSeekBar.setProgress(this.mValue);
    this.mSeekBar.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.progress));
    this.mSeekBar.setThumb(mContext.getResources().getDrawable(R.drawable.thumb));
    
  }

  @Override
protected View onCreateDialogView()
  {
	  
	  Typeface cFont = Typeface.createFromAsset(this.mContext.getAssets(),"fonts/adrip1.ttf");
    LinearLayout localLinearLayout = new LinearLayout(this.mContext);
    localLinearLayout.setOrientation(1);
    localLinearLayout.setPadding(6, 6, 6, 6);
    localLinearLayout.setBackgroundColor(Color.WHITE);
    this.mValueText = new TextView(this.mContext);
    this.mValueText.setGravity(1);
    this.mValueText.setTypeface(cFont);
    this.mValueText.setTextSize(20.0F);
    this.mValueText.setTextColor(Color.RED);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -6);
    localLinearLayout.addView(this.mValueText, localLayoutParams);
    this.mSeekBar = new SeekBar(this.mContext);
    this.mSeekBar.setOnSeekBarChangeListener(this);
    localLinearLayout.addView(this.mSeekBar, new LinearLayout.LayoutParams(-1, -6));
    if (shouldPersist())
      this.mValue = getPersistedInt(this.mDefault);
    this.mSeekBar.setMax(this.mMax);
    this.mSeekBar.setProgress(this.mValue);
    
    return localLinearLayout;
  }

  @Override
protected void onDialogClosed(boolean paramBoolean)
  {
    if ((paramBoolean) && (shouldPersist()))
      persistInt(this.mValue);
  }

  @SuppressLint("UseValueOf")
public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
	  Typeface cFont = Typeface.createFromAsset(this.mContext.getAssets(),"fonts/adrip1.ttf");
    this.mValue = paramInt;
    if (this.mValue < this.mMin)
      this.mValue = this.mMin;
    String str = String.valueOf(this.mValue);
    TextView localTextView = this.mValueText;
    if (this.mSuffix == null);
    localTextView.setText(str);
    localTextView.setTextColor(Color.RED);
    localTextView.setTypeface(cFont);
    callChangeListener(new Integer(paramInt));
  }

  @Override
protected void onSetInitialValue(boolean paramBoolean, Object paramObject)
  {
    super.onSetInitialValue(paramBoolean, paramObject);
    int i = 0;
    if ((paramBoolean) && (shouldPersist()))
      i = getPersistedInt(this.mDefault);
    this.mValue = i;
  }

  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
  }

  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
  }

  public void setMax(int paramInt)
  {
    this.mMax = paramInt;
  }

  public void setProgress(int paramInt)
  {
    this.mValue = paramInt;
    if (this.mSeekBar != null)
      this.mSeekBar.setProgress(paramInt);
  }
}