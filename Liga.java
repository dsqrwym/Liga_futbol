import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Liga {
    public static Scanner teclado = new Scanner(System.in);
    public static Scanner tecladoOracion = new Scanner(System.in);
    public static Random aleatorio = new Random();

    public static void main(String[] args) {
        ArrayList<Object> listaPer = new ArrayList<>();
        byte opcion;
        do {
            menu();
            opcion = leerByte("Dame una opcion", "No es una opcion");
            switch (opcion) {
                case 1: {
                    insertaJugador(listaPer);
                    break;
                }
                case 2: {
                    insertaArbitro(listaPer);
                    break;
                }
                case 3:{
                    mostrarTodo(listaPer);
                    break;
                }
                case 4:{
                    if (listaPer.isEmpty()){
                        System.out.println("No existe ninguna persona");
                    }else {
                        ordenaPerVeloRecursivo(listaPer, 0, 1);
                    }
                    break;
                }
                case 5:{
                    mostrarJugadores(listaPer);
                    break;
                }
                case 6:{
                    if (listaPer.isEmpty()){
                        System.out.println("No existe ninguna persona");
                    }else {
                        ordenaPerVelo(listaPer);
                    }
                    break;
                }
                case 7:{
                    case7IterativoYRecusivo(listaPer);
                    break;
                }
                case 8:{
                    System.out.println(listaPer.get(jugadorMasRegate(listaPer)));
                    break;
                }
                case 9:{
                    if (listaPer.isEmpty()){
                        System.out.println("Suma de todas las velocidad es: 0, \nPorque no existe ninguna persona.");
                    }else {
                        System.out.println("Suma de todas las velocidad es: " + sumaVelocidadesRecursivo(listaPer, 0));
                    }
                    break;
                }
                case 10:{
                    System.out.println("Fin programa");
                    break;
                }
                case 11: {
                    System.out.println(listaPer.get(jugadorMasRegateRecursiva(listaPer, 0, -1, -1)));
                }
                default:
                    System.out.println("No existe esta opcion");
            }
        }while (opcion != 10);
    }

    private static int sumaVelocidadesRecursivo(ArrayList<Object> listaPer, int index) {
        if (index < listaPer.size()){
            if (listaPer.get(index) instanceof Jugador jugador){
                return sumaVelocidadesRecursivo(listaPer, index+1) + jugador.getVelocidad();
            }else {
                Arbitro arbitro = (Arbitro) listaPer.get(index);
                return sumaVelocidadesRecursivo(listaPer, index+1) + arbitro.getVelocidad();
            }
        }else {
            return 0;
        }
    }
    private static int jugadorMasRegate(ArrayList<Object> listaPer) {
        int posi = -1;
        byte mayor = -1;
        for(int i = 0; i< listaPer.size(); i++){
            if (listaPer.get(i) instanceof Jugador jugador){
                if (mayor < jugador.getRegate() ){
                    mayor = jugador.getRegate();
                    posi = i;
                }
            }
        }
        return posi;
    }
    private static int jugadorMasRegateRecursiva(ArrayList<Object> listaPer, int i, int mayor, int posi) {
        if (i < listaPer.size()) {
            if (listaPer.get(i) instanceof Jugador jugador) {
                if (mayor < jugador.getRegate()) {
                    mayor = jugador.getRegate();
                    posi = i;
                }
            }
            return jugadorMasRegateRecursiva(listaPer, i+1, mayor, posi);
        }else {
            return posi;
        }
    }
    private  static void case7IterativoYRecusivo(ArrayList<Object> listaPer){
        if (listaPer.isEmpty()) {
            System.out.println("No existe ninguna persona.");
        }else {
            System.out.println("Iterativo : ");
            int i = buscaPersona(listaPer);
            if (i == -1){
                System.out.println("¡No encontrado!");
            }else {
                System.out.println(listaPer.get(i));
            }
            System.out.println("Recursivo : ");
            System.out.println("Dame su nombre : ");
            String nombre = tecladoOracion.nextLine();
            i = buscaPersonaRecursivo(listaPer, nombre, 0);
            if (i == -1){
                System.out.println("¡No encontrado!");
            }else {
                System.out.println(listaPer.get(i));
            }
        }
    }
    private static int buscaPersonaRecursivo(ArrayList<Object> listaPer ,String nombre, int index) {
        if (index<listaPer.size()){
            if (listaPer.get(index) instanceof Jugador jugador){
                if (jugador.getNombre().equalsIgnoreCase(nombre)){
                    return index;
                }
            }else {
                Arbitro arbitro = (Arbitro) listaPer.get(index);
                if (arbitro.getNombre().equalsIgnoreCase(nombre)){
                    return index;
                }
            }
        }else {
            return -1;
        }
        return buscaPersonaRecursivo(listaPer, nombre,index+1);
    }
    private static void mostrarJugadores(ArrayList<Object> listaPer) {
        boolean hay = false;
        for(Object p : listaPer){
            if (p instanceof Jugador){
                System.out.println("\n" +p +"\n");
                hay = true;
            }
        }
        if (!hay){
            System.out.println("No hay ningun jugador");
        }
    }
    private static int buscaPersona(ArrayList<Object> listaPer) {
        int i = 0;
        System.out.println("Dame su nombre : ");
        String nombre = tecladoOracion.nextLine();
        while (i< listaPer.size()){
            if (listaPer.get(i) instanceof Jugador jugador){
                if (jugador.getNombre().equalsIgnoreCase(nombre)){
                    return i;
                }
            }else {
                Arbitro arbitro = (Arbitro) listaPer.get(i);
                if (arbitro.getNombre().equalsIgnoreCase(nombre)){
                    return i;
                }
            }
            i++;
        }
        return -1;
    }
    private static void ordenaPerVeloRecursivo(ArrayList<Object> vec, int i, int j){
        if (i < vec.size()-1){
            byte velocidad_I;
            byte velocidad_J;
            if (j < vec.size()) {
                if (vec.get(i) instanceof Jugador jugadori) {
                    velocidad_I = jugadori.getVelocidad();
                    if (vec.get(j) instanceof Jugador jugadorj) {
                        velocidad_J = jugadorj.getVelocidad();
                    }else {
                        Arbitro arbitroj = (Arbitro) vec.get(j);
                        velocidad_J = arbitroj.getVelocidad();
                    }
                }else {
                    Arbitro arbitroi = (Arbitro) vec.get(i);
                    velocidad_I = arbitroi.getVelocidad();
                    if (vec.get(j) instanceof Jugador jugadorj){
                        velocidad_J = jugadorj.getVelocidad();
                    }else {
                        Arbitro arbitroj = (Arbitro) vec.get(j);
                        velocidad_J = arbitroj.getVelocidad();
                    }
                }
                if (velocidad_I > velocidad_J){
                    Object aux = vec.get(i);
                    vec.set(i, vec.get(j));
                    vec.set(j, aux);
                }
                ordenaPerVeloRecursivo(vec, i,j+1);
            }
            ordenaPerVeloRecursivo(vec, i+1, i+2);
        }
    }
    private static void ordenaPerVelo(ArrayList<Object> listaPer){
        for (int i = 0; i < listaPer.size()-1; i++) {
            byte velocidad_I;
            byte velocidad_J;
            for (int j = i+1; j < listaPer.size(); j++){
                if (listaPer.get(i) instanceof Jugador jugadori) {
                    velocidad_I = jugadori.getVelocidad();
                    if (listaPer.get(j) instanceof Jugador jugadorj) {
                        velocidad_J = jugadorj.getVelocidad();
                    }else {
                        Arbitro arbitroj = (Arbitro) listaPer.get(j);
                        velocidad_J = arbitroj.getVelocidad();
                    }
                }else {
                    Arbitro arbitroi = (Arbitro) listaPer.get(i);
                    velocidad_I = arbitroi.getVelocidad();
                    if (listaPer.get(j) instanceof Jugador jugadorj){
                        velocidad_J = jugadorj.getVelocidad();
                    }else {
                        Arbitro arbitroj = (Arbitro) listaPer.get(j);
                        velocidad_J = arbitroj.getVelocidad();
                    }
                }
                if (velocidad_I > velocidad_J){
                    Object aux = listaPer.get(i);
                    listaPer.set(i, listaPer.get(j));
                    listaPer.set(j, aux);
                }
            }
        }
        System.out.println("¡HECHO!");
    }
    private static void mostrarTodo(ArrayList<Object> listaPer) {
        if (listaPer.isEmpty()){
            System.out.println("No existe ningun persona.");
        }else {
            for (Object o : listaPer) {
                System.out.println("\n" +o +"\n");
            }
        }
    }
    private static void insertaJugador(ArrayList<Object> listaPer){
        System.out.println("Nombre : ");
        String nombre = tecladoOracion.nextLine();
        System.out.println("Posicion: ");
        String posicion = teclado.next();
        Jugador j = new Jugador(nombre, posicion, comprobarEstado("Esta lesionado? (Solo acepta Si o No)"));
        listaPer.add(j);
    }
    private static void insertaArbitro(ArrayList<Object> listaPer){
        System.out.println("Nombre : ");
        String nombre = tecladoOracion.nextLine();
        System.out.println("Colegio: ");
        String colegio = tecladoOracion.nextLine();
        Arbitro a = new Arbitro(nombre, colegio, comprobarEstado("Esta activo? (Solo acepta Si o No)"));
        listaPer.add(a);
    }
    private static boolean comprobarEstado(String pregunta){
        do {
            System.out.println(pregunta);
            String introducido = teclado.next();
            if (introducido.equalsIgnoreCase("Si") || introducido.equalsIgnoreCase("S")){
                return true;
            }
            if (introducido.equalsIgnoreCase("No") || introducido.equalsIgnoreCase("N")) {
                return false;
            }
        }while (true);
    }
    private static void menu(){
        System.out.println("""
                1) inserta jugador
                2) inserta arbitro
                3) mostrar todo el vector
                4) Ordenar personas por velocidad (recursivo)
                5) Mostrar solo jugadores
                6) Ordenar personas por velocidad (iterativo)
                7) Buscar jugador o arbitro por nombre (iterativo y recursivo)
                8) Jugador con más regate (iterativo)
                9) Suma todas las velocidades (recursivo)
                10) Salir""");
    }
    public static byte leerByte(String pregunta, String error){
        boolean correcto;
        String num;
        byte numero = 0;
        do {
            correcto = true;
            System.out.println(pregunta);
            num = teclado.next();
            try{
                numero = Byte.parseByte(num);
            }catch (Exception e){
                correcto = false;
                System.out.println(error +num);
            }
        }while (!correcto);
        return numero;
    }
}