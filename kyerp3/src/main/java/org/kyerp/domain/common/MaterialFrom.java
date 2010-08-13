package org.kyerp.domain.common;

/**
 * 材料来源
 * 
 * @author y109 2009-11-29下午04:09:53
 */
public enum MaterialFrom {
	/** 本单位厂料 **/
	MAIN {
		@Override
		public String getName() {
			return "本单位";
		}
	},
	/** 客户自备 **/
	CUSTOMER {
		@Override
		public String getName() {
			return "客户自备";
		}
	};

	public abstract String getName();
}
