package models.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.entities.Titular;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-06-14T12:01:38")
@StaticMetamodel(Domicilio.class)
public class Domicilio_ { 

    public static volatile SingularAttribute<Domicilio, Integer> piso;
    public static volatile ListAttribute<Domicilio, Titular> titulares;
    public static volatile SingularAttribute<Domicilio, Integer> numero;
    public static volatile SingularAttribute<Domicilio, String> calle;
    public static volatile SingularAttribute<Domicilio, String> departamento;
    public static volatile SingularAttribute<Domicilio, Integer> id;

}