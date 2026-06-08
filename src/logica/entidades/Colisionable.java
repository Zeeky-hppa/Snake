package logica.entidades;

public interface Colisionable {

    /**
     * Define el comportamiento cuando la serpiente colisiona con esta entidad
     * Cada objeto que implemente esta interfaz decide si hace crecer a la
     * serpiente, si le suma puntos, si la mata, o si le da un poder
     * * @param s La instancia de la Serpiente que colisiono con el objeto.
     */
    void afectar(Serpiente s);

}