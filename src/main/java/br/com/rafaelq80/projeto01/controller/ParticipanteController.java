package br.com.rafaelq80.projeto01.controller;

import static br.com.rafaelq80.projeto01.controller.ResponseUtil.resourceUri;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelq80.projeto01.exception.ResourceNotFoundException;
import br.com.rafaelq80.projeto01.model.Participante;
import br.com.rafaelq80.projeto01.repository.ParticipanteRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ParticipanteController {

    private final ParticipanteRepository participanteRepository;

    @GetMapping("/participantes")
    public Page<Participante> getAllparticipantes(Pageable pageable) {

        return participanteRepository.findAll(pageable);
    }

    @PostMapping("/participantes")
    public ResponseEntity<Participante> saveParticipante(@Validated @RequestBody Participante request) {
        return Optional.of(request).map(participanteRepository::save)
                .map(participante -> ResponseEntity.created(resourceUri(participante.getId())).body(participante))
                .orElseThrow(IllegalArgumentException::new);
    }

    @PutMapping("/participantes/{participanteId}")
    public ResponseEntity<Participante> updateParticipante(@PathVariable @Min(1) final Long participanteId,
            @Validated @RequestBody Participante request) {
        return participanteRepository.findById(participanteId).map(participante -> {
            participante.setNome(request.getNome());
            participante.setEmail(request.getEmail());
            participante.setObservacoes(request.getObservacoes());
            return participante;
        }).map(participanteRepository::save)
                .map(participante -> ResponseEntity.ok().location(resourceUri(participanteId)).body(participante))
                .orElseThrow(() -> new ResourceNotFoundException(participanteId));

    }

    @DeleteMapping("/participantes/{participanteId}")
    public ResponseEntity<?> deleteParticipante(@PathVariable Long participanteId) {
        return participanteRepository.findById(participanteId).map(participante -> {
            participanteRepository.delete(participante);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(participanteId));
    }

    @GetMapping("/participantes/id/{participanteId}")
    public Page<Participante> findByParticipanteId(@PathVariable(value = "participanteId") Long participanteId,
            Pageable pageable) {
        return participanteRepository.findById(participanteId, pageable);
    }

    // Consulta Autores por Nome
    @GetMapping("/participantes/nome/{participanteNome}")
    public Page<Participante> findByParticipanteNome(@PathVariable(value = "participanteNome") String participanteNome,
            Pageable pageable) {
        return participanteRepository.findByNome(participanteNome, pageable);
    }

}
