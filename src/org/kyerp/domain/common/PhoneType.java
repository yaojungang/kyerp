/**
 * 
 */
package org.kyerp.domain.common;

/**
 * @author y109 2009-11-29下午02:13:37
 */
/** 电话类型 */
public enum PhoneType {
	MOBILE {
		@Override
		public String getName() {
			return "手机";
		}
	},
	WORK {
		@Override
		public String getName() {
			return "办公电话";
		}
	},
	HOME {
		@Override
		public String getName() {
			return "家庭电话";
		}
	},
	FAX {
		@Override
		public String getName() {
			return "传真";
		}
	},
	OTHER {
		@Override
		public String getName() {
			return "其它";
		}
	};

	public abstract String getName();
}