package edu.cibertec.cursoCliente.dao.impl;

import edu.cibertec.cursoCliente.dao.CursoDAO;
import edu.cibertec.cursoCliente.dto.CursoDTO;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class CursoDAOImpl implements CursoDAO {

    private RestTemplate restTemplate;

    @Value("${uri.rest.cursos}")
    private String urlServidor;

    public CursoDAOImpl() {
        restTemplate = new RestTemplate();
    }

    @Override
    public List<CursoDTO> listarTodos() {
        List<CursoDTO> rpta = null;
        CursoDTO[] lista = restTemplate.getForObject(urlServidor, CursoDTO[].class);
        rpta = Arrays.asList(lista);
        return rpta;
    }

    @Override
    public CursoDTO obtenerUno(int codigo) {
        CursoDTO rpta = null;
        rpta = restTemplate.getForObject(urlServidor + "/" + codigo, CursoDTO.class);
        return rpta;
    }

    @Override
    public void insertar(CursoDTO ce) {
        restTemplate.postForEntity(urlServidor, ce, CursoDTO.class);
    }

    @Override
    public void modificar(CursoDTO ce) {
        restTemplate.put(urlServidor + "/" + ce.getIdcurso(), ce);
    }

    @Override
    public void eliminar(int codigo) {
        restTemplate.delete(urlServidor + "/" + codigo);

    }

    @Override
    public List<CursoDTO> buscarPorNombre(String nombre) {
        List<CursoDTO> rpta = null;

        // Incluye el parámetro "nombre" en la URL
        CursoDTO[] cursos = restTemplate.getForObject(urlServidor + "/buscarPorNombre?nombre=" + nombre, CursoDTO[].class);

        // Convierte el array de DTOs a una lista (si es necesario)
        rpta = Arrays.asList(cursos);

        return rpta;
    }
}
