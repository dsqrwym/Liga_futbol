public class Jugador {
    private final String nombre;
    private final String posicion;
    private final byte velocidad = (byte)Liga.aleatorio.nextInt(101);
    private final byte regate = (byte)Liga.aleatorio.nextInt(101);
    private final boolean estaLesionado;
    private String estadoJugador(){
        if (estaLesionado){
            return "lesionado";
        }
        return "saludable";
    }
    public byte getVelocidad() {
        return velocidad;
    }
    public byte getRegate() {
        return regate;
    }

    public String getNombre() {
        return nombre;
    }
    public Jugador(String nombre, String posicion, boolean estaLesionado) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.estaLesionado = estaLesionado;
    }

    public String toString() {
        return "Jugador " +nombre +", posicion --> " +posicion +".\n"
                +"Velocidad --> " +velocidad +".\n"
                +"Regate --> " +regate +".\n"
                +"Estado --> " +estadoJugador();
    }
}
