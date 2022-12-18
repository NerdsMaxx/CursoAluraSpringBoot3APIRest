package med.voll.api.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping( "pacientes" )
public class PacienteController {
    
    @Autowired
    private PacienteRepository repository;
    
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar( @RequestBody @Valid
                                     DadosCadastroPaciente dados, UriComponentsBuilder uriComponentsBuilder ) {
        var paciente = new Paciente( dados );
        repository.save( paciente );
        
        var uri = uriComponentsBuilder.path( "/pacientes/{id}" )
                                      .buildAndExpand( paciente.getId() )
                                      .toUri();
        
        return ResponseEntity.created( uri )
                             .body( new DadosDetalhamentoPaciente( paciente ) );
    }
    
    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> listar(
            @PageableDefault( size = 10, sort = { "nome" }, page = 0 ) Pageable paginacao ) {
        var page = repository.findAllByAtivoTrue( paginacao )
                             .map( DadosListagemPaciente::new );
        
        return ResponseEntity.ok( page );
    }
    
    @PutMapping( "/{id}" )
    @Transactional
    public ResponseEntity atualizar(
            @PathVariable Long id, @RequestBody DadosAtualizacaoPaciente dados ) {
        var paciente = repository.getReferenceById( id );
        paciente.atualizar( dados );
        
        return ResponseEntity.ok( paciente );
    }
    
    @DeleteMapping( "/{id}" )
    @Transactional
    public ResponseEntity excluir( @PathVariable Long id ) {
        var paciente = repository.getReferenceById( id );
        paciente.excluir();
        
        return ResponseEntity.noContent()
                             .build();
    }
    
    @GetMapping( "/{id}" )
    public ResponseEntity detalhar( @PathVariable Long id ) {
        try {
            var paciente = repository.getReferenceById( id );
            
            return ResponseEntity.ok( new DadosDetalhamentoPaciente( paciente ) );
        } catch ( Exception erro ) {
            if ( erro instanceof EntityNotFoundException ) {
                return ResponseEntity.status( HttpStatus.CONFLICT )
                                     .body( "Não foi encontrado o paciente." );
            }
            
            throw erro;
        }
    }
}