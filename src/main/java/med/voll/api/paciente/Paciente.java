package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.DadosAtualizacaoMedico;

@Table( name = "pacientes" )
@Entity( name = "Paciente" )
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = "id" )
public class Paciente {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    @Embedded
    private Endereco endereco;
    
    private Boolean ativo;
    
    public Paciente( final DadosCadastroPaciente dados ) {
        this.ativo = true;
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new Endereco( dados.endereco() );
    }
    
    public void atualizar( final DadosAtualizacaoPaciente dados ) {
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
    
    
    public void excluir(){
        this.ativo = false;
    }
}