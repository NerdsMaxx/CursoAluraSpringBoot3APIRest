package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    
    private String logradouro;
    
    private String bairro;
    
    private String cep;
    
    private String numero;
    
    private String complemento;
    
    private String cidade;
    
    private String uf;
    
    public Endereco( DadosEndereco endereco ) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
    }
    
    public void atualizar( final DadosEndereco dados ) {
        String logradouro = dados.logradouro();
        if ( logradouro != null ) {
            this.logradouro = logradouro;
        }
        
        String bairro = dados.bairro();
        if ( bairro != null ) {
            this.bairro = bairro;
        }
        
        String cep = dados.cep();
        if ( cep != null ) {
            this.cep = cep;
        }
        
        String numero = dados.numero();
        if ( numero != null ) {
            this.numero = numero;
        }
        
        String complemento = dados.complemento();
        if ( complemento != null ) {
            this.complemento = complemento;
        }
        
        String cidade = dados.cidade();
        if ( cidade != null ) {
            this.cidade = cidade;
        }
        
        String uf = dados.uf();
        if ( uf != null ) {
            this.uf = uf;
        }
    }
}