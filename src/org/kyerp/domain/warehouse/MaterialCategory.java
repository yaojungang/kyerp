package org.kyerp.domain.warehouse;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * 材料类别 category
 * 
 * @author y109 2009-11-29下午11:15:51
 */
@Entity
/**该类的标识*/
@DiscriminatorValue("material_category")
/**继承映射策略*/
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class MaterialCategory extends BaseCategory implements Serializable{
	private static final long	serialVersionUID	= -3328204953749595577L;

	/** 是否纸张分类 */
	public MaterialCategory() {
	}

}
