package gr.uom.kiniti.bank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity
{

	protected void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		setContentView(R.layout.splash);
		
		Thread timer = new Thread()
		{
			public void run()
			{
				try
				{
					sleep(5000);
				}
				catch (InterruptedException ie)
				{
					System.out.println(ie);
				}
				finally
				{
					// manifest sync
					Intent openStartingPoint = new Intent("gr.uom.kiniti.bank.STARTINGPOINT");
					startActivity(openStartingPoint);
				}
			}
		};

		timer.start();
	}

	protected void onPause()
	{
		super.onPause();
		finish();
	}

}
