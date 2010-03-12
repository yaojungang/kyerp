package org.kyerp.domain.warehouse;

/**
 * 单据状态
 * 
 * @author y109 2010-3-1上午08:28:00
 */
public enum BillStatus {
	WRITING {
		@Override
		public String getName() {
			return "编制";
		}
	},
	WAITING_FOR_CHECK {
		@Override
		public String getName() {
			return "等待审核";
		}
	},
	CHECKING {
		@Override
		public String getName() {
			return "未审核";
		}
	},
	CHECKED {
		@Override
		public String getName() {
			return "已审核";
		}
	};
	public abstract String getName();
}
