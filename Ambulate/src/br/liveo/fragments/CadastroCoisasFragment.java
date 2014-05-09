package br.liveo.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import br.liveo.ambulate.R;
import br.liveo.asynctask.AmbulateFragment;
import br.liveo.dao.CoisasDAO;
import br.liveo.interfaces.IFragment;
import br.liveo.utils.Utils;

public class CadastroCoisasFragment extends AmbulateFragment implements IFragment{

	private EditText edtNome;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.cadastro_coisas_fragment, container, false);		
		edtNome = (EditText) rootView.findViewById(R.id.edtNome);		
		
		rootView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT ));		
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		setHasOptionsMenu(true);
		
		customBotoesConcluidoDescartar();		
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}
	
	private boolean validacao(){		
		return ( edtNome.getText() != null && !edtNome.getText().toString().equals(""));
	}
	
	private void customBotoesConcluidoDescartar(){
		
		LayoutInflater inflater = (LayoutInflater) ((ActionBarActivity)getActivity()).getSupportActionBar().getThemedContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View customActionBarView = null;
		
		customActionBarView = inflater.inflate(R.layout.actionbar_custom_view_concluido_descartar, null);			        
        customActionBarView.findViewById(R.id.actionbar_concluido).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    	if (validacao()){
                    		startTransacao(CadastroCoisasFragment.this, R.string.msg_erro_coisa_salva);
                    	}else{
                    		Utils.toastShort(getActivity(), R.string.msg_aviso_informe_nome);
                    	}
                    }
                });
                        	
        
        customActionBarView.findViewById(R.id.actionbar_descartar).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish(); 
                    }
                });        	        	
                
        ((ActionBarActivity)getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM,ActionBar.DISPLAY_SHOW_CUSTOM | 
				                                ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
		
        ((ActionBarActivity)getActivity()).getSupportActionBar().setCustomView(customActionBarView,
				new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
						ViewGroup.LayoutParams.MATCH_PARENT));   		
	}

	@Override
	public void executar() throws Exception {
		// TODO Auto-generated method stub
		CoisasDAO.getInstance(getActivity()).salvarCoisa(edtNome.getText().toString());		
	}

	@Override
	public void resultado() {
		// TODO Auto-generated method stub
		Utils.toastShort(getActivity(), R.string.msg_aviso_coisa_salva);				
		getActivity().setResult(1);			
		getActivity().finish();		
	}
}
