package com.centralerrosapi.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Log.class)
public abstract class Log_ {

	public static volatile SingularAttribute<Log, Date> createdAt;
	public static volatile SingularAttribute<Log, System> system;
	public static volatile SingularAttribute<Log, Level> level;
	public static volatile SingularAttribute<Log, Long> id;
	public static volatile SingularAttribute<Log, String> detail;
	public static volatile SingularAttribute<Log, String> title;
	public static volatile SingularAttribute<Log, Category> category;

}

