package org.kyerp.domain.warehouse;

public enum InventoryOwnerType {
	CLIENT {
		@Override
		public String getName() {
			return "客户";
		}
	},
	OUR_UNIT {
		@Override
		public String getName() {
			return "本单位";
		}
	};
	public abstract String getName();
}
