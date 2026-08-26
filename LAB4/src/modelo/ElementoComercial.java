package modelo;

public abstract class ElementoComercial<K> implements Identificable<K> {
    protected String codigo;
    protected String nombre;
    protected double precioBase;
    protected K categoria;

    public ElementoComercial(String codigo, String nombre, K categoria, double precioBase) {
        this.codigo=codigo;
        this.nombre=nombre;
        this.categoria=categoria;
        this.precioBase=precioBase;
    }

    @Override
    public String getCodigo() {
        return codigo;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    @Override
    public K getCategoria() {
        return categoria;
    }
}
