package com.example.loterias.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Sorteo implements Parcelable {
    public static final String Key = "sorteo";
    private final int ID;
    private String fecha;
    private ArrayList<Integer> combinacion;
    private final int idFoto;

    public Sorteo(int id, String fecha, ArrayList<Integer> combinacion, final int idFoto) {
        this.ID = id;
        this.fecha = fecha;
        this.combinacion = combinacion;
        this.idFoto = idFoto;
    }

    protected Sorteo(Parcel in) {
        ID = in.readInt();
        fecha = in.readString();
        idFoto = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(fecha);
        dest.writeInt(idFoto);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Sorteo> CREATOR = new Creator<Sorteo>() {
        @Override
        public Sorteo createFromParcel(Parcel in) {
            return new Sorteo(in);
        }

        @Override
        public Sorteo[] newArray(int size) {
            return new Sorteo[size];
        }
    };

    public int getId() {
        return ID;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Integer> getCombinacion() {
        return combinacion;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public static class orderByDate implements Comparator<Sorteo> {
        private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        private Date date1, date2;
        @Override
        public int compare(Sorteo o1, Sorteo o2) {
            try {
                date1 = simpleDateFormat.parse(o1.getFecha());
                date2 = simpleDateFormat.parse(o2.getFecha());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int result = date1.compareTo(date2);

            if(result == 0)
                return o1.getId() - o2.getId();
            return result;
        }
    }
}
