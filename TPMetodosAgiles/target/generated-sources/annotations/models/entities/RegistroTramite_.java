package models.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.entities.AccionRealizarEnum;
import models.entities.Licencia;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-06-11T19:14:41")
@StaticMetamodel(RegistroTramite.class)
 class RegistroTramite_ { 

    public static volatile SingularAttribute<RegistroTramite, AccionRealizarEnum> accion;
    public static volatile SingularAttribute<RegistroTramite, Date> fecha;
    public static volatile SingularAttribute<RegistroTramite, Licencia> licencia;
    public static volatile SingularAttribute<RegistroTramite, Integer> id;

}