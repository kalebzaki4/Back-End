package br.com.estudos.adopet.api.service;

import br.com.estudos.adopet.api.dto.AtualizacaoTutorDto;
import br.com.estudos.adopet.api.dto.CadastroTutorDto;
import br.com.estudos.adopet.api.exception.ValidacaoException;
import br.com.estudos.adopet.api.model.Tutor;
import br.com.estudos.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public void cadastrar(CadastroTutorDto dto) {
        boolean jaCadastrado = repository.existsByTelefoneOrEmail(dto.telefone(), dto.email());

        if (jaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        }

        repository.save(new Tutor(dto));
    }

    public void atualizar(AtualizacaoTutorDto dto) {
        Tutor tutor = repository.getReferenceById(dto.id());
        tutor.atualizarDados(dto);
    }

}
