package models.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.entities.Licencia;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-06-13T18:17:18")
@StaticMetamodel(ClaseLicencia.class)
public class ClaseLicencia_ { 

    public static volatile SingularAttribute<ClaseLicencia, Integer> id;
    public static volatile SingularAttribute<ClaseLicencia, String> especificacion;
    public static volatile ListAttribute<ClaseLicencia, Licencia> licencias;

}