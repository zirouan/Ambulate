package br.liveo.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import br.liveo.interfaces.IFragment;

public class AsyncTaskFragment extends AsyncTask<Void, Void, Boolean> {
	
	private static final String TAG = "AsyncTaskFragment";

	private int msgErro;	
	private int progressId;	
	private final Fragment fragment;
	private Exception exceptionErro;	
	private final IFragment transacao;	
	
	public AsyncTaskFragment(Fragment fragment, IFragment transacao, int progressId, int msgErro) {
		this.msgErro = msgErro;
		this.fragment = fragment;
		this.transacao = transacao;
		this.progressId = progressId;		
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		try {
			iniciarProgressBar();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
		}
	}
	
	@Override
	protected Boolean doInBackground(Void... params) {
		try {
			transacao.executar();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage(), e);
			this.exceptionErro = e;			
			return false;
		} finally {
			try {
				Activity ac = fragment.getActivity();
				if(ac != null) {
					ac.runOnUiThread(
						new Runnable() {
							@Override
							public void run() {
								finalizarProgressBar();
							}
						});
				}
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
			}
		}
		return true;
	}
	
	@Override
	protected void onPostExecute(Boolean ok) {
		if (ok) {
			transacao.resultado();
		} else {			
			Toast.makeText(fragment.getActivity(), msgErro + exceptionErro.getMessage(), Toast.LENGTH_SHORT).show();			
		}
	}
	
	private void iniciarProgressBar() {
		View view = fragment.getView();
		if (view != null) {
			RelativeLayout progress = (RelativeLayout) view.findViewById(progressId);
			if (progress != null) {
				progress.setVisibility(RelativeLayout.VISIBLE);
			}
		}	
	}
	public void finalizarProgressBar() {
		View view = fragment.getView();
		if (view != null) {
			final RelativeLayout progress = (RelativeLayout) view.findViewById(progressId);
			if (progress != null) {
				progress.setVisibility(RelativeLayout.GONE);
			}
		}							
	}
}
