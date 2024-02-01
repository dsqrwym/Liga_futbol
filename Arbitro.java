public class Arbitro {
    private final String nombre;
    private final String colegio;
    private final byte velocidad = (byte)Liga.aleatorio.nextInt(101);
    private final byte acierto = (byte)Liga.aleatorio.nextInt(101);
    private final boolean estaActivo;
    private String estadoArbitro(){
        if (estaActivo){
            return "Activo";
        }
        return "Desactivo";
    }
    public Arbitro(String nombre, String colegio, boolean estaActivo) {
        this.nombre = nombre;
        this.colegio = colegio;
        this.estaActivo = estaActivo;
    }
    public String getNombre() {
        return nombre;
    }

    public byte getVelocidad() {
        return velocidad;
    }
    public String toString() {
        return "Arbitro " +nombre +", colegio --> " +colegio +".\n"
                +"Velocidad --> " +velocidad +".\n"
                +"Acierto --> " +acierto +".\n"
                +"Estado --> " +estadoArbitro();
    }
}
