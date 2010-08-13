package org.kyerp.domain.security;

/**
 * 系统资源类型
 * 
 * @author y109 2009-12-29下午03:04:08
 */
public enum SystemResourceType {
	ROLE {
		@Override
		public String getName() {
			return "角色";
		}
	},
	URL {
		@Override
		public String getName() {
			return "URL";
		}
	},
	MENU {
		@Override
		public String getName() {
			return "菜单";
		}
	};
	public abstract String getName();
}
