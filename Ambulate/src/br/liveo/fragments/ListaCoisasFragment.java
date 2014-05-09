package br.liveo.fragments;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import br.liveo.adapter.CoisasAdapter;
import br.liveo.ambulate.CadastroCoisasActivity;
import br.liveo.ambulate.R;
import br.liveo.asynctask.AmbulateFragment;
import br.liveo.dao.CoisasDAO;
import br.liveo.interfaces.IFragment;
import br.liveo.modelo.Coisas;
import br.liveo.utils.Constantes;

public class ListaCoisasFragment extends AmbulateFragment implements IFragment {
	
	private ListView lista;
	private CoisasAdapter coisasAdapter;
	private RelativeLayout layout_sem_registro;	
	private ArrayList<Coisas> listaCoisas = new ArrayList<Coisas>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
				
		View rootView = inflater.inflate(R.layout.lista_coisas_fragment, container, false);	

		lista = (ListView) rootView.findViewById(R.id.lista);						
		layout_sem_registro = (RelativeLayout) rootView.findViewById(R.id.layout_sem_registro);
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
			resultado();						
		}else{
			startTransacao(this, R.string.msg_erro_carregar_lista);
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
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch (item.getItemId()) {
		case R.id.menu_adicionar:
			startActivityForResult(new Intent(getActivity(), CadastroCoisasActivity.class), 0);
			break;
		}
		
		return true;
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
		
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);		
		if (resultCode == 1){ // no futuro isso irá sofre alterações
			startTransacao(this, R.string.msg_erro_carregar_lista);			
		}
	}

	@Override
	public void executar() throws Exception {
		// TODO Auto-generated method stub
		listaCoisas = CoisasDAO.getInstance(getActivity()).obterTudoCoisas();		
	}

	@Override
	public void resultado() {
		// TODO Auto-generated method stub
		layout_sem_registro.setVisibility(RelativeLayout.GONE);	
		
		if (listaCoisas != null && listaCoisas.size() > 0){
			coisasAdapter = new CoisasAdapter(getActivity(), listaCoisas);
			lista.setAdapter(coisasAdapter);
		}else{
			layout_sem_registro.setVisibility(RelativeLayout.VISIBLE);			
		}						
	}
}
