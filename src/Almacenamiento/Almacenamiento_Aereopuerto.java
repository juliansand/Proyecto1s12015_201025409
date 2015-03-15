
package Almacenamiento;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.io.*;
import java.util.StringTokenizer;

public class Almacenamiento_Aereopuerto {

    private DecimalFormat redondeo;
    private DecimalFormatSymbols simbol;

    private String nomb;
    private String pais;
    private String pass;
    private long id;
    private double n1,n2,n3,nf;

    private StringTokenizer strz;
    private String [] dats;

    public Almacenamiento_Aereopuerto(){

        nomb = "";
        pais = "";
        pass = "";
        
        dats = new String[3];
    }

    public boolean setInfoEscrit(String nomb, String pais, String pass){

        if(nomb.equals("") || pais.equals("") || pass.equals("")){
            return false;
        }
        else{
            this.nomb = nomb;
            this.pais = pais;
            this.pass = pass;
            return true;
        }
    }

    public String getDatosAGuardar(){

        String aux = "Nombre: " + nomb + "\n" +
                     "Pais: " + pais + "\n" +
                     "Contraseña: " + pass;
                     
        return aux;
    }

    public boolean guardar(){

        File f = new File("Datos.txt");

        try{
            if(!f.exists()){

                f.createNewFile();
            }

            FileWriter fw = new FileWriter(f, true);

            String datos = nomb + "_" + pais + "_" + pass + "\n";

            fw.write(datos + "");
            fw.close();
            
            return true;

        }
        catch(IOException ex){
            return false;
        }
    }

    
    public boolean buscarDato(String id){

        boolean est = false;

        try{
            FileReader fr = new FileReader("Datos.txt");
            BufferedReader br = new BufferedReader(fr);

            String s;

            while((s = br.readLine()) != null){

                String aux = s.substring(0,id.length());

                if(aux.equals(id)){

                    strz = new StringTokenizer(s, "_");
                    
                    int i = 0;
                    while(strz.hasMoreTokens()){

                        dats[i] = (String)strz.nextElement();
                        i++;
                    }

                    est = true;
                    s = null;
                    break;
                }
            }
        }
        catch(FileNotFoundException ex){
            est = false;
        }
        catch(IOException ex){
            est = false;
        }

        return est;
    }

    public String getDatos(){

        String datos = "";

        try{

            FileReader fr = new FileReader("Datos.txt");
            BufferedReader br = new BufferedReader(fr);

            String s;

            while((s = br.readLine()) != null){

                datos += (s + "\n");
            }

            return datos;
        }
        catch(FileNotFoundException ex){
            return "ERROR ARCHIVO NO ENCONTRADO";
        }
        catch(IOException ex){
            return "ERROR AL PROCESAR ARCHIVO ";
        }
    }

    public String getNombre(){
        return dats[0];
    }

    public String getPais(){
        return dats[1];
    }

    public String getContraseña(){
        return dats[2];
    }

}
