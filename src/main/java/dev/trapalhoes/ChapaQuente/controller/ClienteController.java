
package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.config.PasswordConfig;
import dev.trapalhoes.ChapaQuente.model.Cliente;
import dev.trapalhoes.ChapaQuente.service.ClienteService;
import dev.trapalhoes.ChapaQuente.verification.CaptchaVerification;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;
    private final PasswordConfig passwordConfig;
    private final CaptchaVerification captchaVerification;

    public ClienteController(
            ClienteService clienteService,
            PasswordConfig passwordConfig,
            CaptchaVerification captchaVerification) {

        this.clienteService = clienteService;
        this.passwordConfig = passwordConfig;
        this.captchaVerification = captchaVerification;
    }

    public record DadosClientes(
            @NotBlank String nome,
            @NotBlank String senha,
            @NotBlank String email,
            @NotBlank String cpf,
            @NotBlank String token
    ) {}

    public record RespostaCliente(
            Integer id,
            String nome,
            String email,
            Integer pontos
    ) {}

    public record DadosPontos(
            @NotNull @PositiveOrZero Integer pontos
    ) {}

    @GetMapping
    public List<RespostaCliente> getAll() {

        return clienteService.getAll()
                .stream()
                .map(this::paraResposta)
                .toList();
    }

    @PostMapping
    public RespostaCliente create(
            @RequestBody @Valid DadosClientes dadosClientes) {

        if (!captchaVerification.verificar(dadosClientes.token())) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Verificação do CAPTCHA inválida"
            );
        }

        Cliente cliente = new Cliente();

        cliente.setNome(dadosClientes.nome());
        cliente.setSenha(
                passwordConfig.passwordEncoder().encode(dadosClientes.senha())
        );
        cliente.setEmail(dadosClientes.email());
        cliente.setCpf(dadosClientes.cpf());
        cliente.setPontos(0);

        Cliente salvo = clienteService.save(cliente);

        return paraResposta(salvo);
    }

    @PostMapping("/pontos/{id}")
    public RespostaCliente atualizarPontos(
            @PathVariable Integer id,
            @RequestBody @Valid DadosPontos dadosPontos) {

        Cliente atualizado = clienteService.atualizarPontos(
                id,
                dadosPontos.pontos()
        );

        return paraResposta(atualizado);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        clienteService.delete(id);
    }

    private RespostaCliente paraResposta(Cliente cliente) {

        return new RespostaCliente(
                cliente.getId_Cliente(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getPontos()
        );
    }
}
