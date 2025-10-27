package miPrincipal.modelo;

public class Libro {
private String titulo;
    
private String autor;
    private String isbn;

    
    
    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
    }

     public String getTitulo() {
        return titulo;
    }

       public String getAutor() {
        return autor;
    }

    
public String getIsbn() {
        return isbn;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
         ", autor='" + autor + '\'' +
                
         ", isbn='" + isbn + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Libro libro = (Libro) obj;
        return isbn.equals(libro.isbn);
    }
}