package org.kyerp.domain.warehouse;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 * 库存：印版
 * 
 * @author y109 2009-11-29下午11:56:07
 */
@Entity
@DiscriminatorValue("plate")
public class PlateOfMaterial extends Material implements Serializable {
	private static final long	serialVersionUID	= -2596458191489771405L;

}
