package br.liveo.fragments;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ListView;
import br.liveo.adapter.CoisasAdapter;
import br.liveo.ambulate.R;
import br.liveo.dao.CoisasDAO;
import br.liveo.modelo.Coisas;
import br.liveo.utils.Constantes;

public class ListaCoisasFragment extends Fragment {
	
	private ListView lista;
	private CoisasAdapter coisasAdapter;
	private ArrayList<Coisas> listaCoisas = new ArrayList<Coisas>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
				
		View rootView = inflater.inflate(R.layout.lista_coisas_fragment, container, false);		
		lista = (ListView) rootView.findViewById(R.id.lista);		
		
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));		
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
		
		if (savedInstanceState != null){
			
			listaCoisas = savedInstanceState.getParcelableArrayList(Constantes.OBJ_COISAS);
			
			if (listaCoisas != null && listaCoisas.size() > 0){
				coisasAdapter = new CoisasAdapter(getActivity(), listaCoisas);
				lista.setAdapter(coisasAdapter);
			}
			
		}else{
			new ExecutarConsultaCoisas().execute();
		}
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putParcelableArrayList(Constantes.OBJ_COISAS, listaCoisas);
	}
		
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		
		inflater.inflate(R.menu.menu, menu);
 	    
		SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menu_pesquisar));
	    searchView.setQueryHint(this.getString(R.string.pesquisar));
	    
	    ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text))
        .setHintTextColor(getResources().getColor(R.color.branco));	    
	    searchView.setOnQueryTextListener(onQuerySearchView);		
	}	
	
	private OnQueryTextListener onQuerySearchView = new OnQueryTextListener() {
		
		@Override
		public boolean onQueryTextSubmit(String arg0) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean onQueryTextChange(String texto) {
			// TODO Auto-generated method stub
			coisasAdapter.filtrarCoisas(texto);
			return false;
		}
	};
	
	private class ExecutarConsultaCoisas extends AsyncTask<Void, Void, ArrayList<Coisas>>{
		
		@Override
		protected ArrayList<Coisas> doInBackground(Void... params) {
			// TODO Auto-generated method stub
			return CoisasDAO.getInstance(getActivity()).obterTudoCoisas();
		}
		
		@Override
		protected void onPostExecute(ArrayList<Coisas> result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			if (result != null && result.size() > 0){
				listaCoisas = result;
				coisasAdapter = new CoisasAdapter(getActivity(), result);
				lista.setAdapter(coisasAdapter);
			}
		}
	}
}
