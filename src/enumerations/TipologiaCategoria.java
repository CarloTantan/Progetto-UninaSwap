package enumerations;

public enum TipologiaCategoria {
    LIBRI(1, "Libri"),
    CANCELLERIA(2, "Cancelleria"),
    VESTITI(3, "Vestiti"),
    ELETTRONICA(4, "Elettronica"),
    MUSICA(5, "Musica"),
    GIOCHI(6, "Giochi"),
    SPORT(7, "Sport"),
    CASA(8, "Casa"),
    ALTRO(9, "Altro");
    
    private final int id;
    private final String nome;
    
    TipologiaCategoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    @Override
    public String toString() {
        return nome;
    }
    
    public static TipologiaCategoria fromNome(String nome) {
        for (TipologiaCategoria categoria : values()) {
            if (categoria.getNome().equals(nome)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria non valida: " + nome);
    }
    
    public static TipologiaCategoria fromId(int id) {
        for (TipologiaCategoria categoria : values()) {
            if (categoria.getId() == id) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("ID categoria non valido: " + id);
    }
}