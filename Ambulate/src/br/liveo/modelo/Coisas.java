package br.liveo.modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Coisas implements Parcelable {

	private int id;
	private String nome;
	
	public Coisas() {
		// TODO Auto-generated constructor stub
	}
	
	public Coisas(Parcel source) {
		// TODO Auto-generated constructor stub
	    String[] data = new String[2];
	    source.readStringArray(data);

	    this.setId(Integer.valueOf(data[0]));	    
        this.setNome(data[1]);		
	}
	
	public static final Parcelable.Creator<Coisas> CREATOR = new Creator<Coisas>() {
		
		@Override
		public Coisas[] newArray(int size) {
			// TODO Auto-generated method stub
			return new Coisas[size];
		}
		
		@Override
		public Coisas createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new Coisas(source);
		}
	};
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeStringArray(new String[] {String.valueOf(getId()),
				this.getNome()});		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
