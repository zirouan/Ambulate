package br.liveo.asynctask;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import br.liveo.ambulate.R;
import br.liveo.interfaces.IFragment;

public class AmbulateFragment extends Fragment {

	private AsyncTaskFragment task;	
	private int progressId = R.id.layout_progressBar;
		
	public void startTransacao(IFragment transacao, int msgErro) {		
		task = new AsyncTaskFragment(this, transacao, progressId, msgErro);
		task.execute();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		if ( task != null){			
			boolean executando = task.getStatus().equals(AsyncTask.Status.RUNNING);			
			if (executando){				
				task.cancel(true);
				task.finalizarProgressBar();				
			}			
		}
	}	
}
