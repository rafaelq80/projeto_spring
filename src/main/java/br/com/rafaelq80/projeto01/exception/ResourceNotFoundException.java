package br.com.rafaelq80.projeto01.exception;

public class ResourceNotFoundException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Long id) {
        super("ID não encontrado: " + id);
    }
}
