package br.liveo.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.liveo.ambulate.R;
import br.liveo.modelo.Coisas;

public class CoisasAdapter extends BaseAdapter {

	private Context context;
	private List<Coisas> listaCoisas = null;
	private ArrayList<Coisas> arraylistaCoisas;

	public CoisasAdapter(Context context, List<Coisas> listaCoisas) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.listaCoisas = listaCoisas;
		this.arraylistaCoisas = new ArrayList<Coisas>();
		this.arraylistaCoisas.addAll(listaCoisas);		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listaCoisas.size();
	}

	@Override
	public Coisas getItem(int position) {
		// TODO Auto-generated method stub
		return listaCoisas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public class ViewHolder {
		TextView txtId;		
		TextView txtNome;			
	}	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
				
		final ViewHolder holder;
		
		if ( convertView == null ) {
			
			holder = new ViewHolder();
			
			LayoutInflater inflater = ( LayoutInflater ) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);			
			convertView = inflater.inflate(R.layout.lista_coisas_items, null);		
	
			holder.txtId = ( TextView ) convertView.findViewById(R.id.txtId);
			holder.txtNome = ( TextView ) convertView.findViewById(R.id.txtNome);			
									
			convertView.setTag(holder);			
		} else {			
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtId.setText(String.valueOf(listaCoisas.get(position).getId()));
		holder.txtNome.setText(listaCoisas.get(position).getNome());		
	    
		return convertView;
	}
	
	public void filtrarCoisas(String charText) {
		
		charText = charText.toLowerCase(Locale.getDefault());
		
		listaCoisas.clear();
		
		if (charText.length() == 0) {
			listaCoisas.addAll(arraylistaCoisas);
		} 
		else 
		{
			for (Coisas coisas : arraylistaCoisas) 
			{
				if (coisas.getNome().toLowerCase(Locale.getDefault()).contains(charText)) 
				{					
					listaCoisas.add(coisas);
				}
			}
		}
		
		notifyDataSetChanged();
	}
	
}
