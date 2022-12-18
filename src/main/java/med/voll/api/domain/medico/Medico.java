package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.endereco.Endereco;

@Table( name = "medicos" )
@Entity( name = "Medico" )
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = "id" )
public class Medico {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    
    private String nome;
    
    private String email;
    
    private String telefone;
    
    private String crm;
    
    private Boolean ativo;
    
    @Enumerated( EnumType.STRING )
    private Especialidade especialidade;
    
    @Embedded
    private Endereco endereco;
    
    public Medico( final DadosCadastroMedico dados ) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco( dados.endereco() );
    }
    
    public void atualizar( final DadosAtualizacaoMedico dados ) {
        String nome = dados.nome();
        if ( nome != null ) {
            this.nome = nome;
        }
        
        String telefone = dados.telefone();
        if ( telefone != null ) {
            this.telefone = telefone;
        }
        
        DadosEndereco endereco = dados.endereco();
        if ( endereco != null ) {
            this.endereco.atualizar( endereco );
        }
    }
    
    public void excluir() {
        this.ativo = false;
    }
}