package com.proyecto.gastrosuite.service;

import com.proyecto.gastrosuite.model.Usuario;
import com.proyecto.gastrosuite.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> obtenerPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> actualizar(Long id, Usuario usuarioDetalles) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setUsername(usuarioDetalles.getUsername());
            if (usuarioDetalles.getPassword() != null && !usuarioDetalles.getPassword().isEmpty()) {
                usuario.setPassword(usuarioDetalles.getPassword());
            }
            usuario.setActivo(usuarioDetalles.isActivo());
            if (usuarioDetalles.getRoles() != null) {
                usuario.setRoles(usuarioDetalles.getRoles());
            }
            if (usuarioDetalles.getRestaurante() != null) {
                usuario.setRestaurante(usuarioDetalles.getRestaurante());
            }
            return usuarioRepository.save(usuario);
        });
    }

    public boolean eliminar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
