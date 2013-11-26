package com.curtrostudios.testlwp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.preference.DialogPreference;
import android.view.View;
import android.view.ViewGroup;

public final class SocialPref extends DialogPreference
{
  public SocialPref(Context paramContext)
  {
    this(paramContext, (byte)0);
  }

  private SocialPref(Context paramContext, byte paramByte)
  {
    this(paramContext, '\000');
  }

  private SocialPref(Context paramContext, char paramChar)
  {
    super(paramContext, null);
    setWidgetLayoutResource(R.layout.pref_social);
    setTitle(R.string.SocialTitle);
    setSummary(R.string.SocialSummary);
    setDialogTitle(R.string.SocialTitle);
    setDialogLayoutResource(R.layout.pref_social_dialog);
    setNegativeButtonText(R.string.Cancel);
  }

  private void a(View paramView, int paramInt)
  {
    if (paramView != null)
    {
      View localView = paramView.findViewById(paramInt);
      if (localView != null)
        localView.setOnClickListener(new s(this));
    }
  }

  private void b(View paramView, int paramInt)
  {
    if (paramView != null)
    {
      View localView = paramView.findViewById(paramInt);
      if (localView != null)
        localView.setOnClickListener(new s(this));
    }
  }

  private void c(View paramView, int paramInt)
  {
    if (paramView != null)
    {
      View localView = paramView.findViewById(paramInt);
      if (localView != null)
        localView.setOnClickListener(new s(this));
    }
  }

  private void d(View paramView, int paramInt)
  {
    if (paramView != null)
    {
      View localView = paramView.findViewById(paramInt);
      if (localView != null)
        localView.setOnClickListener(new s(this));
    }
  }

  protected final void a()
  {
    Dialog localDialog = getDialog();
    if (localDialog != null)
      localDialog.dismiss();
    Context localContext = getContext();
    if (localContext != null)
    {
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setData(Uri.parse("https://plus.google.com/gplus_account_here"));
      localContext.startActivity(localIntent);
    }
  }

  protected final void b()
  {
    Dialog localDialog = getDialog();
    if (localDialog != null)
      localDialog.dismiss();
    Context localContext = getContext();
    if (localContext != null)
    {
      Intent localIntent1 = new Intent();
      localIntent1.setAction("android.intent.action.VIEW");
      localIntent1.setData(Uri.parse("fb://profile/profile_id"));
      Intent localIntent2 = ac.a(localContext, localIntent1, false);
      if (localIntent2 == null)
      {
        localIntent2 = new Intent();
        localIntent2.setAction("android.intent.action.VIEW");
        localIntent2.setData(Uri.parse("https://facebook.com/profile_page"));
      }
      localContext.startActivity(localIntent2);
    }
  }

  protected final void c()
  {
    Dialog localDialog = getDialog();
    if (localDialog != null)
      localDialog.dismiss();
    Context localContext = getContext();
    if (localContext != null)
    {
      Intent localIntent1 = new Intent();
      localIntent1.setAction("android.intent.action.VIEW");
      localIntent1.setData(Uri.parse("twitter://user?screen_name=screen_name_here"));
      Intent localIntent2 = ac.a(localContext, localIntent1, false);
      if (localIntent2 == null)
      {
        localIntent2 = new Intent();
        localIntent2.setAction("android.intent.action.VIEW");
        localIntent2.setData(Uri.parse("https://twitter.com/intent/user?screen_name=screen_name_here"));
      }
      localContext.startActivity(localIntent2);
    }
  }

  protected final void d()
  {
    Dialog localDialog = getDialog();
    if (localDialog != null)
      localDialog.dismiss();
    Context localContext = getContext();
    if (localContext != null)
    {
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setData(Uri.parse("http://www.youtube.com/user/username_here"));
      localContext.startActivity(localIntent);
    }
  }

  protected final View onCreateDialogView()
  {
    View localView = super.onCreateDialogView();
    a(localView, d.tv_social_gplus);
    b(localView, d.tv_social_facebook);
    c(localView, d.tv_social_twitter);
    d(localView, d.tv_social_ytube);
    return localView;
  }

  protected final View onCreateView(ViewGroup paramViewGroup)
  {
    View localView = super.onCreateView(paramViewGroup);
    a(localView, d.iv_social_gplus);
    b(localView, d.iv_social_facebook);
    c(localView, d.iv_social_twitter);
    d(localView, d.iv_social_ytube);
    return localView;
  }
}