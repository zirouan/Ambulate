package br.liveo.ambulate;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import br.liveo.fragments.CadastroCoisasFragment;

public class CadastroCoisasActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.container_fragment);
		
		if (savedInstanceState == null){
			FragmentManager fragmentManager = getSupportFragmentManager();							
			fragmentManager.beginTransaction().replace(R.id.container, new CadastroCoisasFragment()).commit();
		}		
	}
}
