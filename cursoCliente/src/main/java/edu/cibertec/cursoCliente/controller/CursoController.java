package edu.cibertec.cursoCliente.controller;

import edu.cibertec.cursoCliente.dto.CursoDTO;
import edu.cibertec.cursoCliente.service.CursoService;
import java.sql.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @RequestMapping("cursoMostrar")
    public String curso() {
        return "resultadoCursos";
    }

    @RequestMapping("cursoListar")
    public ModelAndView cursoMostrar() {
        ModelAndView mv = new ModelAndView("curso", "lista",
                cursoService.listarTodos());
        mv.addObject("cursoBean", new CursoDTO());
        return mv;
    }

    @RequestMapping("cursoGrabar")
    public ModelAndView cursoGrabar(@ModelAttribute("cursoBean") CursoDTO curso) {
        ModelAndView mv = new ModelAndView("curso");
        cursoService.insertar(curso);
        mv.addObject("lista", cursoService.listarTodos());
        mv.addObject("cursoBean", new CursoDTO());

        return mv;
    }

    @RequestMapping("cursoEliminar")
    public ModelAndView cursoEliminar(@RequestParam("codigo") int codigo) {
        cursoService.eliminar(codigo);
        return new ModelAndView("redirect:cursoListar");
    }

    @GetMapping("/buscarCursosPorNombre")
    public ModelAndView buscarCursosEnCliente(@RequestParam("nombre") String nombre, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("resultadoCursos");
        String cadena = request.getParameter("cadena");
        mv.addObject("nombre", cursoService.buscarPorNombre(nombre));
        return mv;
    }

}
