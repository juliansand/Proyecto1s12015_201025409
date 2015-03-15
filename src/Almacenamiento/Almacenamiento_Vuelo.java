
package Almacenamiento;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.io.*;
import java.util.StringTokenizer;

public class Almacenamiento_Vuelo {

    private DecimalFormat redondeo;
    private DecimalFormatSymbols simbol;

    private String nomb;
    private String apel;
    private String grad;
    private long id;
    private double n1,n2,n3,nf;

    private StringTokenizer strz;
    private String [] dats;

    public Almacenamiento_Vuelo(){

        simbol = new DecimalFormatSymbols();
        simbol.setDecimalSeparator('.');
        redondeo = new DecimalFormat("#.##", simbol);

        nomb = "";
        apel = "";
        grad = "";
        id = 0L;
        n1 = 0D;
        n2 = 0D;
        n3 = 0D;

        dats = new String[8];
    }

    public boolean setInfoEscrit(String nomb, String apel, String grad){

        if(nomb.equals("") || apel.equals("") || grad.equals("")){
            return false;
        }
        else{
            this.nomb = nomb;
            this.apel = apel;
            this.grad = grad;
            return true;
        }
    }

    public boolean setInfoNumer(String id, Object n1, Object n2, Object n3){

        try{
            this.id = Long.parseLong(id);
            this.n1 = Double.parseDouble(redondeo.format(n1));
            this.n2 = Double.parseDouble(redondeo.format(n2));
            this.n3 = Double.parseDouble(redondeo.format(n3));
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }

    public String getDatosAGuardar(){

        String aux = "Nombre(s): " + nomb + "\n" +
                     "Apellido(s): " + apel + "\n" +
                     "ID: " + id + "\n" +
                     "Grado: " + grad + "\n" +
                     "N1: " + n1 + "\n" +
                     "N2: " + n2 + "\n" +
                     "N3: " + n3 + "\n" +
                     "NF: " + this.notaFinal();

        return aux;
    }

    private double notaFinal(){

        double aux = (n1*0.60d) + (n2*0.30d) + (n2*0.10d);
        nf = Double.parseDouble(redondeo.format(aux));
        
        return nf;
    }

    public boolean guardar(){

        File f = new File("Datos.txt");

        try{
            if(!f.exists()){

                f.createNewFile();
            }

            FileWriter fw = new FileWriter(f, true);

            String datos = id + "_" + apel + "_" + nomb + "_" + grad + "_" +
                            n1 + "_" + n2 + "_" + n3 + "_" + nf + "\n";

            fw.write(datos + "");
            fw.close();
            
            return true;

        }
        catch(IOException ex){
            return false;
        }
    }

    public boolean VerificarRepetido(String id){

        boolean est = false;

        try{
            FileReader fr = new FileReader("Datos.txt");
            BufferedReader br = new BufferedReader(fr);

            String s;

            while((s = br.readLine()) != null){

                String aux = s.substring(0,id.length());

                if(aux.equals(id)){
                    est = true;
                    s = null;
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
        return dats[2];
    }

    public String getApellido(){
        return dats[1];
    }

    public String getID(){
        return dats[0];
    }

    public String getGrado(){
        return dats[3];
    }

    public String getN1(){
        return dats[4];
    }

    public String getN2(){
        return dats[5];
    }

    public String getN3(){
        return dats[6];
    }

    public String getNF(){
        return dats[7];
    }

}
