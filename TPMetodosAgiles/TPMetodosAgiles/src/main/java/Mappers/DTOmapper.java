package Mappers;
import models.entities.*;
import dto.*;
public class DTOmapper {
    public LicenciaDTO mapLicenciaToDto(Licencia licencia){
        LicenciaDTO licenciaDTO = new LicenciaDTO();
        licenciaDTO.setTitular(this.mapTitularToDTO(licencia.getTitular()));
        licenciaDTO.setClase(licencia.getClaseLicencia());
        licenciaDTO.setFechaInicioVigencia(licencia.getFechaInicioVigencia());
        licenciaDTO.setFechaFinVigencia(licencia.getFechaFinVigencia());
        licenciaDTO.setNumeroCopia(licencia.getNroCopia());
        licenciaDTO.setObservacion(licencia.getObservacion());
        licenciaDTO.setNumeroLicencia(licencia.getNroLicencia());
        return licenciaDTO;
    }
    public TitularDTO mapTitularToDTO(Titular titular){
        TitularDTO titularDTO = new TitularDTO();
        titularDTO.setApellido(titular.getApellido());
        titularDTO.setNombre(titular.getNombre());
        titularDTO.setNroDoc(titular.getNumeroDocumento());
        titularDTO.setTipoDoc(titular.getTipoDocumento());
        titularDTO.setFechaNacimiento(titular.getFechaNacimiento());
        return titularDTO;
    }
    public Licencia mapDtoToLicencia(LicenciaDTO licenciaDTO){
        Licencia licencia = new Licencia();
        licencia.setTitular(this.mapDtoToTitular(licenciaDTO.getTitularDTO()));
        licencia.setClaseLicencia(licenciaDTO.getClase());
        licencia.setFechaInicioVigencia(licenciaDTO.getFechaInicioVigencia());
        licencia.setFechaFinVigencia(licenciaDTO.getFechaFinVigencia());
        licencia.setNroCopia(licenciaDTO.getNumeroCopia());
        licencia.setObservacion(licenciaDTO.getObservacion());
        licencia.setNroLicencia(licenciaDTO.getNumeroLicencia());
        return licencia;
    }
    public Titular mapDtoToTitular(TitularDTO titularDTO){
        Titular titular = new Titular();
        titular.setApellido(titularDTO.getApellido());
        titular.setNombre(titularDTO.getNombre());
        titular.setNumeroDocumento(titularDTO.getNroDoc());
        titular.setTipoDocumento(titularDTO.getTipoDoc());
        titular.setFechaNacimiento(titularDTO.getFechaNacimiento());
        return titular;
    }
    public UsuarioDTO mapUsuarioToDTO(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNroDocumento(usuario.getNumeroDocumento());
        usuarioDTO.setContrasenia(usuario.getContrasenia());
        usuarioDTO.setRol(usuario.getRol().toString());
        return usuarioDTO;
    }
}
