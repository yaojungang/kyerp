package org.kyerp.domain.common;

/**
 * 数据字典类型
 * 
 * @author y109 2009-11-29下午02:02:26
 */
public enum DateDicType {
	PRESS_TYPE {
		@Override
		public String getName() {
			return "印刷方式";
		}
	},
	BINDING_TYPE {
		@Override
		public String getName() {
			return "装订方式";
		}
	},
	PACKAGE_TYPE {
		@Override
		public String getName() {
			return "包装方式";
		}
	};
	public abstract String getName();
}
