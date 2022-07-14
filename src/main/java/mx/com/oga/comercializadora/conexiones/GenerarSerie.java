package mx.com.oga.comercializadora.conexiones;

public class GenerarSerie {

    int dato;
    String numeros;

    public String NumeroSerie(int dato) {
        
        this.dato = dato + 1;

        if ((this.dato >= 100000001) && (this.dato <100000000)) {
            numeros = "" + this.dato;
        }
        if ((this.dato >= 1000000) && (this.dato <10000000)) {
            numeros = "0" + this.dato;
        }
        if ((this.dato >= 100000) && (this.dato <1000000)) {
            numeros = "00" + this.dato;
        }
        if ((this.dato >= 10000) && (this.dato <100000)) {
            numeros = "000" + this.dato;
        }
        if ((this.dato >= 1000) && (this.dato <10000)) {
            numeros = "0000" + this.dato;
        }
        if ((this.dato >= 100) && (this.dato <1000)) {
            numeros = "00000" + this.dato;
        }
        if ((this.dato >= 10) && (this.dato <100)) {
            numeros = "000000" + this.dato;
        }
        if (this.dato < 10) {
            numeros = "0000000" + this.dato;
        }
        return numeros;
    }
}
