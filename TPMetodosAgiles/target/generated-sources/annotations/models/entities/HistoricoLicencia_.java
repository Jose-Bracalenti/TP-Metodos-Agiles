package models.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.entities.Licencia;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-06-15T13:05:54")
@StaticMetamodel(HistoricoLicencia.class)
public class HistoricoLicencia_ { 

    public static volatile SingularAttribute<HistoricoLicencia, Licencia> licencia;
    public static volatile SingularAttribute<HistoricoLicencia, Date> fechaInicioVigencia;
    public static volatile SingularAttribute<HistoricoLicencia, Long> nroLicencia;
    public static volatile SingularAttribute<HistoricoLicencia, Integer> id;
    public static volatile SingularAttribute<HistoricoLicencia, Date> fechaFinVigencia;

}