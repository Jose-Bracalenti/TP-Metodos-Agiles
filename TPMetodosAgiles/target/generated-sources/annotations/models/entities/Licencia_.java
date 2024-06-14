package models.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.entities.ClaseLicencia;
import models.entities.HistoricoLicencia;
import models.entities.RegistroTramite;
import models.entities.Titular;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-06-13T18:17:18")
@StaticMetamodel(Licencia.class)
public class Licencia_ { 

    public static volatile SingularAttribute<Licencia, Integer> nroCopia;
    public static volatile SingularAttribute<Licencia, Date> fechaInicioVigencia;
    public static volatile SingularAttribute<Licencia, Long> nroLicencia;
    public static volatile ListAttribute<Licencia, RegistroTramite> registroTramites;
    public static volatile SingularAttribute<Licencia, ClaseLicencia> claseLicencia;
    public static volatile SingularAttribute<Licencia, Integer> id;
    public static volatile SingularAttribute<Licencia, Date> fechaFinVigencia;
    public static volatile ListAttribute<Licencia, HistoricoLicencia> historicosLicencia;
    public static volatile SingularAttribute<Licencia, String> observacion;
    public static volatile SingularAttribute<Licencia, Titular> titular;

}