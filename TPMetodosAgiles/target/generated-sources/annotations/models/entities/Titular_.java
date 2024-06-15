package models.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.entities.Domicilio;
import models.entities.FactorRHEnum;
import models.entities.GrupoSanguineoEnum;
import models.entities.Licencia;
import models.entities.SexoEnum;
import models.entities.TipoDocumento;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-06-15T13:05:54")
@StaticMetamodel(Titular.class)
public class Titular_ { 

    public static volatile SingularAttribute<Titular, Boolean> donanteDeOrganos;
    public static volatile SingularAttribute<Titular, Date> fechaNacimiento;
    public static volatile SingularAttribute<Titular, String> numerodocumento;
    public static volatile SingularAttribute<Titular, String> nombre;
    public static volatile SingularAttribute<Titular, TipoDocumento> tipoDocumento;
    public static volatile SingularAttribute<Titular, Domicilio> domicilio;
    public static volatile SingularAttribute<Titular, GrupoSanguineoEnum> grupoSanguineo;
    public static volatile SingularAttribute<Titular, String> apellido;
    public static volatile SingularAttribute<Titular, Integer> id;
    public static volatile SingularAttribute<Titular, String> cuil;
    public static volatile SingularAttribute<Titular, SexoEnum> sexo;
    public static volatile ListAttribute<Titular, Licencia> licencias;
    public static volatile SingularAttribute<Titular, FactorRHEnum> factorRH;

}