package models.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.entities.Titular;
import models.entities.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-06-13T18:17:18")
@StaticMetamodel(TipoDocumento.class)
public class TipoDocumento_ { 

    public static volatile ListAttribute<TipoDocumento, Titular> titulares;
    public static volatile SingularAttribute<TipoDocumento, Integer> id;
    public static volatile SingularAttribute<TipoDocumento, String> especificacion;
    public static volatile ListAttribute<TipoDocumento, Usuario> usuarios;

}